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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoard = new GameBoard();
        tileList = new JPanel[8][8];
        this.setSize(tile_size*8, tile_size*8);
        this.setLayout(new GridLayout(8,8));
        Container contentPane = getContentPane();


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
