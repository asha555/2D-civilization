package hotciv.standard.winningStrategies;

import hotciv.framework.Game;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.strategies.WinningStrategy;

public class AlphaCivWinningStrategy implements WinningStrategy {
    @Override
    public Player findWinner(Game game) {
        if(game.getAge() >= -3000)
            return Player.RED;
        else
            return Player.NONE;
    }
}
