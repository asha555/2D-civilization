package hotciv.framework;

import hotciv.framework.enumsAndConstants.Player;

import java.util.function.BiConsumer;

/** ModifiableGame is the interface allowing a client to access and
 * modify the map, cities and units of a HotCiv game.
*/

public interface ModifiableGame extends Game{
  void setTileAt(Position p, Tile newTile);
  
  void setUnitAt(Position p, ModifiableUnit unit);
  void removeUnitAt(Position p);
  void forAllUnitsDo(BiConsumer<? super Position, ? super Unit> procedure);
  
  void setCityAt(Position p, ModifiableCity city);
  void forAllCitiesDo(BiConsumer<? super Position, ? super City> procedure);
  void changeCityOwnerAt(Position p, Player newOwner);
}
