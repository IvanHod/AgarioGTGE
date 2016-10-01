package view.gameobject;


import view.GameObjectView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyBacteriaView extends GameObjectView {

    private final String ENEMY_BACTERIA_SPRITE_IMAGE_PATH = "assets/sprites/enemy/bacteria";

    private final int IMAGES_COUNT = 4;

    BufferedImage enemyImage;


    public EnemyBacteriaView() throws IOException {

        int imageIndex = ThreadLocalRandom.current().nextInt(1, IMAGES_COUNT + 1);

        this.enemyImage = ImageIO.read(new File(ENEMY_BACTERIA_SPRITE_IMAGE_PATH + imageIndex + ".png"));
    }

    @Override
    public BufferedImage getObjectImage() {

        return enemyImage;
    }
}
