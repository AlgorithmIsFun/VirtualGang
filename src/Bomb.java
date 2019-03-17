import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bomb extends Box {

	private Image image;
	private ImageView image_view;
	
	public Bomb (int x, int y){
		this.x = x;
		this.y = y;
		setMinWidth(buttonLength);
		setMaxWidth(buttonLength);
		setMinHeight(buttonLength);
		setMaxHeight(buttonLength);
	}
	
	@Override
	public void reveal() {
		// TODO Auto-generated method stub
		if (this.flagged) {
			this.unflag();
		}
		image = new Image("Images/Bomb.png");
		image_view = new ImageView(image);
		image_view.setFitHeight(buttonLength);
		image_view.setFitWidth(buttonLength);
		this.setGraphic(image_view);
		this.setDisable(true);
	}

	@Override
	public void flag() {
		// TODO Auto-generated method stub
		image = new Image("Images/Flag.png");
		image_view = new ImageView(image);
		image_view.setFitHeight(buttonLength);
		image_view.setFitWidth(buttonLength);
		this.setGraphic(image_view);
		this.flagged = true;
	}

	@Override
	public void unflag() {
		// TODO Auto-generated method stub
		image_view.setImage(null);
		this.flagged = false;
	}

}