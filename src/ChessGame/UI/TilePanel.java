package ChessGame.UI;

import ChessGame.GameLogic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TilePanel extends JPanel {
    private Tile tile;

    public TilePanel(Tile tile) {
        this.tile = tile;
        setBackground(tile.getColor());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tile.isOcc() && tile.getPiece().getColor().equals(Color.WHITE)) {
            BufferedImage img = tile.getPiece().getImgWhite(); // Load the image here

            if (img != null) {
                BufferedImage transparentImg = makeBackgroundTransparent(img, Color.WHITE); // Replace Color.WHITE with the actual background color

                g.drawImage(transparentImg, 0, 0, 100, 100, this);
            }
        }
        else if(tile.isOcc() && tile.getPiece().getColor().equals(Color.BLACK)){
            BufferedImage img = tile.getPiece().getImgBlack();
            if (img != null) {
                BufferedImage transparentImg = makeBackgroundTransparent(img, Color.WHITE); // Replace Color.WHITE with the actual background color

                g.drawImage(transparentImg, 0, 0, 100, 100, this);
            }
        }
        else if(!tile.isOcc()){
            setBackground(tile.getColor());
            g.setColor(tile.getColor());
            
            g.fillRect(0,0,100,100);
        }
    }


    private BufferedImage makeBackgroundTransparent(BufferedImage image, Color backgroundColor) {
        int transparentColor = new Color(0, 0, 0, 0).getRGB();

        BufferedImage transparentImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);

                int pixelRed = (pixel >> 16) & 0xFF;
                int pixelGreen = (pixel >> 8) & 0xFF;
                int pixelBlue = pixel & 0xFF;

                int bgRed = backgroundColor.getRed();
                int bgGreen = backgroundColor.getGreen();
                int bgBlue = backgroundColor.getBlue();

                if (pixelRed != bgRed || pixelGreen != bgGreen || pixelBlue != bgBlue) {
                    transparentImage.setRGB(x, y, pixel);
                } else {
                    transparentImage.setRGB(x, y, transparentColor);
                }
            }
        }

        return transparentImage;
    }
}