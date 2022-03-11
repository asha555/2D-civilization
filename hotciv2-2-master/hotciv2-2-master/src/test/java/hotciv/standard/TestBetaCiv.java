package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.standard.gameFactories.BetaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/** Skeleton class for BetaCiv test cases
*/
public class TestBetaCiv {
  private Game game;

  private void nextRound() {
    doNRounds(1);
  }
  
  private void doNRounds(int n) {
    for(int i = 0; i < n; i++) {
      game.endOfTurn();
      game.endOfTurn();
    }
  }
  
  /** Fixture for BetaCiv testing. */
  @BeforeEach
  public void setUp() {
    game = new GameImpl(new BetaCivFactory());
  }

  @Test
  public void blueWinsWhenConqueringAllCities() {
    Position posBlueLegion = new Position(3,2);
    Position posSouthOfRed = new Position(2,1);
    Position posRedCity = new Position(1,1);
    game.endOfTurn();
    game.moveUnit(posBlueLegion, posSouthOfRed);
    nextRound();
    game.moveUnit(posSouthOfRed, posRedCity);
    game.endOfTurn();
    assertThat(game.getWinner(), is(Player.BLUE));
  }
  
  @Test
  public void WorldAgeAdvanceAccordingToTheSpecs() {
    assertThat(game.getAge(), is(-4000));
    nextRound();
    assertThat(game.getAge(), is(-3900));
    goToYearAssertCorrectYearCheckAgeAdvancementIsAsSpecified(-100,99);
    nextRound();
    assertThat(game.getAge(), is(1));
    nextRound();
    assertThat(game.getAge(), is(50));
    nextRound();
    assertThat(game.getAge(), is(100));
    nextRound();
    assertThat(game.getAge(), is(150));
    goToYearAssertCorrectYearCheckAgeAdvancementIsAsSpecified(1750, 25);
    goToYearAssertCorrectYearCheckAgeAdvancementIsAsSpecified(1900, 5);
    goToYearAssertCorrectYearCheckAgeAdvancementIsAsSpecified(1970, 1);
  }
  
  //Help Meathod
  private void goToYearAssertCorrectYearCheckAgeAdvancementIsAsSpecified(int year, int ageAdvancement) {
    while(game.getAge() < year) nextRound();
    assertThat(game.getAge(), is(year));
    nextRound();
    assertThat(game.getAge(), is(year+ageAdvancement));
  }
  
}
