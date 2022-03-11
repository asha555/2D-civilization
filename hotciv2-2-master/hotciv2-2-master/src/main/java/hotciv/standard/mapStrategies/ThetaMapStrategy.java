package hotciv.standard.mapStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.strategies.InitialMapStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.MapUtilities;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class ThetaMapStrategy  implements InitialMapStrategy {
    @Override
    public HashMap<Position, Tile> getInitialMap(int noOfRows, int noOfCols) {
        String[] layout = new String[] {
                "...ooModdoo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...odd..........",
                ".ooodo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoddo.offoooo",
                "oodddodo...ooooo",
                ".ooMMMdooo......",
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
        cities.put(new Position(4, 5), new CityImpl(Player.BLUE));
        cities.put(new Position(8, 12), new CityImpl(Player.RED));
        return cities;
    }

    @Override
    public HashMap<Position, ModifiableUnit> getInitialUnits(int noOfRows, int noOfCols) {
        HashMap<Position, ModifiableUnit> units = new HashMap<>();
        //Place Units
        units.put(new Position(3, 8), new UnitImpl(Player.RED, GameConstants.ARCHER));
        units.put(new Position(4, 4), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        units.put(new Position(5, 5), new UnitImpl(Player.RED, GameConstants.SETTLER));
        units.put(new Position(9, 6), new UnitImpl(Player.BLUE, GameConstants.CARAVAN));
        return units;
    }
}
