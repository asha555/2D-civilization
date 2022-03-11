package hotciv.standard.mapStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.InitialMapStrategy;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;

import java.util.HashMap;

public class AlphaCivMapStrategy implements InitialMapStrategy {
    @Override
    public HashMap<Position, Tile> getInitialMap(int noOfRows, int noOfCols) {
        HashMap<Position, Tile> map = new HashMap<>();
        //Initially place Plane-Tiles everywhere
        for (int col = 0; col < noOfCols; col++) {
            for (int row = 0; row < noOfRows; row++) {
                map.put(new Position(col, row), new TileImpl(GameConstants.PLAINS));
            }
        }
        //Replace some of the plane-tiles with other tiles
        map.put(new Position(0, 1), new TileImpl(GameConstants.HILLS));
        map.put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));
        map.put(new Position(2, 2), new TileImpl(GameConstants.MOUNTAINS));
        
        return map;
    }
    
    @Override
    public HashMap<Position, ModifiableCity> getInitialCities(int noOfRows, int noOfCols) {
        HashMap<Position, ModifiableCity> cities = new HashMap<>();
        //Place Cities
        cities.put(new Position(4, 1), new CityImpl(Player.BLUE));
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        
        return cities;
    }
    
    @Override
    public HashMap<Position, ModifiableUnit> getInitialUnits(int noOfRows, int noOfCols) {
        UnitFactory unitFactory = new AlphaCivUnitFactory();
        HashMap<Position, ModifiableUnit> units = new HashMap<>();
        //Place Units
        units.put(new Position(2, 0), unitFactory.createUnit(Player.RED, GameConstants.ARCHER));
        units.put(new Position(3, 2), unitFactory.createUnit(Player.BLUE, GameConstants.LEGION));
        units.put(new Position(4, 3), unitFactory.createUnit(Player.RED, GameConstants.SETTLER));
        
        return units;
    }
}
