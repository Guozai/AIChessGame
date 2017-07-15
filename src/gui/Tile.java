package gui;

import static gui.ChessGame.TILE_SIZE;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by yipin on 15/07/2017.
 */
public class Tile extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private boolean isTileBlack = false;

    public boolean getPreviousTileColor() {
        return isTileBlack;
    }

    public void setPreviousTileColor(boolean isTileBlack) {
        this.isTileBlack = isTileBlack;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        relocate(x * TILE_SIZE, y * TILE_SIZE);
        setFill(light ? Color.WHITE : Color.DARKGRAY);
    }
}
