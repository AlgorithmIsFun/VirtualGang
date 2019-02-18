import javafx.beans.InvalidationListener;

public class Bomb extends Box {
	Boolean boom = false;

	@Override
	public void getx(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gety(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRevealed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ColorSet getColorSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reveal() {
		// TODO Auto-generated method stub
		
	}
	
	public Boolean explode() {
		return boom;
		
	}
	
	@Override
	public void flag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unflag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

}
