package hotciv.framework.factories;

import hotciv.framework.ModifiableUnit;
import hotciv.framework.enumsAndConstants.Player;

public interface UnitFactory {
    boolean isValidUnittype(String unitType);
    Integer priceOf(String unitType);
    ModifiableUnit createUnit(Player owner, String unitType);
}
