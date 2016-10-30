package gameobject;

import com.golden.gamedev.object.Sprite;

import view.GameObjectView;

/**
 * Объект игры
 */
public abstract class GameObject {

    /**
     * Представление объекта игры
     */
    protected GameObjectView gameObjectView;

    /***
     * Возвращает спрайт объекта игры
     *
     * @return спрайт объекта игры
     */
    public Sprite sprite() {
        return gameObjectView.getObjectSprite();
    }
}
