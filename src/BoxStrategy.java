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
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseButton.PRIMARY) {
			this.model.smiley.updateImage("Surprised");
        }
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseButton.PRIMARY) {
			this.model.smiley.updateImage("Default");
        }
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseButton.PRIMARY) {
			this.model.smiley.updateImage("Default");
			Box box = (Box) e.getSource();
			this.model.reveal(box.getx(), box.gety());
          }
		if(e.getButton() == MouseButton.SECONDARY) {
        	Box box = (Box) e.getSource();
      		this.model.flag(box.getx(), box.gety());
          }
	}
}