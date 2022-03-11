package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.EpsilonCivBattleStrategy;
import hotciv.standard.winningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.RandomNextIntState;
import hotciv.standard.mapStrategies.AlphaCivMapStrategy;
import hotciv.standard.unitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.worldAgingStrategies.AlphaCivWorldAgingStrategy;

public class EpsilonCivFactory extends AlphaCivFactory {
    @Override
    public BattleStrategy createBattleStrategy() {
        return new EpsilonCivBattleStrategy(new RandomNextIntState());
    }
    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonCivWinningStrategy();
    }
}
