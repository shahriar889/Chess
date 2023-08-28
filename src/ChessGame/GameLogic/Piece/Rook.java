package ChessGame.GameLogic.Piece;

import ChessGame.GameLogic.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Rook implements Pieces{
    private final String type = "Rook";
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
    public Point[] getDirection() {
        return direction;
    }
    @Override
    public Color getTileColor(){
        return null;
    }

    public Rook(Color color, Tile tile) {
        this.currentTile = tile;
        this.color = color;
        if(this.color.equals(Color.WHITE)){
            try {
                imgWhite = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/whiteRook.png"));
                imgBlack = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                imgBlack = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/BlackRook.png"));
                imgWhite = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        this.direction = new Point[4];
        this.direction[0] = new Point(1,0); // going down
        this.direction[1] = new Point(-1,0); // going up
        this.direction[2] = new Point(0,1); // going right
        this.direction[3] = new Point(0,-1); //going left

    }

    @Override
    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
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
