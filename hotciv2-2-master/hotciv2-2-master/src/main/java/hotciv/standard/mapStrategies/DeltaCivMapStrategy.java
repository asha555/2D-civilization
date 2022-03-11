package hotciv.standard.mapStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.strategies.InitialMapStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.MapUtilities;
import hotciv.standard.TileImpl;

import java.util.HashMap;

public class DeltaCivMapStrategy implements InitialMapStrategy {
    @Override
    public HashMap<Position, Tile> getInitialMap(int noOfRows, int noOfCols) {
        String[] layout = new String[] {
                "...ooMooooo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...ooo..........",
                ".ooooo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoooo.offoooo",
                "oooooooo...ooooo",
                ".ooMMMoooo......",
                "..ooooooffoooo..",
                "....ooooooooo...",
                "..ooohhoo.......",
                ".....ooooooooo..",
        };
        return MapUtilities.mapStringToHAshMapConversion(layout);
    }
    
    @Override
    public HashMap<Position, ModifiableCity> getInitialCities(int noOfRows, int noOfCols) {
        HashMap<Position, ModifiableCity> cities = new HashMap<>();
        //Place Cities
        Position redCityPosition = new Position(8,12);
        cities.put(redCityPosition, new CityImpl(Player.RED));

        Position blueCityPosition = new Position(4,5);
        cities.put(blueCityPosition, new CityImpl(Player.BLUE));
        
        return cities;
    }
    
    @Override
    public HashMap<Position, ModifiableUnit> getInitialUnits(int noOfRows, int noOfCols) {
        HashMap<Position, ModifiableUnit> units = new HashMap<>();
        return units;
    }
}
