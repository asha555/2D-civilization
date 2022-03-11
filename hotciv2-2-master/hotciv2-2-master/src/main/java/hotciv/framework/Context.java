package hotciv.framework;

import hotciv.framework.strategies.WorldAgingStrategy;
import hotciv.framework.strategies.WinningStrategy;

/** Represents versions of the varaiants in the game.
 *
 *
 * Responsibilities:
 * 1) Providing the correct
 *    a) Winning Strategy
 *    b) World Aging Strategy
 */
public interface Context {
  /**
   * Return the WinningStrategy
   *
   * @return the WinningStrategy
   */
  WinningStrategy getWinningStrategy();
  
  /**
   * Return the AgeStrategy
   *
   * @return the AgeStrategy
   */
  WorldAgingStrategy getWorldAgingStrategy();

}