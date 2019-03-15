import java.util.ArrayList;

public class ColorSet {

	private int hex[] =new int[3];
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	
	public ColorSet (int r, int g, int b) {
		this.hex[0] = r;
		this.hex[1] = g;
		this.hex[1] = b;
	}
	
	public int[] getHex() {
		return hex ;
	}
	
	public void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}
	
	public void removeBomb(Bomb bomb) {
		bombs.remove(bomb);
	}
	
	public ArrayList<Bomb> getBombs() {
		return bombs;
	}
	
	public boolean isFilled() {
		for (Bomb b:this.bombs) {
			if (!b.isRevealed()) {
				return false;
			}
		}
		return true;
	}
	
}
