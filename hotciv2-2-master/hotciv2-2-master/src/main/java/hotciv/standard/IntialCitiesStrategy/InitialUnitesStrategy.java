package hotciv.standard.IntialCitiesStrategy;

import hotciv.framework.ModifiableUnit;
import hotciv.framework.Position;

import java.util.HashMap;

public interface InitialUnitesStrategy {
    HashMap<Position, ModifiableUnit> getInitialUnits(int noOfRows, int noOfCols);
}
