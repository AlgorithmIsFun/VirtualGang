import javax.swing.JButton;

import javafx.beans.Observable;
import javafx.scene.canvas.GraphicsContext;

public abstract class Box extends JButton {
	int x = 0;
	int y = 0;
	boolean reveals = false;
	boolean flagSet = false;
	ColorSet colorset;
	int getx(int x) {return this.x;};
	int gety(int y) {return this.y;};
	boolean isRevealed() {return this.reveals;}; 
	ColorSet getColorSet() {return this.colorset;};
	abstract void reveal();
	abstract void flag();
	abstract void unflag();	
}
