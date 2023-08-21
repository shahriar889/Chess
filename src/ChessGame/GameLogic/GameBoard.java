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
        tile1 = this.getTile(5,4); tile1.setOcc(true); tile1.setPiece(new Pawn(Color.BLACK, tile1));
        tile2 = this.getTile(3,3); tile2.setOcc(true); tile2.setPiece(new Knight(Color.white, tile2));

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(!this.board[i][j].isOcc()){
                    System.out.println("Tile at row: "+this.board[i][j].getRow()+" and at column "+
                            this.board[i][j].getCol()+ " and it is empty.");
                }
                else {
                    System.out.println("Tile at row: "+this.board[i][j].getRow()+" and at column "+
                            this.board[i][j].getCol()+ " and is not empty empty. The piece in here is a "+
                            this.board[i][j].getPiece().getColor().toString()+ "color "+ this.board[i][j].getPiece().getType());

                }
            }
        }
    }
    public Tile getTile(int row, int col){
        if(row >= 0 && row <8){
            if(col >= 0 && col < 8){
                return this.board[row][col];
            }
        }
        return null;
    }

    public Moves getPieceMove(Pieces pieces){
        boolean isStop = false;

        Tile tile = pieces.getCurrentTile();
        Moves moves = new Moves();
        if(pieces.getType().equals("Pawn") && pieces.getColor() == Color.WHITE){
            int xCur = tile.getRow();
            int yCur = tile.getCol();
            System.out.println("In White Pawn");
            if(this.getTile(xCur-1,yCur) != null){
                if(this.getTile(xCur-1,yCur).isOcc() == false) {
                    System.out.println("X: " + (xCur -1)+ " Y: " + (yCur));
                    moves.getMoveList().add(this.getTile(xCur-1, yCur));
                }
            }
            if(this.getTile(xCur-1,yCur+1) != null){
                if(this.getTile(xCur-1,yCur+1).isOcc() == true && this.getTile(xCur-1,yCur+1).getPiece().getColor() != pieces.getColor()) {
                    if (this.getTile(xCur - 1, yCur + 1).getPiece().getType().equals("King")) {
                        moves.setCheck(true);
                        moves.getMoveList().add(this.getTile(xCur - 1, yCur + 1));
                        return moves;
                    }

                    moves.getMoveList().add(this.getTile(xCur - 1, yCur + 1));
                }
            }
            if(this.getTile(xCur-1,yCur-1) != null){
                if(this.getTile(xCur-1,yCur-1).isOcc() && this.getTile(xCur-1,yCur-1).getPiece().getColor() != pieces.getColor()) {
                    if (this.getTile(xCur - 1, yCur - 1).getPiece().getType().equals("King")) {
                        moves.setCheck(true);
                        moves.getMoveList().add(this.getTile(xCur - 1, yCur - 1));
                        return moves;
                    }

                    moves.getMoveList().add(this.getTile(xCur - 1, yCur - 1));
                }
            }
        }
        else if(pieces.getType().equals("Pawn") && pieces.getColor() == Color.BLACK){
            int xCur = tile.getRow();
            int yCur = tile.getCol();
            System.out.println("In Black Pawn");
            if(this.getTile(xCur+1,yCur) != null){
                if(this.getTile(xCur+1,yCur).isOcc() == false) {
                    System.out.println("X: " + (xCur +1)+ " Y: " + (yCur));
                    moves.getMoveList().add(this.getTile(xCur+1, yCur));
                }
            }
            if(this.getTile(xCur+1,yCur+1) != null){
                if(this.getTile(xCur+1,yCur+1).isOcc() == true && this.getTile(xCur+1,yCur+1).getPiece().getColor() != pieces.getColor()) {
                    if (this.getTile(xCur + 1, yCur + 1).getPiece().getType().equals("King")) {
                        moves.setCheck(true);
                        moves.getMoveList().add(this.getTile(xCur + 1, yCur + 1));
                        return moves;
                    }

                    moves.getMoveList().add(this.getTile(xCur + 1, yCur + 1));
                }
            }
            if(this.getTile(xCur+1,yCur-1) != null){
                if(this.getTile(xCur+1,yCur-1).isOcc() && this.getTile(xCur+1,yCur-1).getPiece().getColor() != pieces.getColor()) {
                    if (this.getTile(xCur + 1, yCur - 1).getPiece().getType().equals("King")) {
                        moves.setCheck(true);
                        moves.getMoveList().add(this.getTile(xCur + 1, yCur - 1));
                        return moves;
                    }

                    moves.getMoveList().add(this.getTile(xCur + 1, yCur - 1));
                }
            }
        }
        else if (!pieces.getType().equals("Pawn") && !pieces.getType().equals("King") && !pieces.getType().equals("Knight")){
            System.out.println("This piece is a "+ pieces.getColor().toString()+" "+pieces.getType());
            for(int i = 0; i < pieces.getDirection().length;i++){
                int xCur = tile.getRow();
                int yCur = tile.getCol();
                System.out.println(pieces.getDirection().length);
                System.out.println("In direction "+i);
                    Point dir = pieces.getDirection()[i];
                    while (isStop == false){
                        if(this.getTile(xCur+dir.x, yCur+dir.y) != null){
                            if(this.getTile(xCur+dir.x, yCur+dir.y).isOcc() == false) {
                                moves.getMoveList().add(this.getTile(xCur + dir.x, yCur + dir.y));
                                xCur = xCur + dir.x;
                                yCur = yCur + dir.y;
                            }
                            else {
                                isStop = true;
                            }
                        }
                        else {
                            isStop = true;
                        }
                    }
                    if(this.getTile(xCur+dir.x, yCur+dir.y) != null){
                        if(this.getTile(xCur+dir.x, yCur+dir.y).isOcc() &&this.getTile(xCur+dir.x, yCur+dir.y).getPiece().getColor() != pieces.getColor()) {
                            if (this.getTile(xCur + dir.x, yCur + dir.y).getPiece().getType().equals("King")) {
                                moves.setCheck(true);
                            }
                            moves.getMoveList().add(this.getTile(xCur+dir.x, yCur+dir.y));
                        }
                    }
                isStop = false;
            }

        }
        else {
            for(int i = 0; i < 8; i++){
                int xCur = tile.getRow();
                int yCur = tile.getCol();
                Point dir = pieces.getDirection()[i];
                if(this.getTile(xCur+dir.x, yCur+dir.y) != null){
                    if(this.getTile(xCur+dir.x, yCur+dir.y).isOcc() == false) {
                        moves.getMoveList().add(this.getTile(xCur + dir.x, yCur + dir.y));
                    }
                    if(this.getTile(xCur+dir.x,yCur+dir.y).isOcc() == true && this.getTile(xCur+dir.x,yCur+dir.y).getPiece().getColor() != pieces.getColor()) {
                        if (this.getTile(xCur + dir.x, yCur + dir.y).getPiece().getType().equals("King")) {
                            moves.setCheck(true);
                        }
                        moves.getMoveList().add(this.getTile(xCur+dir.x, yCur+dir.y));
                    }
                }
            }
        }

        return moves;
    }
}
