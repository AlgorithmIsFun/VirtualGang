
public class BombFactory {

	public static BoxStrategy create(String strategyName, Mine_Model mineModel){
		BoxStrategy strategy=null;
		if(strategyName== "Bomb"){
			strategy=(BoxStrategy) new SquareStrategy(mineModel);
		} else if(strategyName=="WhiteSpace"){
			strategy=(BoxStrategy) new WhiteSpaceStrategy(mineModel);
		} else if(strategyName=="Number"){
			strategy=(BoxStrategy)new SquareStrategy(mineModel);
		}
		return strategy;
	}
}
