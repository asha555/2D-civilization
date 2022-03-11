package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.canMoveUntoStrategies.AlphaCivCanMoveUntoStrategy;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.mapStrategies.AlphaCivMapStrategy;
import hotciv.standard.battleStrategies.AlphaCivBattleStrategy;
import hotciv.standard.unitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.winningStrategies.AlphaCivWinningStrategy;
import hotciv.standard.worldAgingStrategies.AlphaCivWorldAgingStrategy;

public class AlphaCivFactory implements GameFactory {
    @Override
    public WorldAgingStrategy createAgeStrategy() {
        return new AlphaCivWorldAgingStrategy();
    }
    @Override
    public BattleStrategy createBattleStrategy() {
        return new AlphaCivBattleStrategy();
    }
    @Override
    public InitialMapStrategy createInitialMapStrategy() {
        return new AlphaCivMapStrategy();
    }
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaCivUnitActionStrategy();
    }
    @Override
    public WinningStrategy createWinningStrategy() {
        return new AlphaCivWinningStrategy();
    }
    @Override
    public UnitFactory createUnitFactory() {
        return new AlphaCivUnitFactory();
    }
    @Override
    public CanMoveUntoStrategy createCanMoveUntoStrategy() {
        return new AlphaCivCanMoveUntoStrategy();
    }
}
