import java.awt.TextField;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * @author Asmah Al-arryan
 * End screen
 */
public class End_Screen {
	Mine_Model mineModel;
	Start_Screen view;
	public End_Screen(String result, Stage game) {
		Stage stage = new Stage();
		initUI(stage,result,game);
	}
	
	private void initUI(Stage stage, String result, Stage game) {
		VBox root = new VBox();
		root.setMinSize(550, 500);
		root.setPadding(new Insets(50,0,0,45));
		root.setId("background");
		root.setSpacing(35);
		
		Text Title = new Text("Color Sweeper \n  "+result);
		Title.setId("Title");
		Title.setTranslateX(20);
		
	
		Button Btn = MakeButton("Play Again");
		Btn.setOnAction((event) -> {
		new Start_Screen();
		game.close();
		stage.close();
		});
		
		Button Btn2 = MakeButton("Exit");
		Btn2.setOnAction((event) -> {
		stage.close();
		game.close();
		});
		
		
		root.getChildren().addAll(Title,Btn, Btn2);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StartScreen.css");
		stage.setScene(scene);
		stage.setTitle("ColorSweeper");
		stage.setResizable(false);
		stage.show();
	}

	public Button MakeButton(String name) {
		Button currButton = new Button(name);
		currButton.setMinSize(200, 15);
		currButton.setTranslateX(120);
		return currButton;
	}
	
}
