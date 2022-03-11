package hotciv.standard.canMoveUntoStrategies;

import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.strategies.CanMoveUntoStrategy;

public class AlphaCivCanMoveUntoStrategy implements CanMoveUntoStrategy {
    @Override
    public boolean canMoveOnto(String unitType, String tileType) {
        switch (tileType){
            case GameConstants.PLAINS: case GameConstants.HILLS: case GameConstants.FOREST:
                return true;
            default:
                return false;
        }
    }
}
