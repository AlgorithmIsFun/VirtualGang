import javafx.scene.control.Button;

public abstract class Box extends Button {
	int x = 0;
	int y = 0;
	//boolean revealed = false;
	boolean flagSet = false;
	ColorSet colorset;
	int getx() {return this.x;};
	int gety() {return this.y;};
	boolean isRevealed() {return !(this.isDisabled());} 
	ColorSet getColorSet() {return this.colorset;}
	abstract void reveal();
	abstract void flag();
	abstract void unflag();
	abstract void add_bombs();
}
