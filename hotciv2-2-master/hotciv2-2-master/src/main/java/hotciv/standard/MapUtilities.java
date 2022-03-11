package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.enumsAndConstants.GameConstants;

import java.util.HashMap;

public class MapUtilities {
    public static HashMap<Position, Tile> mapStringToHAshMapConversion(String[] layout) {
        HashMap<Position, Tile> map = new HashMap<>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                if ( tileChar == 'd' ) { type = GameConstants.DESERT; }
                Position p = new Position(r,c);
                map.put( p, new TileImpl(type));
            }
        }
        return map;
    }
}
