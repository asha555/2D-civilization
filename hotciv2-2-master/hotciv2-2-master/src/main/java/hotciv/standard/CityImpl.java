package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;

public class CityImpl implements ModifiableCity {
    private Player owner;
    private int size = 1;
    private String production = GameConstants.ARCHER;
    private int treasury;
    private int population;
    private int income = 6;
    
    public CityImpl(Player owner) {
        this.owner = owner;
        this.treasury = 0;
        this.population = 1;
    }


    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getPopulationSize() {
        return population;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }
    
    @Override
    public void changeProductionTo(String newProduction) {
        production = newProduction;
    }

    @Override
    public String getProduction() { return production; }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void increaseTreasury() {
        treasury += income;
    }
    
    @Override
    public void decreaseTreasury(int amount) {
        treasury -= amount;
    }
    
    @Override
    public void increasePopulationSizeBy(int n) {
        population += n;
    }
    
    @Override
    public void setOwner(Player newOwner) {
        this.owner = newOwner;
    }
}
