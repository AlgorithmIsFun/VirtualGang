import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SquareStrategy implements BoxStrategy{
	
	Mine_Model model;
	public SquareStrategy(Mine_Model mod) {
		// TODO Auto-generated constructor stub
		this.model = mod;
	}
	
	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			this.mouseClicked(event);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseButton.PRIMARY) {
			Box box = (Box) e.getSource();
			this.model.reveal(box.getx(), box.gety());
          }
		if(e.getButton() == MouseButton.SECONDARY) {
        	Box box = (Box) e.getSource();
      		this.model.Flag(box.getx(), box.gety());
          }
	}
}