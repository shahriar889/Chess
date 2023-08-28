package ChessGame.GameLogic.Piece;

import ChessGame.GameLogic.Tile;
import com.sun.security.auth.module.JndiLoginModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Knight implements Pieces{
    private final String type = "Knight";
    private Tile currentTile;
    private Color color;
    private BufferedImage imgWhite;
    private BufferedImage imgBlack;
    private Point[] direction;
    @Override
    public Color getTileColor(){
        return null;
    }

    @Override
    public Tile getCurrentTile() {
        return currentTile;
    }

    @Override
    public Point[] getDirection() {
        return direction;
    }

    public Knight(Color color, Tile tile) {
        this.currentTile = tile;
        this.color = color;
        this.direction = new Point[8];
        if(this.color.equals(Color.WHITE)){
            try {
                imgWhite = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/whiteKnight.png"));
                imgBlack = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                imgBlack = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/blackKnight.png"));
                imgWhite = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        this.direction[0] = new Point(1,2);//going right 2 spaces and down 1 space
        this.direction[1] = new Point(-1,2); // going right 2 spaces and up 1 space
        this.direction[2] = new Point(2,1); // going right 1 space and down 2 spaces
        this.direction[3] = new Point(-2,1); //going right 1 space and up 2 spaces
        this.direction[4] = new Point(-1,-2); //going left 2 spaces and up 1 space
        this.direction[5] = new Point(1,-2); //going left 2 spaces and down 1 space
        this.direction[6] = new Point(2,-1);  //going left 1 spaces and down 2 spaces
        this.direction[7] = new Point(-2,-1);  //going left 1 space and up 2 spaces

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
