
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
	
	private View view;
	
	private Button[][] btn = new Button [40][40];
	public Grid_Panel(View view) {
		this.view = view;
	GridPane gridPane = new GridPane();
	gridPane.setPadding(new Insets(10,10,10,10));
	for(int i = 0; i < 40; i++) {
		for(int j= 0; j<9; j++) {
			btn[i][j] = new Button();
			this.getChildren().addAll(this.btn);
		}
	
	}
	
	}
	
}