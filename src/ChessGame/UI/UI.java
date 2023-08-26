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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UI extends JFrame {
    private Pieces checkPiece;

    private Color isCheckColor;

    enum click_State {
        FIRST_CLICK,
        Second_Click
    }

    private class ClickStateWrapperClass {
        click_State state;

        public ClickStateWrapperClass(click_State initialState) {
            state = initialState;
        }

        public click_State getState() {
            return state;
        }

        public void setState(click_State state) {
            this.state = state;
        }
    }

    private JPanel[][] tileList;
    private GameBoard gameBoard;
    private ClickStateWrapperClass aClass;
    private final int tile_size = 100;
    private Color turn;
    private Pieces selectedPiece;
    private Moves currentPieceMove;

    public JPanel[][] getTileList() {
        return tileList;
    }

    public UI() {
        isCheckColor = null;
        aClass = new ClickStateWrapperClass(click_State.FIRST_CLICK);
        turn = Color.WHITE;
        selectedPiece = null;
        currentPieceMove = new Moves();
        this.setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoard = new GameBoard();
        tileList = new JPanel[8][8];
        this.setSize(tile_size * 8, tile_size * 8);
        this.setLayout(new GridLayout(8, 8));
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
                        if (aClass.getState() == click_State.FIRST_CLICK) {
                            Tile tile = (gameBoard.getTile(x, y));
                            if (tile.isOcc() && turn == tile.getPiece().getColor()) {
                                if (isCheckColor == null || turn == isCheckColor) {
                                    if(isCheckColor == Color.WHITE)
                                        System.out.println(" check on white");
                                    if(isCheckColor == Color.BLACK)
                                        System.out.println("check on black");
                                    currentPieceMove = gameBoard.getPieceMove(tile.getPiece());
                                    if (currentPieceMove.getMoveList().size() == 0) {
                                        currentPieceMove = new Moves();
                                    } else {
                                        aClass.setState(click_State.Second_Click);
                                        selectedPiece = tile.getPiece();
                                        System.out.println("The piece selected is a " + selectedPiece.getColor() + " " + selectedPiece.getType());
                                    }
                                } else {
                                    turn = isCheckColor;
                                }
                            }
                        } else if (aClass.getState() == click_State.Second_Click) {
                            Tile tile = (gameBoard.getTile(x, y));
                            System.out.println(currentPieceMove.getMoveList().size());
                            if (currentPieceMove.getMoveList().contains(tile) && isCheckColor == null) {
                                aClass.setState(click_State.FIRST_CLICK);
                                putPieceInTile(x, y, selectedPiece);
                                if (turn == Color.WHITE) {
                                    turn = Color.BLACK;
                                } else {
                                    turn = Color.WHITE;
                                }
                                isCheckColor = IsCheck(gameBoard.getP1Pieces(), gameBoard.getP2Pieces());
                                if(isCheckColor != null){
                                    checkPiece = tile.getPiece();
                                    System.out.println("Check Happened");
                                }
                                else {
                                    checkPiece = null;
                                }

                            } else if (isCheckColor != null) {
                                System.out.println("Under Check");
                                boolean isCheck = gameBoard.isCheck(x, y, selectedPiece, checkPiece);
                                if (isCheck == false) {
                                    putPieceInTile(x,y, selectedPiece);
                                    isCheckColor = null;
                                    if (turn == Color.WHITE) {
                                        turn = Color.BLACK;
                                    } else {
                                        turn = Color.WHITE;
                                    }
                                    aClass.setState(click_State.FIRST_CLICK);
                                    isCheckColor = IsCheck(gameBoard.getP1Pieces(), gameBoard.getP2Pieces());
                                    if (isCheckColor != null){
                                        checkPiece = tile.getPiece();
                                    }
                                    else {
                                        checkPiece = null;
                                    }
                                } else {
                                    aClass.setState(click_State.FIRST_CLICK);
                                    System.out.println("King is still under Check, choose piece again");
                                }

                            } else {
                                System.out.println("Invalid Move, do again");
                            }

                        }
//                        if(getGameBoard().getTile(x,y).isOcc()){
//                            System.out.println("Tile is occupied and at row: "+ x+ " at column: "+y);
//                            Pieces pieces = gameBoard.getTile(x,y).getPiece();
//                            Moves moves = gameBoard.getPieceMove(pieces);
//                            for(int xx = 0; xx< moves.getMoveList().size(); xx++){
//                                System.out.println("Tile x co-ordinate: "+moves.getMoveList().get(xx).getRow()+ ", "+
//                                "y co-ordinate: "+moves.getMoveList().get(xx).getCol());
//                            }
//                        }
                    }
                });
                contentPane.add(panel);
            }

        }


        this.setResizable(false);
        this.setVisible(true);

    }

    public void putPieceInTile(int row, int col, Pieces pieces) {
        Tile tile = gameBoard.getTile(row, col);
        if (tile.isOcc()) {
            this.gameBoard.removePieceFromPlayer(tile.getPiece());
        }
        Tile tile1 = pieces.getCurrentTile();
        tile1.setOcc(false);
        tile1.setPiece(null);
        int x = tile1.getRow();
        int y = tile1.getCol();
        tileList[x][y].repaint();
        if (tile.isOcc() == false) {
            tile.setOcc(true);
        }
        tile.setPiece(pieces);
        pieces.setCurrentTile(tile);
        tileList[row][col].repaint(); // Repaint the tile panel to reflect the changes
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

 public Color IsCheck(ArrayList<Pieces> white, ArrayList<Pieces> black){
        for(Pieces pieces: white){
            Moves moves = gameBoard.getPieceMove(pieces);
            if(moves.isCheck()) {
                return Color.BLACK;
            }
        }
     for(Pieces pieces: black){
         Moves moves = gameBoard.getPieceMove(pieces);
         if(moves.isCheck()) {
             return Color.WHITE;
         }
     }
     return null;

    }




    public static void main(String[] args){
        UI ui = new UI();




    }
}
