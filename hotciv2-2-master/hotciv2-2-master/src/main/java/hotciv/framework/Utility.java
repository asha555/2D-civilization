package hotciv.framework;

import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * At 09 May 2018
 *
 * @author Henrik Baerbak Christensen, CS @ AU
 */
public class Utility {
    public class TerrainFactor {
        public static final int HILLS = 2;
        public static final int FORESTS = 2;
        public static final int PLAINS = 1;
        public static final int CITY = 3;
    }
    
    public static Iterator<Position> get8neighborhoodIterator(Position center) {
        List<Position> list = new ArrayList<>();
        // Define the 'delta' to add to the row for the 8 positions
        int[] rowDelta = new int[] {-1, -1, 0, +1, +1, +1, 0, -1};
        // Define the 'delta' to add to the colum for the 8 positions
        int[] columnDelta = new int[] {0, +1, +1, +1, 0, -1, -1, -1};
        
        for (int index = 0; index < rowDelta.length; index++) {
            int row = center.getRow() + rowDelta[index];
            int col = center.getColumn() + columnDelta[index];
            if (row >= 0 && col >= 0
                    && row < GameConstants.WORLDSIZE
                    && col < GameConstants.WORLDSIZE)
                list.add(new Position(row, col));
        }
        return list.iterator();
    }
    
    public static Iterable<Position> get8neighborhoodOf(Position center) {
        final Iterator<Position> iterator = get8neighborhoodIterator(center);
        Iterable<Position> iterable = new Iterable<>() {
            @Override
            public Iterator<Position> iterator() {
                return iterator;
            }
        };
        return iterable;
    }
    
    /**
     * get the terrain factor for the attack and defense strength according to the
     * GammaCiv specification
     *
     * @param game
     *          the game the factor should be given for
     * @param position
     *          the position that the factor should be calculated for
     * @return the terrain factor
     */
    public static int getTerrainFactor(Game game, Position position) {
        // cities overrule underlying terrain
        if ( game.getCityAt(position) != null ) { return TerrainFactor.CITY; }
        String tileType = game.getTileAt(position).getTypeString();
        if ( tileType.equals(GameConstants.FOREST) ) { return TerrainFactor.FORESTS; }
        if ( tileType.equals(GameConstants.HILLS)) { return TerrainFactor.HILLS; }
        
        return TerrainFactor.PLAINS;
    }
    
    /**
     * calculate the additional support a unit at position p owned by a given
     * player gets from friendly units on the given game.
     *
     * @param game
     *          the game to calculate on
     * @param position
     *          the position of the unit whose support is wanted
     * @param player
     *          the player owning the unit at position 'position'
     * @return the support for the unit, +1 for each friendly unit in the 8
     *         neighborhood.
     */
    public static int getFriendlySupport(Game game, Position position,
                                         Player player) {
        Iterator<Position> neighborhood = Utility.get8neighborhoodIterator(position);
        Position p;
        int support = 0;
        while ( neighborhood.hasNext() ) {
            p = neighborhood.next();
            if ( game.getUnitAt(p) != null &&
                    game.getUnitAt(p).getOwner() == player ) {
                support++;
            }
        }
        return support;
    }
}
