package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.standard.gameFactories.ThetaCivFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestThetaCiv {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ThetaCivFactory());
    }
    @Test
    public void newTileTypeDesertIntroduced(){
        Position Desert = new Position(6,4);
        assertThat(game.getTileAt(Desert).getTypeString(), containsString(new TileImpl(GameConstants.DESERT).getTypeString()));
    }

    @Test
    public void ShouldBeACaravanTileNineSix(){
        Position Caravan = new Position(9, 6);
        assertThat(game.getUnitAt(Caravan).getOwner(), is(Player.BLUE));
    }
    @Test
    public void aCaravanCost30(){
        assertThat(game.getUnitFactory().priceOf(GameConstants.CARAVAN), is(GameConstants.price.get(GameConstants.CARAVAN)));
    }
    
    @Test
    public void aCaravanHasAnAttackStrengthTwo(){
        Position Caravan = new Position(9, 6);
        assertThat(game.getUnitAt(Caravan).getAttackingStrength(), is(1));
    }
    
    @Test
    public void aCaravanHasAnDefenseStrengthFour(){
        Position Caravan = new Position(9, 6);
        assertThat(game.getUnitAt(Caravan).getDefensiveStrength(), is(4));
    }
    
    @Test
    public void aCaravancantravelAdistanceOfTwo(){
        Position posOneBlueCaravan = new Position(9, 6);
        Position posTwoBlueCaravan = new Position(10, 6);
        Position posThreeBlueCaravan = new Position(11, 6);
        Position posFourToFarForCaravan = new Position(12, 6);
        game.endOfTurn(); //make BLUEs turn
        assertThat(game.moveUnit(posOneBlueCaravan, posTwoBlueCaravan), is(true));
        assertThat(game.moveUnit(posTwoBlueCaravan, posThreeBlueCaravan), is(true));
        assertThat(game.moveUnit(posThreeBlueCaravan, posFourToFarForCaravan), is(false));
    }
    
    
    @Test
    public void produceACaravanOnTileEightTwelve() {
        Position posCityWhichWillProduceCaraven = new Position(8,12);
        assertNull(game.getUnitAt(posCityWhichWillProduceCaraven));
        game.changeProductionInCityAt(posCityWhichWillProduceCaraven, GameConstants.CARAVAN);
        doProvidedNumberOfNewRounds(GameConstants.price.get(GameConstants.CARAVAN)/6+1);
        assertThat(game.getUnitAt(posCityWhichWillProduceCaraven).getTypeString(), is(GameConstants.CARAVAN));
    }
    
    @Test
    public void theUnitActionOnACaravanInACityMakesTheUnitDisappearAndCityPopulationIncreaseByTwo() {
        Position posCityWhichWillProduceCaraven = new Position(8,12);
        game.changeProductionInCityAt(posCityWhichWillProduceCaraven, GameConstants.CARAVAN);
        doProvidedNumberOfNewRounds(GameConstants.price.get(GameConstants.CARAVAN)/6+1);
        game.performUnitActionAt(posCityWhichWillProduceCaraven);
        assertThat(game.getCityAt(posCityWhichWillProduceCaraven).getPopulationSize(), is(3));
    }
    
    @Test
    public void correctWorldLayout() {
        Position posMoutain = new Position(0, 5);
        Position posHill = new Position(4, 8);
        Position posForest = new Position(5, 2);
        Position posDesert = new Position(6, 4);
        Position posOcean = new Position(0, 0);
        Position posPlain = new Position(2, 2);
        assertThat(game.getTileAt(posMoutain).getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat(game.getTileAt(posHill).getTypeString(), is(GameConstants.HILLS));
        assertThat(game.getTileAt(posForest).getTypeString(), is(GameConstants.FOREST));
        assertThat(game.getTileAt(posDesert).getTypeString(), is(GameConstants.DESERT));
        assertThat(game.getTileAt(posOcean).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(posPlain).getTypeString(), is(GameConstants.PLAINS));
    }
    
    @Test
    public void correctUnitAndCityPlacement() {
        Position posRedArcher = new Position(3,8);
        Unit redArcher = game.getUnitAt(posRedArcher);
        assertThat(redArcher.getTypeString(), is(GameConstants.ARCHER));
        assertThat(redArcher.getOwner(), is(Player.RED));
    
        Position posBlueLegion = new Position(4,4);
        Unit blueLegion = game.getUnitAt(posBlueLegion);
        assertThat(blueLegion.getTypeString(), is(GameConstants.LEGION));
        assertThat(blueLegion.getOwner(), is(Player.BLUE));
    
        Position posRedSettler = new Position(5,5);
        Unit redSettler = game.getUnitAt(posRedSettler);
        assertThat(redSettler.getTypeString(), is(GameConstants.SETTLER));
        assertThat(redSettler.getOwner(), is(Player.RED));
    
        Position posBlueCaravan = new Position(9,6);
        Unit blueCaravan = game.getUnitAt(posBlueCaravan);
        assertThat(blueCaravan.getTypeString(), is(GameConstants.CARAVAN));
        assertThat(blueCaravan.getOwner(), is(Player.BLUE));
    
        Position posBlueCity = new Position(4,5);
        City blueCity = game.getCityAt(posBlueCity);
        assertThat(blueCity.getOwner(), is(Player.BLUE));
    
        Position posRedCity = new Position(8,12);
        City redCity = game.getCityAt(posRedCity);
        assertThat(redCity.getOwner(), is(Player.RED));
    }
    
    private void doProvidedNumberOfNewRounds(int numberOfNewRounds) {
        for(int i = 0; i < numberOfNewRounds; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void onlyCaravanCanMoveOntoDesert() {
        Position posBlueCaravan = new Position(9,6);
        Position posDesert = new Position(9,5);
        game.endOfTurn(); //Make it blue turn
        assertThat(game.moveUnit(posBlueCaravan, posDesert), is(true));
    }

    @Test
    public void anArcherCannotMoveOntoDesertTile() {
        Position posRedSettler = new Position(5,5);
        Position posDesert = new Position(6,5);
        assertThat(game.moveUnit(posRedSettler, posDesert), is(false));
    }
 
}
