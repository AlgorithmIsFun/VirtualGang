/**
 * 
 * @author Alexei Abdullah
 *
 * A an abstract class to be inherited by 3 types of strategies.
 *
 */
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class BoxStrategy implements EventHandler<MouseEvent>{
	
	Mine_Model model;
	public BoxStrategy(Mine_Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}
	
	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		if (this.model.enable) {
			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				this.mouseClicked(event);
			}
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				this.mousePressed(event);
			}
			if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				this.mouseReleased(event);
			}
		}
	}
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseButton.PRIMARY || e.getButton() == MouseButton.SECONDARY) {
			this.model.getSmiley().updateImage("Surprised");
        }
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseButton.PRIMARY || e.getButton() == MouseButton.SECONDARY) {
			this.model.getSmiley().updateImage("Default");
        }
	}
	
	public abstract void mouseClicked(MouseEvent e);
}