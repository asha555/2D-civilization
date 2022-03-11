package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.FortifyState;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;

public class UnitImpl implements ModifiableUnit {
    private Player owner;
    private String unitType;
    private int attackingStrength;
    private int defensiveStrength;
    private int maxTravelDistancePrTurn;
    
    private int moveCount;
    private FortifyState fortifyState = FortifyState.NotFortified;
    
    public UnitImpl(Player owner, String unitType) {
        this.owner = owner;
        this.unitType = unitType;
        this.attackingStrength = GameConstants.attackStrengths.get(unitType);
        this.defensiveStrength = GameConstants.defenceStrengths.get(unitType);
        this.maxTravelDistancePrTurn = GameConstants.maxTravelDistane.get(unitType);
        this.moveCount = 0;
    }

    @Override
    public String getTypeString() {
        return unitType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
    
    @Override
    public void incrementMoveCount() {
        moveCount++;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }
    
    @Override
    public void reestablishMoveCount() {
        switch(fortifyState) {
            case IsFortified: moveCount = Integer.MAX_VALUE; break;
            case NotFortified: moveCount = 0; break;
        }
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return attackingStrength;
    }

    @Override
    public int getMaxTravelDistance() {
        return maxTravelDistancePrTurn;
    }
    
    @Override
    public void setFortifyState(FortifyState newState) {
        fortifyState = newState;
        if(fortifyState == FortifyState.IsFortified) moveCount = Integer.MAX_VALUE;
    }
    
    @Override
    public FortifyState getFortifiedState() {
        return fortifyState;
    }

    @Override
    public void setDefensiveStrength(int newDefensiveStrength) {
        defensiveStrength = newDefensiveStrength;
    }
}
