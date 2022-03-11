package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.gameFactories.AlphaCivFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;

/** Skeleton class for AlphaCiv test cases
 */
public class TestSemiCiv {
    private Game game;
    /** Fixture for alphaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());
    }

    private void nextRound() {
        doNRounds(1);
    }

    private void doNRounds(int n) {
        for(int i = 0; i < n; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }
}
