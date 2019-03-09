
public class WhiteSpaceStrategy {
	Mine_Model model;
	public WhiteSpaceStrategy(Mine_Model mod) {
		// TODO Auto-generated constructor stub
		this.model = mod;
	}
	//Reveal all Whitespace
	public void reveal_Whitespace(int x, int y) {
		//Checks if coordinates are within Box_Grid Bounds
		if (0 <= x && x < this.model.Box_Grid.length) {
			if (0 <= y && y < this.model.Box_Grid.length) {
				//Checks if Box_Grid[x][y] is a Whitespace then reveal the Box
				if (this.model.Box_Grid[x][y] instanceof Whitespace) {
					this.model.reveal(x, y);
					//Recursively call Whitespace on all nearby Whitespace
					for(int i = -1; i < 2; i++) {
						if (i == 0) {
							reveal_Whitespace(x, y-1);
							reveal_Whitespace(x, y+1);
						}
						else {
							reveal_Whitespace(x+i, y);
						}
					}
				}
				
			}
		}
	}
}
