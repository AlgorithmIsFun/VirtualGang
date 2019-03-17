import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Alexei
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
		if(e.getButton() == MouseButton.PRIMARY) {
			this.model.revealAllBombs();
          }
		if(e.getButton() == MouseButton.SECONDARY) {
        	Box bomb = (Box) e.getSource();
      		this.model.flag(bomb.getx(), bomb.gety());
          }
	}
	
}
