package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.mapStrategies.DeltaCivMapStrategy;
import hotciv.standard.battleStrategies.AlphaCivBattleStrategy;
import hotciv.standard.unitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.winningStrategies.AlphaCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.AlphaCivWorldAgingStrategy;

public class DeltaCivFactory extends AlphaCivFactory {
    @Override
    public InitialMapStrategy createInitialMapStrategy() {
        return new DeltaCivMapStrategy();
    }
}
