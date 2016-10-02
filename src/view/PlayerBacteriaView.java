package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PlayerBacteriaView extends GameObjectView {

    private final String PLAYER_SPRITE_IMAGE_PATH = "assets/sprites/player/cell.png";

    private BufferedImage playerImage;

    public PlayerBacteriaView() throws IOException {
        this.playerImage = ImageIO.read(new File(PLAYER_SPRITE_IMAGE_PATH));
    }

    @Override
    public BufferedImage getObjectImage() {
        return playerImage;
    }
}
