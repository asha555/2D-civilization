package hotciv.standard.unitFactories;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.*;
import hotciv.framework.factories.UnitFactory;
import hotciv.standard.UnitImpl;

public class ThetaCivUnitFactory implements UnitFactory {
    private final AlphaCivUnitFactory alphaCivUnitFactory;
    
    public ThetaCivUnitFactory(AlphaCivUnitFactory alphaCivUnitFactory) {
        this.alphaCivUnitFactory = alphaCivUnitFactory;
    }
    
    @Override
    public boolean isValidUnittype(String unitType) {
        switch (unitType) {
            case GameConstants.CARAVAN: return true;
            default: return alphaCivUnitFactory.isValidUnittype(unitType);
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
