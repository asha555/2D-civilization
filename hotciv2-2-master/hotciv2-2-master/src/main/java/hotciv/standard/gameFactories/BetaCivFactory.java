package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.battleStrategies.AlphaCivBattleStrategy;
import hotciv.standard.mapStrategies.AlphaCivMapStrategy;
import hotciv.standard.unitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.winningStrategies.BetaCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.BetaCivWorldAgingStrategy;

public class BetaCivFactory extends AlphaCivFactory {
    @Override
    public WorldAgingStrategy createAgeStrategy() {
        return new BetaCivWorldAgingStrategy();
    }
    @Override
    public WinningStrategy createWinningStrategy() {
        return new BetaCivWinningStrategy();
    }
}
