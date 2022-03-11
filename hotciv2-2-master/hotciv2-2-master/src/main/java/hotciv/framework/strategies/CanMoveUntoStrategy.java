package hotciv.framework.strategies;

public interface CanMoveUntoStrategy {
    /** Returns whether the provided unitType is allowed to stand on the provided tileType.
     * @param unitType The unitType about which you want the information.
     * @param tileType The tileType about which you want the information.
     * @return true if the unit is allowed to stand on the provided tileType; otherwise false.
     */
    boolean canMoveOnto(String unitType, String tileType);
}
