package ChessGame.GameLogic.Piece;

import ChessGame.GameLogic.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Bishop implements Pieces{
    private Color tileColor;
    private final String type = "Bishop";

    @Override
    public Color getTileColor() {
        return tileColor;
    }

    private Tile currentTile;
    private Color color;
    private BufferedImage imgWhite;
    private BufferedImage imgBlack;
    private Point[] direction;



    @Override
    public Tile getCurrentTile() {
        return currentTile;
    }

    @Override
    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    @Override
    public Point[] getDirection() {
        return direction;
    }

    public Bishop(Color color, Tile tile) {
        this.currentTile = tile;
        this.tileColor = tile.getColor();
        this.color = color;
        if(this.color.equals(Color.WHITE)){
            try {
                imgWhite = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/whiteBishop.png"));
                imgBlack = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                imgBlack = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/blackBishop.png"));
                imgWhite = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        this.direction = new Point[4];
        this.direction[0] = new Point(1,1); // going right and down
        this.direction[1] = new Point(-1,-1); // going left and up
        this.direction[2] = new Point(1,-1); //going left and down
        this.direction[3] = new Point(-1,1); //going right and up

    }

    @Override
    public BufferedImage getImgWhite() {
        return imgWhite;
    }

    @Override
    public BufferedImage getImgBlack() {
        return imgBlack;
    }

    @Override
    public Color getColor() {
        return color;
    }



    @Override
    public String getType() {
        return type;
    }
}
