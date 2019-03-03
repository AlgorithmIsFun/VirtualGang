import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Whitespace extends Box {
	
	Image image;
	ImageView image_view;
	
	public Whitespace () {
	}
	
	@Override
	public void reveal() {
		// TODO Auto-generated method stub
		this.setDisabled(true);
	}

	@Override
	public void flag() {
		// TODO Auto-generated method stub
		image = new Image("Images/Flag.png");
		image_view = new ImageView(image);
		this.setGraphic(image_view);
		setMinWidth(image.getWidth());
		setMaxWidth(image.getWidth());
		setMinHeight(image.getHeight());
		setMaxHeight(image.getHeight());
	}

	@Override
	public void unflag() {
		// TODO Auto-generated method stub
		image_view.setImage(null);
	}

}