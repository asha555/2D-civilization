package hotciv.standard.unitFactories;

import hotciv.framework.ModifiableUnit;
import hotciv.framework.enumsAndConstants.*;
import hotciv.framework.factories.UnitFactory;
import hotciv.standard.UnitImpl;

public class AlphaCivUnitFactory implements UnitFactory {
    @Override
    public boolean isValidUnittype(String unitType) {
        switch (unitType) {
            case GameConstants.ARCHER:;
            case GameConstants.LEGION:;
            case GameConstants.SETTLER:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public Integer priceOf(String unitType) {
        if(isValidUnittype(unitType)) {
            return GameConstants.price.get(unitType);
        } else {
            return null;
        }
        
    }
    
    @Override
    public ModifiableUnit createUnit(Player owner, String unitType) {
        if(isValidUnittype(unitType)) {
            return new UnitImpl(owner, unitType);
        } else {
            return null;
        }
        
    }
}
