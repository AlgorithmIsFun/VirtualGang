import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 

/**
 * @author ali ibrahim
 *
 */
public class View extends Application {
	
	private Mine_Model model;
	private Grid_Panel grid;
	private Header_Panel header;
	private Smiley smiley;
	
	
	@Override
    public void start(Stage stage) {
		
	}
	
	
	/**
	 * Contstructs the view and initializes stage
	 * @param model
	 */
	public View(Mine_Model model) {
		this.model = model;
		Stage stage = new Stage();
		initUI(stage);
	}
	
	/**
	 * Creates the window with a header and grid
	 * @param stage
	 */
	private void initUI(Stage stage) {
		this.header = new Header_Panel(smiley);
		this.grid = new Grid_Panel(this.model.Box_Grid);
		
		// Initializes BorderPane and sets properties
		BorderPane window = new BorderPane();
		window.setTop(this.header);
		window.setCenter(this.grid);
		window.setStyle("-fx-padding: 10;");
        window.setStyle("-fx-border-style: solid inside;");
        window.setStyle("-fx-border-width: 5;");
        window.setStyle("-fx-border-insets: 5;");
        window.setStyle("-fx-border-radius: 5;");
        window.setStyle("-fx-border-color: black;");
        
       
        Scene scene = new Scene(window);
        
        stage.setScene(scene);
        stage.setTitle("Color Sweeper");
	}

	
}
