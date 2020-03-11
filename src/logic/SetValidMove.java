package logic;

import gui.PieceType;
import gui.Tile;

/**
 * Created by yipin on 15/07/2017.
 */
public class SetValidMove {

//    public boolean checkValidMove(PieceType type, int x, int y, int x0, int y0, Tile[][] board) {
//        if (checkPieceDefaultValid(type, x, y, x0, y0, board) && checkIfWithinBorder(x, y)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void setValidMove(PieceType type, int x0, int y0, Tile[][] board) {

        // The chessboard is displayed as following
        //   0 1 2 3 4 5 6 7
        // 0| | | | | | | | |
        // 1| | | | | | | | |
        // 2| | | | | | | | |
        // 3| | | | | | | | |
        // 4| | | | | | | | |
        // 5| | | | | | | | |
        // 6| | | | | | | | |
        // 7| | | | | | | | |

        // initialize board
        for (int m = 0; m < 7; m++) {
            for (int n = 0; n < 7; n++) {
                board[m][n].setHighlight(false);
            }
        }

        int countOpponent;
        int x, y;
        // The legal move of black pawn if there is no other piece on the chessboard
        switch(type) {
            case BPAWN:
                if (y0 == 1) {
                    board[x0][y0 + 1].setHighlight(true);
                    board[x0][y0 + 2].setHighlight(true);
                } else if (y0 < 7){
                    if (!board[x0][y0 + 1].hasPiece())
                        board[x0][y0 + 1].setHighlight(true);
                    // When a white piece is on the front left or front right of a black pawn, it is a valid move.
                    if (x0 - 1 >= 0 && checkHasOpponentOnPosition(PieceType.BPAWN, board[x0 - 1][y0 + 1]))
                        board[x0 - 1][y0 + 1].setHighlight(true);
                    if (x0 + 1 < 8 && checkHasOpponentOnPosition(PieceType.BPAWN, board[x0 + 1][y0 + 1]))
                        board[x0 + 1][y0 + 1].setHighlight(true);
                }
                break;
            case WPAWN:
                if (y0 == 6) {
                    board[x0][y0 - 1].setHighlight(true);
                    board[x0][y0 - 2].setHighlight(true);
                } else if (y0 > 0){
                    if (!board[x0][y0 - 1].hasPiece())
                        board[x0][y0 - 1].setHighlight(true);
                    // When a black piece is on the front left or front right of a white pawn, it is a valid move.
                    if (x0 - 1 >= 0 && checkHasOpponentOnPosition(PieceType.WPAWN, board[x0 - 1][y0 - 1]))
                        board[x0 - 1][y0 - 1].setHighlight(true);
                    if (x0 + 1 < 8 && checkHasOpponentOnPosition(PieceType.WPAWN, board[x0 + 1][y0 - 1]))
                        board[x0 + 1][y0 - 1].setHighlight(true);
                }
                break;
            case BROOK:
                // set original rook position as valid move
                board[x0][y0].setHighlight(true);
                // set y to original rook position, countOpponent to no white pieces count.
                y = y0;
                countOpponent = 0;
                // set valid moves for the top of rook.
                while (countOpponent < 2 && y >= 0) {
                    // Highlight tiles that have no piece on it
                    if (!board[x0][y].hasPiece()) {
                        board[x0][y].setHighlight(true);
                        // Do not highlight tiles that have no piece and there is a piece between the rook and this tile
                        for (int n = y0 - 1; n > y; n--) {
                            if (board[x0][n].hasPiece()) {
                                board[x0][y].setHighlight(false);
                                break;
                            }
                        }
                    }
                    // Highlight tiles that have the first white piece on the route of rook.
                    if (checkHasOpponentOnPosition(PieceType.BROOK, board[x0][y])) {
                        // Increase the number of white piece count on the route of rook.
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x0][y].setHighlight(true);
                            // Check if there is no piece between this piece and the rook.
                            for (int n = y0 - 1; n > y; n--) {
                                if (board[x0][n].hasPiece()) {
                                    board[x0][y].setHighlight(false);
                                    break;
                                }
                            }
                        }
                    }
                    y--;
                }
                // reset y to original rook position, countOpponent to no white pieces count.
                y = y0;
                countOpponent = 0;
                // set valid moves for the bottom of rook.
                while (countOpponent < 2 && y < 8) {
                    if (!board[x0][y].hasPiece()) {
                        board[x0][y].setHighlight(true);
                        // Do not highlight tiles that have no piece and there is a piece between the rook and this tile
                        for (int n = y0 + 1; n < y; n++) {
                            if (board[x0][n].hasPiece()) {
                                board[x0][y].setHighlight(false);
                                break;
                            }
                        }
                    }
                    if (checkHasOpponentOnPosition(PieceType.BROOK, board[x0][y])) {
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x0][y].setHighlight(true);
                            // Check if there is no piece between this piece and the rook.
                            for (int n = y0 + 1; n < y; n++) {
                                if (board[x0][n].hasPiece()) {
                                    board[x0][y].setHighlight(false);
                                    break;
                                }

                            }
                        }
                    }
                    y++;
                }
                // set valid moves for the left of rook.
                x = x0;
                countOpponent = 0;

                while (countOpponent < 2 && x >= 0) {
                    if (!board[x][y0].hasPiece()) {
                        board[x][y0].setHighlight(true);

                        for (int m = x0 - 1; m > x; m--) {
                            if (board[m][y0].hasPiece()) {
                                board[x][y0].setHighlight(false);
                                break;
                            }
                        }
                    }
                    if (checkHasOpponentOnPosition(PieceType.BROOK, board[x][y0])) {
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x][y0].setHighlight(true);
                            for (int m = x0 -1; m > x; m--) {
                                if (board[m][y0].hasPiece()) {
                                    board[x][y0].setHighlight(false);
                                    break;
                                }
                            }
                        }
                    }
                    x--;
                }
                // set valid moves for the right of rook.
                x = x0;
                countOpponent = 0;

                while (countOpponent < 2 && x < 8) {
                    if (!board[x][y0].hasPiece()) {
                        board[x][y0].setHighlight(true);

                        for (int m = x0 + 1; m < x; m++) {
                            if (board[m][y0].hasPiece()) {
                                board[x][y0].setHighlight(false);
                                break;
                            }
                        }
                    }
                    if (checkHasOpponentOnPosition(PieceType.BROOK, board[x][y0])) {
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x][y0].setHighlight(true);
                            for (int m = x0 + 1; m < x; m++) {
                                if (board[m][y0].hasPiece()) {
                                    board[x][y0].setHighlight(false);
                                    break;
                                }
                            }
                        }
                    }
                    x++;
                }
                break;
            case WROOK:
                // set original rook position as valid move
                board[x0][y0].setHighlight(true);
                // set y to original rook position, countOpponent to no black pieces count.
                y = y0;
                countOpponent = 0;
                // set valid moves for the top of rook.
                while (countOpponent < 2 && y >= 0) {
                    // Highlight tiles that have no piece on it
                    if (!board[x0][y].hasPiece()) {
                        board[x0][y].setHighlight(true);
                        // Do not highlight tiles that have no piece and there is a piece between the rook and this tile
                        for (int n = y0 - 1; n > y; n--) {
                            if (board[x0][n].hasPiece()) {
                                board[x0][y].setHighlight(false);
                                break;
                            }
                        }
                    }
                    // Highlight tiles that have the first black piece on the route of rook.
                    if (checkHasOpponentOnPosition(PieceType.WROOK, board[x0][y])) {
                        // Increase the number of black piece count on the route of rook.
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x0][y].setHighlight(true);
                            // Check if there is no piece between this piece and the rook.
                            for (int n = y0 - 1; n > y; n--) {
                                if (board[x0][n].hasPiece()) {
                                    board[x0][y].setHighlight(false);
                                    break;
                                }
                            }
                        }
                    }
                    y--;
                }
                // set valid moves for the bottom of rook.
                y = y0;
                countOpponent = 0;

                while (countOpponent < 2 && y < 8) {
                    if (!board[x0][y].hasPiece()) {
                        board[x0][y].setHighlight(true);
                        // Do not highlight tiles that have no piece and there is a piece between the rook and this tile
                        for (int n = y0 + 1; n < y; n++) {
                            if (board[x0][n].hasPiece()) {
                                board[x0][y].setHighlight(false);
                                break;
                            }
                        }
                    }
                    if (checkHasOpponentOnPosition(PieceType.WROOK, board[x0][y])) {
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x0][y].setHighlight(true);
                            // Check if there is no piece between this piece and the rook.
                            for (int n = y0 + 1; n < y; n++) {
                                if (board[x0][n].hasPiece()) {
                                    board[x0][y].setHighlight(false);
                                    break;
                                }

                            }
                        }
                    }
                    y++;
                }
                // set valid moves for the left of rook.
                x = x0;
                countOpponent = 0;

                while (countOpponent < 2 && x >= 0) {
                    if (!board[x][y0].hasPiece()) {
                        board[x][y0].setHighlight(true);

                        for (int m = x0 - 1; m > x; m--) {
                            if (board[m][y0].hasPiece()) {
                                board[x][y0].setHighlight(false);
                                break;
                            }
                        }
                    }
                    if (checkHasOpponentOnPosition(PieceType.WROOK, board[x][y0])) {
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x][y0].setHighlight(true);
                            for (int m = x0 -1; m > x; m--) {
                                if (board[m][y0].hasPiece()) {
                                    board[x][y0].setHighlight(false);
                                    break;
                                }
                            }
                        }
                    }
                    x--;
                }
                // set valid moves for the right of rook.
                x = x0;
                countOpponent = 0;

                while (countOpponent < 2 && x < 8) {
                    if (!board[x][y0].hasPiece()) {
                        board[x][y0].setHighlight(true);

                        for (int m = x0 + 1; m < x; m++) {
                            if (board[m][y0].hasPiece()) {
                                board[x][y0].setHighlight(false);
                                break;
                            }
                        }
                    }
                    if (checkHasOpponentOnPosition(PieceType.WROOK, board[x][y0])) {
                        countOpponent++;
                        if (countOpponent < 2) {
                            board[x][y0].setHighlight(true);
                            for (int m = x0 + 1; m < x; m++) {
                                if (board[m][y0].hasPiece()) {
                                    board[x][y0].setHighlight(false);
                                    break;
                                }
                            }
                        }
                    }
                    x++;
                }
                break;
            case BKNIGHT:
            case WKNIGHT:
//                if (((x == x0 - 2 && (y == y0 - 1 || y == y0 + 1)) || (x == x0 - 1 && (y == y0 - 2 || y == y0 + 2)) ||
//                        (x == x0 + 2 && (y == y0 - 1 || y == y0 + 1)) || (x == x0 + 1 && (y == y0 - 2 || y == y0 + 2)))
//                        && !board[x][y].hasPiece()) {
//                    flag = true;
//                }
                break;
            case BBISHOP:
            case WBISHOP:
//                if (((x == x0 - 1 && y == y0 - 1) || (x == x0 - 2 && y == y0 - 2) || (x == x0 - 3 && y == y0 - 3) || (x == x0 - 4 && y == y0 - 4) ||
//                        (x == x0 - 5 && y == y0 - 5) || (x == x0 - 6 && y == y0 - 6) || (x == x0 - 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
//                    flag = true;
//                } else if (((x == x0 + 1 && y == y0 - 1) || (x == x0 + 2 && y == y0 - 2) || (x == x0 + 3 && y == y0 - 3) || (x == x0 + 4 && y == y0 - 4) ||
//                        (x == x0 + 5 && y == y0 - 5) || (x == x0 + 6 && y == y0 - 6) || (x == x0 + 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
//                    flag = true;
//                } else if (((x == x0 - 1 && y == y0 + 1) || (x == x0 - 2 && y == y0 + 2) || (x == x0 - 3 && y == y0 + 3) || (x == x0 - 4 && y == y0 + 4) ||
//                        (x == x0 - 5 && y == y0 + 5) || (x == x0 - 6 && y == y0 + 6) || (x == x0 - 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
//                    flag = true;
//                } else if (((x == x0 + 1 && y == y0 + 1) || (x == x0 + 2 && y == y0 + 2) || (x == x0 + 3 && y == y0 + 3) || (x == x0 + 4 && y == y0 + 4) ||
//                        (x == x0 + 5 && y == y0 + 5) || (x == x0 + 6 && y == y0 + 6) || (x == x0 + 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
//                    flag = true;
//                }
                break;
            case BQUEEN:
            case WQUEEN:
//                if ((x == x0 || y == y0) && !board[x][y].hasPiece()) {
//                    flag = true;
//                } else if (((x == x0 - 1 && y == y0 - 1) || (x == x0 - 2 && y == y0 - 2) || (x == x0 - 3 && y == y0 - 3) || (x == x0 - 4 && y == y0 - 4) ||
//                        (x == x0 - 5 && y == y0 - 5) || (x == x0 - 6 && y == y0 - 6) || (x == x0 - 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
//                    flag = true;
//                } else if (((x == x0 + 1 && y == y0 - 1) || (x == x0 + 2 && y == y0 - 2) || (x == x0 + 3 && y == y0 - 3) || (x == x0 + 4 && y == y0 - 4) ||
//                        (x == x0 + 5 && y == y0 - 5) || (x == x0 + 6 && y == y0 - 6) || (x == x0 + 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
//                    flag = true;
//                } else if (((x == x0 - 1 && y == y0 + 1) || (x == x0 - 2 && y == y0 + 2) || (x == x0 - 3 && y == y0 + 3) || (x == x0 - 4 && y == y0 + 4) ||
//                        (x == x0 - 5 && y == y0 + 5) || (x == x0 - 6 && y == y0 + 6) || (x == x0 - 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
//                    flag = true;
//                } else if (((x == x0 + 1 && y == y0 + 1) || (x == x0 + 2 && y == y0 + 2) || (x == x0 + 3 && y == y0 + 3) || (x == x0 + 4 && y == y0 + 4) ||
//                        (x == x0 + 5 && y == y0 + 5) || (x == x0 + 6 && y == y0 + 6) || (x == x0 + 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
//                    flag =true;
//                }
                break;
            case BKING:
            case WKING:
//                if (((x == x0 - 1 && (y == y0 - 1 || y == y0 || y == y0 + 1)) || (x == x0 && (y == y0 - 1 || y == y0 + 1)) ||
//                        (x == x0 + 1 && (y == y0 - 1 || y == y0 || y == y0 + 1))) && !board[x][y].hasPiece()) {
//                    flag = true;
//                }
                break;
            default:
                board[x0][y0].setHighlight(true);
                break;
        }
    }

//    boolean checkIfWithinBorder(int x, int y) {
//        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    boolean checkHasSameSideOnPosition(PieceType type, Tile tile) {
        // check if there is a piece on the position
        if (!tile.hasPiece())
            return false;

        switch (type) {
            case BPAWN:
            case BROOK:
            case BKNIGHT:
            case BBISHOP:
            case BQUEEN:
            case BKING:
                if (tile.getPiece().getType() == PieceType.BPAWN ||
                        tile.getPiece().getType() == PieceType.BROOK ||
                        tile.getPiece().getType() == PieceType.BBISHOP ||
                        tile.getPiece().getType() == PieceType.BKNIGHT ||
                        tile.getPiece().getType() == PieceType.BQUEEN ||
                        tile.getPiece().getType() == PieceType.BKING) {
                    return true;
                } else {
                    return false;
                }
            case WPAWN:
            case WROOK:
            case WKNIGHT:
            case WBISHOP:
            case WQUEEN:
            case WKING:
                if (tile.getPiece().getType() == PieceType.WPAWN ||
                        tile.getPiece().getType() == PieceType.WROOK ||
                        tile.getPiece().getType() == PieceType.WBISHOP ||
                        tile.getPiece().getType() == PieceType.WKNIGHT ||
                        tile.getPiece().getType() == PieceType.WQUEEN ||
                        tile.getPiece().getType() == PieceType.WKING) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    boolean checkHasOpponentOnPosition(PieceType type, Tile tile) {
        // check if there is a piece on the position
        if (!tile.hasPiece())
            return false;

        switch (type) {
            case BPAWN:
            case BROOK:
            case BKNIGHT:
            case BBISHOP:
            case BQUEEN:
            case BKING:
                if (tile.getPiece().getType() == PieceType.WPAWN ||
                        tile.getPiece().getType() == PieceType.WROOK ||
                        tile.getPiece().getType() == PieceType.WBISHOP ||
                        tile.getPiece().getType() == PieceType.WKNIGHT ||
                        tile.getPiece().getType() == PieceType.WQUEEN ||
                        tile.getPiece().getType() == PieceType.WKING) {
                    return true;
                } else {
                    return false;
                }
            case WPAWN:
            case WROOK:
            case WKNIGHT:
            case WBISHOP:
            case WQUEEN:
            case WKING:
                if (tile.getPiece().getType() == PieceType.BPAWN ||
                        tile.getPiece().getType() == PieceType.BROOK ||
                        tile.getPiece().getType() == PieceType.BBISHOP ||
                        tile.getPiece().getType() == PieceType.BKNIGHT ||
                        tile.getPiece().getType() == PieceType.BQUEEN ||
                        tile.getPiece().getType() == PieceType.BKING) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
