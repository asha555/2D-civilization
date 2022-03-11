package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.EpsilonCivBattleStrategy;
import hotciv.standard.RandomNextIntState;
import hotciv.standard.mapStrategies.DeltaCivMapStrategy;
import hotciv.standard.unitActionStrategies.GammaCivUnitActionStrategy;
import hotciv.standard.winningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.BetaCivWorldAgingStrategy;

public class SemiCivFactory extends AlphaCivFactory {
    @Override
    public WorldAgingStrategy createAgeStrategy() {
        return new BetaCivWorldAgingStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new EpsilonCivBattleStrategy(new RandomNextIntState());
    }

    @Override
    public InitialMapStrategy createInitialMapStrategy() {
        return new DeltaCivMapStrategy() ;
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaCivUnitActionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonCivWinningStrategy();
    }

    @Override
    public UnitFactory createUnitFactory() {
        return new AlphaCivUnitFactory();
    }
}
