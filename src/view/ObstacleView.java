package view;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;


public class ObstacleView extends GameObjectView {


    final String OBSTACLE_SPRITE_IMAGE_PATH = "assets/sprites/obstacle/obstacle";

    public ObstacleView() throws IOException {
        int imageIndex = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + imageIndex + ".png"));
        gameObjectSprite.setImage(gameObjectImage);
    }

}
