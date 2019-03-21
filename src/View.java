import javafx.application.Application;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author aliibrahim
 *
 */
public class View extends Application implements Observer {
	private Mine_Model model;
	private Grid_Panel grid;
	private Header_Panel header;
	private Smiley smiley;
	private Stage stage;
	
	@Override
    public void start(Stage arg0) throws Exception {
		return;
	}
	
	/**
	 * Constructs View class and creates stage
	 * @param model
	 */
	public View(Mine_Model model) {
		this.model = model;
		this.stage = new Stage();
		initUI(stage);
	}
	
	/**
	 * Creates a BorderPane and adds the Panels in their respective locations
	 * @param stage
	 */
	private void initUI(Stage stage) {
		
		this.smiley = this.model.getSmiley();
		this.header = new Header_Panel(this.smiley, this.model);
		this.model.addObserver(this);
		this.grid = new Grid_Panel(this.model.getBox_Grid());
		
		BorderPane window = new BorderPane();
		window.setTop(this.header);
		window.setCenter(this.grid);
        window.setStyle("-fx-padding: 10;");
        window.setStyle("-fx-border-style: solid inside;");
        window.setStyle("-fx-border-width: 2;");
        window.setStyle("-fx-border-insets: 5;");
        window.setStyle("-fx-border-radius: 5;");
        window.setStyle("-fx-border-color: blue;");
        
        
        //creates a scene and adds the BorderPane to it
        Scene scene = new Scene(window);
        
        stage.setScene(scene);
        stage.setTitle("Color Sweeper");
        stage.show();
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (model.enable) {
			this.header.updatePanel();
		}else if (model.enable == false) {
			this.header.removeHint();
			new End_Screen((String)arg,stage);
		}
		
	}

}
