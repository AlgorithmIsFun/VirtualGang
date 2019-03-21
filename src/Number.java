import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 
 * @author Alexei
 * 
 * A Number Box which is a subclass of Box. The Number Box displays the number of Adjacent Bombs.
 *
 */

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
		this.colorset = colors;
	}
	
	@Override
	public void reveal() {
		// TODO Auto-generated method stub
		this.setDisable(true);
		this.setStyle("-fx-text-fill: "+this.getColorSet().getHex()+";" + "-fx-opacity: 1;");
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
		this.setGraphic(null);
		this.flagged = false;
	}

}
