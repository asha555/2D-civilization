package hotciv.standard.gameFactories;

import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;

public class AlternativeGameFactory implements GameFactory {
    private WorldAgingStrategy worldAgeStrategy;
    private WinningStrategy winningStrategy;
    private UnitActionStrategy unitActionStrategy;
    private InitialMapStrategy initialMapStrategy;
    private BattleStrategy battleStrategy;
    private UnitFactory unitFactory;
    private CanMoveUntoStrategy canMoveUntoStrategy;
    
    public AlternativeGameFactory(
        WorldAgingStrategy worldAgeStrategy,
        WinningStrategy winningStrategy,
        UnitActionStrategy unitActionStrategy,
        InitialMapStrategy initialMapStrategy,
        BattleStrategy battleStrategy,
        UnitFactory unitFactory,
        CanMoveUntoStrategy canMoveUntoStrategy) {
        this.worldAgeStrategy = worldAgeStrategy;
        this.winningStrategy = winningStrategy;
        this.unitActionStrategy = unitActionStrategy;
        this.initialMapStrategy = initialMapStrategy;
        this.battleStrategy = battleStrategy;
        this.unitFactory = unitFactory;
        this.canMoveUntoStrategy = canMoveUntoStrategy;
    }
    @Override
    public WorldAgingStrategy createAgeStrategy() {
        return worldAgeStrategy;
    }
    @Override
    public BattleStrategy createBattleStrategy() {
        return battleStrategy;
    }
    @Override
    public InitialMapStrategy createInitialMapStrategy() {
        return initialMapStrategy;
    }
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return unitActionStrategy;
    }
    @Override
    public WinningStrategy createWinningStrategy() {
        return winningStrategy;
    }
    @Override
    public UnitFactory createUnitFactory() {
        return unitFactory;
    }
    @Override
    public CanMoveUntoStrategy createCanMoveUntoStrategy() {
        return canMoveUntoStrategy;
    }
}
