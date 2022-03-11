package hotciv.standard;
import hotciv.framework.*;

public class TileImpl implements Tile {
    private String type;

    public TileImpl(String tileType) {
        this.type = tileType;
    }

    @Override
    public String getTypeString() {
        return type;
    }
}
