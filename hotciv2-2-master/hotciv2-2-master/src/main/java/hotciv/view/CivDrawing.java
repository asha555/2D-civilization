package hotciv.view;

import hotciv.framework.*;
import hotciv.framework.enumsAndConstants.GameConstants;
import hotciv.framework.enumsAndConstants.Player;
import hotciv.view.figure.CityFigure;
import hotciv.view.figure.HotCivFigure;
import hotciv.view.figure.TextFigure;
import hotciv.view.figure.UnitFigure;
import minidraw.framework.*;
import minidraw.standard.ImageFigure;
import minidraw.standard.StandardDrawing;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** CivDrawing is a specialized Drawing (MVC model component) from
 * MiniDraw that dynamically builds the list of Figures for MiniDraw
 * to render the Unit and other information objects that are visible
 * in the Game instance.
 *
 * //TTODO: This is a TEMPLATE for the SWEA Exercise! This means
 * that it is INCOMPLETE and that there are several options
 * for CLEANING UP THE CODE when you add features to it!
*/

public class CivDrawing 
  implements Drawing, GameObserver {
  // rename to something.
  protected Drawing drawing;
  /** store all moveable figures visible in this drawing = units */
  protected Map<Unit, UnitFigure> unitFigureMap;
  protected Map<City, CityFigure> cityFiqureMap;


  /** the Game instance that this CivDrawing is going to render units
   * from */
  protected Game game;


  public CivDrawing( DrawingEditor editor, Game game ) {
    super();
    this.drawing = new StandardDrawing();
    this.game = game;
    this.unitFigureMap = new HashMap<>();
    this.cityFiqureMap = new HashMap<>();

    // register this unit drawing as listener to any game state
    // changes...
    game.addObserver(this);
    // ... and build up the set of figures associated with
    // units in the game.
    defineUnitMap();
    // and the set of 'icons' in the status panel
    defineCityMap();

    defineIcons();
  }



  /** The CivDrawing should not allow client side
   * units to add and manipulate figures; only figures
   * that renders game objects are relevant, and these
   * should be handled by observer events from the game
   * instance. Thus this method is 'killed'.
   */
  public Figure add(Figure arg0) {
    throw new RuntimeException("Should not be used...");
  }


  /** erase the old list of units, and build a completely new
   * one from scratch by iterating over the game world and add
   * Figure instances for each unit in the world.
   */
  protected void defineUnitMap() {
    // ensure no units of the old list are accidental in
    // the selection!
    clearSelection();

    // remove all unit figures in this drawing
    removeAllUnitFigures();

    // iterate world, and create a unit figure for
    // each unit in the game world, as well as
    // create an association between the unit and
    // the unitFigure in 'unitFigureMap'.
    Position p;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        Unit unit = game.getUnitAt(p);
        if ( unit != null ) {
          String type = unit.getTypeString();
          // convert the unit's Position to (x,y) coordinates
          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                                   GfxConstants.getYFromRow(p.getRow()) );
          UnitFigure unitFigure =
            new UnitFigure( type, point, unit );
          unitFigure.addFigureChangeListener(this);
          unitFigureMap.put(unit, unitFigure);

          // also insert in delegate list as it is
          // this list that is iterated by the
          // graphics rendering algorithms
          drawing.add(unitFigure);
        }
      }
    }
  }



  /** remove all unit figures in this
   * drawing, and reset the map (unit->unitfigure).
   * It is important to actually remove the figures
   * as it forces a graphical redraw of the screen
   * where the unit figure was.
   */
  protected void removeAllUnitFigures() {
    for (Unit u : unitFigureMap.keySet()) {
      UnitFigure uf = unitFigureMap.get(u);
      drawing.remove(uf);
    }
    unitFigureMap.clear();
  }
//hi
  protected ImageFigure turnShieldIcon;
  protected ImageFigure ageView;
  protected ImageFigure UnitSheildIcon;
  protected ImageFigure cityTypeIcon;
  protected ImageFigure unitTypeIcon;
  protected ImageFigure CitySheildIcon;

  public void createPoistionsInMap() {
    Position p;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        p = new Position(r, c);
      }
    }
    }

    protected void defineIcons () {
      // TODO: Further development to include rest of figures needed
      // === Type strings for the various types of HotCivFigures used in HotCiv
      //unit type , city type , turnsheild type , unitsheild type
      // public static final String UNIT_TYPE_STRING = "UNIT_TYPE";
      CitySheildIcon = new HotCivFigure("city", new Point(GfxConstants.CITY_SHIELD_X,
              GfxConstants.CITY_SHIELD_Y), GfxConstants.CITY_TYPE_STRING);
      drawing.add(CitySheildIcon);

      cityTypeIcon = new HotCivFigure("city", new Point(GfxConstants.getXFromColumn(4), GfxConstants.getYFromRow(2)),
              GfxConstants.CITY_TYPE_STRING);

      //unitTypeIcon =  new HotCivFigure("hmm",new Point(GfxConstants.getXFromColumn()))
      //ageIcon = // dont  know exact noatation new ImageFigure(GfxConstants.NOTHING, new Point())
      //int age = game.getAge();
      //if (age < 0)
        //hmm text is supposed to be a string and bc is not a position?
        //ageView = new TextFigure(age , "BC",
        // new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_X));
        // drawing.add(ageView);


        turnShieldIcon =
                new HotCivFigure("redshield",
                        new Point(GfxConstants.TURN_SHIELD_X,
                                GfxConstants.TURN_SHIELD_Y),
                        GfxConstants.TURN_SHIELD_TYPE_STRING);
      updateTurnShield(game.getPlayerInTurn());
      // insert in delegate figure list to ensure graphical
      // rendering.
      drawing.add(turnShieldIcon);
    }

    // === Observer Methods ===

    public void worldChangedAt (Position pos){

      //System.out.println( "CivDrawing: world changes at "+pos);
      // this is a really brute-force algorithm: destroy
      // all known units and build up the entire set again
      defineUnitMap();
      defineCityMap();
    }

    private void defineCityMap () {
      // ensure no city of the old list are accidental in
      // the selection!
      clearSelection();

      // remove all city figures in this drawing
      removeAllUnitFigures();

      // iterate world, and create a city figure for
      // each city in the game world, as well as
      // create an association between the unit and
      // the cityFigure in 'cityFigureMap'.
      Position p;
      for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
        for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
          p = new Position(r,c);
          City city = game.getCityAt(p);
          if ( city != null ) {
            // convert the city's Position to (x,y) coordinates
            Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                    GfxConstants.getYFromRow(p.getRow()) );
            CityFigure cityFigure =
                    new CityFigure( city, point );
            cityFigure.addFigureChangeListener(this);
            cityFiqureMap.put(city, cityFigure);

            // also insert in delegate list as it is
            // this list that is iterated by the
            // graphics rendering algorithms
            drawing.add(cityFigure);

          }
        }
      }
    }

  protected TextFigure agefiqure;
  public void turnEnds(Player nextPlayer, int age) {
   /*
    System.out.println( "CivDrawing: turnEnds for "+
                        nextPlayer+" at "+age );
    updateTurnShield(nextPlayer);*/
    if(age > 0){
      agefiqure.setText( "BC"+ game.getAge()); //hmm the textFiqure has parameter string text and position SOOOOO
    }
    else{
      agefiqure.setText( "AC"+ game.getAge());
    }
    drawing.add(ageView);
  }

  private void updateTurnShield(Player nextPlayer) {
    String playername = "red";
    if ( nextPlayer == Player.BLUE ) { playername = "blue"; }
    turnShieldIcon.set( playername+"shield",
                        new Point( GfxConstants.TURN_SHIELD_X,
                                   GfxConstants.TURN_SHIELD_Y ) );
  }
  protected TextFigure  movesCountIcon;
  protected ImageFigure productionIcon;
  protected ImageFigure workForceIcon;


  public void tileFocusChangedAt(Position position) {
    // TODO: Implementation pending
    System.out.println( "Fake it: tileFocusChangedAt "+position );
    if (game.getUnitAt(position) != null) {
      theUnitTheFocusIsNowOn(position);
    }
    if (game.getCityAt(position) != null) {
      theCityTheFocusIsNowOn(position);
    }
    }



  private void theUnitTheFocusIsNowOn(Position position) {
    int moves = game.getUnitAt(position).getMoveCount();

      String owner = GfxConstants.RED_SHIELD;
      if (game.getUnitAt(position).getOwner().equals(GfxConstants.BLUE_SHIELD)) {
        owner = GfxConstants.RED_SHIELD;
      }
      movesCountIcon = new TextFigure(String.valueOf(moves), new Point(GfxConstants.UNIT_COUNT_X, GfxConstants.UNIT_COUNT_Y));
      drawing.add(movesCountIcon);
      //just moved the definition, not sure it needs to be here.
      UnitSheildIcon = new HotCivFigure("redArcher", new Point(GfxConstants.UNIT_SHIELD_X,
              GfxConstants.UNIT_SHIELD_Y), GfxConstants.UNIT_SHIELD_TYPE_STRING);
      drawing.add(UnitSheildIcon);
    }

  private void theCityTheFocusIsNowOn(Position position) {

      String owner = GfxConstants.RED_SHIELD;
      if(game.getCityAt(position).getOwner().equals(GfxConstants.BLUE_SHIELD)){
        owner = GfxConstants.BLUE_SHIELD;
      }
      String production = game.getCityAt(position).getProduction();
      if(game.getCityAt(position).getProduction() != null){
         production = game.getCityAt(position).getProduction();
      }
      productionIcon = new ImageFigure(production, new Point(GfxConstants.CITY_PRODUCTION_X,
              GfxConstants.CITY_PRODUCTION_Y));

    workForceIcon = new ImageFigure("apple", new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y));

  }



  @Override
  public void requestUpdate() {
    // A request has been issued to repaint
    // everything. We simply rebuild the
    // entire Drawing.
    defineUnitMap();
    defineIcons();
    defineCityMap();

  }

  @Override
  public void addToSelection(Figure arg0) {
    drawing.addToSelection(arg0);
  }

  @Override
  public void clearSelection() {
    drawing.clearSelection();
  }

  @Override
  public void removeFromSelection(Figure arg0) {
    drawing.removeFromSelection(arg0);
  }

  @Override
  public List<Figure> selection() {
    return drawing.selection();
  }

  @Override
  public void toggleSelection(Figure arg0) {
    drawing.toggleSelection(arg0);
  }

  @Override
  public void figureChanged(FigureChangeEvent arg0) {
    drawing.figureChanged(arg0);
  }

  @Override
  public void figureInvalidated(FigureChangeEvent arg0) {
    drawing.figureInvalidated(arg0);
  }

  @Override
  public void figureRemoved(FigureChangeEvent arg0) {
    drawing.figureRemoved(arg0);
  }

  @Override
  public void figureRequestRemove(FigureChangeEvent arg0) {
    drawing.figureRequestRemove(arg0);
  }

  @Override
  public void figureRequestUpdate(FigureChangeEvent arg0) {
    drawing.figureRequestUpdate(arg0);
  }

  @Override
  public void addDrawingChangeListener(DrawingChangeListener arg0) {
    drawing.addDrawingChangeListener(arg0);
  }

  @Override
  public void removeDrawingChangeListener(DrawingChangeListener arg0) {
    drawing.removeDrawingChangeListener(arg0);
  }

  @Override
  public Figure findFigure(int arg0, int arg1) {
    return drawing.findFigure(arg0, arg1);
  }

  @Override
  public Iterator<Figure> iterator() {
    return drawing.iterator();
  }

  @Override
  public void lock() {
    drawing.lock();
  }

  @Override
  public Figure remove(Figure arg0) {
    return drawing.remove(arg0);
  }

  @Override
  public void unlock() {
    drawing.unlock();
  }
}
