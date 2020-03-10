package logic;

import gui.PieceType;
import gui.Tile;

/**
 * Created by yipin on 15/07/2017.
 */
public class CheckMoveValidation {

    public boolean checkValidMove(PieceType type, int x, int y, int x0, int y0, Tile[][] board) {
        if (checkPieceDefaultValid(type, x, y, x0, y0, board) && checkIfWithinBorder(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkPieceDefaultValid(PieceType type, int x, int y, int x0, int y0, Tile[][] board) {
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

        boolean flag = false;
        // The legal move of black pawn if there is no other piece on the chessboard
        switch(type) {
            case BPAWN:
                if (y0 == 1) {
                    if (x == x0 && (y == 2 || y == 3) && !board[x][y].hasPiece()) {
                        flag = true;
                    }
                } else {
                    if (y0 < 7) {
                        if (x == x0 && y == y0 + 1 && !board[x][y].hasPiece()) {
                            flag = true;
                        }
                    }
                }
                break;
            case WPAWN:
                if (y0 == 6) {
                    if (x == x0 && (y == 5 || y == 4) && !board[x][y].hasPiece()) {
                        flag = true;
                    }
                } else {
                    if (y0 > 0) {
                        if (x == x0 && y == y0 - 1 && !board[x][y].hasPiece()) {
                            flag = true;
                        }
                    }
                }
                break;
            case BROOK:
            case WROOK:
                if (x == x0 || y == y0) {
                    flag = true;
                    if (x == x0 && y < y0) {
                        for (int i = y; i < y0; i++) {
                            if (board[x][i].hasPiece())
//                                if (!board[x][i+1].hasPiece() && (board[x][i].getPiece().getType() == PieceType.BPAWN ||
//                                        board[x][y].getPiece().getType() == PieceType.BROOK ||
//                                        board[x][y].getPiece().getType() == PieceType.BBISHOP ||
//                                        board[x][y].getPiece().getType() == PieceType.BKNIGHT ||
//                                        board[x][y].getPiece().getType() == PieceType.BQUEEN ||
//                                        board[x][y].getPiece().getType() == PieceType.BKING))
//                                    flag = false;
//                                else
//                                    flag = true;
                                flag = false;
                        }
                    }
                    if (x == x0 && y > y0) {
                        for (int i = y0 + 1; i <= y; i++) {
                            if (board[x][i].hasPiece())
                                flag = false;
                        }
                    }
                    if (y == y0 && x < x0) {
                        for (int i = x; i < x0; i++) {
                            if (board[i][y].hasPiece())
                                flag = false;
                        }
                    }
                    if (y == y0 && x > x0) {
                        for (int i = x0 + 1; i <= x; i++) {
                            if (board[i][y].hasPiece())
                                flag = false;
                        }
                    }
                }
                break;
            case BKNIGHT:
            case WKNIGHT:
                if (((x == x0 - 2 && (y == y0 - 1 || y == y0 + 1)) || (x == x0 - 1 && (y == y0 - 2 || y == y0 + 2)) ||
                        (x == x0 + 2 && (y == y0 - 1 || y == y0 + 1)) || (x == x0 + 1 && (y == y0 - 2 || y == y0 + 2)))
                        && !board[x][y].hasPiece()) {
                    flag = true;
                }
                break;
            case BBISHOP:
            case WBISH0P:
                if (((x == x0 - 1 && y == y0 - 1) || (x == x0 - 2 && y == y0 - 2) || (x == x0 - 3 && y == y0 - 3) || (x == x0 - 4 && y == y0 - 4) ||
                        (x == x0 - 5 && y == y0 - 5) || (x == x0 - 6 && y == y0 - 6) || (x == x0 - 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
                    flag = true;
                } else if (((x == x0 + 1 && y == y0 - 1) || (x == x0 + 2 && y == y0 - 2) || (x == x0 + 3 && y == y0 - 3) || (x == x0 + 4 && y == y0 - 4) ||
                        (x == x0 + 5 && y == y0 - 5) || (x == x0 + 6 && y == y0 - 6) || (x == x0 + 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
                    flag = true;
                } else if (((x == x0 - 1 && y == y0 + 1) || (x == x0 - 2 && y == y0 + 2) || (x == x0 - 3 && y == y0 + 3) || (x == x0 - 4 && y == y0 + 4) ||
                        (x == x0 - 5 && y == y0 + 5) || (x == x0 - 6 && y == y0 + 6) || (x == x0 - 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
                    flag = true;
                } else if (((x == x0 + 1 && y == y0 + 1) || (x == x0 + 2 && y == y0 + 2) || (x == x0 + 3 && y == y0 + 3) || (x == x0 + 4 && y == y0 + 4) ||
                        (x == x0 + 5 && y == y0 + 5) || (x == x0 + 6 && y == y0 + 6) || (x == x0 + 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
                    flag = true;
                }
                break;
            case BQUEEN:
            case WQUEEN:
                if ((x == x0 || y == y0) && !board[x][y].hasPiece()) {
                    flag = true;
                } else if (((x == x0 - 1 && y == y0 - 1) || (x == x0 - 2 && y == y0 - 2) || (x == x0 - 3 && y == y0 - 3) || (x == x0 - 4 && y == y0 - 4) ||
                        (x == x0 - 5 && y == y0 - 5) || (x == x0 - 6 && y == y0 - 6) || (x == x0 - 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
                    flag = true;
                } else if (((x == x0 + 1 && y == y0 - 1) || (x == x0 + 2 && y == y0 - 2) || (x == x0 + 3 && y == y0 - 3) || (x == x0 + 4 && y == y0 - 4) ||
                        (x == x0 + 5 && y == y0 - 5) || (x == x0 + 6 && y == y0 - 6) || (x == x0 + 7 && y == y0 - 7)) && !board[x][y].hasPiece()) {
                    flag = true;
                } else if (((x == x0 - 1 && y == y0 + 1) || (x == x0 - 2 && y == y0 + 2) || (x == x0 - 3 && y == y0 + 3) || (x == x0 - 4 && y == y0 + 4) ||
                        (x == x0 - 5 && y == y0 + 5) || (x == x0 - 6 && y == y0 + 6) || (x == x0 - 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
                    flag = true;
                } else if (((x == x0 + 1 && y == y0 + 1) || (x == x0 + 2 && y == y0 + 2) || (x == x0 + 3 && y == y0 + 3) || (x == x0 + 4 && y == y0 + 4) ||
                        (x == x0 + 5 && y == y0 + 5) || (x == x0 + 6 && y == y0 + 6) || (x == x0 + 7 && y == y0 + 7)) && !board[x][y].hasPiece()) {
                    flag =true;
                }
                break;
            case BKING:
            case WKING:
                if (((x == x0 - 1 && (y == y0 - 1 || y == y0 || y == y0 + 1)) || (x == x0 && (y == y0 - 1 || y == y0 + 1)) ||
                        (x == x0 + 1 && (y == y0 - 1 || y == y0 || y == y0 + 1))) && !board[x][y].hasPiece()) {
                    flag = true;
                }
                break;
            default:
                flag = false;
        }

        return flag;
    }

    boolean checkIfWithinBorder(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkHasSameSideOnPosition(int x, int y, Tile[][] board) {
        if (board[x][y].getPiece().getType() == PieceType.BPAWN ||
            board[x][y].getPiece().getType() == PieceType.BROOK ||
            board[x][y].getPiece().getType() == PieceType.BBISHOP ||
            board[x][y].getPiece().getType() == PieceType.BKNIGHT ||
            board[x][y].getPiece().getType() == PieceType.BQUEEN ||
            board[x][y].getPiece().getType() == PieceType.BKING) {
            return true;
        }
        return false;
    }

//    boolean checkHasOpponentOnPosition(int x, int y, Tile[][] board) { return false; }
}
