package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.factories.UnitFactory;

import java.util.ArrayList;

public class Transcript implements Game {
    private final Game game;

    public Transcript(Game game) {
        this.game = game;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        System.out.println("--->" + "unit moves from" + from + "to this position" + to + "<---" );
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        System.out.println("--->" + getPlayerInTurn() + " end of turn <---");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        //not implemented yet.
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override

    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println("unit changes production in city at" + p + unitType );
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        System.out.println("perform the action associated with the unit at position p" + p);
        game.performUnitActionAt(p);
    }

    @Override
    public Position getMaxRowMaxColPosition() {
        return game.getMaxRowMaxColPosition();
    }

    @Override
    public int getNoOfEndedRounds() {
        return game.getNoOfEndedRounds();
    }

    @Override
    public ArrayList<Player> getListOfBattlesWon() {
        return game.getListOfBattlesWon();
    }

    @Override
    public UnitFactory getUnitFactory() {
        return game.getUnitFactory();
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
