package hotciv.standard;

import hotciv.framework.*;

import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.standard.gameFactories.AlphaCivFactory;
import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/** Skeleton class for AlphaCiv test cases
*/
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @BeforeEach
  public void setUp() {
    game = new GameImpl(new AlphaCivFactory());
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeBluesTurnAfterRedTurn() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game = new Transcript(game);
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void shouldBeRedCityAtPositionOneOne() {
    Position posRedCity = new Position(1,1);
    assertThat(game.getCityAt(posRedCity).getOwner(), is((new CityImpl(Player.RED).getOwner())));
  }
  
  @Test
  public void shouldBeBlueCityAtPositionFourOne() {
    Position posBlurCity = new Position(4,1);
    assertThat(game.getCityAt(posBlurCity).getOwner(), is((new CityImpl(Player.BLUE).getOwner())));
  }

  @Test
  public void shouldBeOceanAtPositionOneZero() {
    Position oceanPosition = new Position(1,0);
    assertThat(game.getTileAt(oceanPosition).getTypeString(), containsString(new TileImpl(GameConstants.OCEANS).getTypeString()));
  }

  @Test
  public void mostTilesShouldBePLains() {
     Position plains1 = new Position(0,0);
     Position plains2 = new Position(5,3);
     Position plains3 = new Position(5,8);
     Position plains4 = new Position(15,15);
    assertThat(game.getTileAt(plains1).getTypeString(), containsString(new TileImpl(GameConstants.PLAINS).getTypeString()));
    assertThat(game.getTileAt(plains2).getTypeString(), containsString(new TileImpl(GameConstants.PLAINS).getTypeString()));
    assertThat(game.getTileAt(plains3).getTypeString(), containsString(new TileImpl(GameConstants.PLAINS).getTypeString()));
    assertThat(game.getTileAt(plains4).getTypeString(), containsString(new TileImpl(GameConstants.PLAINS).getTypeString()));
  }

  @Test
  public void redHasAnArcherUnitAtPositionTwoZero() {
    Position redArcherUnit = new Position(2,0);
    Unit unit = game.getUnitAt(redArcherUnit);
    assertThat(unit.getOwner(),is(Player.RED));
    assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void blueHasALegionUnitAtPositionThreeTwo() {
    Position blueHasALegionUnit  = new Position(3,2);
    Unit unit = game.getUnitAt(blueHasALegionUnit);
    assertThat(unit.getOwner(),is(Player.BLUE));
    assertThat(unit.getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void redHasASettlerUnitAtPositionFourThree() {
    Position redHasASettler = new Position(4,3);
    Unit unit = game.getUnitAt(redHasASettler);
    assertThat(unit.getOwner(),is(Player.RED));
    assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
  }



  @Test
  public void blueLegionShouldNotMove() {
    Position blueLegionShouldStay = new Position(3,2);
    Position TestPosition = new Position(3,3);
    Unit unit1 = game.getUnitAt(blueLegionShouldStay);
    assertThat(unit1.getOwner(),is(Player.BLUE));

    assertThat(game.moveUnit(blueLegionShouldStay, TestPosition), is (false));

    assertThat(game.getUnitAt(TestPosition), not(unit1));
  }
  
  @Test
  public void MovingAUnitTwoTimesInOneTurnNorAllowed() {
    Position move1 = new Position(4,3);
    Position move2 = new Position(4,4);
    Position move3 = new Position(4,5);
    Unit unit = game.getUnitAt(move1);
    assertThat(unit.getOwner(),is(Player.RED));
    assertThat(unit.getTypeString(), is(GameConstants.SETTLER));

    assertThat(game.moveUnit(move1, move2), is(true));
    assertThat(game.getUnitAt(move2), is(unit));

    assertThat(game.moveUnit(move2, move3), is(false));
    assertThat(game.getUnitAt(move3), not(unit));
  }

  @Test
  public void NotMoveMoreThanOneSpaceAtATime() {
    Position pos1RedSettler = new Position(4,3);
    Position pos2RedSettler = new Position(4,5);
    
    assertThat(game.moveUnit(pos1RedSettler,pos2RedSettler), is(false));
  }

  @Test
  public void TheGameStartsAtGge4000BC() {
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void EachRoundAdvancesTheGameAge100Years() {
    int initialAge = game.getAge();
    newRound();
    int ageAfterOneRound = game.getAge();
    assertThat(ageAfterOneRound, is(initialAge + 100));
  }
  @Test
  public void unitCannotMoveOverMontains(){
    Position moveUnitOverMontain = new Position(3,2);
    Position moveMontain = new Position(2,2);
    game.endOfTurn();
    assertThat(game.moveUnit(moveUnitOverMontain, moveMontain), is(false));
  }

  @Test
  public void RedCannotMoveBlueUnits(){
    Position posBlueUnitMove1 = new Position(3,2);
    Position posBlueUnitMove2 = new Position(2,2);
    
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.moveUnit(posBlueUnitMove1, posBlueUnitMove2), is(false));
  }

  @Test
  public void CityProducesSixIncomeAfterARoundHasEnded(){

    Position posRedCity = new Position(1,1);
    
    assertThat(game.getCityAt(posRedCity).getTreasury(), is(0));
    newRound();
    assertThat(game.getCityAt(posRedCity).getTreasury(), is(6));
  }
  
  @Test
  public void RedDoesNotWinBeforeYear3000BC() {
    while(game.getAge() < -3000) { //Check each round until, but not including -3000 (3000 BC)
      assertThat(game.getWinner(), not(Player.RED));
      newRound();
    }
  }
  
  @Test
  public void RedWinsInYear3000BC() {
    int o = 0;
    for(int i = 0; i < 30; i++){
      newRound();
    }
    //doProvidedNumberOfNewRounds(30);
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void CitiesPopulationSizeIsAlwaysOne() {
    assertThat(game.getCityAt(new Position(1,1)). getPopulationSize(), is(1));
  }
  
  @Test
  public void moveCountIsReestablishedToZeroEachNewRound() {
    Position pos = new Position(4,3);
    Position posMove1 = new Position(4,4);
    Position posMove2 = new Position(4,5);
    Unit unit = game.getUnitAt(pos);
    //First move
    assertThat(game.moveUnit(pos, posMove1), is(true));
    assertThat(game.getUnitAt(posMove1), is(unit));
    newRound();
    //Second move
    assertThat(game.moveUnit(posMove1,posMove2), is(true));
    assertThat(game.getUnitAt(posMove2), is(unit));
  }
  
  @Test
  public void PlayerCanNotChooseInvalidCityProduction(){

    Position posRedCity = new Position(1,1);
    try{
      game.changeProductionInCityAt(posRedCity,"JazzPlayer");
    } catch(RuntimeException e) {}
    assertThat(game.getCityAt(posRedCity).getProduction(), not("JazzPlayer"));
  }
  
  @Test
  public void PlayerCanChooseCityProductionArcher(){
    Position posRedCity = new Position(1,1);
    game = new Transcript(game);
    game.changeProductionInCityAt(posRedCity,GameConstants.ARCHER);

    assertThat(game.getCityAt(posRedCity).getProduction(), is(GameConstants.ARCHER));
  }

  @Test
  public void PlayerCanChooseCityProductionSettler(){
    Position pOfCity = new Position(1,1);
    game.changeProductionInCityAt(pOfCity,GameConstants.SETTLER);
    assertThat(game.getCityAt(pOfCity).getProduction(), is(GameConstants.SETTLER));
    doProvidedNumberOfNewRounds(5); //5 rounds needed to get sufficint treasuries for producing a settler.
    assertThat(game.getUnitAt(pOfCity).getTypeString(), is(GameConstants.SETTLER));
  }
  
  @Test
  public void PlayerCanChooseCityProductionLegion(){
    Position pOfCity = new Position(1,1);
    game.changeProductionInCityAt(pOfCity,GameConstants.LEGION);
    assertThat(game.getCityAt(pOfCity).getProduction(), is(GameConstants.LEGION));
    doProvidedNumberOfNewRounds(3); //3 rounds needed to get sufficint treasuries for producing a legion.
    assertThat(game.getUnitAt(pOfCity).getTypeString(), is(GameConstants.LEGION));
  }
  
  @Test
  public void CitiesProduceProuctionWhenTheyHaveSufficientTresury() {
    Position posRedCity = new Position(1,1);
    Position posBlueCity = new Position(4,1);
    assertThat(game.getUnitAt(posRedCity) == null, is(true));
    assertThat(game.getUnitAt(posBlueCity) == null, is(true));
    doProvidedNumberOfNewRounds(2); //2 rounds needed to get sufficint treasuries for producing an archer.
    assertThat(game.getUnitAt(posRedCity).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(posBlueCity).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void CitiesCanProduceProductionAllTheWayAroundThem() {
    doProvidedNumberOfNewRounds(2*9); //Up to 2 rounds needed to get sufficint treasuries for producing an archer. We want to check that archers are placed correctly all the way around the city, which is why we need 9 archers.
    
    //City Position and Positions around city
    Position posRedCity = new Position(1,1);
    Position posVestOfRedCity = new Position(1,0);
    Position posSouthVestOfRedCity = new Position(2,0);
    Position posSouthOfRedCity = new Position(2,1);
    Position posSouthEastOfRedCity = new Position(2,2);
    Position posEastOfRedCity = new Position(1,2);
    Position posNorthEastOfRedCity = new Position(0,2);
    Position posNorthOfRedCity = new Position(0,1);
    Position posNorthVestOfRedCity = new Position(0,0);
    
    //Assert that a unit have been placed at every tile where the tiletype accepts it.
    assertThat(game.getUnitAt(posRedCity).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(posVestOfRedCity) == null, is(true));
    assertThat(game.getUnitAt(posSouthVestOfRedCity).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(posSouthOfRedCity).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(posSouthEastOfRedCity) == null, is(true));
    assertThat(game.getUnitAt(posEastOfRedCity).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(posNorthEastOfRedCity).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(posNorthOfRedCity).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(posNorthVestOfRedCity).getTypeString(), is(GameConstants.ARCHER));
  }

@Test
  public void UnitsCanInvadeCities(){
    //The needed positions
    Position posOneRedArcher = new Position(2,0);
    Position posTwoRedArcher = new Position(3,0);
    Position posBlueCity = new Position(4,1);
    
    assertThat(game.moveUnit(posOneRedArcher, posTwoRedArcher), is(true));
    newRound();
    assertThat(game.getCityAt(posBlueCity).getOwner(), is(Player.BLUE));
    assertThat(game.moveUnit(posTwoRedArcher, posBlueCity), is(true));
    assertThat(game.getCityAt(posBlueCity).getOwner(), is(Player.RED));
  }

  @Test
  public void ifRedArcherAttemptToMoveToATileWithAnotherRedArcherThenNothingShouldHappen() {
    Position posOneRedArcher = new Position(2,0);
    Position posTwoRedArcher = new Position(3,1);
    Position posThreeRedArcher = new Position(4,2);
    Position posOneRedSettler = new Position(4,3);
  
    Unit redArcher = game.getUnitAt(posOneRedArcher);
    Unit redSettler = game.getUnitAt(posOneRedSettler);
    game.moveUnit(posOneRedArcher, posTwoRedArcher);
    newRound();
    game.moveUnit(posTwoRedArcher, posThreeRedArcher);
    newRound();
    game.moveUnit(posThreeRedArcher, posOneRedSettler);
    assertThat(game.getUnitAt(posThreeRedArcher), is(redArcher));
    assertThat(game.getUnitAt(posOneRedSettler), is(redSettler));
  }
  
  @Test
  public void unitActionForArcherDoesNothingInAlphaCiv() {
    Position archerPos = new Position(2, 0);
    Unit archer = game.getUnitAt(archerPos);
    int initialDefeneStrength = archer.getDefensiveStrength();
    game.performUnitActionAt(archerPos);
    assertThat(archer.getDefensiveStrength(), is(initialDefeneStrength));
    assertThat(game.moveUnit(archerPos, new Position(3,0)), is(true)); //Move happened
  }
  
  @Test
  public void unitActionForSettlerDoesNothingInAlphaCiv() {
    Position settlerPos = new Position(4, 3);
    Unit settler = game.getUnitAt(settlerPos);
    assertThat(game.getCityAt(settlerPos) == null, is(true)); //No city at settlers position before unit action
    game.performUnitActionAt(settlerPos);
    assertThat(game.getCityAt(settlerPos) == null, is(true)); //Still no city at settlers position after unit action
    assertThat(game.getUnitAt(settlerPos), is(settler));
  }
  
  @Test
  public void retriveAttackStrengthForAUnit() {
    Position posRedArcher = new Position(2,0);
    assertThat(game.getUnitAt(posRedArcher).getAttackingStrength(), is(GameConstants.attackStrengths.get(GameConstants.ARCHER)));
  }

  @Test
  public void RedArcherShouldMoveFromTwoZeroToThreeOne() {
    game = new Transcript(game);
    Position redArherShouldMoveFrom = new Position(2,0);
    Position redArherShouldMoveTo = new Position(3,1);

    Unit unit1 = game.getUnitAt(redArherShouldMoveFrom);
    assertThat(unit1.getOwner(),is(Player.RED));
    assertThat(unit1.getTypeString(), is(GameConstants.ARCHER));

    assertThat(game.moveUnit(redArherShouldMoveFrom, redArherShouldMoveTo), is(true));

    assertThat(game.getUnitAt(redArherShouldMoveTo), is(unit1));
  }
  
  /**
   * Help Methods
   */
  private void newRound() {
    doProvidedNumberOfNewRounds(1);
  }
  
  private void doProvidedNumberOfNewRounds(int numberOfNewRounds) {
    for(int i = 0; i < numberOfNewRounds; i++) {
      game.endOfTurn();
      game.endOfTurn();
    }
  }
}
