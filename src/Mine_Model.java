import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Mine_Model implements Observer{
	public Box box;
	int flags;
	int Box_Grid[][];
	String ColorSet;
	Bomb bomb;
	Map<ColorSet, Bomb> map;
	Boolean isWin() {
		return null;}
	void revealBomb() {}
	void Flag() {}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
