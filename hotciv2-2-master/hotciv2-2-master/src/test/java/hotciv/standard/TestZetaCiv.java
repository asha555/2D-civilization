package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.standard.gameFactories.ZetaCivFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//BØH!
public class TestZetaCiv {
    private Game game;
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ZetaCivFactory());
    }

    @Test
    public void upTo20RoundsThereIsNoWinnerWhilstSomeCitiesHaveNotBeenConquered(){
        Position redCityPos = new Position(1,1);
        Position blueCityPos = new Position(4, 1);
        
        doProvidedNumberOfNewRounds(20);
        assertThat(game.getCityAt(redCityPos).getOwner(), is(Player.RED));
        assertThat(game.getCityAt(blueCityPos).getOwner(), is(Player.BLUE));
        assertThat(game.getWinner(), is(Player.NONE));
    }

    @Test
    public void upTo20RoundsRedWinsWhenConqueringAllTheCitiesInTheWorld() {
        Position redArcherInitialPos = new Position(2,0);
        Position redArcherPosTwo = new Position(3,1);
        Position blueCityPos = new Position(4,1);
        game.moveUnit(redArcherInitialPos, redArcherPosTwo);
        newRound();
        game.moveUnit(redArcherPosTwo, blueCityPos);
        game.endOfTurn();
        assertThat(game.getCityAt(blueCityPos).getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(Player.RED));
    }
    
    @Test
    public void after20RoundsBlueWinsAfterThreeSuccessfulAttacks() {
        Position blueLegionInitialPos  = new Position(3,2);
        Position blueLegionPosTwoOne = new Position(2,1);
        Position redArcherPosTwoZero = new Position(2,0);
    
        doProvidedNumberOfNewRounds(21);
        game.endOfTurn(); //Make it blues turn
        game.moveUnit(blueLegionInitialPos, blueLegionPosTwoOne); //Move directly underneath reds city
        newRound();
        assertThat(game.moveUnit(blueLegionPosTwoOne, redArcherPosTwoZero), is(true)); //Kill red archer
        newRound();//Red city produce new archer inside the red city
        newRound();
        newRound();//Red city produce new archer at pos 'blueLegionPosTwoOne'
        assertThat(game.moveUnit(redArcherPosTwoZero, blueLegionPosTwoOne), is(true)); //Kill second red archer
        newRound();//Red city produce new archer at pos 'redArcherPosTwoZero'
        //newRound();
        assertThat(game.moveUnit(blueLegionPosTwoOne, redArcherPosTwoZero), is(true)); //Kill third red archer
        game.endOfTurn();
    
        assertThat(game.getWinner(), is(Player.BLUE));
    }
    
    @Test
    public void after20RoundsThereIsStillNoWinnerEvenIfAPlayerHasThreeVictoriousBattlesIfSomeOfTheVictoriesAreFromBeforeThe21Round() {
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
        doProvidedNumberOfNewRounds(20);
        assertThat(game.moveUnit(blueLegionPosTwoOne, redArcherPosTwoZero), is(true)); //Kill third red archer
        game.endOfTurn();
        assertThat(game.getWinner(), is(Player.NONE));
    }
    
    @Test
    public void ifRedWinsDoingTheFirstWinningStrategyBlueCannotLaterWinDoingTheNextWinningStrategy() {
        //First Red fulfills the requirements to win under the first winning-strategy
        Position redArcherInitialPos = new Position(2,0);
        Position redArcherPosTwo = new Position(3,1);
        Position blueCityPos = new Position(4,1);
        game.moveUnit(redArcherInitialPos, redArcherPosTwo);
        newRound();
        game.moveUnit(redArcherPosTwo, blueCityPos);
        game.endOfTurn();
        assertThat(game.getCityAt(blueCityPos).getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(Player.RED));
        doProvidedNumberOfNewRounds(20);
        //Then Blue fulfills the requirements to win under the second winning-strategy
        Position blueLegionInitialPos  = new Position(3,2);
        Position blueLegionPosTwoOne = new Position(2,1);
        Position redArcherPosTwoZero = new Position(2,0);
        game.moveUnit(blueLegionInitialPos, blueLegionPosTwoOne); //Move directly underneath reds city
        newRound();
        assertThat(game.moveUnit(blueLegionPosTwoOne, redArcherPosTwoZero), is(true)); //Kill red archer
        newRound();//Red city produce new archer inside the red city
        newRound();
        newRound();//Red city produce new archer at pos 'blueLegionPosTwoOne'
        assertThat(game.moveUnit(redArcherPosTwoZero, blueLegionPosTwoOne), is(true)); //Kill second red archer
        newRound();//Red city produce new archer at pos 'redArcherPosTwoZero'
        //newRound();
        assertThat(game.moveUnit(blueLegionPosTwoOne, redArcherPosTwoZero), is(true)); //Kill third red archer
        game.endOfTurn();
        
        //The winner should remain RED, as Red was the first to archive the conditions.
        assertThat(game.getWinner(), is(Player.RED));
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