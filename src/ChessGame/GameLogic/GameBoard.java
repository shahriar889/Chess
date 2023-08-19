package ChessGame.GameLogic;

import ChessGame.GameLogic.Piece.*;

import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private Tile[][] board;
    private ArrayList<Pieces> P1Pieces;
    private ArrayList<Pieces> P2Pieces;

    public GameBoard(){
        P1Pieces = new ArrayList<>();
        P2Pieces = new ArrayList<>();
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
        for(int i = 0; i < 8;i++){
            Tile tile = this.getTile(6, i);
            Tile tile3 = this.getTile(1,i);
            tile.setPiece(new Pawn(Color.WHITE));
            P1Pieces.add(tile.getPiece());
            tile.setOcc(true);
            tile3.setOcc(true); tile3.setPiece(new Pawn(Color.BLACK));
            P2Pieces.add(tile3.getPiece());
        }
        Tile tile1 = this.getTile(7,0); Tile tile2 = this.getTile(7,7);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Rook(Color.WHITE)); tile2.setPiece(new Rook(Color.WHITE));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,1); tile2 = this.getTile(7,6);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Knight(Color.WHITE)); tile2.setPiece(new Knight(Color.WHITE));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,2); tile2 = this.getTile(7,5);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Bishop(Color.WHITE)); tile2.setPiece(new Bishop(Color.WHITE));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,3); tile2 = this.getTile(7,4);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new King(Color.WHITE)); tile2.setPiece(new Queen(Color.WHITE));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,0); tile2 = this.getTile(0,7);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Rook(Color.BLACK)); tile2.setPiece(new Rook(Color.BLACK));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,1); tile2 = this.getTile(0,6);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Knight(Color.BLACK)); tile2.setPiece(new Knight(Color.BLACK));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,2); tile2 = this.getTile(0,5);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Bishop(Color.BLACK)); tile2.setPiece(new Bishop(Color.BLACK));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,3); tile2 = this.getTile(0,4);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new King(Color.BLACK)); tile2.setPiece(new Queen(Color.BLACK));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
    }
    public Tile getTile(int row, int col){
        return this.board[row][col];
    }
}
