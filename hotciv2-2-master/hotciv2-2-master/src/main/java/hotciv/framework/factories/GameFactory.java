package hotciv.framework.factories;

import hotciv.framework.strategies.*;

public interface GameFactory {
    WorldAgingStrategy createAgeStrategy();
    BattleStrategy createBattleStrategy();
    InitialMapStrategy createInitialMapStrategy();
    UnitActionStrategy createUnitActionStrategy();
    WinningStrategy createWinningStrategy();
    UnitFactory createUnitFactory();
    CanMoveUntoStrategy createCanMoveUntoStrategy();
}
