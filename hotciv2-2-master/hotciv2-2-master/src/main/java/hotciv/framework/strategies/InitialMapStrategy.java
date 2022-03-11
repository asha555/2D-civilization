package hotciv.framework.strategies;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Requirements: Constructs the initial map.
 */
public interface InitialMapStrategy {
    /**
     * Constructs the initial map.
     *
     * @return The initial map
     */
    HashMap<Position, Tile> getInitialMap(int noOfRows, int noOfCols);
    HashMap<Position, ModifiableCity> getInitialCities(int noOfRows, int noOfCols);
    HashMap<Position, ModifiableUnit> getInitialUnits(int noOfRows, int noOfCols);
    
}
