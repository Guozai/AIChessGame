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
                    board[x0][y0 + 1].setHighlight(true);
                }
                break;
            case WPAWN:
                if (y0 == 6) {
                    board[x0][y0 - 1].setHighlight(true);
                    board[x0][y0 - 2].setHighlight(true);
                } else if (y0 > 0){
                    board[x0][y0 - 1].setHighlight(true);
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
                            if (board[x0][n].hasPiece())
                                board[x0][y].setHighlight(false);
                        }
                    }
//                    // Highlight tiles that have no black piece on it on the route of rook.
//                    if (!(board[x0][y].hasPiece() && checkHasSameSideOnPosition(PieceType.BROOK, x0, y, board))) {
//                        board[x0][y].setHighlight(true);
//                    }
                    // Increase the number of white piece count on the route of rook.
                    if (board[x0][y].hasPiece() && checkHasOpponentOnPosition(PieceType.BROOK, x0, y, board)) {
                        countOpponent++;
                    }
                    // Highlight tiles that have the first white piece on the route of rook.
                    if (board[x0][y].hasPiece() && checkHasOpponentOnPosition(PieceType.BROOK, x0, y, board) && countOpponent < 2) {
                        board[x0][y].setHighlight(true);
                        // Check if there is no piece between this piece and the rook.
                        for (int n = y0 - 1; n > y; n--) {
                            if (board[x0][n].hasPiece())
                                board[x0][y].setHighlight(false);

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
                            if (board[x0][n].hasPiece())
                                board[x0][y].setHighlight(false);
                        }
                    }
//                    if (!(board[x0][y].hasPiece() && checkHasSameSideOnPosition(PieceType.BROOK, x0, y, board))) {
//                        board[x0][y].setHighlight(true);
//                    }
                    if (board[x0][y].hasPiece() && checkHasOpponentOnPosition(PieceType.BROOK, x0, y, board)) {
                        countOpponent++;
                    }
                    if (board[x0][y].hasPiece() && checkHasOpponentOnPosition(PieceType.BROOK, x0, y, board) && countOpponent < 2) {
                        board[x0][y].setHighlight(true);
                        for (int n = y0 + 1; n < y; n++) {
                            if (board[x0][n].hasPiece())
                                board[x0][y].setHighlight(false);

                        }
                    }
                    y++;
                }

//                if (x == x0 || y == y0) {
//                    flag = true;
//                    if (x == x0 && y < y0) {
//                        System.out.println("--------------------------------------");
//                        for (int i = y; i < y0; i++) {
//                            if (board[x][i].hasPiece()) {
//                                System.out.println("board[" + x + "][" + i + "] = " + board[x][i].getPiece().getType());
//                            }
//                            if (board[x][i].hasPiece()) {
//                                // Any piece that is not white on the route of rook is not a valid move.
//                                if (board[x][i].getPiece().getType() != PieceType.WPAWN &&
//                                        board[x][i].getPiece().getType() != PieceType.WROOK &&
//                                        board[x][i].getPiece().getType() != PieceType.WBISHOP &&
//                                        board[x][i].getPiece().getType() != PieceType.WKNIGHT &&
//                                        board[x][i].getPiece().getType() != PieceType.WQUEEN &&
//                                        board[x][i].getPiece().getType() != PieceType.WKING) {
//                                    flag = false;
//                                }
//                                // Any white piece behind another piece is not a valid move.
////                                else if (board[x][i].getPiece().getType() == PieceType.WPAWN ||
////                                        board[x][i].getPiece().getType() == PieceType.WROOK ||
////                                        board[x][i].getPiece().getType() == PieceType.WBISHOP ||
////                                        board[x][i].getPiece().getType() == PieceType.WKNIGHT ||
////                                        board[x][i].getPiece().getType() == PieceType.WQUEEN ||
////                                        board[x][i].getPiece().getType() == PieceType.WKING) {
//                                else {
//                                    for (int j = i + 1; j < y0; j++) {
//                                        if (board[x][j].hasPiece())
//                                            flag = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    else if (x == x0 && y > y0) {
//                        System.out.println("--------------------------------------");
//                        for (int i = y0 + 1; i <= y; i++) {
//                            if (board[x][i].hasPiece()) {
//                                System.out.println("board[" + x + "][" + i + "] = " + board[x][i].getPiece().getType());
//                            }
//                            if (board[x][i].hasPiece()) {
//                                if (board[x][i].getPiece().getType() != PieceType.WPAWN &&
//                                        board[x][i].getPiece().getType() != PieceType.WROOK &&
//                                        board[x][i].getPiece().getType() != PieceType.WBISHOP &&
//                                        board[x][i].getPiece().getType() != PieceType.WKNIGHT &&
//                                        board[x][i].getPiece().getType() != PieceType.WQUEEN &&
//                                        board[x][i].getPiece().getType() != PieceType.WKING) {
//                                    flag = false;
//                                }
////                                else if (board[x][i].getPiece().getType() == PieceType.WPAWN ||
////                                        board[x][i].getPiece().getType() == PieceType.WROOK ||
////                                        board[x][i].getPiece().getType() == PieceType.WBISHOP ||
////                                        board[x][i].getPiece().getType() == PieceType.WKNIGHT ||
////                                        board[x][i].getPiece().getType() == PieceType.WQUEEN ||
////                                        board[x][i].getPiece().getType() == PieceType.WKING) {
//                                else {
//                                    for (int j = y0 + 1; j < i; j++) {
//                                        if (board[x][j].hasPiece())
//                                            flag = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    else if (y == y0 && x < x0) {
//                        System.out.println("--------------------------------------");
//                        for (int i = x; i < x0; i++) {
//                            if (board[i][y].hasPiece()) {
//                                System.out.println("board[" + i + "][" + y + "] = " + board[i][y].getPiece().getType());
//                            }
//                            if (board[i][y].hasPiece()) {
//                                if (board[i][y].getPiece().getType() != PieceType.WPAWN &&
//                                        board[i][y].getPiece().getType() != PieceType.WROOK &&
//                                        board[i][y].getPiece().getType() != PieceType.WBISHOP &&
//                                        board[i][y].getPiece().getType() != PieceType.WKNIGHT &&
//                                        board[i][y].getPiece().getType() != PieceType.WQUEEN &&
//                                        board[i][y].getPiece().getType() != PieceType.WKING) {
//                                    flag = false;
//                                }
////                                else if (board[x][i].getPiece().getType() == PieceType.WPAWN ||
////                                        board[x][i].getPiece().getType() == PieceType.WROOK ||
////                                        board[x][i].getPiece().getType() == PieceType.WBISHOP ||
////                                        board[x][i].getPiece().getType() == PieceType.WKNIGHT ||
////                                        board[x][i].getPiece().getType() == PieceType.WQUEEN ||
////                                        board[x][i].getPiece().getType() == PieceType.WKING) {
//                                else {
//                                    for (int j = i + 1; j < x0; j++) {
//                                        if (board[j][y].hasPiece())
//                                            flag = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    else if (y == y0 && x > x0) {
//                        System.out.println("--------------------------------------");
//                        for (int i = x0 + 1; i <= x; i++) {
//                            if (board[i][y].hasPiece()) {
//                                System.out.println("board[" + i + "][" + y + "] = " + board[i][y].getPiece().getType());
//                            }
//                            if (board[i][y].hasPiece()) {
//                                if (board[i][y].getPiece().getType() != PieceType.WPAWN &&
//                                        board[i][y].getPiece().getType() != PieceType.WROOK &&
//                                        board[i][y].getPiece().getType() != PieceType.WBISHOP &&
//                                        board[i][y].getPiece().getType() != PieceType.WKNIGHT &&
//                                        board[i][y].getPiece().getType() != PieceType.WQUEEN &&
//                                        board[i][y].getPiece().getType() != PieceType.WKING) {
//                                    flag = false;
//                                }
////                                else if (board[x][i].getPiece().getType() == PieceType.WPAWN ||
////                                        board[x][i].getPiece().getType() == PieceType.WROOK ||
////                                        board[x][i].getPiece().getType() == PieceType.WBISHOP ||
////                                        board[x][i].getPiece().getType() == PieceType.WKNIGHT ||
////                                        board[x][i].getPiece().getType() == PieceType.WQUEEN ||
////                                        board[x][i].getPiece().getType() == PieceType.WKING) {
//                                else {
//                                    for (int j = x0 + 1; j < i; j++) {
//                                        if (board[j][y].hasPiece())
//                                            flag = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
                break;
            case WROOK:
//                if (x == x0 || y == y0) {
//                    flag = true;
//                    if (x == x0 && y < y0) {
//                        for (int i = y; i < y0; i++) {
//                            if (board[x][i].hasPiece() && (board[x][i].getPiece().getType() != PieceType.BPAWN &&
//                                        board[x][i].getPiece().getType() != PieceType.BROOK &&
//                                        board[x][i].getPiece().getType() != PieceType.BBISHOP &&
//                                        board[x][i].getPiece().getType() != PieceType.BKNIGHT &&
//                                        board[x][i].getPiece().getType() != PieceType.BQUEEN &&
//                                        board[x][i].getPiece().getType() != PieceType.BKING)) {
//                                flag = false;
//                            }
//                            else if (board[x][i].hasPiece()) {
//                                for (int j = i + 1; j < y0; j++) {
//                                    if (board[x][j].hasPiece())
//                                        flag = false;
//                                }
//                            }
//                        }
//                    }
//                    else if (x == x0 && y > y0) {
//                        for (int i = y0 + 1; i <= y; i++) {
//                            if (board[x][i].hasPiece() && (board[x][i].getPiece().getType() != PieceType.BPAWN &&
//                                    board[x][i].getPiece().getType() != PieceType.BROOK &&
//                                    board[x][i].getPiece().getType() != PieceType.BBISHOP &&
//                                    board[x][i].getPiece().getType() != PieceType.BKNIGHT &&
//                                    board[x][i].getPiece().getType() != PieceType.BQUEEN &&
//                                    board[x][i].getPiece().getType() != PieceType.BKING)) {
//                                flag = false;
//                            }
//                            else if (board[x][i].hasPiece()) {
//                                for (int j = y0 + 1; j < i; j++) {
//                                    if (board[x][j].hasPiece())
//                                        flag = false;
//                                }
//                            }
//                        }
//                    }
//                    else if (y == y0 && x < x0) {
//                        for (int i = x; i < x0; i++) {
//                            if (board[i][y].hasPiece() && (board[i][y].getPiece().getType() != PieceType.BPAWN &&
//                                    board[i][y].getPiece().getType() != PieceType.BROOK &&
//                                    board[i][y].getPiece().getType() != PieceType.BBISHOP &&
//                                    board[i][y].getPiece().getType() != PieceType.BKNIGHT &&
//                                    board[i][y].getPiece().getType() != PieceType.BQUEEN &&
//                                    board[i][y].getPiece().getType() != PieceType.BKING)) {
//                                flag = false;
//                            }
//                            else if (board[i][y].hasPiece()) {
//                                for (int j = i + 1; j < x0; j++) {
//                                    if (board[j][y].hasPiece())
//                                        flag = false;
//                                }
//                            }
//                        }
//                    }
//                    else if (y == y0 && x > x0) {
//                        for (int i = x0 + 1; i <= x; i++) {
//                            if (board[i][y].hasPiece() && (board[i][y].getPiece().getType() != PieceType.BPAWN &&
//                                    board[i][y].getPiece().getType() != PieceType.BROOK &&
//                                    board[i][y].getPiece().getType() != PieceType.BBISHOP &&
//                                    board[i][y].getPiece().getType() != PieceType.BKNIGHT &&
//                                    board[i][y].getPiece().getType() != PieceType.BQUEEN &&
//                                    board[i][y].getPiece().getType() != PieceType.BKING)) {
//                                flag = false;
//                            }
//                            else if (board[i][y].hasPiece()) {
//                                for (int j = x0 + 1; j < i; j++) {
//                                    if (board[j][y].hasPiece())
//                                        flag = false;
//                                }
//                            }
//                        }
//                    }
//                }
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

    boolean checkIfWithinBorder(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkHasSameSideOnPosition(PieceType type, int x, int y, Tile[][] board) {
        switch (type) {
            case BPAWN:
            case BROOK:
            case BKNIGHT:
            case BBISHOP:
            case BQUEEN:
            case BKING:
                if (board[x][y].getPiece().getType() == PieceType.BPAWN ||
                        board[x][y].getPiece().getType() == PieceType.BROOK ||
                        board[x][y].getPiece().getType() == PieceType.BBISHOP ||
                        board[x][y].getPiece().getType() == PieceType.BKNIGHT ||
                        board[x][y].getPiece().getType() == PieceType.BQUEEN ||
                        board[x][y].getPiece().getType() == PieceType.BKING) {
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
                if (board[x][y].getPiece().getType() == PieceType.WPAWN ||
                        board[x][y].getPiece().getType() == PieceType.WROOK ||
                        board[x][y].getPiece().getType() == PieceType.WBISHOP ||
                        board[x][y].getPiece().getType() == PieceType.WKNIGHT ||
                        board[x][y].getPiece().getType() == PieceType.WQUEEN ||
                        board[x][y].getPiece().getType() == PieceType.WKING) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    boolean checkHasOpponentOnPosition(PieceType type, int x, int y, Tile[][] board) {
        switch (type) {
            case BPAWN:
            case BROOK:
            case BKNIGHT:
            case BBISHOP:
            case BQUEEN:
            case BKING:
                if (board[x][y].getPiece().getType() == PieceType.WPAWN ||
                        board[x][y].getPiece().getType() == PieceType.WROOK ||
                        board[x][y].getPiece().getType() == PieceType.WBISHOP ||
                        board[x][y].getPiece().getType() == PieceType.WKNIGHT ||
                        board[x][y].getPiece().getType() == PieceType.WQUEEN ||
                        board[x][y].getPiece().getType() == PieceType.WKING) {
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
                if (board[x][y].getPiece().getType() == PieceType.BPAWN ||
                        board[x][y].getPiece().getType() == PieceType.BROOK ||
                        board[x][y].getPiece().getType() == PieceType.BBISHOP ||
                        board[x][y].getPiece().getType() == PieceType.BKNIGHT ||
                        board[x][y].getPiece().getType() == PieceType.BQUEEN ||
                        board[x][y].getPiece().getType() == PieceType.BKING) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
