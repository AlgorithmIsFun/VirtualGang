
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * A class responsible for changing the Smiley Emoticon in Header Panel.
 * @author Ritvik Bhardwaj Abdullah
 *
 **/
public class Smiley extends Label{
	
	private int smileyLength = 60;
	
	/**
	 * Creates an instance of Smiley.
	 */
	private String currentReaction;
	public Smiley() {
		super();
		Image image = new Image("Images/DefaultSmiley.png");
		ImageView iv = new ImageView(image);
		iv.setFitHeight(smileyLength);
		iv.setFitWidth(smileyLength);
		this.currentReaction = "Default";
		setMinWidth(smileyLength);
		setMaxWidth(smileyLength);
		setMinHeight(smileyLength);
		setMaxHeight(smileyLength);
		this.setGraphic(iv);
	}
	
	/**
	 * Update the current Smiley with reaction's image.
	 * @param reaction: the string containing the updated image.
	 * Precondition: reaction is a valid string name such that Images/reactionSmiley.png is a valid Image.
	 */
	
	public void updateImage(String reaction) {
		Image image = new Image("Images/" + reaction + "Smiley.png");
		ImageView iv = new ImageView(image);
		iv.setFitHeight(smileyLength);
		iv.setFitWidth(smileyLength);
		this.currentReaction = reaction;
		setMinWidth(smileyLength);
		setMaxWidth(smileyLength);
		setMinHeight(smileyLength);
		setMaxHeight(smileyLength);
		this.setGraphic(iv);
	}
	/**
	 * Return the current reaction of the Smiley.
	 * @return currentReaction
	 */
	public String getReaction() {
		return this.currentReaction;
	}

}
