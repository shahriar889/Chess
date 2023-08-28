package ChessGame.GameLogic;

import java.util.ArrayList;

public class Moves{
    private ArrayList<Tile> moveList;
    private boolean isCheck;



    public Moves() {
        this.moveList = new ArrayList<>();
        this.isCheck = false;
    }

    public ArrayList<Tile> getMoveList() {
        return moveList;
    }

    public void setMoveList(ArrayList<Tile> moveList) {
        this.moveList = moveList;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
