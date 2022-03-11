package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.*;
import hotciv.framework.factories.UnitFactory;
import hotciv.standard.canMoveUntoStrategies.AlphaCivCanMoveUntoStrategy;
import hotciv.standard.gameFactories.AlternativeGameFactory;
import hotciv.standard.mapStrategies.PlainMapStrategy;
import hotciv.standard.unitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.winningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.AlphaCivWorldAgingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//hi
public class TestEpsilonCivBattleStrategy {
    private ModifiableGame game;
    private EpsilonCivBattleStrategy epsilonBattle;
    private PrescribedNextIntState nextIntState;
    private UnitFactory unitFactory = new AlphaCivUnitFactory();
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlternativeGameFactory( //We don't use the EpsilonFactory, as we want to specify another map strategy to increase the readability of the tests
                new AlphaCivWorldAgingStrategy(),
                new EpsilonCivWinningStrategy(),
                new AlphaCivUnitActionStrategy(),
                new PlainMapStrategy(),
                new EpsilonCivBattleStrategy(new RandomNextIntState()),
                new AlphaCivUnitFactory(),
                new AlphaCivCanMoveUntoStrategy()
        ));
        nextIntState = new PrescribedNextIntState();
        epsilonBattle = new EpsilonCivBattleStrategy(nextIntState);
    }
    
    @Test
    public void theCombinedAttackStrengthOfASingleBlueLegionWithNoFriendlyAdjacentUnitsAreCalculatedCorrect() {
        //Needed constants
        final Position blueLegionPos = new Position(0,0);
        final int adjacentFriendlyUnits = 0;
        //Prepare map
        game.setUnitAt(blueLegionPos, unitFactory.createUnit(Player.BLUE, GameConstants.LEGION));
        //Test
        assertThat(epsilonBattle.calcCombinedAttackStrength(game, blueLegionPos), is((GameConstants.attackStrengths.get(GameConstants.LEGION)+adjacentFriendlyUnits)*Utility.TerrainFactor.PLAINS));
    }
    
    @Test
    public void theCombinedAttackStrengthOfThreeRedAdjacentArchersAreCalculatedCorrect() {
        //Needed constants
        final Position redArcherOnePos = new Position(0,0);
        final Position redArcherTwoPos = new Position(0,1);
        final Position redArcherThreePos = new Position(0,2);
        final int adjacentFriendlyUnits = 2;
        //Prepare map
        game.setUnitAt(redArcherOnePos, unitFactory.createUnit(Player.RED, GameConstants.ARCHER));
        game.setUnitAt(redArcherTwoPos, unitFactory.createUnit(Player.RED, GameConstants.ARCHER));
        game.setUnitAt(redArcherThreePos, unitFactory.createUnit(Player.RED, GameConstants.ARCHER));
        //Test
        assertThat(epsilonBattle.calcCombinedAttackStrength(game, redArcherTwoPos), is((GameConstants.attackStrengths.get(GameConstants.ARCHER)+adjacentFriendlyUnits)*Utility.TerrainFactor.PLAINS));
    }
    
    @Test
    public void theCombinedDefenseStrengthOfASingleArcherWithNoFriendlyAdjacentUnitsAreCalculatedCorrect() {
        //Needed constants
        final Position redArcherPos = new Position(0,0);
        final int adjacentFriendlyUnits = 0;
        //Prepare map
        game.setUnitAt(redArcherPos, unitFactory.createUnit(Player.RED, GameConstants.ARCHER));
        //Test
        assertThat(epsilonBattle.calcCombinedDefendStrength(game, redArcherPos), is((GameConstants.defenceStrengths.get(GameConstants.ARCHER)+adjacentFriendlyUnits)*Utility.TerrainFactor.PLAINS));
    }

    @Test
    public void theCombinedDefenseStrengthOfABlueLegionWithOneAdjacentUnitIsCalculatedCorrect() {
        //Needed constants
        final Position blueLegionOnePos = new Position(0,0);
        final Position blueLegionTwoPos = new Position(0,1);
        final int adjacentFriendlyUnits = 1;
        //Prepare map
        game.setUnitAt(blueLegionOnePos, unitFactory.createUnit(Player.BLUE, GameConstants.LEGION));
        game.setUnitAt(blueLegionTwoPos, unitFactory.createUnit(Player.BLUE, GameConstants.LEGION));
        //Test
        game.endOfTurn();//blues turn
        assertThat(epsilonBattle.calcCombinedDefendStrength(game, blueLegionOnePos), is((GameConstants.defenceStrengths.get(GameConstants.LEGION)+adjacentFriendlyUnits)*Utility.TerrainFactor.PLAINS));
    }
    
    @Test
    public void theCombinedAttackStrengthOfASingleRedArcherIsCalculatedCorrectOnAHill() {
        //Needed constants
        final Position redArcherPos = new Position(0,0);
        final int adjacentFriendlyUnits = 0;
        //Prepare map
        game.setTileAt(redArcherPos, new TileImpl(GameConstants.HILLS));
        game.setUnitAt(redArcherPos, unitFactory.createUnit(Player.RED,GameConstants.ARCHER));
        //Test
        assertThat(epsilonBattle.calcCombinedAttackStrength(game, redArcherPos), is((GameConstants.attackStrengths.get(GameConstants.ARCHER)+adjacentFriendlyUnits)*Utility.TerrainFactor.HILLS));
    }
    
    @Test
    public void theCombinedDefenseStrengthOfASingleRedArcherIsCalculatedCorrectOnAHill() {
        //Needed constants
        final Position redArcherPos = new Position(0,0);
        final int adjacentFriendlyUnits = 0;
        //Prepare map
        game.setTileAt(redArcherPos, new TileImpl(GameConstants.HILLS));
        game.setUnitAt(redArcherPos, unitFactory.createUnit(Player.RED,GameConstants.ARCHER));
        //Test
        assertThat(epsilonBattle.calcCombinedDefendStrength(game, redArcherPos), is((GameConstants.defenceStrengths.get(GameConstants.ARCHER)+adjacentFriendlyUnits)*Utility.TerrainFactor.HILLS));
    }
    
    @Test
    public void theCombinedDefenseStrengthOfASingleRedArcherIsCalculatedCorrectInACity() {
        //Needed constants
        final Position redArcherPos = new Position(0,0);
        final int adjacentFriendlyUnits = 0;
        //Prepare map
        game.setCityAt(redArcherPos, new CityImpl(Player.RED));
        game.setUnitAt(redArcherPos, unitFactory.createUnit(Player.RED,GameConstants.ARCHER));
        //Test
        assertThat(epsilonBattle.calcCombinedDefendStrength(game, redArcherPos), is((GameConstants.defenceStrengths.get(GameConstants.ARCHER)+adjacentFriendlyUnits)*Utility.TerrainFactor.CITY));
    }
    
    @Test
    public void singleRedSettlerLoosesWhenAttackingBlueLegionOnPlain() {
        //Needed constants
        final Position redSettlerPos = new Position(0,0);
        final Position blueLegionPos = new Position(0,1);
        final ModifiableUnit redSettler = unitFactory.createUnit(Player.RED, GameConstants.SETTLER);
        final ModifiableUnit blueLegion = unitFactory.createUnit(Player.BLUE, GameConstants.LEGION);
        //Prepare map
        game.setUnitAt(redSettlerPos, redSettler);
        game.setUnitAt(blueLegionPos, blueLegion);
        //Test
        game.moveUnit(redSettlerPos, blueLegionPos);
        assertThat(game.getUnitAt(blueLegionPos), is(blueLegion));
        assertThat(game.getUnitAt(redSettlerPos) == null, is(true));
    }
    
    @Test
    public void redLegionWinsWhenAttackingBlueArcherOnHillIfD1Is5AndD2Is3() {
        //Constants
        Position redLegionPos = new Position(0,0);
        Position blueArcherPos = new Position(0,1);
        Position hillPos = blueArcherPos;
        int d1 = 5;
        int d2 = 3;
        //Prepare map
        game.setTileAt(hillPos, new TileImpl(GameConstants.HILLS));
        game.setUnitAt(redLegionPos, unitFactory.createUnit(Player.RED, GameConstants.LEGION));
        game.setUnitAt(blueArcherPos, unitFactory.createUnit(Player.BLUE, GameConstants.ARCHER));
        //Prepare nextIntState
        nextIntState.setPrescribedNextInts(new int[] {d1-1,d2-1});
        //Test
        assertThat(epsilonBattle.wasAttackSuccessful(redLegionPos, blueArcherPos, game), is(true));
    }
    
    @Test
    public void redLegionLosesWhenAttackingBlueArcherOnHillIfD1Is3AndD2Is1() {
        //Constants
        Position redLegionPos = new Position(0,0);
        Position blueArcherPos = new Position(0,1);
        Position hillPos = blueArcherPos;
        int d1 = 6;
        int d2 = 4;
        //Prepare map
        game.setTileAt(hillPos, new TileImpl(GameConstants.HILLS));
        game.setUnitAt(redLegionPos, unitFactory.createUnit(Player.RED, GameConstants.LEGION));
        game.setUnitAt(blueArcherPos, unitFactory.createUnit(Player.BLUE, GameConstants.ARCHER));
        //Prepare nextIntState
        nextIntState.setPrescribedNextInts(new int[] {d1-1,d2-1});
        //Test
        assertThat(epsilonBattle.wasAttackSuccessful(redLegionPos, blueArcherPos, game), is(false));
    }
    
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