package ChessGame.UI;

import ChessGame.GameLogic.GameBoard;
import ChessGame.GameLogic.Moves;
import ChessGame.GameLogic.Piece.*;
import ChessGame.GameLogic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                final int x = i;
                final int y = j;
                JPanel panel = new TilePanel(gameBoard.getTile(i, j));
                panel.setSize(tile_size, tile_size);
                panel.setVisible(true);
                tileList[i][j] = panel;
                tileList[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(getGameBoard().getTile(x,y).isOcc()){
                            System.out.println("Tile is occupied and at row: "+ x+ " at column: "+y);
                            Pieces pieces = gameBoard.getTile(x,y).getPiece();
                            Moves moves = gameBoard.getPieceMove(pieces);
                            for(int xx = 0; xx< moves.getMoveList().size(); xx++){
                                System.out.println("Tile x co-ordinate: "+moves.getMoveList().get(xx).getRow()+ ", "+
                                "y co-ordinate: "+moves.getMoveList().get(xx).getCol());
                            }
                        }
                    }
                });
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
