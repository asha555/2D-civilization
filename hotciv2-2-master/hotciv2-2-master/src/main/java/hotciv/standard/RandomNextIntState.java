package hotciv.standard;

import hotciv.framework.NextIntState;

import java.util.Random;

public class RandomNextIntState implements NextIntState {
    Random random = new Random();
    
    @Override
    public int nextInt(int lessThan) {
        return random.nextInt(lessThan);
    }
}
