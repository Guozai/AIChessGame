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

    public PieceType getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);

        // load the image
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

        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView piece = new ImageView();
        if (type == PieceType.BPAWN) {
            piece.setImage(imgBPawn);
        } else if (type == PieceType.BROOK) {
            piece.setImage(imgBRook);
        } else if (type == PieceType.BBISHOP) {
            piece.setImage(imgBBishop);
        } else if (type == PieceType.BKNIGHT) {
            piece.setImage(imgBKnightL);
        } else if (type == PieceType.BQUEEN) {
            piece.setImage(imgBQueen);
        } else if (type == PieceType.BKING) {
            piece.setImage(imgBKing);

        } else if (type == PieceType.WPAWN) {
            piece.setImage(imgWPawn);
        } else if (type == PieceType.WROOK) {
            piece.setImage(imgWRook);
        } else if (type == PieceType.WBISH0P) {
            piece.setImage(imgWBishop);
        } else if (type == PieceType.WKNIGHT) {
            piece.setImage(imgWKnightL);
        } else if (type == PieceType.WQUEEN) {
            piece.setImage(imgWQueen);
        } else if (type == PieceType.WKING) {
            piece.setImage(imgWKing);
        }
        piece.setFitWidth(TILE_SIZE);
        piece.setPreserveRatio(true);
        piece.setSmooth(true);
        piece.setCache(true);

        getChildren().add(piece);
    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
}
