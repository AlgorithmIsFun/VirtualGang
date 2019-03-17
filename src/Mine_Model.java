import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.scene.input.MouseEvent;

public class Mine_Model{
	public Box box;
	private int flags;
	private Box Box_Grid[][];
	//private ColorSet ColorSet;
	private Box All_Bombs[];
	private Random rand;
	//private Map<ColorSet, Bomb> map;
	
	public Mine_Model() {
		//initializes all variables and map
		//box = new Bomb();
		//flags = 0;
		//ColorSet = new ColorSet();
		/*bomb = new Bomb();
		map = new HashMap<ColorSet, Bomb>();
		this.whitespace_strategy = new WhiteSpaceStrategy(this);*/
		rand = new Random();
	}
	
	public void createGrid(int x, int y) {
		//auto-generates a grid
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Box_Grid[i][j] = new Whitespace(i,j);
				Box_Grid[i][j].addEventHandler(MouseEvent.ANY, BoxStrategyFactory.create("WhiteSpace", this));
			}
		}
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
			Box_Grid[x][y] = new Bomb(x,y);
			Box_Grid[x][y].addEventHandler(MouseEvent.ANY, BoxStrategyFactory.create("Bomb", this));
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
		return true;
	}
	
	public void reveal(int x, int y) {
		//reveals the box at x,y coordinates
		if (!Box_Grid[x][y].isRevealed()) {
				Box_Grid[x][y].reveal();
		}
	}
	
	public void revealAllBombs() {
		int i;
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
	
	public void flag(int x, int y) {
		//flags the box at x,y coordinates
		if (!Box_Grid[x][y].isRevealed()) {
			Box_Grid[x][y].flag();
		}
	}
	
	public void assignNumbers() {
		for (int i = 0; i < Box_Grid.length; i++) {
			for (int j = 0; j < Box_Grid[0].length; j++) {
				if (!(Box_Grid[i][j] instanceof Bomb)) {
					if (countAdjacentBombs(i,j) > 0) {
						Box_Grid[i][j] = new Number(countAdjacentBombs(i,j), i, j);
						Box_Grid[i][j].addEventHandler(MouseEvent.ANY, BoxStrategyFactory.create("Number", this));
					}
				}
			}
		}
	}
	
	public int countAdjacentBombs(int x, int y) {
		int bomb_count = 0;
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				if (!(i == x && j == y) && i<Box_Grid.length && i >= 0 && j>=0 && j < Box_Grid[0].length 
						&& Box_Grid[i][j] instanceof Bomb) {
					bomb_count++;
				}
			}
		}
		return bomb_count;
	}
	
	public Box[][] getBox_Grid() {
		return Box_Grid;
	}
	
	public void createAllBoxes(int width, int height, int totalbombs, int totalcolors) {
		this.Box_Grid = new Box[width][height];
		this.All_Bombs = new Box[totalbombs];
		createGrid(width, height);
		assignBombs(totalbombs);
		assignNumbers();
	}
}
