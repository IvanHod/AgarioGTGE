package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


public class ObstacleView extends GameObjectView {


    private final String OBSTACLE_SPRITE_IMAGE_PATH = "assets/sprites/obstacle/obstacle";

    private BufferedImage obstacleImage;

    public ObstacleView() throws IOException {
        int imageIndex = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        this.obstacleImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + imageIndex + ".png"));
    }


    @Override
    public BufferedImage getObjectImage() {
        return obstacleImage;
    }
}
