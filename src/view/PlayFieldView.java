package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayFieldView {

    private final String PLAY_FIELD_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";

    private int playFieldHeight;

    private int playFieldWidth;

    private BufferedImage bgImage;

    public PlayFieldView(int height, int width) throws IOException {
        this.playFieldHeight = height;
        this.playFieldWidth = width;
        this.bgImage = ImageIO.read(new File(PLAY_FIELD_BACKGROUND_IMAGE_PATH));
    }

    public int getPlayFieldHeight() {
        return playFieldHeight;
    }

    public int getPlayFieldWidth() {
        return playFieldWidth;
    }
}
