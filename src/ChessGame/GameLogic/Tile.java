package ChessGame.GameLogic;

import ChessGame.GameLogic.Piece.Pieces;

import java.awt.*;

public class Tile {
    private int col; //variable to store column of tile
    private int row; //variable to store row of tile
    private int pos; //variable to store position of tile
    private Color color;
    private boolean isOcc; //variable store if tile is occupied or not
    private Pieces piece;

    public int getCol() {
        return col;
    }

    public boolean isOcc() {
        return isOcc;
    }

    public void setOcc(boolean occ) {
        isOcc = occ;
    }

    public int getRow() {
        return row;
    }

    public int getPos() {
        return pos;
    }

    public Color getColor() {
        return color;
    }



    //constructor
    public Tile(Color color, int row, int col){
        this.col = col;
        this.row = row;
        this.color = color;
        this.pos = (row*10)+col;
        this.isOcc = false;
        this.piece = null;
    }

    public Pieces getPiece() {
        return piece;
    }

    public void setPiece(Pieces piece) {
        this.piece = piece;
    }
}
