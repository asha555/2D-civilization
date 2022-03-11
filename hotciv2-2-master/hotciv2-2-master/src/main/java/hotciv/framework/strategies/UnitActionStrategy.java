package hotciv.framework.strategies;

import hotciv.framework.ModifiableGame;
import hotciv.framework.Position;

public interface UnitActionStrategy {
    void performUnitActionAt(Position p, ModifiableGame game);
}
