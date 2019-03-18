import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Number extends Box {

	private Image image;
	private ImageView image_view;
	private int num;
	
	public Number (int num, int x, int y, ColorSet colors) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
		setMinWidth(buttonLength);
		setMaxWidth(buttonLength);
		setMinHeight(buttonLength);
		setMaxHeight(buttonLength);
	}
	
	@Override
	public void reveal() {
		// TODO Auto-generated method stub
		this.setDisable(true);
		this.setText(String.valueOf(this.num));
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
