import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Abdullah
 *
 */

public class WhiteSpaceStrategy extends BoxStrategy {
	
	WhiteSpaceStrategy(Mine_Model model){
		super(model);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseButton.PRIMARY) {
			Box White_Space = (Box) e.getSource();
			this.model.revealRecursively(White_Space.getx(), White_Space.gety());
        }
		else if(e.getButton() == MouseButton.SECONDARY) {
        	Box White_Space = (Box) e.getSource();
        	if (White_Space.flagged) {
    			White_Space.unflag();
    		}
        	else {
        		this.model.flag(White_Space.getx(), White_Space.gety());
        	}
        }
	}
	
}
