package hotciv.standard.winningStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.strategies.WinningStrategy;

public class BetaCivWinningStrategy implements WinningStrategy {
    @Override
    public Player findWinner(Game game) {
        Player firstFoundOwner = Player.NONE;
        Position p = game.getMaxRowMaxColPosition();
        for(int row = 0, maxRow = p.getRow(); row <= maxRow; row++) {
            for(int col = 0, maxCol = p.getColumn(); col <= maxCol; col++) {
                City city = game.getCityAt(new Position(row, col));
    
                //Naming booleans
                boolean noCityExistOnTile = city == null;
                boolean isFirstFoundCity = firstFoundOwner == Player.NONE;
                
                if(noCityExistOnTile) {
                    continue;
                } else if(isFirstFoundCity) {
                    firstFoundOwner = city.getOwner();
                } else if(firstFoundOwner.equals(city.getOwner())) {
                    continue;
                } else {
                    return Player.NONE;
                }
            }
        }
        return firstFoundOwner;
    }
}
