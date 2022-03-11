package hotciv.standard.worldAgingStrategies;

import hotciv.framework.strategies.WorldAgingStrategy;

public class BetaCivWorldAgingStrategy implements WorldAgingStrategy {
    /**
     * A call to this method age the stored age according to the following algorithm.
     *  Between 4000BC and 100BC                 100 years pass per round.
     *  Around birth of Christ the sequence is   -100, -1, +1, +50.
     *  Between 50AD and 1750                    50 years pass per round.
     *  Between 1750 and 1900                    25 years pass per round.
     *  Between 1900 and 1970                    5 years per round.
     *  After 1970                               1 year per round.
     * Precondition: The method may only be called when the age needs to be advanced, as the method itself don't know when it is the correct time to advance the age.
     */
    @Override
    public int advanceAge(int age){
        int newAge = 0;
        if(age < -100)
            newAge = age + 100;
        else if(age < 50) // Age is [-100, 50) so we use the algorithm for around the birth of Christ.
            switch(age) {
                case -100:  newAge = -1; break;
                case -1:    newAge = 1; break;
                case 1:     newAge = 50; break;
            }
        else if(age < 1750)
            newAge = age+ 50;
        else if(age < 1900)
            newAge = age + 25;
        else if(age < 1970)
            newAge = age + 5;
        else
            newAge = age + 1;
        return newAge;
    }
}

