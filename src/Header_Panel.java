import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;

/**
 * 
 * @author Alexei
 *
 * A header panel to store the flag count box, the smiley button and the bomb color box.
 *
 * Parent class: HBox
 * 
 */
public class Header_Panel extends HBox {
	
	public Smiley smiley;
	
	private StackPane flag_count_stack;
	private Rectangle flag_count_box;
	private Label flag_count_text;
	private Mine_Model model;
	private Button bomb_color_box;
	private HintScreen hint;
	
	public Header_Panel(Smiley smiley, Mine_Model model){
		
		this.smiley = smiley;
		this.model = model;
		
		// Create the Flag Count Box to show the number of flags left to use
		flag_count_box = new Rectangle(60, 60);
		flag_count_box.setFill(Color.BLACK);
		flag_count_text = new Label(String.valueOf(model.getFlagCount()));
		flag_count_text.setTextFill(Color.RED);
		flag_count_stack = new StackPane();
		flag_count_stack.getChildren().addAll(flag_count_box, flag_count_text);
		
		// Displays the current Color Set
		bomb_color_box = new Button();
		bomb_color_box.setMaxSize(60, 60);
		bomb_color_box.setMinSize(60, 60);
		bomb_color_box.setStyle("-fx-background-color: #000000;");
		bomb_color_box.setOnAction((event) -> {
			//Custom Mode
			if (this.model.enable == true) {
				this.hint = new HintScreen(this.model.getAllColorSet());
			}
		});
		
		this.setSpacing((this.model.getBox_Grid().length * 40 + 20 - 60 * 3)/2);
		this.getChildren().addAll(this.flag_count_stack, this.smiley, this.bomb_color_box);
		
	}
	public void removeHint() {
		if (this.hint != null) {
			this.hint.closeScreen();
		}
	}


	public void updatePanel() {
		// TODO Auto-generated method stub
		this.flag_count_text.setText(String.valueOf(model.getFlagCount()));
		if (model.getCurrentColorSet() != null) {
		this.bomb_color_box.setStyle("-fx-background-color: "+model.getCurrentColorSet().getHex()+"; ");
		}
	}
	
}
