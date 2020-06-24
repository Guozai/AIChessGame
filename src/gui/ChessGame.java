package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Logic;

/**
 * Created by yipin on 15/07/2017.
 */

public class ChessGame extends Application {

    public static final int TILE_SIZE = 80;
    public static final int WIDTH = 8; // The board has 8 columns
    public static final int HEIGHT = 8; // and 8 rows.

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private final Group tileGroup = new Group();
    private final Group pieceGroup = new Group();

    // the private logic chess board for class logic
    private Logic logic = new Logic();

    // the private PieceType value to store the pawn promotion type
    private PieceType typePromote;
    private boolean hasChosenPromotionTypeB = false;
    private boolean hasChosenPromotionTypeW = false;

    private boolean isGameFinished = false;

    // load the images
    // Black pieces
    final Image imgBRook = new Image(ChessGame.class.getResourceAsStream("Chess_rdt60.png"));
    final Image imgBBishop = new Image(ChessGame.class.getResourceAsStream("Chess_bdt60.png"));
    final Image imgBKnightL = new Image(ChessGame.class.getResourceAsStream("Chess_ndt60.png"));
    final Image imgBQueen = new Image(ChessGame.class.getResourceAsStream("Chess_qdt60.png"));
    // White pieces
    final Image imgWRook = new Image(ChessGame.class.getResourceAsStream("Chess_rlt60.png"));
    final Image imgWBishop = new Image(ChessGame.class.getResourceAsStream("Chess_blt60.png"));
    final Image imgWKnightL = new Image(ChessGame.class.getResourceAsStream("Chess_nlt60.png"));
    final Image imgWQueen = new Image(ChessGame.class.getResourceAsStream("Chess_qlt60.png"));

    private Parent createContent() {
        BorderPane chess = new BorderPane();

        // Top and bottom area
        HBox top = new HBox(50);
        top.setAlignment(Pos.CENTER);

        Text txtPlayerB = new Text("Black Side");
        txtPlayerB.setFont(Font.font(STYLESHEET_MODENA, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 35));
        LinearGradient fontGradient = new LinearGradient(
                .5,
                0,
                .5,
                1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.SKYBLUE),
                new Stop(1, Color.SILVER)
        );
        txtPlayerB.setFill(fontGradient);
        top.getChildren().add(txtPlayerB);

        HBox bottom = new HBox(50);
        bottom.setAlignment(Pos.CENTER);
        Text txtPlayerW = new Text("White Side");
        txtPlayerW.setFont(Font.font(STYLESHEET_MODENA, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 35));
        txtPlayerW.setFill(fontGradient);
        bottom.getChildren().add(txtPlayerW);

        Pane center = new Pane();
        center.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        center.getChildren().addAll(tileGroup, pieceGroup);

        // Create chess board
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);
            }
        }

        // Add chess pieces
        Piece bRook1 = makePiece(PieceType.BROOK, 0, 0);
        Piece bKnight1 = makePiece(PieceType.BKNIGHT, 1, 0);
        Piece bBishop1 = makePiece(PieceType.BBISHOP, 2, 0);
        Piece bQueen = makePiece(PieceType.BQUEEN, 3, 0);
        Piece bKing = makePiece(PieceType.BKING, 4, 0);
        Piece bBishop2 = makePiece(PieceType.BBISHOP, 5, 0);
        Piece bKnight2 = makePiece(PieceType.BKNIGHT, 6, 0);
        Piece bRook2 = makePiece(PieceType.BROOK, 7, 0);

        Piece bPawn1 = makePiece(PieceType.BPAWN, 0, 1);
        Piece bPawn2 = makePiece(PieceType.BPAWN, 1, 1);
        Piece bPawn3 = makePiece(PieceType.BPAWN, 2, 1);
        Piece bPawn4 = makePiece(PieceType.BPAWN, 3, 1);
        Piece bPawn5 = makePiece(PieceType.BPAWN, 4, 1);
        Piece bPawn6 = makePiece(PieceType.BPAWN, 5, 1);
        Piece bPawn7 = makePiece(PieceType.BPAWN, 6, 1);
        Piece bPawn8 = makePiece(PieceType.BPAWN, 7, 1);

        board[0][0].setPiece(bRook1);
        board[1][0].setPiece(bKnight1);
        board[2][0].setPiece(bBishop1);
        board[3][0].setPiece(bQueen);
        board[4][0].setPiece(bKing);
        board[5][0].setPiece(bBishop2);
        board[6][0].setPiece(bKnight2);
        board[7][0].setPiece(bRook2);

        board[0][1].setPiece(bPawn1);
        board[1][1].setPiece(bPawn2);
        board[2][1].setPiece(bPawn3);
        board[3][1].setPiece(bPawn4);
        board[4][1].setPiece(bPawn5);
        board[5][1].setPiece(bPawn6);
        board[6][1].setPiece(bPawn7);
        board[7][1].setPiece(bPawn8);

        pieceGroup.getChildren().addAll(bRook1, bKnight1, bBishop1, bQueen, bKing, bBishop2, bKnight2, bRook2);
        pieceGroup.getChildren().addAll(bPawn1, bPawn2, bPawn3, bPawn4, bPawn5, bPawn6, bPawn7, bPawn8);

        Piece wRook1 = makePiece(PieceType.WROOK, 0, 7);
        Piece wKnight1 = makePiece(PieceType.WKNIGHT, 1, 7);
        Piece wBishop1 = makePiece(PieceType.WBISHOP, 2, 7);
        Piece wQueen = makePiece(PieceType.WQUEEN, 3, 7);
        Piece wKing = makePiece(PieceType.WKING, 4, 7);
        Piece wBishop2 = makePiece(PieceType.WBISHOP, 5, 7);
        Piece wKnight2 = makePiece(PieceType.WKNIGHT, 6, 7);
        Piece wRook2 = makePiece(PieceType.WROOK, 7, 7);

        Piece wPawn1 = makePiece(PieceType.WPAWN, 0, 6);
        Piece wPawn2 = makePiece(PieceType.WPAWN, 1, 6);
        Piece wPawn3 = makePiece(PieceType.WPAWN, 2, 6);
        Piece wPawn4 = makePiece(PieceType.WPAWN, 3, 6);
        Piece wPawn5 = makePiece(PieceType.WPAWN, 4, 6);
        Piece wPawn6 = makePiece(PieceType.WPAWN, 5, 6);
        Piece wPawn7 = makePiece(PieceType.WPAWN, 6, 6);
        Piece wPawn8 = makePiece(PieceType.WPAWN, 7, 6);

        board[0][7].setPiece(wRook1);
        board[1][7].setPiece(wKnight1);
        board[2][7].setPiece(wBishop1);
        board[3][7].setPiece(wQueen);
        board[4][7].setPiece(wKing);
        board[5][7].setPiece(wBishop2);
        board[6][7].setPiece(wKnight2);
        board[7][7].setPiece(wRook2);

        board[0][6].setPiece(wPawn1);
        board[1][6].setPiece(wPawn2);
        board[2][6].setPiece(wPawn3);
        board[3][6].setPiece(wPawn4);
        board[4][6].setPiece(wPawn5);
        board[5][6].setPiece(wPawn6);
        board[6][6].setPiece(wPawn7);
        board[7][6].setPiece(wPawn8);

        pieceGroup.getChildren().addAll(wPawn1, wPawn2, wPawn3, wPawn4, wPawn5, wPawn6, wPawn7, wPawn8);
        pieceGroup.getChildren().addAll(wRook1, wKnight1, wBishop1, wQueen, wKing, wBishop2, wKnight2, wRook2);

        logic.setBoard(board);

        chess.setTop(top);
        chess.setBottom(bottom);
        chess.setCenter(center);

        return chess;
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE /2) / TILE_SIZE;
    }

    Stage main, popup;
    Scene gameScene;
    Scene popupBScene, popupWScene;
    // Popup scene to notify game finish and which player wins
    Scene winBScene, winWScene;
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create popup scene content
        VBox vBoxB = new VBox();
        Button btnBQueen = new Button();
        btnBQueen.setGraphic(new ImageView(imgBQueen));
        btnBQueen.setOnAction(e -> {
            typePromote = PieceType.BQUEEN;
            hasChosenPromotionTypeB = true;
            popup.close();
        });
        Button btnBBishop = new Button();
        btnBBishop.setGraphic(new ImageView(imgBBishop));
        btnBBishop.setOnAction(e -> {
            typePromote = PieceType.BBISHOP;
            hasChosenPromotionTypeB = true;
            popup.close();
        });
        Button btnBKnight = new Button();
        btnBKnight.setGraphic(new ImageView(imgBKnightL));
        btnBKnight.setOnAction(e -> {
            typePromote = PieceType.BKNIGHT;
            hasChosenPromotionTypeB = true;
            popup.close();
        });
        Button btnBRook = new Button();
        btnBRook.setGraphic(new ImageView(imgBRook));
        btnBRook.setOnAction(e -> {
            typePromote = PieceType.BROOK;
            hasChosenPromotionTypeB = true;
            popup.close();
        });
        vBoxB.setSpacing(10);
        vBoxB.getChildren().addAll(btnBQueen, btnBBishop, btnBKnight, btnBRook);
        popupBScene = new Scene(vBoxB, 78, 310);

        VBox vBoxW = new VBox();
        Button btnWQueen = new Button();
        btnWQueen.setGraphic(new ImageView(imgWQueen));
        btnWQueen.setOnAction(e -> {
            typePromote = PieceType.WQUEEN;
            hasChosenPromotionTypeW = true;
            popup.close();
        });
        Button btnWBishop = new Button();
        btnWBishop.setGraphic(new ImageView(imgWBishop));
        btnWBishop.setOnAction(e -> {
            typePromote = PieceType.WBISHOP;
            hasChosenPromotionTypeW = true;
            popup.close();
        });
        Button btnWKnight = new Button();
        btnWKnight.setGraphic(new ImageView(imgWKnightL));
        btnWKnight.setOnAction(e -> {
            typePromote = PieceType.WKNIGHT;
            hasChosenPromotionTypeW = true;
            popup.close();
        });
        Button btnWRook = new Button();
        btnWRook.setGraphic(new ImageView(imgWRook));
        btnWRook.setOnAction(e -> {
            typePromote = PieceType.WROOK;
            hasChosenPromotionTypeW = true;
            popup.close();
        });
        vBoxW.setSpacing(10);
        vBoxW.getChildren().addAll(btnWQueen, btnWBishop, btnWKnight, btnWRook);
        popupWScene = new Scene(vBoxW, 78, 310);


        // Create winScene content
        GridPane blackGP = new GridPane();
        blackGP.setAlignment(Pos.CENTER);
        blackGP.setHgap(10);
        blackGP.setVgap(1);
        blackGP.setPadding(new Insets(25, 25, 25, 25));

        Label winNoticeB = new Label("Black Side Wins!");
        blackGP.add(winNoticeB, 3, 8);

        Button btnOK = new Button("OK");
        btnOK.setOnAction(e -> {
            popup.close();
            main.close();
        });
        HBox hbBtnB = new HBox(10);
        hbBtnB.setAlignment(Pos.CENTER);
        hbBtnB.getChildren().add(btnOK);
        blackGP.add(hbBtnB, 3, 11);

        blackGP.setGridLinesVisible(false);
        winBScene = new Scene(blackGP, 400, 300);

        GridPane whiteGP = new GridPane();
        whiteGP.setAlignment(Pos.CENTER);
        whiteGP.setHgap(10);
        whiteGP.setVgap(1);
        whiteGP.setPadding(new Insets(25, 25, 25, 25));

        Label winNoticeW = new Label("White Side Wins!");
        whiteGP.add(winNoticeW, 3, 8);

        Button btnOK1 = new Button("OK");
        btnOK1.setOnAction(e -> {
            popup.close();
            main.close();
        });
        HBox hbBtnW = new HBox(10);
        hbBtnW.setAlignment(Pos.CENTER);
        hbBtnW.getChildren().add(btnOK1);
        whiteGP.add(hbBtnW, 3, 11);

        whiteGP.setGridLinesVisible(false);
        winWScene = new Scene(whiteGP, 400, 300);


        // initial popup stage
        popup = new Stage();
        popup.setScene(popupBScene);
        popup.initModality(Modality.APPLICATION_MODAL);

        main = primaryStage;
        gameScene = new Scene(createContent());

        main.setTitle("Chess Game");
        main.setScene(gameScene);
        main.show();
    }

    private int x0, y0;
    /* Move the chess piece */
    private Piece makePiece (PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMousePressed(e -> {
            if (!isGameFinished) {
                x0 = toBoard(piece.getOldX());
                y0 = toBoard(piece.getOldY());

                int tempX, tempY;

                // get the piece type from board in case of pawn promotion
                logic.setValidMove(board[x0][y0].getPiece().getType(), x0, y0);
                board = logic.getBoard();
                for (tempX = 0; tempX < 8; tempX++) {
                    for (tempY = 0; tempY < 8; tempY++) {
                        if (board[tempX][tempY].getHighlight()) {
                            highlightTile(tempX, tempY);
                        }
                    }
                }
            }
        });

        piece.setOnMouseDragged(e -> {
            if (!isGameFinished)
                piece.relocate(e.getSceneX() - TILE_SIZE/2, e.getSceneY() - TILE_SIZE);
        });

        piece.setOnMouseReleased(e -> {
            if (!isGameFinished) {
                int newX = toBoard(piece.getLayoutX());
                int newY = toBoard(piece.getLayoutY());
                int tempX, tempY;
                boolean isMovable = true;

                for (tempX = 0; tempX < 8; tempX++) {
                    for (tempY = 0; tempY < 8; tempY++) {
                        if (board[tempX][tempY].getHighlight()) {
                            stopHighlightTile(tempX, tempY);
                        }
                    }
                }

                // Check if the released position is movable
                if (!board[newX][newY].getHighlight())
                    isMovable = false;

                if (isMovable == false) {
                    piece.abortMove();
                } else if (isMovable == true) {
                    if (newX == x0 && newY == y0) {
                        piece.abortMove();
                    } else if (board[newX][newY].hasPiece() == true) {

                        // Check if game is finished.
                        if (board[newX][newY].getPiece().getType() == PieceType.BKING
                                && (board[x0][y0].getPiece().getType() == PieceType.WPAWN
                                || board[x0][y0].getPiece().getType() == PieceType.WROOK
                                || board[x0][y0].getPiece().getType() == PieceType.WBISHOP
                                || board[x0][y0].getPiece().getType() == PieceType.WKNIGHT
                                || board[x0][y0].getPiece().getType() == PieceType.WQUEEN
                                || board[x0][y0].getPiece().getType() == PieceType.WKING)) {
                            // Pop up window to show game is finished
                            isGameFinished = true;
                            popup.setScene(winWScene);
                            popup.showAndWait();
                        }
                        if (board[newX][newY].getPiece().getType() == PieceType.WKING
                                && (board[x0][y0].getPiece().getType() == PieceType.BPAWN
                                || board[x0][y0].getPiece().getType() == PieceType.BROOK
                                || board[x0][y0].getPiece().getType() == PieceType.BBISHOP
                                || board[x0][y0].getPiece().getType() == PieceType.BKNIGHT
                                || board[x0][y0].getPiece().getType() == PieceType.BQUEEN
                                || board[x0][y0].getPiece().getType() == PieceType.BKING)) {
                            // Pop up window to show game is finished
                            isGameFinished = true;
                            popup.setScene(winBScene);
                            popup.showAndWait();
                        }

                        pieceGroup.getChildren().remove(board[newX][newY].getPiece());
                        board[newX][newY].setPiece(null);
                    }
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    if (newX != x0 || newY != y0) {
                        // Pawn promotion
                        if (board[newX][newY].getPiece().getType() == PieceType.BPAWN && newY == 7) {
                            popup.setScene(popupBScene);
                            popup.showAndWait();

                            // if promotion type is not selected on the popup scene, set promotion type to BQUEEN
                            if (!hasChosenPromotionTypeB)
                                typePromote = PieceType.BQUEEN;
                            board[newX][newY].getPiece().setType(typePromote);
                            board[newX][newY].getPiece().setImage(board[newX][newY].getPiece(), typePromote);
                            hasChosenPromotionTypeB = false;
                        }

                        if (board[newX][newY].getPiece().getType() == PieceType.WPAWN && newY == 0) {
                            popup.setScene(popupWScene);
                            popup.showAndWait();

                            // if promotion type is not selected on the popup scene, set promotion type to WQUEEN
                            if (!hasChosenPromotionTypeW)
                                typePromote = PieceType.WQUEEN;
                            board[newX][newY].getPiece().setType(typePromote);
                            board[newX][newY].getPiece().setImage(board[newX][newY].getPiece(), typePromote);
                            hasChosenPromotionTypeW = false;
                        }

                        // Update hasCastled if castled
                        if (newX == 6 && newY == 0 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.BKING &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        if (newX == 2 && newY == 0 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.BKING &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        if (newX == 3 && newY == 0 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.BROOK &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        if (newX == 5 && newY == 0 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.BROOK &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        if (newX == 6 && newY == 7 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.WKING &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        if (newX == 2 && newY == 7 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.WKING &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        if (newX == 3 && newY == 7 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.WROOK &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        if (newX == 5 && newY == 7 && board[newX][newY].hasPiece() && board[newX][newY].getPiece().getType() == PieceType.WROOK &&
                                !board[newX][newY].getPiece().getHasMoved()) {
                            board[newX][newY].getPiece().setHasCastled(true);
                        }
                        // Update piece property hasMoved.
                        board[newX][newY].getPiece().setHasMoved(true);
                    }
                    // Update logic board
                    logic.setBoard(board);
                    /* Save the new position of x, y */
                    x0 = newX;
                    y0 = newY;
                }
            }
        });

        return piece;
    }

    /* Highlighting the tiles */
    private void highlightTile(int x, int y) {
        if (board[x][y].getFill().equals(Color.DARKGRAY)) {
            board[x][y].setPreviousTileColor(true);
        } else {
            board[x][y].setPreviousTileColor(false);
        }
        board[x][y].setFill(Color.YELLOW);
    }

    private void stopHighlightTile(int x, int y) {
        if (board[x][y].getPreviousTileColor() == true) {
            board[x][y].setFill(Color.DARKGRAY);
        } else {
            board[x][y].setFill(Color.WHITE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { launch(args); }
}
