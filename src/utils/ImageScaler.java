package utils;


import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageScaler {

    public static BufferedImage scaleImage(BufferedImage before, int scaleX, int scaleY) {


        int scaledX = before.getWidth() + scaleX;
        int scaledY = before.getHeight() + scaleY;

        Image image = before.getScaledInstance(scaledX, scaledY, Image.SCALE_SMOOTH);

        BufferedImage buffered = new BufferedImage(scaledX, scaledY, BufferedImage.TRANSLUCENT);
        buffered.getGraphics().drawImage(image, 0, 0, null);


        return buffered;
    }
}
