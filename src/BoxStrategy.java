import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface BoxStrategy extends EventHandler<MouseEvent>{
	
	public void handle(MouseEvent event);
	public void mouseClicked(MouseEvent e);


}
