/**
 * 
 * @author Abdullah
 *
 * A an abstract class to be inherited by 3 types of strategies.
 *
 */
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class BoxStrategy implements EventHandler<MouseEvent>{
	
	Mine_Model model;
	public BoxStrategy(Mine_Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}
	
	@Override
	public void handle(MouseEvent event) {
		System.out.println(event.getEventType());
		System.out.println(this.getClass());
		// TODO Auto-generated method stub
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED || event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			this.mouseClicked(event);
		}
	}
	
	public abstract void mouseClicked(MouseEvent e);
}