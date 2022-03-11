package hotciv.standard.winningStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.strategies.WinningStrategy;

public class ZetaCivWinningStrategy implements WinningStrategy {
    private BetaCivWinningStrategy conquerAllCitiesWinningStrategy;
    private EpsilonCivWinningStrategy epsilonCivWinningStrategy;
    Integer numberVictoriesBeforeRound21 = null;
    
    public ZetaCivWinningStrategy(BetaCivWinningStrategy conquerAllCitiesWinningStrategy,
                                  EpsilonCivWinningStrategy epsilonCivWinningStrategy) {
        this.conquerAllCitiesWinningStrategy = conquerAllCitiesWinningStrategy;
        this.epsilonCivWinningStrategy = epsilonCivWinningStrategy;
    }
    
    @Override
    public Player findWinner(Game game) {
        int playedRounds = game.getNoOfEndedRounds();
        if(playedRounds == 20)
            numberVictoriesBeforeRound21 = game.getListOfBattlesWon().size();
        if(playedRounds <= 20) {
            return conquerAllCitiesWinningStrategy.findWinner(game);
        } else
            return epsilonCivWinningStrategy.findWinner(game, numberVictoriesBeforeRound21);
    }
}
