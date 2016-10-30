package view;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

/**
 * Представление ИИБактерии
 */
public class AIBacteriaView extends GameObjectView {

    /**
     * Путь к изображению ИИБактерии
     */
    private final static String AI_SPRITE_IMAGE_PATH = "assets/sprites/enemy/bacteria";

    /**
     * Количество изображений для ИИБактерии
     */
    private final static int AIBACTERIA_IMAGES_COUNT = 4;

    /**
     * Конструктор класса
     *
     * @throws IOException по указанному пути не найден файл
     */
    public AIBacteriaView() throws IOException {

        // Загрузить случайное изображение ИИБактерии

        int imageIndex = ThreadLocalRandom.current().nextInt(1, AIBACTERIA_IMAGES_COUNT + 1);
        gameObjectImage = ImageIO.read(new File(AI_SPRITE_IMAGE_PATH + imageIndex + ".png"));

        // Установить загруженное изображение для спрайта ИИБактерии

        gameObjectSprite.setImage(gameObjectImage);
    }
}
