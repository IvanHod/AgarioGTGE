package view;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class PlayerBacteriaView extends GameObjectView {

    final String PLAYER_SPRITE_IMAGE_PATH = "assets/sprites/player/cell.png";

    public PlayerBacteriaView() throws IOException {
        gameObjectImage = ImageIO.read(new File(PLAYER_SPRITE_IMAGE_PATH));
        gameObjectSprite.setImage(getObjectImage());
    }

}
