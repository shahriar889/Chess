package ChessGame.GameLogic.Piece;

import ChessGame.GameLogic.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Pawn implements Pieces {
    private final String type = "Pawn";
    private Tile currentTile;
    private Color color;
    private BufferedImage imgWhite;
    private BufferedImage imgBlack;
    private Point[] direction;

    @Override
    public Point[] getDirection() {
        return direction;
    }

    @Override
    public Tile getCurrentTile() {
        return currentTile;
    }

    @Override
    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Pawn(Color color, Tile tile) {
        this.currentTile = tile;
        this.color = color;
        if(this.color.equals(Color.WHITE)){
            try {
                imgWhite = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/whitePawn5.png"));
                imgBlack = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                imgBlack = ImageIO.read(new File("/Users/shahriar/Desktop/Chess/src/ChessGame/GameLogic/Piece/BlackPawn.png"));
                imgWhite = null;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        this.direction = new Point[8];
        this.direction[0] = new Point(0,0);
        this.direction[1] = new Point(0,0);
        this.direction[2] = new Point(0,1);
        this.direction[3] = new Point(0,0);
        this.direction[4] = new Point(0,0);
        this.direction[5] = new Point(0,0);
        this.direction[6] = new Point(0,0);
        this.direction[7] = new Point(0,0);



    }

    @Override
    public Color getTileColor(){
        return null;
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
