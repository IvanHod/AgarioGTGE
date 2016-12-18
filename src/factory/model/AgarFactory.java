package factory.model;

import java.awt.*;
import java.io.IOException;

import factory.GameObjectFactory;
import gameobject.Agar;
import gameobject.GameObject;
import utils.PositionRandomizer;
import view.AgarView;

/**
 * Фабрика Агара
 */
public class AgarFactory extends GameObjectFactory {

    /**
     * Позиция созданного Агара на поле
     */
    private Point position;

    /**
     * Представление Агара
     */
    private AgarView agarView;

    /**
     * Создает объект Агара
     *
     * @return объект Агара
     */
    @Override
    public GameObject createGameObject(int level) {

        try {

            agarView = new AgarView();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Генерация случайной позиции Агара на игровом поле

        position = PositionRandomizer.getRandomPosition();
        agarView.getObjectSprite().setX(position.x);
        agarView.getObjectSprite().setY(position.y);

        return new Agar(agarView);
    }
}
