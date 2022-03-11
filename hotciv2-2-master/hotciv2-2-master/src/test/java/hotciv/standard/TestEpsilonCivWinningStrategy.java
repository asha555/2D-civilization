package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.standard.battleStrategies.AlphaCivBattleStrategy;
import hotciv.standard.canMoveUntoStrategies.AlphaCivCanMoveUntoStrategy;
import hotciv.standard.gameFactories.AlternativeGameFactory;
import hotciv.standard.mapStrategies.AlphaCivMapStrategy;
import hotciv.standard.unitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.winningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.AlphaCivWorldAgingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//hi
public class TestEpsilonCivWinningStrategy {
    private Game game;
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlternativeGameFactory(// We don't use the EpsilonFactory as we want the battle outcome to be easily predictable for more evident tests.
                new AlphaCivWorldAgingStrategy(),
                new EpsilonCivWinningStrategy(),
                new AlphaCivUnitActionStrategy(),
                new AlphaCivMapStrategy(),
                new AlphaCivBattleStrategy(),
                new AlphaCivUnitFactory(),
                new AlphaCivCanMoveUntoStrategy()
        ));
    }

    @Test
    public void blueWinsAfterThreeSuccessfulAttacks(){
        Position blueLegionInitialPos  = new Position(3,2);
        Position blueLegionPosTwoOne = new Position(2,1);
        Position redArcherPosTwoZero = new Position(2,0);

        game.endOfTurn(); //Make it blues turn
        game.moveUnit(blueLegionInitialPos, blueLegionPosTwoOne); //Move directly underneath reds city
        newRound();
        assertThat(game.moveUnit(blueLegionPosTwoOne, redArcherPosTwoZero), is(true)); //Kill red archer
        newRound();//Red city produce new archer inside the red city
        newRound();
        newRound();//Red city produce new archer at pos 'blueLegionPosTwoOne'
        assertThat(game.moveUnit(redArcherPosTwoZero, blueLegionPosTwoOne), is(true)); //Kill second red archer
        newRound();//Red city produce new archer at pos 'redArcherPosTwoZero'
        newRound();
        assertThat(game.moveUnit(blueLegionPosTwoOne, redArcherPosTwoZero), is(true)); //Kill third red archer
        game.endOfTurn();

        assertThat(game.getWinner(), is(Player.BLUE));
    }
    
    @Test
    public void ifRedWinsBlueCannotAlsoWinLater() {
        redKillsThreeBlueUnits();
        assertThat(game.getWinner(), is(Player.RED)); //Verify that winner is RED
    
        blueKillsThreeRedUnits();
        assertThat(game.getWinner(), is(Player.RED)); //Verify that winner is still RED
    }
    
    private void redKillsThreeBlueUnits() {
        //Positions used for the three kills
        Position redUnitPosOne = new Position(2,0);
        Position redUnitPosTwo = new Position(3,0);
        Position blueTargetOnePos = new Position(4,0);
        Position blueTargetTwoPos = new Position(5,0);
        Position blueTargetThreePos = new Position(4,0);
    
        //Red do three kills
        game.moveUnit(redUnitPosOne, redUnitPosTwo);
        doProvidedNumberOfNewRounds(5);//Wait for target units to be produced
        game.moveUnit(redUnitPosTwo, blueTargetOnePos);
        newRound();
        game.moveUnit(blueTargetOnePos, blueTargetTwoPos);
        newRound();
        game.moveUnit(blueTargetTwoPos, blueTargetThreePos);
        game.endOfTurn(); //blue turn
    }
    
    private void blueKillsThreeRedUnits() {
        //Positions used for the three kills
        Position blueUnitPosOne = new Position(3,2);
        Position redTargetOnePos = new Position(2,1);
        Position redTargetTwoPos = new Position(2,0);
        Position redTargetThreePos = new Position(1,1);
    
        //Blue do three kills
        game.moveUnit(blueUnitPosOne, redTargetOnePos);
        newRound();
        game.moveUnit(redTargetOnePos, redTargetTwoPos);
        newRound();
        game.moveUnit(redTargetTwoPos, redTargetThreePos);
        game.endOfTurn(); //end round to assure the find-winner check
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