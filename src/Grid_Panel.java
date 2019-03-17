
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Asmah Al-arryan
 *
 * A Grid panel to store 2D array of buttons.
 * 
 */
public class Grid_Panel extends GridPane{

	Box BoxGrid[][];
	
	public Grid_Panel(Box[][] BoxGrid) {
		this.setPadding(new Insets(10));
		this.BoxGrid = BoxGrid;
		// x,y = col, row
		for (int i = 0; i < this.BoxGrid.length; i++) {
			for (int j = 0; j < this.BoxGrid[0].length; j++) {
				this.add((Button)BoxGrid[i][j], i, j);
			}
		}
	}
	
	
}