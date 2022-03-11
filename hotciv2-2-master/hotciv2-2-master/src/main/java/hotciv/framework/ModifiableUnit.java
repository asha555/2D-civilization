package hotciv.framework;

import hotciv.framework.enumsAndConstants.FortifyState;

public interface ModifiableUnit extends Unit {
    
    /** increment the count of how many moves this unit has performed */
    void incrementMoveCount();
    
    /** reestablish the count of how many moves
     *  this unit has performed, so the unit
     *  is now able to move again
     */
    void reestablishMoveCount();
    
    /** Makes the unit fortify or unfortify depending on the param newState.
     * @param newState of this units fortify state.
     */
    void setFortifyState(FortifyState newState);
    
    /** Changes the defensive strength of this unit to the value of the param newDefensiveStrength.
     * @param newDefensiveStrength The new defensive strength for this unit.
     */
    void setDefensiveStrength(int newDefensiveStrength);
}
