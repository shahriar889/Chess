package ChessGame.GameLogic;

import ChessGame.GameLogic.Piece.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        P1Pieces.add(tile1.getPiece()); P1Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,1); tile2 = this.getTile(7,6);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Knight(Color.WHITE, tile1)); tile2.setPiece(new Knight(Color.WHITE, tile2));
        P1Pieces.add(tile1.getPiece()); P1Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,2); tile2 = this.getTile(7,5);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Bishop(Color.WHITE, tile1)); tile2.setPiece(new Bishop(Color.WHITE, tile2));
        P1Pieces.add(tile1.getPiece()); P1Pieces.add(tile2.getPiece());
        tile1 = this.getTile(7,3); tile2 = this.getTile(7,4);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new King(Color.WHITE, tile1)); tile2.setPiece(new Queen(Color.WHITE, tile2));
        P1Pieces.add(tile1.getPiece()); P1Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,0); tile2 = this.getTile(0,7);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Rook(Color.BLACK, tile1)); tile2.setPiece(new Rook(Color.BLACK, tile2));
        P2Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,1); tile2 = this.getTile(0,6);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Knight(Color.BLACK, tile1)); tile2.setPiece(new Knight(Color.BLACK, tile2));
        P2Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,2); tile2 = this.getTile(0,5);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Bishop(Color.BLACK, tile1)); tile2.setPiece(new Bishop(Color.BLACK, tile2));
        P2Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
        tile1 = this.getTile(0,3); tile2 = this.getTile(0,4);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new King(Color.BLACK, tile1)); tile2.setPiece(new Queen(Color.BLACK, tile2));
        P2Pieces.add(tile1.getPiece()); P2Pieces.add(tile2.getPiece());
//        tile1 = this.getTile(5,4); tile1.setOcc(true); tile1.setPiece(new Pawn(Color.BLACK, tile1));
//        tile2 = this.getTile(3,3); tile2.setOcc(true); tile2.setPiece(new Knight(Color.white, tile2));

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
//            System.out.println("In White Pawn");
            if(this.getTile(xCur-1,yCur) != null){
                if(this.getTile(xCur-1,yCur).isOcc() == false) {
//                    System.out.println("X: " + (xCur -1)+ " Y: " + (yCur));
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
//            System.out.println("In Black Pawn");
            if(this.getTile(xCur+1,yCur) != null){
                if(this.getTile(xCur+1,yCur).isOcc() == false) {
//                    System.out.println("X: " + (xCur +1)+ " Y: " + (yCur));
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
//            System.out.println("This piece is a "+ pieces.getColor().toString()+" "+pieces.getType());
            for(int i = 0; i < pieces.getDirection().length;i++){
                int xCur = tile.getRow();
                int yCur = tile.getCol();
//                System.out.println(pieces.getDirection().length);
//                System.out.println("In direction "+i);
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

    public void removePieceFromPlayer(Pieces pieces){
        Color color = pieces.getColor();
        if(color == Color.WHITE){
            this.P1Pieces.remove(pieces);
        }
        else {
            this.P2Pieces.remove(pieces);
        }
    }

    public void addPieceInPlayer(Pieces pieces){
        Color color = pieces.getColor();
        if(color == Color.WHITE){
            this.P1Pieces.add(pieces);
        }
        else {
            this.P2Pieces.add(pieces);
        }
    }

    public Color isCheck(){
        for (Pieces pieces: P1Pieces){
            Moves moves = getPieceMove(pieces);
            if(moves.isCheck()){
                return Color.BLACK;
            }
        }
        for (Pieces pieces: P2Pieces){
            Moves moves = getPieceMove(pieces);
            if(moves.isCheck()){
                return Color.WHITE;
            }
        }
        return null;

    }

    public ArrayList<Pieces> getP1Pieces() {
        return P1Pieces;
    }

    public ArrayList<Pieces> getP2Pieces() {
        return P2Pieces;
    }
    public boolean isCheckMate(Color turn){
        ArrayList<Pieces> player;
        if(turn == Color.WHITE){
            player = P1Pieces;
        }
        else {
            player = P2Pieces;
        }
        for (Pieces playerPieces: player) {
            Moves playerMoves = getPieceMove(playerPieces);
            for (Tile playerMovesTile : playerMoves.getMoveList()) {
                if (canDoMove(playerPieces, playerMovesTile) == true) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean canDoMove(Pieces pieces, Tile tile){
        Color opp = (pieces.getColor() == Color.WHITE) ? Color.BLACK:Color.WHITE;
        Moves moves = getPieceMove(pieces); // moves for piece sent
        Tile pieceTile = pieces.getCurrentTile(); // current tile of piece sent;
        boolean isOccBe = tile.isOcc(); // is tile occ before move
        Pieces tilePiece = tile.getPiece(); // piece in tile before move
        if (moves.getMoveList().contains(tile)) {
            if(isOccBe == true){
                removePieceFromPlayer(tilePiece);
            }
            pieceTile.setOcc(false); pieceTile.setPiece(null);
            tile.setOcc(true); tile.setPiece(pieces);
            if(isCheck() == null || isCheck() == opp){
                tile.setOcc(isOccBe);
                pieceTile.setOcc(true); pieceTile.setPiece(pieces);
                if(isOccBe == true){
                    addPieceInPlayer(tilePiece);
                    tile.setPiece(tilePiece);
                }
                return true;
            }
        }
        if(isOccBe == true){
            addPieceInPlayer(tilePiece);
            tile.setPiece(tilePiece);
        }
        tile.setOcc(isOccBe);
        pieceTile.setOcc(true); pieceTile.setPiece(pieces);
        return false;
    }
    public boolean isStaleMate(){
        if(P1Pieces.size() == 1 && P2Pieces.size() == 1){
            if(P1Pieces.get(0).getType().equals("King") && P2Pieces.get(0).getType().equals("King")){
                return true;
            }
        }
        else if(P1Pieces.size() == 2 && P2Pieces.size() == 1){
            ArrayList<String> P1String = new ArrayList<>();
            P1String.add(P1Pieces.get(0).getType());
            P1String.add(P1Pieces.get(1).getType());
            Pieces pieces2 = P2Pieces.get(0);
            if(P1String.contains("King")){
                if(P1String.contains("Bishop") || P1String.contains("Knight")) {
                    if (pieces2.getType().equals("King")) {
                        return true;
                    }
                }
            }
        }
        else if(P1Pieces.size() == 1 && P2Pieces.size() == 2){
            ArrayList<String> P2String = new ArrayList<>();
            P2String.add(P2Pieces.get(0).getType());
            P2String.add(P2Pieces.get(1).getType());
            Pieces pieces2 = P1Pieces.get(0);
            if(P2String.contains("King")){
                if(P2String.contains("Bishop") || P2String.contains("Knight")) {
                    if (pieces2.getType().equals("King")) {
                        return true;
                    }
                }
            }
        }
        else if(P1Pieces.size() == 2 && P2Pieces.size() == 2){
            ArrayList<String> P2String = new ArrayList<>();
            P2String.add(P2Pieces.get(0).getType());
            P2String.add(P2Pieces.get(1).getType());
            ArrayList<String> P1String = new ArrayList<>();
            P2String.add(P1Pieces.get(0).getType());
            P2String.add(P1Pieces.get(1).getType());
            if (P1String.contains("King") && P2String.contains("King")) {
                if (P1String.contains("Bishop") && P2String.contains("Bishop")) {
                    Color colorP1 = Color.BLUE;
                    Color colorP2 = Color.CYAN;
                    for (int i = 0; i < 2; i++) {
                        if (P1Pieces.get(0).getTileColor() != null) {
                            colorP1 = P1Pieces.get(0).getTileColor();
                        }
                        if (P2Pieces.get(0).getTileColor() != null) {
                            colorP2 = P2Pieces.get(0).getTileColor();
                        }
                        if (colorP1 == colorP2){
                            return true;
                        }

                    }
                }
            }
        }
        else {
            for(Pieces pieces:P1Pieces){
                Moves moves = getPieceMove(pieces);
                for(Tile tile:moves.getMoveList()){
                    if(canDoMove(pieces, tile) == true){
                        return false;
                    }
                }
            }
            for(Pieces pieces:P2Pieces){
                Moves moves = getPieceMove(pieces);
                for(Tile tile:moves.getMoveList()){
                    if(canDoMove(pieces, tile) == true){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}