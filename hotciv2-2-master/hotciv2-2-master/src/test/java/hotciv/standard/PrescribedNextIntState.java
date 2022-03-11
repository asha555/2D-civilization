package hotciv.standard;

import hotciv.framework.NextIntState;

import java.util.Stack;

public class PrescribedNextIntState implements NextIntState {
    private Stack<Integer> intsInOrder;
    
    public PrescribedNextIntState() {
        intsInOrder = new Stack<>();
    }
    
    public void setPrescribedNextInts(int[] nextIntsInOrder) {
        intsInOrder = new Stack<>();
        for(int i = nextIntsInOrder.length-1; i >= 0; i--) {
            intsInOrder.push(nextIntsInOrder[i]);
        }
    }
    
    @Override
    public int nextInt(int lessThan) {
        Integer nextInt = intsInOrder.pop();
        if(nextInt == null) throw new RuntimeException("There are no more specified integers.");
        if(nextInt >= lessThan) throw new RuntimeException("The specified next int exceeds the specified limit. The int should have been less than: " + lessThan);
        return nextInt;
    }
}
