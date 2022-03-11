package hotciv.framework.strategies;

import hotciv.framework.Game;
import hotciv.framework.enumsAndConstants.Player;

/** Represents the contract which decides the winning condition of the game.
 *
 * Responsibilities:
 * 1) check to see if the winning condition has been meet.
 * 2) save the winner
 * 2) supply the winner
 */
public interface WinningStrategy {
  /**
   * Checks to see if the winning condition has been meet.
   * If so the method saves the winner and return true.
   * If not the method returns false.
   *
   * @param game  The object that contains the state of the game.
   * @return      true if the winning condition has been meet, false otherwise.
   */
  Player findWinner(Game game);

}
