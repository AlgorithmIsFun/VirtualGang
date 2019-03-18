import java.util.ArrayList;

public class Observable {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	// When notify observers is called, each observer is updated
	public void notifyObservers() {
		for (Observer o : this.observers) {
			o.update();
		}
	}
}
