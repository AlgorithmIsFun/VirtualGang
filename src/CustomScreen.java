import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomScreen{
	
	private int Bombs;
	private int x;
	private int y;
	private int Colors;
	public CustomScreen(){
		// TODO Auto-generated constructor stub
		Stage stage = new Stage();
		initUI(stage);
	}

	private void initUI(Stage stage) {
		VBox root = new VBox();
		root.setMinSize(550, 500);
		root.setPadding(new Insets(50,0,0,45));
		root.setId("background");
		root.setSpacing(60);
		
		
		HBox BombBox = new HBox();
		Text BombText = new Text();
		BombText.setId("Configurations");
		BombText.setText("Number of Total Bombs:");
		BombText.setTranslateX(45);
		TextField BombInput = new TextField();
		BombInput.setMinSize(100, 30);
		BombInput.setMaxSize(100, 30);
		BombInput.setTranslateX(80);
		BombInput.setText("0");
		
		HBox SizeBox = new HBox();
		Text SizeText = new Text();
		SizeText.setId("Configurations");
		SizeText.setText("Size of the board (x,y):");
		SizeText.setTranslateX(40);
		TextField SizeInput = new TextField();
		SizeInput.setMinSize(100, 30);
		SizeInput.setMaxSize(100, 30);
		SizeInput.setTranslateX(87);
		SizeInput.setText("(1,1)");
		
		
		HBox ColorBox = new HBox();
		Text ColorText = new Text();
		ColorText.setId("Configurations");
		ColorText.setText("Number of Color Sets:");
		ColorText.setTranslateX(40);
		TextField ColorInput = new TextField();
		ColorInput.setMinSize(100, 30);
		ColorInput.setMaxSize(100, 30);
		ColorInput.setTranslateX(90);
		ColorInput.setText("1");
		
		Button Play = new Button("Play Game");
		Play.setTranslateX(125);
		Play.setOnAction((event) -> {
			//Custom Mode
			if (isValidGame(BombInput,SizeInput,ColorInput)) {
				Mine_Model model = new Mine_Model();
				model.createAllBoxes(x,y,Bombs,Colors);
				View view = new View(model);
				stage.close();
			}
		});
		
		
		BombBox.getChildren().addAll(BombText,BombInput);
		SizeBox.getChildren().addAll(SizeText,SizeInput);
		ColorBox.getChildren().addAll(ColorText,ColorInput);
		Scene scene = new Scene(root);
		root.getChildren().addAll(BombBox,SizeBox,ColorBox,Play);
		scene.getStylesheets().add("StartScreen.css");
		stage.setScene(scene);
		stage.setTitle("ColorSweeper");
		stage.setResizable(false);
		stage.show();
		
		
	}
	public boolean isValidGame(TextField Bombs, TextField Size, TextField Color) {
		//Return True if these inputs match a valid Minesweeper game. Otherwise create a dialogue box and return False
		String TotalBombs = Bombs.getText();
		String TotalSize = Size.getText();
		String TotalColor = Color.getText();
		
		if (TotalBombs.length() == 0 || TotalSize.length() == 0 || TotalColor.length() == 0) {
			createDialogue("Please fill in all Configurations.");
			return false;
		}
		try {
			this.Bombs = Integer.parseInt(TotalBombs);
		}catch (NumberFormatException e) {
			this.Bombs = 0;
			createDialogue("Bombs must be a number");
			return false;
		}
		
		if (this.Bombs < 0) {
			createDialogue("Bombs cannot be a negative number.");
			return false;
		}
		
		if (TotalSize.length() < 5) {
			createDialogue("The size must be in the format: (x,y)");
			return false;
		}
		
		String[] Sizes = TotalSize.split(",");
		if (Sizes.length != 2) {
			createDialogue("The size must be in the format: (x,y)");
			return false;
		}
		if (Sizes[0].charAt(0) != '(' || Sizes[1].charAt(Sizes[1].length()-1) != ')') {
			createDialogue("The size must be in the format: (x,y)");
			return false;
		}
		try {
			this.x = Integer.parseInt(Sizes[0].substring(1));
			
		}catch (NumberFormatException e) {
			this.x = 1;
			createDialogue("Width must be a number");
			return false;
		}
		
		if (this.x < 0) {
			createDialogue("Width cannot be a negative number.");
			return false;
		}
		
		try {
			this.y = Integer.parseInt(Sizes[1].substring(0,Sizes[1].length()-1));
			
		}catch (NumberFormatException e) {
			this.y = 1;
			createDialogue("Height must be a number");
			return false;
		}
		
		if (this.y < 0) {
			createDialogue("Height cannot be a negative number.");
			return false;
		}
		
		try {
			this.Colors = Integer.parseInt(TotalColor);
		}catch (NumberFormatException e) {
			this.Colors = 0;
			createDialogue("Color sets must be a number");
			return false;
		}
		
		if (this.Colors < 0) {
			createDialogue("Color sets cannot be a negative number.");
			return false;
		}
		
		if (this.Bombs > this.x * this.y) {
			createDialogue("Too many Bombs for this board size");
			return false;
		}
		
		if (this.Bombs < this.Colors) {
			createDialogue("Too little Bombs for this many Color Sets");
			return false;
		}
		
		return true;
		
	}
	
	public void createDialogue(String say) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Invalid Configurations");
		alert.setHeaderText("Information Alert");
		String s =say;
		alert.setContentText(s);
		alert.show();
	}
	
}
