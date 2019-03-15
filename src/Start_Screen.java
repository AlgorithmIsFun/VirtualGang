import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
/*
 * Starter Screen built by Ritvik Bhardwaj
 */
public class Start_Screen {
	
	//Initializes the GUI Elements
	public Start_Screen() {
		Stage stage = new Stage();
		initUI(stage);
	}
	
	/*
	 * Creates and displays the GUI elements
	 * @param: stage: The stage the elements will appear on
	 */
	void initUI(Stage stage) {
		//Create the background
		VBox root = new VBox();
		root.setMinSize(550, 500);
		root.setPadding(new Insets(50,0,0,45));
		root.setId("background");
		root.setSpacing(35);
		
		//Create the Title
		Text  Title = new Text("Color Sweeper");
		Title.setId("Title");
		Title.setTranslateX(20);
		
		//Create the Buttons
		Button EasyButton = MakeButton("Easy");
		EasyButton.setOnAction((event) -> {
			//Easy Mode 10 mines 8x8
			setConfigurations(8,8,10,3);
			stage.close();
		});
		Button MediumButton = MakeButton("Medium")
;		MediumButton.setOnAction((event) -> {
			//Medium Mode 40 mines 16 x 16
				setConfigurations(16,16,40,4);
				stage.close();
		});
		
		Button HardButton = MakeButton("Hard");
		HardButton.setOnAction((event) -> {
			//Hard Mode 99 Mines 16x30
			setConfigurations(16,30,99,10);
			stage.close();
		});
		
		Button CustomButton = MakeButton("Custom");
		CustomButton.setOnAction((event) -> {
			//Custom Mode
			CustomScreen screen = new CustomScreen();
			stage.close();
		});
		
		
		//Adding the Buttons to the GUI and Showing the GUI
		root.getChildren().addAll(Title,EasyButton,MediumButton,HardButton,CustomButton);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StartScreen.css");
		stage.setScene(scene);
		stage.setTitle("ColorSweeper");
		stage.setResizable(false);
		stage.show();
		
	}
	
	/*
	 * Creates a Button with predefined specifications
	 * @param name: Name of the Button
	 */
	public Button MakeButton(String name) {
		Button currButton = new Button(name);
		currButton.setMinSize(200, 15);
		currButton.setTranslateX(120);
		return currButton;
	}
	
	/*
	 * Set the configurations of the game
	 * @param x: width of the board size
	 * @param y: height of the board size
	 * @param Bomb: total number of bombs
	 * @param Colors: total number of color sets
	 */
	
	public void setConfigurations(int x, int y, int Bomb, int Colors) {
		Mine_Model model = new Mine_Model();
		model.createAll(x,y,Bomb,Colors);
		View view = new View(model);
	}
	
}
