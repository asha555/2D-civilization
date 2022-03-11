package hotciv.standard.unitActionStrategies;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.FortifyState;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.strategies.UnitActionStrategy;
import hotciv.standard.UnitImpl;
import hotciv.standard.CityImpl;

public class GammaCivUnitActionStrategy implements UnitActionStrategy {
    private int MultiplyDefenceStrength =  2;
    private int DivideDefenceStrength =  2;
    @Override
    public void performUnitActionAt(Position p, ModifiableGame game) {
        UnitImpl unit = (UnitImpl) game.getUnitAt(p);
        switch(unit.getTypeString()) { //Choose action based on unit type.
            case GameConstants.SETTLER:
                if(game.getCityAt(p) == null) {
                    game.setCityAt(p, new CityImpl(unit.getOwner()));
                    game.removeUnitAt(p);
                }
                break;
            case GameConstants.ARCHER:
                if(unit.getFortifiedState() == FortifyState.IsFortified) {
                    unit.setFortifyState(FortifyState.NotFortified);
                    unit.setDefensiveStrength(unit.getDefensiveStrength()/DivideDefenceStrength );
                    break;
                } else {
                    unit.setFortifyState(FortifyState.IsFortified);
                    unit.setDefensiveStrength(unit.getDefensiveStrength()*MultiplyDefenceStrength);
                    break;
                }
                
        }
    }
}
