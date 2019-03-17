/**
 * 
 * @author Alexei
 *
 * A Factory to create different Event Handlers (Strategies) for the 3 types of Button Boxes (Number, Bomb
 * and Whitespace)
 *
 */
public class BoxStrategyFactory {

	public static BoxStrategy create(String strategyName, Mine_Model mineModel){
		BoxStrategy strategy = null;
		if (strategyName == "Bomb"){
			strategy = (BoxStrategy) new BombStrategy(mineModel);
		} 
		else if (strategyName == "WhiteSpace"){
			strategy = (BoxStrategy) new WhiteSpaceStrategy(mineModel);
		} 
		else if (strategyName == "Number"){
			strategy = (BoxStrategy) new NumberStrategy(mineModel);
		}
		return strategy;
	}
}
