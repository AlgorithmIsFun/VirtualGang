import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Mine_Model implements Observer{
	public Box box;
	int flags;
	Box Box_Grid[][];
	ColorSet ColorSet;
	Bomb bomb;
	Random rand;
	Map<ColorSet, Bomb> map;
	WhiteSpaceStrategy whitespace_strategy;
	
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
	public void create_grid(int x, int y) {
		//auto-generates a grid
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Box_Grid[i][j] = new Whitespace();
			}
		}
	}
	public void assign_bombs(int quantity) {
		//assigns a set-amount of bombs to the board in random locations
		int x = rand.nextInt(Box_Grid.length);
		int y = rand.nextInt(Box_Grid[0].length);
		for(int i = 0; i < quantity; i++) {
			while (!(Box_Grid[x][y] instanceof Whitespace)) {
				x = rand.nextInt(Box_Grid.length);
				y = rand.nextInt(Box_Grid[0].length);
			}
			Box_Grid[x][y] = new Bomb();
			assign_numbers_to_Whitespace(x, y);
		}
	}
	
	public void assign_numbers_to_Whitespace(int x, int y) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i != 0 && j != 0) {
					if (Box_Grid[x][y] instanceof Whitespace) {
						Box_Grid[x][y].add_bombs();
					}
				}
			}
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
	public void Flag(int x, int y) {
		//flags the box at x,y coordinates
		if (!Box_Grid[x][y].isRevealed()) {
			Box_Grid[x][y].flag();
		}
	}
}
