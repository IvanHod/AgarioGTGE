package view;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AgarView extends GameObjectView {


    final static String AGAR_SPRITE_IMAGE_PATH = "assets/sprites/agar/agar.png";

    public AgarView() throws IOException {
        gameObjectImage = ImageIO.read(new File(AGAR_SPRITE_IMAGE_PATH));
        gameObjectSprite.setImage(gameObjectImage);
    }
}
