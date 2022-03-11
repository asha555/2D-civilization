package hotciv.standard.battleStrategies;
import hotciv.framework.*;
import hotciv.framework.strategies.BattleStrategy;

public class AlphaCivBattleStrategy implements BattleStrategy {
    @Override
    public boolean wasAttackSuccessful(Position attackingUnitPosition, Position defendingUnitPosition, Game game) {
        return true;
    }
}
