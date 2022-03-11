package hotciv.standard.winningStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.strategies.WinningStrategy;

import java.util.HashMap;
import java.util.List;

public class EpsilonCivWinningStrategy implements WinningStrategy {
    HashMap<Player, Integer> victoryCount = new HashMap<>();
    Player winner = Player.NONE;
    int previouslyIncalculatedVictories = 0;

    @Override
    public Player findWinner(Game game) {
        return findWinner(game, previouslyIncalculatedVictories);
    }
    
    public Player findWinner(Game game, int doNotAddToEachPlayersVictoriesFromBeforeThisBattleNr) {
        if(winner == Player.NONE) {
            return continueSearchForWinner(game, Math.max(previouslyIncalculatedVictories, doNotAddToEachPlayersVictoriesFromBeforeThisBattleNr));
        } else {
            return winner;
        }
    }
    
    private Player continueSearchForWinner(Game game, int doNotAddToEachPlayersVictoriesFromBeforeThisBattleNr) {
        List<Player> battlesWon = game.getListOfBattlesWon();
        for(int i = doNotAddToEachPlayersVictoriesFromBeforeThisBattleNr; i < battlesWon.size(); i++) {
            addVictoryToVictoriousPlayer(battlesWon.get(i));
            previouslyIncalculatedVictories = i;
            if(winner != Player.NONE) return winner;
            else continue;
        }
        return Player.NONE;
    }
   
    private void addVictoryToVictoriousPlayer(Player victoriousPlayer) {
        Integer battlesWonByCurrentPlayer = victoryCount.get(victoriousPlayer);
        if(battlesWonByCurrentPlayer == null ){
            victoryCount.put(victoriousPlayer, 1);
        } else {
            victoryCount.put(victoriousPlayer, battlesWonByCurrentPlayer + 1);
            if(battlesWonByCurrentPlayer + 1 >= 3) {
                winner = victoriousPlayer;
            }
        }
    }
    
    

}

