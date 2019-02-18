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
	
	public Mine_Model() {
		//initializes all variables and map
		box = new Bomb();
		flags = 0;
		ColorSet = new ColorSet();
		bomb = new Bomb();
		rand = new Random();
		map = new HashMap<ColorSet, Bomb>();
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
		int x = rand.nextInt(Box_Grid.length);;
		int y = rand.nextInt(Box_Grid[0].length);;
		for(int i = 0; i < quantity; i++) {
			while (!(Box_Grid[x][y] instanceof Whitespace)) {
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
				if (Box_Grid[i][j] instanceof Bomb && ((Bomb) Box_Grid[i][j]).explode()) {
					return false;
				}
			}
		}
		return true;
}
	public void reveal(int x, int y) {
		//reveals the box at x,y coordinates
		if (Box_Grid[x][y] instanceof Bomb) {
			Box_Grid[x][y].isRevealed();
		}
	}
	public void Flag(int x, int y) {
		//flags the box at x,y coordinates
		if (Box_Grid[x][y].isRevealed()) {
			Box_Grid[x][y].flag();;
		}
	}
	
}
