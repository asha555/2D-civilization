package hotciv.standard;
import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.framework.factories.GameFactory;
import hotciv.framework.factories.UnitFactory;
import hotciv.framework.strategies.*;
import hotciv.standard.gameFactories.SemiCivFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/** Skeleton implementation of HotCiv.
*/

public class GameImpl implements ModifiableGame {
  private HashMap<Position, Tile> map; //A Hashmap to hold the game map
  private HashMap<Position, ModifiableUnit> units; //A Hashmap to hold each unit at each given position
  private HashMap<Position, ModifiableCity> cities; //A Hashmap to hold each city at each given position
  private int noOfCols = 16;//16 cols is specified by the specification
  private int noOfRows = 16;//16 rows is specified by the specification
  private int age = -4000;//-4000 == 4000 BC, as specified by the specification
  private Player firstPlayer = Player.RED;//specified by the specification
  private Player currentPlayer;//Keeps track of the current player
  private Player winner = Player.NONE;
  private int maxMovesPrUnitPrTurn = 1;
  private InitialMapStrategy initialMapStrategy ;
  private WinningStrategy winningStrategy;
  private WorldAgingStrategy worldAgingStrategy;
  private UnitActionStrategy unitActionStrategy;
  private BattleStrategy battleStrategy;
  private CanMoveUntoStrategy canMoveUntoStrategy;
  private UnitFactory unitFactory;
  private ArrayList<Player> battlesWon = new ArrayList<>();
  private int endedRounds = 0;
  private SemiCivFactory combinationStrategy;
  private  ArrayList<GameObserver> observers = new ArrayList<>();

  GameImpl(GameFactory gameFactory) {
    //Initialize strategies
    this.worldAgingStrategy = gameFactory.createAgeStrategy();
    this.winningStrategy = gameFactory.createWinningStrategy();
    this.unitActionStrategy = gameFactory.createUnitActionStrategy();
    this.initialMapStrategy = gameFactory.createInitialMapStrategy();
    this.battleStrategy = gameFactory.createBattleStrategy();
    this.canMoveUntoStrategy = gameFactory.createCanMoveUntoStrategy();
    
    //Initialize unit factory
    this.unitFactory = gameFactory.createUnitFactory();
    
    //First player
    this.currentPlayer = Player.RED;

    //Initialise Map
    this.map = initialMapStrategy.getInitialMap(noOfRows, noOfCols);
    this.cities = initialMapStrategy.getInitialCities(noOfRows, noOfCols);
    this.units = initialMapStrategy.getInitialUnits(noOfRows, noOfCols);

  }
  
  public Tile getTileAt(Position p) {
    return map.get(p);
  }
  public void setTileAt(Position p, Tile newTile) {
    map.replace(p,newTile);
  }
  
  public Unit getUnitAt(Position p) {
    return units.get(p);
  }
  public void setUnitAt(Position p, ModifiableUnit unit) {
    units.put(p, unit);
    setWorldChanged(p);
  }
  public void removeUnitAt(Position p) {
    units.remove(p);
    setWorldChanged(p);
  }
  public void forAllUnitsDo(BiConsumer<? super Position, ? super Unit> procedure) {
    units.forEach(procedure);
  }
  
  public City getCityAt(Position p) {
    return cities.get(p);
  }
  public void setCityAt(Position p, ModifiableCity city) {
    cities.put(p, city);
    setWorldChanged(p);
  }
  public void forAllCitiesDo(BiConsumer<? super Position, ? super City> procedure) {
    cities.forEach(procedure);
  }
  public void changeCityOwnerAt(Position p, Player newOwner) {
    cities.get(p).setOwner(newOwner);
    setWorldChanged(p);
  }
  
  public Player getPlayerInTurn() {
    return currentPlayer;
  }

  public Player getWinner() {
    return winner;
  }
  
  public boolean moveUnit(Position from, Position to) {
    if (!areRequirementsForMoveUnitMeet(from, to)) return false;
    
    boolean toPositionContainsOpponent = getUnitAt(to) instanceof UnitImpl;
    if (toPositionContainsOpponent) { //Attack
        if(battleStrategy.wasAttackSuccessful(from, to, this)) {
          doMoveUnit(from, to);
          incrementBattlesWonBy(currentPlayer);
          boolean toPositionContainsCity = getCityAt(to) != null;
          if(toPositionContainsCity)
            changeCityOwnerAt(to ,currentPlayer); // attack becomes owner of the city
        } else {
          removeUnitAt(from); //defending city won so remove unit from the attacks from position?
        }
    } else {
      doMoveUnit(from, to);
      boolean toPositionContainsCity = getCityAt(to) != null;
      if(toPositionContainsCity)
        changeCityOwnerAt(to, currentPlayer);
    }
    return true;
  }
  
  private void incrementBattlesWonBy(Player p) {
      battlesWon.add(p);
  }
  
  public ArrayList<Player> getListOfBattlesWon() {
      return (ArrayList<Player>) battlesWon.clone();
  }
  
  @Override
  public UnitFactory getUnitFactory() {
    return unitFactory;
  }

  @Override
  public void addObserver(GameObserver observer) {
    observers.add(observer);
  }

  @Override
  public void setTileFocus(Position position){
    observers.forEach((observer)->observer.tileFocusChangedAt(position));
  }

  public void setWorldChanged(Position position) {
    observers.forEach((observer)->observer.worldChangedAt(position));
  }

  private void doMoveUnit(Position from, Position to) {
    ModifiableUnit unit = (ModifiableUnit) getUnitAt(from);
    removeUnitAt(from);
    setUnitAt(to, unit);
    ((UnitImpl) unit).incrementMoveCount();
  }
  
  private boolean areRequirementsForMoveUnitMeet(Position from, Position to) {
    Unit unitAtFromPos = getUnitAt(from);
    Unit unitAtToPos = getUnitAt(to);
    
    boolean isOwnerPLayerInTurn = unitAtFromPos.getOwner().equals(getPlayerInTurn());
    if (!isOwnerPLayerInTurn) return false;

    boolean hasMovesLeft = unitAtFromPos.getMoveCount() < unitAtFromPos.getMaxTravelDistance();
    if (!hasMovesLeft) return false;

    boolean areTilesAdjacent = isAdjacentTiles(from, to);
    if (!areTilesAdjacent) return false;
    
    if(unitAtToPos != null) {
      boolean notCurentPlayerUnitAtToPosition = !(unitAtToPos.getOwner().equals(getPlayerInTurn()));
      if(!notCurentPlayerUnitAtToPosition) return false;
    }
    
    boolean canUnitNotMoveUntoTile = !canMoveUntoStrategy.canMoveOnto(unitAtFromPos.getTypeString(), getTileAt(to).getTypeString());
    if(canUnitNotMoveUntoTile) return false;
    
    return true;
  }



  private boolean isAdjacentTiles(Position from, Position to) {
    return from.getColumn()-to.getColumn() <= 1 &&
            from.getColumn()-to.getColumn() >= -1 &&
            from.getRow()-to.getRow() <= 1 &&
            from.getRow()-to.getRow() >= -1;
  }

  public void endOfTurn() {
    currentPlayer = nextPlayer();
    if(winner.equals(Player.NONE)) // don't put winner if we already have one.
      winner = winningStrategy.findWinner(this);
    if(endOfRound()) {
      endedRounds++;
      advanceAge();
      reestablishMoveCounts();
      increaseCitiesTreasury();
      doProductionForAllCities();
    }
    observers.forEach((observer) -> observer.turnEnds(currentPlayer, age));
  }

  private void doProductionForAllCities() {
    forAllCitiesDo((position, city) -> {
      int treasury = city.getTreasury();
      String unitType = city.getProduction();
      int unitPrice = unitFactory.priceOf(unitType);
      int noOfUnitsToProduce;
      int noOfProducedUnits;
      noOfUnitsToProduce = treasury / unitPrice;
      noOfProducedUnits = makeUnits(position, city.getOwner(), noOfUnitsToProduce, unitType);
      ((CityImpl) city).decreaseTreasury(noOfProducedUnits * unitPrice);
    });
  }

  
  private int makeUnits(Position position, Player owner, int noOfUnitsToProduce, String unitType) {
    int r = position.getRow();
    int c = position.getColumn();
    int producedUnits = 0;

    for(; noOfUnitsToProduce > producedUnits; producedUnits++) { //First
      if(placeUnitIfPossibleElseReturnFalse(new Position(r, c), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r, c-1), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r+1, c-1), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r+1, c), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r+1, c+1), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r, c+1), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r-1, c+1), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r-1, c), owner, unitType));
      else if(placeUnitIfPossibleElseReturnFalse(new Position(r-1, c-1), owner, unitType));
      else break;
    }
    return producedUnits;
  }
  
  private boolean placeUnitIfPossibleElseReturnFalse(Position p, Player owner, String unitType) {
    boolean isThereAUnitOnTile = getUnitAt(p) != null;
    if(isThereAUnitOnTile) return false;

    boolean canTileNotContainUnit = !canMoveUntoStrategy.canMoveOnto(unitType, getTileAt(p).getTypeString());
    if(canTileNotContainUnit) return false;
  
    ModifiableUnit unit = unitFactory.createUnit(owner, unitType);
    setUnitAt(p, unit);
    return true;
  }
  
  private void increaseCitiesTreasury() {
    forAllCitiesDo(((position, city) -> ((CityImpl) city).increaseTreasury()));
  }

  private void advanceAge() {
    age = worldAgingStrategy.advanceAge(age);
  }

  private void reestablishMoveCounts() {
    forAllUnitsDo(((position, unit) -> ((ModifiableUnit) unit).reestablishMoveCount()));
  }

  private boolean endOfRound() {
    return firstPlayer.equals(currentPlayer);
  }

  private Player nextPlayer() {
    if (Player.RED.equals(currentPlayer))
      return Player.BLUE;
    else
      return Player.RED;
  }

  public int getAge() {
    return age;
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

  public void changeProductionInCityAt( Position p, String unitType ) {
    City city = getCityAt(p);
    if(city != null && isValidUnitType(unitType)) {
      ((CityImpl) city).changeProductionTo(unitType);
    }
  }

  public void performUnitActionAt( Position p ) {
    unitActionStrategy.performUnitActionAt(p, this);
  }
  
  @Override
  public Position getMaxRowMaxColPosition() {
    return new Position(noOfRows-1, noOfCols-1);
  }
  
  public int getNoOfEndedRounds() {
    return endedRounds;
  }
  
  private boolean isValidUnitType(String unitType) {
    return unitFactory.isValidUnittype(unitType);
  }
}
