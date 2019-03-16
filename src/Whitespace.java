import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Whitespace extends Box {
	
	Image image;
	ImageView image_view;
	int num_bombs;
	
	public Whitespace () {
		this.num_bombs = 0;
		setMinWidth(buttonLength);
		setMaxWidth(buttonLength);
		setMinHeight(buttonLength);
		setMaxHeight(buttonLength);
	}
	
	@Override
	public void reveal() {
		// TODO Auto-generated method stub
		this.setDisabled(true);
	}

	public void add_bombs() {
		this.num_bombs += 1;
	}
	
	@Override
	public void flag() {
		// TODO Auto-generated method stub
		image = new Image("Images/Flag.png");
		image_view = new ImageView(image);
		this.setGraphic(image_view);
	}

	@Override
	public void unflag() {
		// TODO Auto-generated method stub
		image_view.setImage(null);
	}

}