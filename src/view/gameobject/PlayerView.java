package view.gameobject;

import view.GameObjectView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerView extends GameObjectView {

    private final String PLAYER_SPRITE_IMAGE_PATH = "assets/sprites/player/cell.png";

    private BufferedImage playerImage;

    public PlayerView() throws IOException {
        this.playerImage = ImageIO.read(new File(PLAYER_SPRITE_IMAGE_PATH));
    }

    @Override
    public BufferedImage getObjectImage() {
        return playerImage;
    }
}
