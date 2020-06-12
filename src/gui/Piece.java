package gui;

import static gui.ChessGame.TILE_SIZE;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;

/**
 * Created by yipin on 15/07/2017.
 */
public class Piece extends StackPane {
    private PieceType type;
    private double oldX, oldY;
    private boolean hasMoved;
    private boolean hasCastled;

    // load the images
    // Black pieces
    final Image imgBPawn = new Image(ChessGame.class.getResourceAsStream("Chess_pdt60.png"));
    final Image imgBRook = new Image(ChessGame.class.getResourceAsStream("Chess_rdt60.png"));
    final Image imgBBishop = new Image(ChessGame.class.getResourceAsStream("Chess_bdt60.png"));
    final Image imgBKnightL = new Image(ChessGame.class.getResourceAsStream("Chess_ndt60.png"));
    final Image imgBQueen = new Image(ChessGame.class.getResourceAsStream("Chess_qdt60.png"));
    final Image imgBKing = new Image(ChessGame.class.getResourceAsStream("Chess_kdt60.png"));
    // White pieces
    final Image imgWPawn = new Image(ChessGame.class.getResourceAsStream("Chess_plt60.png"));
    final Image imgWRook = new Image(ChessGame.class.getResourceAsStream("Chess_rlt60.png"));
    final Image imgWBishop = new Image(ChessGame.class.getResourceAsStream("Chess_blt60.png"));
    final Image imgWKnightL = new Image(ChessGame.class.getResourceAsStream("Chess_nlt60.png"));
    final Image imgWQueen = new Image(ChessGame.class.getResourceAsStream("Chess_qlt60.png"));
    final Image imgWKing = new Image(ChessGame.class.getResourceAsStream("Chess_klt60.png"));

    public PieceType getType() {
        return type;
    }
    public void setType(PieceType type) { this.type = type; }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public boolean getHasMoved() { return hasMoved; }
    public void setHasMoved(boolean hasMoved) { this.hasMoved = hasMoved; }

    public boolean getHasCastled() { return hasCastled; }
    public void setHasCastled(boolean hasCastled) { this.hasCastled = hasCastled; }

    public Piece(PieceType type, int x, int y) {
        this.type = type;
        this.hasMoved = false;
        this.hasCastled = false;

        move(x, y);

        setImage(this, type);
    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }

    public void setImage(Piece piece, PieceType type) {
        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView skin = new ImageView();
        if (type == PieceType.BPAWN) {
            skin.setImage(imgBPawn);
        } else if (type == PieceType.BROOK) {
            skin.setImage(imgBRook);
        } else if (type == PieceType.BBISHOP) {
            skin.setImage(imgBBishop);
        } else if (type == PieceType.BKNIGHT) {
            skin.setImage(imgBKnightL);
        } else if (type == PieceType.BQUEEN) {
            skin.setImage(imgBQueen);
        } else if (type == PieceType.BKING) {
            skin.setImage(imgBKing);

        } else if (type == PieceType.WPAWN) {
            skin.setImage(imgWPawn);
        } else if (type == PieceType.WROOK) {
            skin.setImage(imgWRook);
        } else if (type == PieceType.WBISHOP) {
            skin.setImage(imgWBishop);
        } else if (type == PieceType.WKNIGHT) {
            skin.setImage(imgWKnightL);
        } else if (type == PieceType.WQUEEN) {
            skin.setImage(imgWQueen);
        } else if (type == PieceType.WKING) {
            skin.setImage(imgWKing);
        }
        skin.setFitWidth(TILE_SIZE);
        skin.setPreserveRatio(true);
        skin.setSmooth(true);
        skin.setCache(true);

        piece.getChildren().clear();
        piece.getChildren().add(skin);
    }
}
