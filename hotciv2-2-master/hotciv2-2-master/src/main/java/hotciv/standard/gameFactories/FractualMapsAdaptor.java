package hotciv.standard.gameFactories;

import hotciv.framework.ModifiableCity;
import hotciv.framework.ModifiableUnit;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.strategies.InitialMapStrategy;


import java.util.HashMap;

import hotciv.standard.IntialCitiesStrategy.InitialCitiesStrategy;
import hotciv.standard.IntialCitiesStrategy.InitialUnitesStrategy;
import hotciv.standard.MapUtilities;
import thirdparty.ThirdPartyFractalGenerator;

public class FractualMapsAdaptor implements InitialMapStrategy {

    private ThirdPartyFractalGenerator generator;
    private InitialCitiesStrategy initialCities;
    private InitialUnitesStrategy initialUnits;


    public FractualMapsAdaptor(InitialCitiesStrategy initialCities, InitialUnitesStrategy initialUnits){
       this.generator= new ThirdPartyFractalGenerator();
        this.initialCities = initialCities;
        this.initialUnits = initialUnits;
    }

    @Override
    public HashMap<Position, Tile> getInitialMap(int noOfRows, int noOfCols) {
        String[] layout = new String[noOfRows];
        for(int row=0 ; row < GameConstants.WORLDSIZE; row++) {
            StringBuffer stringBuffer = new StringBuffer();
            for(int col = 0; col < GameConstants.WORLDSIZE; col++) {
                stringBuffer.append(generator.getLandscapeAt(row, col));
            }
            layout[row] = stringBuffer.toString();
        }
        return MapUtilities.mapStringToHAshMapConversion(layout);
    }


    @Override
    public HashMap<Position, ModifiableCity> getInitialCities(int noOfRows, int noOfCols) {
        return initialCities.getInitialCities(GameConstants.WORLDSIZE, GameConstants.WORLDSIZE);
    }

    @Override
    public HashMap<Position, ModifiableUnit> getInitialUnits(int noOfRows, int noOfCols) {
         return initialUnits.getInitialUnits(GameConstants.WORLDSIZE, GameConstants.WORLDSIZE);
    }
}
