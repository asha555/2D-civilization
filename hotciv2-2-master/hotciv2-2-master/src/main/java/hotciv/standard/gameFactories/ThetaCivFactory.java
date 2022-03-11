package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.canMoveUntoStrategies.AlphaCivCanMoveUntoStrategy;
import hotciv.standard.canMoveUntoStrategies.ThetaCivCanMoveUntoStrategy;
import hotciv.standard.unitActionStrategies.ThetaCivUnitActionStrategy;
import hotciv.standard.unitFactories.AlphaCivUnitFactory;
import hotciv.standard.unitFactories.ThetaCivUnitFactory;
import hotciv.standard.mapStrategies.ThetaMapStrategy;

public class ThetaCivFactory extends GammaCivFactory {
    @Override
    public InitialMapStrategy createInitialMapStrategy() {
        return new ThetaMapStrategy();
    }
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new ThetaCivUnitActionStrategy();
    }
    @Override
    public UnitFactory createUnitFactory() {
        return new ThetaCivUnitFactory(new AlphaCivUnitFactory());
    }
    @Override
    public CanMoveUntoStrategy createCanMoveUntoStrategy() {
        return new ThetaCivCanMoveUntoStrategy(new AlphaCivCanMoveUntoStrategy());
    }
}
