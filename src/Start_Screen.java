import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class Start_Screen {

	private Mine_Model model;
	private int totalBombs = 0;
	private int totalColors = 0;
	private int width = 0;
	private int length = 0;

	
	public Start_Screen(Stage stage) {
		this.model = new Mine_Model();
		initUI(stage);
	}

	private void initUI(Stage stage) {
		VBox root = new VBox();
		root.setMinSize(550, 500);
		
		Text  label = new Text("Color Sweeper");
		label.setId("fancytext");
		label.setTranslateX(20);
		
		Button Start = new Button("Start Game");
		
		
		root.getChildren().addAll(label,Start);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StartScreen.css");
		stage.setScene(scene);
		stage.setTitle("Testing");
		stage.show();
		
	}
	
}
