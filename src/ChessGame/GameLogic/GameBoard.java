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
            tile.setPiece(new Pawn(Color.WHITE, tile));
            P1Pieces.add(tile.getPiece());
            tile.setOcc(true);
            tile3.setOcc(true); tile3.setPiece(new Pawn(Color.BLACK, tile3));
            P2Pieces.add(tile3.getPiece());
        }
        Tile tile1 = this.getTile(7,0); Tile tile2 = this.getTile(7,7);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Rook(Color.WHITE, tile1)); tile2.setPiece(new Rook(Color.WHITE, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,1); tile2 = this.getTile(7,6);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Knight(Color.WHITE, tile1)); tile2.setPiece(new Knight(Color.WHITE, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,2); tile2 = this.getTile(7,5);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Bishop(Color.WHITE, tile1)); tile2.setPiece(new Bishop(Color.WHITE, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,3); tile2 = this.getTile(7,4);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new King(Color.WHITE, tile1)); tile2.setPiece(new Queen(Color.WHITE, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,0); tile2 = this.getTile(0,7);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Rook(Color.BLACK, tile1)); tile2.setPiece(new Rook(Color.BLACK, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,1); tile2 = this.getTile(0,6);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Knight(Color.BLACK, tile1)); tile2.setPiece(new Knight(Color.BLACK, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,2); tile2 = this.getTile(0,5);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Bishop(Color.BLACK, tile1)); tile2.setPiece(new Bishop(Color.BLACK, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,3); tile2 = this.getTile(0,4);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new King(Color.BLACK, tile1)); tile2.setPiece(new Queen(Color.BLACK, tile2));
        P1Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
    }
    public Tile getTile(int row, int col){
        return this.board[row][col];
    }

    public Moves getPieceMove(Pieces pieces){
        boolean isStop = false;

        Tile tile = pieces.getCurrentTile();
        int xCur = tile.getRow();
        int yCur = tile.getCol();
        Moves moves = new Moves();
        if(pieces.getType().equals("Pawn")){
            if(!this.getTile(xCur, yCur+1).isOcc() && yCur+1 < 8){
                moves.getMoveList().add(this.getTile(xCur, yCur+1));
            }
            if(this.getTile(xCur+1,yCur+1).isOcc() && !this.getTile(xCur+1, yCur+1).getPiece().getColor().equals(pieces.getColor())
            && xCur+1 < 8 && yCur+1 < 8){
                if(this.getTile(xCur+1, 1).getPiece().getType().equals("King")) {
                    moves.setCheck(true);
                    return moves;
                }
                moves.getMoveList().add(this.getTile(xCur+1, yCur+1));
            }
        }
        else if (!pieces.getType().equals("Pawn") && !pieces.getType().equals("King")){
            for(int i = 0; i < 8;i++){
                if(pieces.getDirection()[1].x != 0 && pieces.getDirection()[1].y != 0){
                    Point dir = pieces.getDirection()[i];
                    while (isStop == false){
                        if(!this.getTile(xCur+ dir.x, yCur+dir.y).isOcc() && xCur+dir.x >= 0 && xCur+dir.x < 8
                                && yCur+dir.y >= 0 && yCur+dir.y < 8){
                            moves.getMoveList().add(this.getTile(xCur+ dir.x, yCur+dir.y));
                            xCur = xCur+dir.x; yCur = yCur+dir.y;
                        }
                        else {
                            isStop = true;
                        }
                    }
                    if(!this.getTile(xCur+dir.x, yCur+dir.y).getPiece().getColor().equals(pieces.getColor()) && xCur+dir.x >= 0 && xCur+dir.x < 8
                            && yCur+dir.y >= 0 && yCur+dir.y < 8){
                        if(this.getTile(xCur+dir.x, yCur+dir.y).getPiece().getType().equals("King")) {
                            moves.setCheck(true);
                            break;
                        }
                            moves.getMoveList().add(this.getTile(xCur+dir.x, yCur+dir.y));
                    }
                }
            }

        }
        else {
            for(int i = 0; i < 8; i++){
                Point dir = pieces.getDirection()[i];
                if(!this.getTile(xCur+dir.x, yCur+dir.y).isOcc() && xCur+dir.x >= 0 && xCur+dir.x < 8
                        && yCur+dir.y >= 0 && yCur+dir.y < 8){
                    moves.getMoveList().add(this.getTile(xCur+dir.x, yCur+dir.y));
                }
                else if(!this.getTile(xCur+dir.x, yCur+dir.y).getPiece().getColor().equals(pieces.getColor()) && xCur+dir.x >= 0 && xCur+dir.x < 8
                        && yCur+dir.y >= 0 && yCur+dir.y < 8){
                    if(this.getTile(xCur+dir.x, yCur+dir.y).getPiece().getType().equals("King")) {
                        moves.setCheck(true);
                        return moves;
                    }
                    moves.getMoveList().add(this.getTile(xCur+dir.x, yCur+dir.y));
                }
            }
        }

        return moves;
    }
}
