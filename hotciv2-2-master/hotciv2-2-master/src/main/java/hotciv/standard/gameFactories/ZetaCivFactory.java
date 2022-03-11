package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.winningStrategies.ZetaCivWinningStrategy;
import hotciv.standard.battleStrategies.AlphaCivBattleStrategy;
import hotciv.standard.mapStrategies.AlphaCivMapStrategy;
import hotciv.standard.unitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.winningStrategies.BetaCivWinningStrategy;
import hotciv.standard.winningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.AlphaCivWorldAgingStrategy;

public class ZetaCivFactory extends AlphaCivFactory {
    @Override
    public WinningStrategy createWinningStrategy() {
        return new ZetaCivWinningStrategy(new BetaCivWinningStrategy(), new EpsilonCivWinningStrategy());
    }
}
