package ChessGame.GameLogic.Piece;

import ChessGame.GameLogic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface Pieces {

     Point[] getDirection();
     Color getColor();
     BufferedImage getImgWhite();
     BufferedImage getImgBlack();
     String getType();
     Tile getCurrentTile();
     void setCurrentTile(Tile tile);

}
