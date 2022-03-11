package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.strategies.BattleStrategy;

import static hotciv.framework.Utility.*;

public class EpsilonCivBattleStrategy implements BattleStrategy {
    NextIntState nextIntState;
    public EpsilonCivBattleStrategy(NextIntState nextIntState) {
        this.nextIntState = nextIntState;
    }
    
    @Override
    public boolean wasAttackSuccessful(Position attackingUnitPosition, Position defendingUnitPosition, Game game) {
        int combinedAttackStrength = calcCombinedAttackStrength(game, attackingUnitPosition);
        int combinedDefendStrength = calcCombinedDefendStrength(game, defendingUnitPosition);
        int d1 = nextIntState.nextInt(6) + 1;
        int d2 = nextIntState.nextInt(6) + 1;
        return combinedAttackStrength * d1 > combinedDefendStrength * d2;
    }
    
    protected int calcCombinedAttackStrength(Game game, Position unitPos) {
        int res = 0;
        res += game.getUnitAt(unitPos).getAttackingStrength();
        res += getFriendlySupport(game, unitPos, game.getUnitAt(unitPos).getOwner());
        res *= getTerrainFactor(game, unitPos);
        return res;
    }
    
    protected int calcCombinedDefendStrength(Game game, Position unitPos) {
        int res = 0;
        res += game.getUnitAt(unitPos).getDefensiveStrength();
        res += getFriendlySupport(game, unitPos, game.getUnitAt(unitPos).getOwner());
        res *= getTerrainFactor(game, unitPos);
        return res;
    }
}
