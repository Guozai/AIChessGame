package logic;

import gui.PieceType;

/**
 * Created by yipin on 15/07/2017.
 */
public class CheckMoveValidation {

    public boolean checkValidMove(PieceType type, int x, int y, int x0, int y0) {
        if (checkPieceDefaultValid(type, x, y, x0, y0) && checkIfWithinBorder(x, y)) {
            return true;
        } else {
            return false;
        }
        //checkHasOpponentOnPosition(x, y);
    }

    boolean checkPieceDefaultValid(PieceType type, int x, int y, int x0, int y0) {
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

        // The legal move of black pawn if there is no other piece on the chessboard
        if (type == PieceType.BPAWN) {
            if (y0 == 1) {
                if (x == x0 && (y == 2 || y == 3)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (y0 < 7) {
                    if (x == x0 && y == y0 + 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else if (type == PieceType.WPAWN) {
            if (y0 == 6) {
                if (x == x0 && (y == 5 || y == 4)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (y0 > 0) {
                    if (x == x0 && y == y0 - 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else if (type == PieceType.BROOK || type == PieceType.WROOK) {
            if (x == x0 || y == y0) {
                return true;
            } else {
                return false;
            }
        } else if (type == PieceType.BKNIGHT || type == PieceType.WKNIGHT) {
            if ((x == x0 - 2 && (y == y0 - 1 || y == y0 + 1)) || (x == x0 - 1 && (y == y0 - 2 || y == y0 + 2)) ||
                    (x == x0 + 2 && (y == y0 - 1 || y == y0 + 1)) || (x == x0 + 1 && (y == y0 - 2 || y == y0 + 2))) {
                return true;
            } else {
                return false;
            }
        } else if (type == PieceType.BBISHOP || type == PieceType.WBISH0P) {
            if ((x == x0 - 1 && y == y0 - 1) || (x == x0 - 2 && y == y0 - 2) || (x == x0 - 3 && y == y0 - 3) || (x == x0 - 4 && y == y0 - 4) ||
                    (x == x0 - 5 && y == y0 - 5) || (x == x0 - 6 && y == y0 - 6) || (x == x0 - 7 && y == y0 - 7)) {
                return true;
            } else if ((x == x0 + 1 && y == y0 - 1) || (x == x0 + 2 && y == y0 - 2) || (x == x0 + 3 && y == y0 - 3) || (x == x0 + 4 && y == y0 - 4) ||
                    (x == x0 + 5 && y == y0 - 5) || (x == x0 + 6 && y == y0 - 6) || (x == x0 + 7 && y == y0 - 7)) {
                return true;
            } else if ((x == x0 - 1 && y == y0 + 1) || (x == x0 - 2 && y == y0 + 2) || (x == x0 - 3 && y == y0 + 3) || (x == x0 - 4 && y == y0 + 4) ||
                    (x == x0 - 5 && y == y0 + 5) || (x == x0 - 6 && y == y0 + 6) || (x == x0 - 7 && y == y0 + 7)) {
                return true;
            } else if ((x == x0 + 1 && y == y0 + 1) || (x == x0 + 2 && y == y0 + 2) || (x == x0 + 3 && y == y0 + 3) || (x == x0 + 4 && y == y0 + 4) ||
                    (x == x0 + 5 && y == y0 + 5) || (x == x0 + 6 && y == y0 + 6) || (x == x0 + 7 && y == y0 + 7)) {
                return true;
            } else {
                return false;
            }
        } else if (type == PieceType.BQUEEN || type == PieceType.WQUEEN) {
            if (x == x0 || y == y0) {
                return true;
            } else if ((x == x0 - 1 && y == y0 - 1) || (x == x0 - 2 && y == y0 - 2) || (x == x0 - 3 && y == y0 - 3) || (x == x0 - 4 && y == y0 - 4) ||
                    (x == x0 - 5 && y == y0 - 5) || (x == x0 - 6 && y == y0 - 6) || (x == x0 - 7 && y == y0 - 7)) {
                return true;
            } else if ((x == x0 + 1 && y == y0 - 1) || (x == x0 + 2 && y == y0 - 2) || (x == x0 + 3 && y == y0 - 3) || (x == x0 + 4 && y == y0 - 4) ||
                    (x == x0 + 5 && y == y0 - 5) || (x == x0 + 6 && y == y0 - 6) || (x == x0 + 7 && y == y0 - 7)) {
                return true;
            } else if ((x == x0 - 1 && y == y0 + 1) || (x == x0 - 2 && y == y0 + 2) || (x == x0 - 3 && y == y0 + 3) || (x == x0 - 4 && y == y0 + 4) ||
                    (x == x0 - 5 && y == y0 + 5) || (x == x0 - 6 && y == y0 + 6) || (x == x0 - 7 && y == y0 + 7)) {
                return true;
            } else if ((x == x0 + 1 && y == y0 + 1) || (x == x0 + 2 && y == y0 + 2) || (x == x0 + 3 && y == y0 + 3) || (x == x0 + 4 && y == y0 + 4) ||
                    (x == x0 + 5 && y == y0 + 5) || (x == x0 + 6 && y == y0 + 6) || (x == x0 + 7 && y == y0 + 7)) {
                return true;
            } else {
                return false;
            }
        } else if (type == PieceType.BKING || type == PieceType.WKING) {
            if ((x == x0 - 1 && (y == y0 - 1 || y == y0 || y == y0 + 1)) || (x == x0 && (y == y0 - 1 || y == y0 + 1)) ||
                    (x == x0 + 1 && (y == y0 - 1 || y == y0 || y == y0 + 1))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    boolean checkIfWithinBorder(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkHasSameSideOnPosition(int x, int y) {
        return false;
    }

    boolean checkHasOpponentOnPosition(int x, int y) {
        return false;
    }
}
