import javafx.scene.control.Button;

public abstract class Box extends Button {
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
