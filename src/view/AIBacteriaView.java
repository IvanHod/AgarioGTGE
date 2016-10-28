package view;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class AIBacteriaView extends GameObjectView {

    final static String AI_SPRITE_IMAGE_PATH = "assets/sprites/enemy/bacteria";

    public AIBacteriaView() throws IOException {
        int imageIndex = ThreadLocalRandom.current().nextInt(1, 4 + 1);
        gameObjectImage = ImageIO.read(new File(AI_SPRITE_IMAGE_PATH + imageIndex + ".png"));
        gameObjectSprite.setImage(getObjectImage());
    }
}
