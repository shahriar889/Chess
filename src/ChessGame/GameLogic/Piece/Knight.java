package ChessGame.GameLogic.Piece;

import ChessGame.GameLogic.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Knight implements Pieces{
    private final String type = "Knight";
    private Color color;
    private BufferedImage imgWhite;
    private BufferedImage imgBlack;
    @Override
    public ArrayList<Tile> getMoves(){
        return new ArrayList<Tile>();
    }

    public Knight(Color color) {
        this.color = color;
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
