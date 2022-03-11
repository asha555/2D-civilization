package hotciv.standard.IntialCitiesStrategy;

import hotciv.framework.ModifiableCity;
import hotciv.framework.Position;

import java.util.HashMap;

public interface InitialCitiesStrategy {
    HashMap<Position, ModifiableCity> getInitialCities(int noOfRows, int noOfCols);
}
