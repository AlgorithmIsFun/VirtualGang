import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HintScreen {

	ColorSet[] allColorSets;
	Stage currStage;
	public HintScreen(ColorSet[] colors) {
		this.currStage = new Stage();
		this.allColorSets = colors;
		initUI(this.currStage);
	}
	
	void initUI(Stage stage) {
		//Create the background
		
		
		//Create the Title
		Text Title = new Text("Cheat Sheet");
		Title.setTranslateX(20);
		Title.setFill(Color.WHITE);
		
		GridPane grid = new GridPane();
		grid.setId("background");
		grid.setVgap(30);
		grid.setMaxSize(200, 200);
		
	     grid.add(Title,0,0);
	     for (int i = 0; i < this.allColorSets.length; i++) {
	    	 HBox Root = new HBox();
	    	 Root.setMinSize(200,50);
	    	 Root.setMaxSize(200, 50);
	    	 Root.setSpacing(50);
	    	 Root.setTranslateX(5);
	 
	    	 
	    	 Rectangle Color = new Rectangle(90,50);
	    	 Color.setStyle("-fx-fill:" + this.allColorSets[i].getHex()+";");
	    	 
	    	 
	    	 Label amount = new Label(String.valueOf(this.allColorSets[i].getBombs().size()));
	    	 amount.setMinSize(100, 50);
	    	 amount.setStyle("-fx-text-fill: #FFFFFF;");
	    	 
	    	 Root.getChildren().addAll(Color,amount);
	    	 grid.add(Root,0,i+1);
	     }
	     
	     ScrollPane scrollPane = new ScrollPane(grid);
	     scrollPane.setId("background");
	     scrollPane.setMinSize(200, 200);
	     scrollPane.setMaxSize(200, 200);
	     
		//Adding the Buttons to the GUI and Showing the GUI
		Scene scene = new Scene(scrollPane);
		scene.getStylesheets().add("StartScreen.css");
		stage.setScene(scene);
		stage.setTitle("ColorSweeper");
		stage.setResizable(false);
		stage.show();
		
	}
	public void closeScreen() {
		this.currStage.close();
	}

}
