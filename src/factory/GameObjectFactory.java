package factory;

import gameobject.GameObject;

/**
 * Фабрика объектов игры
 */
public abstract class GameObjectFactory {

    /**
     * Создает объект игры
     *
     * @return объект игры
     */
    public abstract GameObject createGameObject(int level);
}
