package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Position;
import hotciv.framework.enumsAndConstants.Player;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;

public class GameObserverSpy implements GameObserver {
    public ArrayList<Position> tileFocusChangedAtPositions = new ArrayList<>();
    public ArrayList<Position> worldChangedAtPositions = new ArrayList<>();
    public ArrayList<Player> endOfTurnNotificationOfPlayers = new ArrayList<>();
    public ArrayList<Integer> endOfTurnNotificationOfAges = new ArrayList<>();

    @Override
    public void worldChangedAt(Position pos) {
        worldChangedAtPositions.add(pos);
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        endOfTurnNotificationOfPlayers.add(nextPlayer);
        endOfTurnNotificationOfAges.add(age);
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        tileFocusChangedAtPositions.add(position);
    }
}
