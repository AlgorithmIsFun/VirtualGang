import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * @author Alexei
 * 
 * A hint screen showing all the bomb colors (Color sets). 
 *
 */
public class HintScreen {
	
	ColorSet allColors[];
	
	public HintScreen(ColorSet[] colors) {
		 this.allColors = colors;
		 Stage stage = new Stage();
		 initUI(stage);
	}
	
	public void initUI(Stage stage) {
		VBox root = new VBox();
		root.setMinWidth(340);
		Label label = new Label("Color Sets with Bomb Count");
		root.getChildren().add(label);
		Rectangle colorBox;
		StackPane colorBoxStack;
		Label bombCount;
		for(int i = 0; i < allColors.length; i++) {
			colorBox = new Rectangle(60,60);
			colorBox.setFill(Color.rgb(allColors[i].getR(), allColors[i].getG(), allColors[i].getB()));
			colorBoxStack = new StackPane();
			bombCount = new Label(String.valueOf(allColors[i].getBombs().size()));
			colorBoxStack.getChildren().addAll(colorBox, bombCount);
			root.getChildren().add(colorBoxStack);
		}
		ScrollPane colorScroller = new ScrollPane();
		colorScroller.setContent(root);
		colorScroller.setMinWidth(340);
		Scene scene = new Scene(colorScroller);
		stage.setScene(scene);
		stage.setTitle("Color Hint Screen");
		stage.show();
	}

}
