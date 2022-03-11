package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Tile;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Template for the EndOfTurn Tool exercise (FRS 36.42)...
 *
 * @author Henrik Bærbak Christensen, Aarhus University
 */
public class EndOfTurnTool extends NullTool {
  private final DrawingEditor editor;
  private final Game game;
  private NullTool state;
  private HotCivFigure figureBelowClickPoint;

  public EndOfTurnTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    super.mouseDown(e, x, y);
    game.endOfTurn();
    // TODO: Remove print statement, and implement end-of-turn behaviour
    System.out.println("TODO: EndOfTurn tool received 'mouse down' event");
    //find the fiqure if any just below mouse click position
     figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x,y);
    // Next determine the state of tool to use
     if(figureBelowClickPoint == null){
     // state = NullTool();
    } else {
       if (figureBelowClickPoint.getTypeString().equals(GfxConstants.TURN_SHIELD_TYPE_STRING))
         state = new EndOfTurnTool(editor, game);
     }
  }
  //delegate to the selected state
//state.mouseDown(e,x,y)
}