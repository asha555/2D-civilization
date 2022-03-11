package hotciv.standard.canMoveUntoStrategies;

import hotciv.framework.strategies.CanMoveUntoStrategy;
import hotciv.framework.enumsAndConstants.*;

public class ThetaCivCanMoveUntoStrategy implements CanMoveUntoStrategy {
    AlphaCivCanMoveUntoStrategy alphaCivCanMoveUntoStrategy;
    
    public ThetaCivCanMoveUntoStrategy(AlphaCivCanMoveUntoStrategy alphaCivCanMoveUntoStrategy) {
        this.alphaCivCanMoveUntoStrategy = alphaCivCanMoveUntoStrategy;
    }
    
    @Override
    public boolean canMoveOnto(String unitType, String tileType) {
        switch(unitType) {
            case GameConstants.CARAVAN: switch(tileType) {
                case GameConstants.PLAINS: case GameConstants.HILLS:
                case GameConstants.FOREST: case GameConstants.DESERT:
                    return true;
                }
            default:
                return alphaCivCanMoveUntoStrategy.canMoveOnto(unitType, tileType);
        }
    }
}
