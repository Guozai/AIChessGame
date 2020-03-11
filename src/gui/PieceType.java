package gui;

/**
 * Created by yipin on 15/07/2017.
 */
public enum PieceType {

    BPAWN(-1), BROOK(-2), BBISHOP(-3), BKNIGHT(-4), BQUEEN(-5), BKING(-6), WPAWN(1), WROOK(2), WBISHOP(3), WKNIGHT(4), WQUEEN(5), WKING(6);

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}
