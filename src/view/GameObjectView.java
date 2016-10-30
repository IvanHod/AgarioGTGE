package view;

import com.golden.gamedev.object.Sprite;

import java.awt.image.BufferedImage;

/**
 * Представление объекта игры
 */
public abstract class GameObjectView {

    /**
     * Изображение объекта игры
     */
    protected BufferedImage gameObjectImage;

    /**
     * Спрайт объекта игры
     */
    protected Sprite gameObjectSprite;

    /**
     * Конструктор класса без параметров
     */
    protected GameObjectView() {
        gameObjectSprite = new Sprite();
    }

    /**
     * Возвращает спрайт объекта игры
     *
     * @return спрайт объекта игры
     */
    public Sprite getObjectSprite() {
        return gameObjectSprite;
    }
}
