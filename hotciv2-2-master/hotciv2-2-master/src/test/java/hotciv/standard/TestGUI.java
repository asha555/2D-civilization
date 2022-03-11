package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.standard.gameFactories.AlphaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGUI {
    private Game game;
    private GameObserverSpy spy;

    /** Fixture for alphaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());
        spy = new GameObserverSpy();
        game.addObserver(spy);
    }

    @Test
    public void spyOnObserversTileFocus(){
        Position position = new Position(0,0);

        game.setTileFocus(position);

        assertEquals(position, spy.tileFocusChangedAtPositions.get(0));
    }
    @Test
     public void aUnitAppears(){
        Position blueHasALegionUnit  = new Position(3,2);
        ModifiableUnit unit = new UnitImpl(Player.RED, GameConstants.LEGION);

        ((ModifiableGame) game).setUnitAt(blueHasALegionUnit, unit);

        assertEquals(blueHasALegionUnit, spy.worldChangedAtPositions.get(0));

    }

    @Test
    public void ChangeCityOwner(){
        Position cityPosition = new Position(1,1);
        Player oldCityOwner = Player.RED;
        Player newCityOwner = Player.BLUE;
        assertEquals(oldCityOwner, game.getCityAt(cityPosition).getOwner());
        ((ModifiableGame) game).changeCityOwnerAt(cityPosition, newCityOwner);
        assertEquals(cityPosition, spy.worldChangedAtPositions.get(0));
    }

    @Test
    public void aUnitIsRemoved(){
        Position blueHasALegionUnit  = new Position(3,2);
        ModifiableUnit unit = new UnitImpl(Player.RED, GameConstants.LEGION);

        ((ModifiableGame) game).setUnitAt(blueHasALegionUnit, unit);
        ((ModifiableGame) game).removeUnitAt(blueHasALegionUnit);

        assertEquals(blueHasALegionUnit, spy.worldChangedAtPositions.get(1));
    }

    @Test
    public void observersNotifiedWhenMovingUnit() {
        Position originalPosition = new Position(0,0);
        Position newPosition = new Position(0,1);
        ((ModifiableGame) game).setUnitAt(originalPosition, new UnitImpl(Player.RED, GameConstants.SETTLER));
        game.moveUnit(originalPosition, newPosition);
        assertEquals(originalPosition, spy.worldChangedAtPositions.get(1));
        assertEquals(newPosition, spy.worldChangedAtPositions.get(2));
    }
    @Test
    public void aCityAppears(){
        Position cityPosition = new Position(0,1);
        ((ModifiableGame) game).setCityAt(cityPosition, new CityImpl(Player.RED));
        assertEquals(spy.worldChangedAtPositions.get(0),cityPosition);
    }

    @Test
    public void endOfTurnNotifiesObserversCorrectly() {
        game.endOfTurn();
        assertEquals(Player.BLUE, spy.endOfTurnNotificationOfPlayers.get(0));
        assertEquals(-4000, spy.endOfTurnNotificationOfAges.get(0));
    }
}
