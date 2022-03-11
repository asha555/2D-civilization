package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.Position;
import hotciv.standard.gameFactories.DeltaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/** Skeleton class for BetaCiv test cases
*/
public class TestDeltaCiv {
  private Game game;

  private void nextRound() {
    game.endOfTurn();
    game.endOfTurn();
  }
  
  /** Fixture for BetaCiv testing. */
  @BeforeEach
  public void setUp() {
    game = new GameImpl(new DeltaCivFactory());
  }
  
  @Test
  public void redCityAtPositionEightTwelfth() {
    Position posRedCity = new Position(8,12);
    assertThat(game.getCityAt(posRedCity).getOwner(), is(Player.RED));
  }
  
  @Test
  public void blueCityAtPositionFourFive() {
    Position posBlueCity = new Position(4,5);
    assertThat(game.getCityAt(posBlueCity).getOwner(), is(Player.BLUE));
  }
  
  @Test
  public void correctTileAtRandomLocationOne() {
    Position posRandomPlainTile = new Position(1,2);
    assertThat(game.getTileAt(posRandomPlainTile).getTypeString(), is(GameConstants.PLAINS));
  }
  
  @Test
  public void correctTileAtRandomLocationTwo() {
    Position posRandomPlainTile = new Position(3,6);
    assertThat(game.getTileAt(posRandomPlainTile).getTypeString(), is(GameConstants.PLAINS));
  }
  
  @Test
  public void correctTileAtRandomLocationThree() {
    Position posRandomPlainsTile = new Position(7,7);
    assertThat(game.getTileAt(posRandomPlainsTile).getTypeString(), is(GameConstants.PLAINS));
  }
  
  @Test
  public void correctTileAtRandomLocationFour() {
    Position posRandomOceanTile = new Position(0,15);
    assertThat(game.getTileAt(posRandomOceanTile).getTypeString(), is(GameConstants.OCEANS));
  }
  
  @Test
  public void correctTileAtRandomLocationFive() {
    Position posRandomPlainTile = new Position(14,2);
    assertThat(game.getTileAt(posRandomPlainTile).getTypeString(), is(GameConstants.PLAINS));
  }
  
  @Test
  public void correctTileAtRandomLocationSix() {
    Position posRandomOceanTile = new Position(15,15);
    assertThat(game.getTileAt(posRandomOceanTile).getTypeString(), is(GameConstants.OCEANS));
  }
  
  @Test
  public void correctTileAtRandomLocationSeven() {
    Position posRandomMountainsTile = new Position(0,5);
    assertThat(game.getTileAt(posRandomMountainsTile).getTypeString(), is(GameConstants.MOUNTAINS));
  }
  
  @Test
  public void correctTileAtRandomLocationEight() {
    Position posRandomHillTile = new Position(1,4);
    assertThat(game.getTileAt(posRandomHillTile).getTypeString(), is(GameConstants.HILLS));
  }
  
  @Test
  public void correctTileAtRandomLocationNine() {
    Position posRandomForestTile = new Position(12,9);
    assertThat(game.getTileAt(posRandomForestTile).getTypeString(), is(GameConstants.FOREST));
  }
}
