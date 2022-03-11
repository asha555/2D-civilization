package hotciv.framework.strategies;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface BattleStrategy {
    // responsible for finding out if the attack was sucessful or not
    boolean wasAttackSuccessful(Position attackingUnitPosition, Position defendingUnitPosition, Game game);
}
