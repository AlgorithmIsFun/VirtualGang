import javafx.scene.control.Button;

public abstract class Box extends Button {
	protected int x = 0;
	protected int y = 0;
	protected int buttonLength = 40;
	protected boolean flagged = false;
	Box(){
		super();
	}
	protected boolean flagSet = false;
	protected ColorSet colorset;
	public int getx() {return this.x;};
	public int gety() {return this.y;};
	public boolean isRevealed() {return (this.isDisabled());} 
	public ColorSet getColorSet() {return this.colorset;}
	public abstract void reveal();
	public abstract void flag();
	public abstract void unflag();
}
