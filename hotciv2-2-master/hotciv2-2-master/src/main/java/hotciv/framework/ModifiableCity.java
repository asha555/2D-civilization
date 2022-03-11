package hotciv.framework;

import hotciv.framework.enumsAndConstants.Player;

public interface ModifiableCity extends City {
    /**
     * change the owner of this city.
     */
    void setOwner(Player p);
    
    void changeProductionTo(String newProduction);
    
    void increaseTreasury();
    
    void decreaseTreasury(int amount);
    
    void increasePopulationSizeBy(int n);
}
