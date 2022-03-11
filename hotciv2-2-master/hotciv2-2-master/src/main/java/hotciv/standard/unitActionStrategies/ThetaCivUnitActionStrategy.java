package hotciv.standard.unitActionStrategies;

import hotciv.framework.ModifiableCity;
import hotciv.framework.ModifiableGame;
import hotciv.framework.Position;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.standard.UnitImpl;

public class ThetaCivUnitActionStrategy extends GammaCivUnitActionStrategy {
    final int caravanCityPopulationIncrease = 2;
    @Override
    public void performUnitActionAt(Position p, ModifiableGame game) {
        UnitImpl unit = (UnitImpl) game.getUnitAt(p);
        switch(unit.getTypeString()) {
            case GameConstants.CARAVAN:
                ModifiableCity city = (ModifiableCity) game.getCityAt(p);
                if(city != null) {
                    city.increasePopulationSizeBy(caravanCityPopulationIncrease);
                    game.removeUnitAt(p);
                }
                break;
            default:
                super.performUnitActionAt(p, game);
                break;
        }
    }
}
