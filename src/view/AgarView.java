package view;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Представление Агара
 */
public class AgarView extends GameObjectView {

    /**
     * Путь к изображению Агара
     */
    private final static String AGAR_SPRITE_IMAGE_PATH = "assets/sprites/agar/agar.png";

    /**
     * Конструктор класса
     *
     * @throws IOException по указанному пути не найден файл
     */
    public AgarView() throws IOException {

        // Загрузить изображение Агара

        gameObjectImage = ImageIO.read(new File(AGAR_SPRITE_IMAGE_PATH));

        // Установить загруженное изображение для спрайта Агара

        gameObjectSprite.setImage(gameObjectImage);
    }
}
