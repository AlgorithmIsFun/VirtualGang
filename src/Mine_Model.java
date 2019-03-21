import java.util.Random;
import javafx.scene.input.MouseEvent;
import java.util.Observable;
/**
 * 
 * @author Abdullah Alexei
 *
 * A Mine Model to store and manipulate all game data
 *
 */

public class Mine_Model extends Observable{

	public Box box;
	private Smiley smiley;
	public boolean enable;
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
		this.enable = true;
		createGrid(width, height);
		assignBombs(totalbombs);
		assignNumbers();
		this.flags = totalbombs;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void reveal(int x, int y) {
		//reveals the box at x,y coordinates
		if (!(Box_Grid[x][y].isRevealed())) {
			if (Box_Grid[x][y].flagged) {
				this.unflag(x, y);
			}
			Box_Grid[x][y].reveal();
			isWin();
		}
	}
	
	public void unflag(int x, int y) {
		//reveals the box at x,y coordinates
		if (!(Box_Grid[x][y].isRevealed()) && enable == true) {
				Box_Grid[x][y].unflag();
				this.flags = this.flags + 1;
				this.setChanged();
				this.notifyObservers();
		}
	}
	
	public void flag(int x, int y) {
		//flags the box at x,y coordinates
		if (!Box_Grid[x][y].isRevealed() && enable == true) {
			if (flags > 0) {
				System.out.println("Hello!");
				if (this.currColorset != null) {
					if (Box_Grid[x][y] instanceof Bomb && !this.currColorset.equals(Box_Grid[x][y].getColorSet())) {
						revealAllBombs();
						return;
					}
				}else {
					if (Box_Grid[x][y] instanceof Bomb) {
						this.currColorset = Box_Grid[x][y].getColorSet();
					}
				}
				Box_Grid[x][y].flag();
				this.flags = flags - 1;
				if (this.currColorset != null && this.currColorset.isFilled()) {
					this.currColorset = null;
				}
				this.setChanged();
				this.notifyObservers();
			}
		}
	}
	public ColorSet[] getAllColorSet() {
		return this.allColorsSets;
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
			this.allColorsSets[i % this.totalColors].addBomb((Bomb)Box_Grid[x][y]);
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
		if (enable == true) {
			enable = false;
			this.smiley.updateImage("Win");
			this.setChanged();
			this.notifyObservers("Winner!");
			return true;
		}
		return false;
	}
	
	
	public void revealAllBombs() {
		this.smiley.updateImage("Game_Over");
		enable = false;
		for(int i = 0; i < this.All_Bombs.length; i++) {
			reveal(this.All_Bombs[i].getx(), this.All_Bombs[i].gety());
		}
		this.setChanged();
		this.notifyObservers("Game Over!");
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
		isWin();
	}
	
	
	public void assignNumbers() {
		for (int i = 0; i < Box_Grid.length; i++) {
			for (int j = 0; j < Box_Grid[0].length; j++) {
				if (!(Box_Grid[i][j] instanceof Bomb) ) {
					//System.out.println(i);
					//System.out.println(j);
					//System.out.println("Total:" + countAdjacentBombs(i,j));
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
	
	public Smiley getSmiley() {
		return this.smiley;
	}
	
	public void setCurrentColorSet(int x, int y) {
		this.currColorset = this.Box_Grid[x][y].getColorSet();
	}
	
	public ColorSet getCurrentColorSet() {
		return this.currColorset;
	}
	
	public int getFlagCount() {
		return this.flags;
	}
	
	public ColorSet[] getAllColorSets() {
		return this.allColorsSets;
	}
	
}
