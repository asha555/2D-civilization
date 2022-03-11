package hotciv.standard.worldAgingStrategies;

import hotciv.framework.strategies.WorldAgingStrategy;

public class AlphaCivWorldAgingStrategy implements WorldAgingStrategy {
    @Override
    public int advanceAge(int age){
        return age + 100;
    }
}

