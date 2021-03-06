import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Abdullah Alexei
 *
 * The Strategy for Number Box.
 *
 */
public class NumberStrategy extends BoxStrategy {
	
	NumberStrategy(Mine_Model model){
		super(model);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.model.getSmiley().updateImage("Default");
		if(e.getButton() == MouseButton.PRIMARY) {
			Box number = (Box) e.getSource();
			this.model.reveal(number.getx(), number.gety());
        }
		else if(e.getButton() == MouseButton.SECONDARY) {
        	Box number = (Box) e.getSource();
        	if (number.flagged) {
    			this.model.unflag(number.getx(), number.gety());
    		}
        	else {
        		this.model.flag(number.getx(), number.gety());
        	}
        }
	}

}
