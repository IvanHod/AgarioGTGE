package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Представление Бактерии игрока
 */
public class PlayerBacteriaView extends GameObjectView {

    /**
     * Путь к изображению бактерии Игрока
     */
    private final static String PLAYER_SPRITE_IMAGE_PATH = "assets/sprites/player/cell.png";

    /**
     * Конструктор класса
     *
     * @throws IOException по указанному пути не найден файл
     */
    public PlayerBacteriaView() throws IOException {

        // Загрузить изображение Бактерии игрока

        gameObjectImage = ImageIO.read(new File(PLAYER_SPRITE_IMAGE_PATH));

        // Установить загруженное изображение для спрайта Бактерии игрока

        gameObjectSprite.setImage(gameObjectImage);
    }

}
