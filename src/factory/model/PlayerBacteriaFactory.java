package factory.model;

import java.io.IOException;

import factory.GameObjectFactory;
import gameobject.GameObject;
import gameobject.PlayerBacteria;
import view.PlayerBacteriaView;

/**
 * Фабрика Бактерий игрока
 */
public class PlayerBacteriaFactory extends GameObjectFactory {

    /**
     * Создает объект Бактерии игрока
     *
     * @return объект Бактерии игрока
     */
    @Override
    public GameObject createGameObject(int level) {

        try {
            return new PlayerBacteria(new PlayerBacteriaView());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
