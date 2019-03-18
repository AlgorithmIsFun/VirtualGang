import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Abdullah Alexei
 *
 * A Mine Model to store and manipulate all game data
 *
 */

public class Mine_Model{

	public Box box;
	public Smiley smiley;
	private int flags;
	private Box Box_Grid[][];
	private ColorSet currColorset;
	private Box All_Bombs[];
	private Random rand;
	private ColorSet allColorsSets[];
	private int totalColors;
	
	//private Map<ColorSet, Bomb> map;
	
	public Mine_Model() {
		//initializes all variables and map
		flags = 0;
		currColorset = null;
		rand = new Random();
	}
	
	public void createAllBoxes(int width, int height, int totalbombs, int totalcolors){
		this.Box_Grid = new Box[width][height];
		this.All_Bombs = new Box[totalbombs];
		this.allColorsSets = generateColorsets(totalcolors);
		this.totalColors = totalcolors;
		this.smiley = new Smiley();
		createGrid(width, height);
		assignBombs(totalbombs);
		assignNumbers();
		this.flags = totalbombs;
	}
	
	public void reveal(int x, int y) {
		//reveals the box at x,y coordinates
		if (!(Box_Grid[x][y].isRevealed())) {
			if (Box_Grid[x][y].flagged) {
				Box_Grid[x][y].unflag();
				this.flags = this.flags + 1;
			}
				Box_Grid[x][y].reveal();
		}
	}
	
	public void unflag(int x, int y) {
		//reveals the box at x,y coordinates
		if (!(Box_Grid[x][y].isRevealed())) {
				Box_Grid[x][y].unflag();
				this.flags = this.flags + 1;
		}
	}
	
	public void flag(int x, int y) {
		//flags the box at x,y coordinates
		if (!Box_Grid[x][y].isRevealed()) {
			if (flags > 0) {
				Box_Grid[x][y].flag();
				this.flags = flags - 1;
			}
		}
	}
	
	public Box[][] getBox_Grid() {
		return this.Box_Grid;
	}
	
	public void createGrid(int x, int y) {
		//auto-generates a grid
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Box_Grid[i][j] = new Whitespace(i,j);
				Box_Grid[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, BoxStrategyFactory.create("WhiteSpace", this));
				Box_Grid[i][j].addEventHandler(MouseEvent.MOUSE_PRESSED, BoxStrategyFactory.create("WhiteSpace", this));
				Box_Grid[i][j].addEventHandler(MouseEvent.MOUSE_RELEASED, BoxStrategyFactory.create("WhiteSpace", this));
			}
		}
	}
	private ColorSet[] generateColorsets(int colors) {
		ColorSet[] colorList = new ColorSet[colors];
		int randomR = rand.nextInt(255);
		int randomG = rand.nextInt(255);
		int randomB = rand.nextInt(255);
		int interval = 255 / (colors + 1);
		for (int i =0; i < colors; i ++) {
			colorList[i] = new ColorSet((randomR + (i+1)*interval) % 256,(randomG + (i+1)*interval)%256,(randomB + (i+1)*interval) % 256);
		}
		
		return colorList;
		
	}
	public void assignBombs(int quantity) {
		//assigns a set-amount of bombs to the board in random locations
		int x = rand.nextInt(Box_Grid.length);
		int y = rand.nextInt(Box_Grid[0].length);
		for(int i = 0; i < quantity; i++) {
			while (Box_Grid[x][y] instanceof Bomb) {
				x = rand.nextInt(Box_Grid.length);
				y = rand.nextInt(Box_Grid[0].length);
			}
			Box_Grid[x][y] = new Bomb(x,y,this.allColorsSets[i % this.totalColors]);
			Box_Grid[x][y].addEventHandler(MouseEvent.MOUSE_CLICKED, BoxStrategyFactory.create("Bomb", this));
			Box_Grid[x][y].addEventHandler(MouseEvent.MOUSE_PRESSED, BoxStrategyFactory.create("Bomb", this));
			Box_Grid[x][y].addEventHandler(MouseEvent.MOUSE_RELEASED, BoxStrategyFactory.create("Bomb", this));
			this.All_Bombs[i] = Box_Grid[x][y];
		}
	}
	
	public Boolean isWin() {
		//checks if all whitespace is clicked on and no bombs have exploded
		for (int i = 0; i < Box_Grid.length; i++) {
			for (int j = 0; j < Box_Grid[0].length; j++) {
				if (Box_Grid[i][j] instanceof Bomb && Box_Grid[i][j].isRevealed()) {

					return false;
				}
				else if (!(Box_Grid[i][j] instanceof Bomb) && !(Box_Grid[i][j].isRevealed())) {
		
					return false;
				}
			}
		}
		this.smiley.updateImage("Win");
		return true;
		
	}
	
	
	public void revealAllBombs() {
		int i;
		this.smiley.updateImage("Game_Over");
		for(i = 0; i < this.All_Bombs.length; i++) {
			reveal(this.All_Bombs[i].getx(), this.All_Bombs[i].gety());
		}
	}
	
	public void revealRecursively(int x, int y) {
		if (x >= 0 && x < this.Box_Grid.length) {
			if (y >= 0 && y < this.Box_Grid[0].length) {
				//Checks if Box_Grid[x][y] is a Whitespace then reveal the Box
				if (this.Box_Grid[x][y] instanceof Whitespace && !(this.Box_Grid[x][y].isRevealed())) {
					this.reveal(x, y);
					//Recursively call Whitespace on all nearby Whitespace
					for(int i = x-1; i <= x + 1; i++) {
						for(int j = y-1; j <= y + 1; j++) {
							if(!(i == x && j == y)) {
								revealRecursively(i,j);
							}
						}
					}
				}
				else if (this.Box_Grid[x][y] instanceof Number && !(this.Box_Grid[x][y].isRevealed())) {
					reveal(x,y);
				}
			}
		}		
	}
	
	
	public void assignNumbers() {
		for (int i = 0; i < Box_Grid.length; i++) {
			for (int j = 0; j < Box_Grid[0].length; j++) {
				if (!(Box_Grid[i][j] instanceof Bomb) ) {
					System.out.println(i);
					System.out.println(j);
					System.out.println("Total:" + countAdjacentBombs(i,j));
					if (countAdjacentBombs(i,j) > 0) {
						Box_Grid[i][j] = new Number(countAdjacentBombs(i,j), i, j,getAverageColor(i,j));
						Box_Grid[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, BoxStrategyFactory.create("Number", this));
						Box_Grid[i][j].addEventHandler(MouseEvent.MOUSE_PRESSED, BoxStrategyFactory.create("Number", this));
						Box_Grid[i][j].addEventHandler(MouseEvent.MOUSE_RELEASED, BoxStrategyFactory.create("Number", this));
					}
				}
			}
		}
	}
	
	public ColorSet getAverageColor(int x, int y) {
		int averageR = 0;
		int averageG = 0;
		int averageB = 0;
		int totalBombs = 0;
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				if (!(i == x && j == y) && i<this.Box_Grid.length && i >= 0 && j>=0 && 
						j < this.Box_Grid[0].length && this.Box_Grid[i][j] instanceof Bomb) {
					averageR = averageR + this.Box_Grid[i][j].getColorSet().getR();
					averageG = averageG + this.Box_Grid[i][j].getColorSet().getG();
					averageB = averageB + this.Box_Grid[i][j].getColorSet().getB();
					totalBombs++;
				}
			}
		}
		
		
		
		return new ColorSet(averageR / totalBombs,averageG / totalBombs,averageB / totalBombs);
	}
	
	public int countAdjacentBombs(int x, int y) {
		int bomb_count = 0;
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				if (!(i == x && j == y) && i<this.Box_Grid.length && i >= 0 && j>=0 && 
						j < this.Box_Grid[0].length && this.Box_Grid[i][j] instanceof Bomb) {
					bomb_count++;
				}
			}
		}
		return bomb_count;
	}
	

}
