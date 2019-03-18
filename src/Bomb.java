import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Ritvik Bhardwaj
 * Class responsible for storing the attributes a Bomb has.
 */
public class Bomb extends Box {

	private Image image;
	private ImageView image_view;
	
	public Bomb (int x, int y, ColorSet colors){
		super();
		this.x = x;
		this.y = y;
		setMinWidth(buttonLength);
		setMaxWidth(buttonLength);
		setMinHeight(buttonLength);
		setMaxHeight(buttonLength);
		this.colorset = colors;
	}
	
	@Override
	public void reveal() {
		// TODO Auto-generated method stub
		image = new Image("Images/Bomb.png");
		image_view = new ImageView(image);
		image_view.setFitHeight(buttonLength);
		image_view.setFitWidth(buttonLength);
		this.setGraphic(image_view);
		this.setStyle("-fx-background-color: " + this.colorset.getHex() + "; "+ "-fx-opacity: 1;");
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