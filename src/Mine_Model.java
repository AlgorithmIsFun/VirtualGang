import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Mine_Model implements Observer{
	public Box box;
	int flags;
	private Box Box_Grid[][];
	private ColorSet ColorSet;
	private Bomb bomb;
	private Random rand;
	private Map<ColorSet, Bomb> map;
	private WhiteSpaceStrategy whitespace_strategy;
	
	public Mine_Model() {
		//initializes all variables and map
		box = new Bomb();
		flags = 0;
		ColorSet = new ColorSet();
		bomb = new Bomb();
		rand = new Random();
		map = new HashMap<ColorSet, Bomb>();
		this.whitespace_strategy = new WhiteSpaceStrategy(this);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// updates the Box_Grid for mouse events
		
	}
	public void createGrid(int x, int y) {
		//auto-generates a grid
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Box_Grid[i][j] = new Whitespace();
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
			Box_Grid[x][y] = new Bomb();
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
			if (Box_Grid[x][y] instanceof Whitespace) {
				this.whitespace_strategy.reveal_Whitespace(x, y);
			}else {
				Box_Grid[x][y].reveal();
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
						Box_Grid[i][j] = new Number(countAdjacentBombs(i,j));
					}
				}
			}
		}
	}
	
	public int countAdjacentBombs(int x, int y) {
		int bomb_count = 0;
		int x_upper_bound = x+1;
		int x_lower_bound = x-1;
		int y_upper_bound = y+1;
		int y_lower_bound = y-1;
		if (x == 0) {
			x_lower_bound = 0;
		}
		if (x == Box_Grid.length) {
			x_upper_bound = Box_Grid.length;
		}
		if (y == 0) {
			y_lower_bound = 0;
		}
		if (y == Box_Grid[0].length) {
			y_upper_bound = Box_Grid[0].length;
		}
		else {
			for (int i = x_lower_bound; i <= x_upper_bound; i++) {
				for (int j = y_lower_bound; j <= y_upper_bound; j++) {
					if (Box_Grid[i][j] instanceof Bomb && i != x && j != y) {
						bomb_count++;
					}
				}
			}
		}
		return bomb_count;
	}
	
	public Box[][] getBox_Grid() {
		return Box_Grid;
	}
	
	public void createAllBoxes(int width, int height, int totalbombs, int totalcolors) {
		createGrid(width, height);
		assignBombs(totalbombs);
		assignNumbers();
	}
}
