package hotciv.framework.enumsAndConstants;

import java.util.HashMap;

/** Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.
 */
public class GameConstants {
  // The size of the world is set permanently to a 16x16 grid 
  public static final int WORLDSIZE = 16;
  
  // Valid unit types
  public static final String NOUNIT = "no unit";
  public static final String ARCHER = "archer";
  public static final String LEGION = "legion";
  public static final String SETTLER = "settler";
  public static final String CARAVAN = "caravan";
  
  // The constants for the various unit types
  public static final HashMap<String, Integer> attackStrengths = new HashMap<>(){
    {
      put(ARCHER, 2);
      put(LEGION, 4);
      put(SETTLER, 0);
      put(CARAVAN, 1);
    }
  };
  
  public static final HashMap<String, Integer> defenceStrengths = new HashMap<>(){
    {
      put(ARCHER, 3);
      put(LEGION, 2);
      put(SETTLER, 3);
      put(CARAVAN, 4);
    }
  };
  
  public static final HashMap<String, Integer> price = new HashMap<>(){
    {
      put(ARCHER, 10);
      put(LEGION, 15);
      put(SETTLER, 30);
      put(CARAVAN, 30);
    }
  };
  
  public static final HashMap<String, Integer> maxTravelDistane = new HashMap<>(){
    {
      put(ARCHER, 1);
      put(LEGION, 1);
      put(SETTLER, 1);
      put(CARAVAN, 2);
    }
  };
  // Valid terrain types
  public static final String NOTILE = "no tile";
  public static final String PLAINS = "plains";
  public static final String OCEANS = "ocean";
  public static final String FOREST = "forest";
  public static final String HILLS = "hills";
  public static final String MOUNTAINS = "mountain";
  public static final String DESERT = "desert";
  
  // Valid production balance types
  public static final String productionFocus = "hammer";
  public static final String foodFocus = "apple";


 
}