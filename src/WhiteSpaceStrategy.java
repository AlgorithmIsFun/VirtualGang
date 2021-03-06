import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Abdullah
 *
 *	The Strategy for the Whitespace Box. Handles Mouse Button events.  
 *
 */

public class WhiteSpaceStrategy extends BoxStrategy {
	
	WhiteSpaceStrategy(Mine_Model model){
		super(model);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.model.getSmiley().updateImage("Default");
		if(e.getButton() == MouseButton.SECONDARY) {
        	Box White_Space = (Box) e.getSource();
        	if (White_Space.flagged) {
    			this.model.unflag(White_Space.getx(), White_Space.gety());
    		}
        	else {
        		this.model.flag(White_Space.getx(), White_Space.gety());
        	}
        }
		else if(e.getButton() == MouseButton.PRIMARY) {
			Box White_Space = (Box) e.getSource();
			this.model.revealRecursively(White_Space.getx(), White_Space.gety());
        }
	}
	
}
