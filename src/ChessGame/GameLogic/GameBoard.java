package ChessGame.GameLogic;

import ChessGame.GameLogic.Piece.Pieces;

import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private Tile[][] board;
    private ArrayList<Pieces> P1Pieces;
    private ArrayList<Pieces> P2Pieces;

    public GameBoard(){
        this.board = new Tile[8][8];
        int x = 1;
        Color color;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(x%2 == 1){
                    color = new Color(255,229,204);
                }
                else {
                    color = new Color(102,51,0);
                }
                Tile tile = new Tile(color,i,j);
                this.board[i][j] = tile;
                x++;
            }
            x++;
        }
    }
    public Tile getTile(int row, int col){
        return this.board[row][col];
    }
}
