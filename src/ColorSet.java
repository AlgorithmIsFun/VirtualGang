import java.util.ArrayList;

public class ColorSet {
	
	/**
	 * 
	 * @author Ali Ibrahim
	 *Responsible for storing the information for different colors needed
	 */

	private int hex[] =new int[3];
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	
	public ColorSet (int r, int g, int b) {
		this.hex[0] = r;
		this.hex[1] = g;
		this.hex[2] = b;
	}
	public boolean equals(Object o) {
	    // self check
	    if (this == o)
	        return true;
	    // null check
	    if (o == null)
	        return false;
	    // type check and cast
	    if (getClass() != o.getClass())
	        return false;
	    ColorSet color2 = (ColorSet) o;
	    // field comparison
	    return color2.getHex().equals(this.getHex());
	}
	
	public int getR() { return this.hex[0];}
	public int getG() { return this.hex[1];}
	public int getB() { return this.hex[2];}
	public String getHex() {
		return String.format("#%02x%02x%02x",
			    (int)this.hex[0],
			   (int)this.hex[1],
			    (int)this.hex[2]);
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
