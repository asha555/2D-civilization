package hotciv.framework;

import hotciv.framework.enumsAndConstants.Player;

/** Represents a city in the game.

Responsibilities:
1) Knows its owner.
2) Knows its population size.
*/
public interface City {
  /**
   * return the owner of this city.
   *
   * @return the player that controls this city.
   */
  Player getOwner();
  
  /**
   * return the size of the population.
   *
   * @return population size.
   */
  int getPopulationSize();

  /**
   * return the treasury, i.e. the
   * number of 'money'/production in the
   * city's treasury which can be used to
   * produce a unit in this city
   *
   * @return an integer, the amount of production
   * in the city treasury
   */
  int getTreasury();

  /**
   * return the type of unit this city is currently producing.
   *
   * @return a string type defining the unit under production,
   * see GameConstants for valid values.
   */
  String getProduction();

  /**
   * return the work force's focus in this city.
   *
   * @return a string type defining the focus, see GameConstants
   * for valid return values.
   */
  String getWorkforceFocus();

  int getSize();
}
