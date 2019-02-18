
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
	private Text flag_count_text;
	
	private Rectangle bomb_color_box;
	
	public Header_Panel(Smiley smiley){
		
		this.smiley = smiley;
		
		this.setPadding(new Insets(10));
		
		flag_count_box = new Rectangle(50, 50);
		flag_count_text = new Text("0");
		flag_count_stack.getChildren().addAll(flag_count_box, flag_count_text);
		
		bomb_color_box = new Rectangle(50, 50);
		
		this.getChildren().addAll(this.flag_count_stack, this.smiley, this.bomb_color_box);
		
	}
	
}
