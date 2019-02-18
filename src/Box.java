import javafx.beans.Observable;
import javafx.scene.canvas.GraphicsContext;

public abstract class Box implements Observable {
	int x = 0;
	int y = 0;
	boolean reveals = false;
	boolean flagSet = false;
	ColorSet colorset = new ColorSet();
	void getx(int x) {
	}
	void gety(int y) {
	}
	boolean isRevealed() {
		return false;
	}
	ColorSet getColorSet() {
		return null;
	}
	void reveal() {
	}
	void flag() {
	}
	void unflag() {
	}	
}
