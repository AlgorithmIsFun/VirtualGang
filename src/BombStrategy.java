import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Abdullah Alexei
 *
 * A Bomb Strategy to call revealAllBombs or to flag the Bomb.
 *
 */
public class BombStrategy extends BoxStrategy {
	
	BombStrategy(Mine_Model model){
		super(model);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.model.getSmiley().updateImage("Default");
		if(e.getButton() == MouseButton.PRIMARY) {
			this.model.revealAllBombs();
        }
		else if(e.getButton() == MouseButton.SECONDARY) {
        	Box bomb = (Box) e.getSource();
        	if (bomb.flagged) {
        		this.model.unflag(bomb.getx(), bomb.gety());
    		}
        	else {
        		this.model.flag(bomb.getx(), bomb.gety());
        		this.model.setCurrentColorSet(bomb.getx(), bomb.gety());
        	}
          }
	}
	
}
