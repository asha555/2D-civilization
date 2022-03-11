package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.battleStrategies.AlphaCivBattleStrategy;
import hotciv.standard.mapStrategies.AlphaCivMapStrategy;
import hotciv.standard.unitActionStrategies.GammaCivUnitActionStrategy;
import hotciv.standard.winningStrategies.AlphaCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.AlphaCivWorldAgingStrategy;

public class GammaCivFactory extends AlphaCivFactory {
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaCivUnitActionStrategy();
    }
}
