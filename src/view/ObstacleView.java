package view;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

/**
 * Представление Препятствия
 */
public class ObstacleView extends GameObjectView {

    /**
     * Путь к изображению Препятствия
     */
    private final static String OBSTACLE_SPRITE_IMAGE_PATH = "assets/sprites/obstacle/obstacle";

    /**
     * Количество изображений для Препятствия
     */
    private final static int OBSTACLE_IMAGES_COUNT = 3;

    /**
     * Конструктор класса
     *
     * @throws IOException по указанному пути не найден файл
     */
    public ObstacleView() throws IOException {

        // // Загрузить случайное изображение Препятствия

        int imageIndex = ThreadLocalRandom.current().nextInt(1, OBSTACLE_IMAGES_COUNT + 1);
        gameObjectImage = ImageIO.read(new File(OBSTACLE_SPRITE_IMAGE_PATH + imageIndex + ".png"));

        // Установить загруженное изображение для спрайта Препятствия

        gameObjectSprite.setImage(gameObjectImage);
    }

}
