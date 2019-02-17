import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * A class responsible for changing the Smiley Emoticon in Header Panel.
 * @author Ritvik Bhardwaj
 *
 **/
public class Smiley extends Label{
	
	/**
	 * Creates an instance of Smiley.
	 */
	private String currentReaction;
	public Smiley() {
		super();
		Image image = new Image("Images/DefaultSmiley.png");
		ImageView iv = new ImageView(image);
		this.setGraphic(iv);
		this.currentReaction = "Default";
		setMinWidth(image.getWidth());
		setMaxWidth(image.getWidth());
		setMinHeight(image.getHeight());
		setMaxHeight(image.getHeight());
	}
	
	/**
	 * Update the current Smiley with reaction's image.
	 * @param reaction: the string containing the updated image.
	 * Precondition: reaction is a valid string name such that Images/reactionSmiley.png is a valid Image.
	 */
	
	public void updateImage(String reaction) {
		Image image = new Image("Images/" + reaction + "Smiley.png");
		ImageView iv = new ImageView(image);
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
