package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.gameFactories.GammaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/** Skeleton class for BetaCiv test cases
*/
public class TestGammaCiv {
  private Game game;

  private void nextRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  // hello
  /** Fixture for BetaCiv testing. */
  @BeforeEach
  public void setUp() {
    game = new GameImpl(new GammaCivFactory());
  }

  @Test
  public void seatlerUnitActionRemovesSeatlerAndCreatesCity() {
    Position p = new Position(4, 3); //Position of seatler
    Unit seatler = game.getUnitAt(p);
    assertThat(game.getCityAt(p) == null, is(true));
    game.performUnitActionAt(p);
    game = new Transcript(game);
    assertThat(game.getUnitAt(p), not(seatler));
    assertThat(game.getCityAt(p).getOwner(), is(seatler.getOwner()));
  }
  
  @Test
  public void archerUnitActionDoublesDefenceButRemovesMoability() {
    Position p = new Position(2, 0); //Position of archer
    Unit archer = game.getUnitAt(p);
    int defensiveStrength = archer.getDefensiveStrength();
    game = new Transcript(game);
    game.performUnitActionAt(p);
    assertThat(archer.getDefensiveStrength(), is(defensiveStrength*2)); //Defensive strength of a fortified archer is doubled.
    assertThat(game.moveUnit(p, new Position(3,0)), is(false)); //The fortified archer cannot move.
  }
  @Test
  public void archerCanDefortifyAndDefenseStrengthIsReducedToHalf() {
    Game innergame = game;
    Position p = new Position(2, 0); //Position of archer
    Unit archer = game.getUnitAt(p);
    int defensiveStrength = archer.getDefensiveStrength();
    game = new Transcript(game);
    game.performUnitActionAt(p);
    game = innergame;
    game.performUnitActionAt(p);
    assertThat(archer.getDefensiveStrength(), is(defensiveStrength)); //Defensive strength of a Defortified archer is as before it fortified
    nextRound();
    assertThat(game.moveUnit(p, new Position(3,0)), is(true)); //The defortified archer cannot move.
  }
}
