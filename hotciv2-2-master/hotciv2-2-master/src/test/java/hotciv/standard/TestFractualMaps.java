package hotciv.standard;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.standard.gameFactories.AlphaCivFactory;
import hotciv.standard.gameFactories.FractualMapsFactory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestFractualMaps {
    private Game game;

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new FractualMapsFactory());
    }

    @Test
    public void printLandscape() {
        ArrayList<String> tileTypesInMap = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            game = new GameImpl(new FractualMapsFactory());
            final String firstTileType = game.getTileAt(new Position(0, 0)).getTypeString();
            for (int row = 0; row < GameConstants.WORLDSIZE; row++) {
                for (int col = 0; col < GameConstants.WORLDSIZE; col++) {
                    tileTypesInMap.add(game.getTileAt(new Position(row, col)).getTypeString());
                }
            }
            tileTypesInMap.removeIf(tileString -> tileString.equals(firstTileType)); //all the ones same as the first tile are removed, and check if there any left.
            boolean allTilesAreTheSame = tileTypesInMap.isEmpty();
            assertThat(allTilesAreTheSame, is(false));
        }
    }

}

/*


    @Test
    public void printLandscape(){
        ArrayList<String> tilesInLandScape = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            for(int row = 0; row < GameConstants.WORLDSIZE; row++){
                for(int col = 0; col < GameConstants.WORLDSIZE; col++) {
                    tilesInLandScape.add(game.getTileAt(new Position(row,col)).getTypeString());
                }
            }
            assertThat(tilesInLandScape.stream().reduce((tileString1, tileString2) -> tileString1.equals(tileString2)) ? , is(false));
            game = new GameImpl(new FractualMapsFactory());
        }
    }
}
 */