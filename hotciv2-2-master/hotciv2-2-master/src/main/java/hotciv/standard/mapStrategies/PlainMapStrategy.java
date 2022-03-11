package hotciv.standard.mapStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.strategies.InitialMapStrategy;
import hotciv.standard.TileImpl;

import java.util.HashMap;

public class PlainMapStrategy implements InitialMapStrategy {
    @Override
    public HashMap<Position, Tile> getInitialMap(int noOfRows, int noOfCols) {
        HashMap<Position, Tile> map = new HashMap<>();
        //Initially place Plane-Tiles everywhere
        for (int col = 0; col < noOfCols; col++) {
            for (int row = 0; row < noOfRows; row++) {
                map.put(new Position(col, row), new TileImpl(GameConstants.PLAINS));
            }
        }
        
        return map;
    }
    
    @Override
    public HashMap<Position, ModifiableCity> getInitialCities(int noOfRows, int noOfCols) {
        HashMap<Position, ModifiableCity> cities = new HashMap<>();
        return cities;
    }
    
    @Override
    public HashMap<Position, ModifiableUnit> getInitialUnits(int noOfRows, int noOfCols) {
        HashMap<Position, ModifiableUnit> units = new HashMap<>();
        return units;
    }
}
