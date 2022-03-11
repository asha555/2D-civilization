package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.IntialCitiesStrategy.InitialCitiesStrategyImpl;
import hotciv.standard.IntialCitiesStrategy.InitialUnitsStrategy;

public class FractualMapsFactory implements GameFactory {
    @Override
    public WorldAgingStrategy createAgeStrategy() {
        return null;
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return null;
    }

    @Override
    public InitialMapStrategy createInitialMapStrategy() {
        return new FractualMapsAdaptor(new InitialCitiesStrategyImpl(), new InitialUnitsStrategy());
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return null;
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return null;
    }

    @Override
    public UnitFactory createUnitFactory() {
        return null;
    }

    @Override
    public CanMoveUntoStrategy createCanMoveUntoStrategy() {
        return null;
    }
}
