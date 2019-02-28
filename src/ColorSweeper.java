
import javafx.application.Application;
import javafx.stage.Stage;

public class ColorSweeper extends Application{

	Start_Screen view;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	/**
	 * The main entry point for this application. This method is called after the init method has returned, 
	 * and after the system is ready for the application to begin running.
	 * @param stage - the primary stage for this application, onto which the application scene is set.
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// View + Controller
		this.view = new Start_Screen();
	}

}
