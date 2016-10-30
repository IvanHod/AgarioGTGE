package factory.model;


import java.awt.*;
import java.io.IOException;

import factory.GameObjectFactory;
import gameobject.GameObject;
import gameobject.Obstacle;
import utils.PositionRandomizer;
import view.ObstacleView;

/**
 * Фабрика Препятствий
 */
public class ObstacleFactory extends GameObjectFactory {

    /**
     * Позиция созданного Препятствия на поле
     */
    private Point position;

    /**
     * Представление Препятствия
     */
    private ObstacleView obstacleView;

    /**
     * Создает объект Препятствия
     *
     * @return объект Препятствия
     */
    @Override
    public GameObject createGameObject() {

        try {
            obstacleView = new ObstacleView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Генерация случайной позиции Препятствия на игровом поле

        position = PositionRandomizer.getRandomPosition();
        obstacleView.getObjectSprite().setX(position.x);
        obstacleView.getObjectSprite().setY(position.y);

        return new Obstacle(obstacleView);
    }
}
