package ChessGame.UI;

import ChessGame.GameLogic.GameBoard;
import ChessGame.GameLogic.Piece.*;
import ChessGame.GameLogic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class UI extends JFrame {
    private JPanel[][] tileList;
    private GameBoard gameBoard;
    private final int tile_size = 100;

    public JPanel[][] getTileList() {
        return tileList;
    }

    public UI(){
        this.setTitle("Chess Game");
        gameBoard = new GameBoard();
        tileList = new JPanel[8][8];
        this.setSize(tile_size*8, tile_size*8);
        this.setLayout(new GridLayout(8,8));
        Container contentPane = getContentPane();
        for(int i = 0; i < 8;i++){
            Tile tile = gameBoard.getTile(6, i);
            Tile tile3 = gameBoard.getTile(1,i);
            tile.setPiece(new Pawn(Color.WHITE));
            tile.setOcc(true);
            tile3.setOcc(true); tile3.setPiece(new Pawn(Color.BLACK));
        }
        Tile tile1 = gameBoard.getTile(7,0); Tile tile2 = gameBoard.getTile(7,7);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Rook(Color.WHITE)); tile2.setPiece(new Rook(Color.WHITE));
        tile1 = gameBoard.getTile(7,1); tile2 = gameBoard.getTile(7,6);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Knight(Color.WHITE)); tile2.setPiece(new Knight(Color.WHITE));
        tile1 = gameBoard.getTile(7,2); tile2 = gameBoard.getTile(7,5);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new Bishop(Color.WHITE)); tile2.setPiece(new Bishop(Color.WHITE));
        tile1 = gameBoard.getTile(7,3); tile2 = gameBoard.getTile(7,4);
        tile1.setOcc(true); tile2.setOcc(true);
        tile1.setPiece(new King(Color.WHITE)); tile2.setPiece(new Queen(Color.WHITE));


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel panel = new TilePanel(gameBoard.getTile(i, j));
                panel.setSize(tile_size, tile_size);
                panel.setVisible(true);
                tileList[i][j] = panel;
                contentPane.add(panel);
            }
        }

        this.setResizable(false);
        this.setVisible(true);

    }
    public void clearPieceFromTile(int row, int col) {
        Tile tile = gameBoard.getTile(row, col);
        tile.setOcc(false);
        tile.setPiece(null);
        tileList[row][col].repaint(); // Repaint the tile panel to reflect the changes
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }


    public static void main(String[] args){
        UI ui = new UI();




    }
}
