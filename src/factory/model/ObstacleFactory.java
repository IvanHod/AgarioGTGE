package factory.model;


import factory.GameObjectFactory;
import gameobject.GameObject;
import gameobject.Obstacle;
import utils.PositionRandomizer;
import view.ObstacleView;

import java.awt.*;
import java.io.IOException;

public class ObstacleFactory extends GameObjectFactory {

    Point position;

    ObstacleView obstacleView;

    @Override
    public GameObject createGameObject() {

        try {
            obstacleView = new ObstacleView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        position = PositionRandomizer.getRandomPosition();

        obstacleView.getObjectSprite().setX(position.x);
        obstacleView.getObjectSprite().setY(position.y);

        return new Obstacle(obstacleView);
    }
}
