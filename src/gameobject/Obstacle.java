package gameobject;

import view.ObstacleView;

/**
 * Препятствие
 */
public class Obstacle extends GameObject {

    /**
     * Конструктор класса с параметром
     *
     * @param obstacleView представление Препятствия
     */
    public Obstacle(ObstacleView obstacleView) {

        gameObjectView = obstacleView;
    }

}
