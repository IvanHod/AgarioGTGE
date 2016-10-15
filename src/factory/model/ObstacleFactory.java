package factory.model;


import factory.GameObjectFactory;
import factory.GameObjectViewFactory;
import factory.view.ObstacleViewFactory;
import gameobject.GameObject;
import gameobject.Obstacle;
import sprite.ObstacleSprite;
import utils.PositionRandomizer;
import view.ObstacleView;

import java.awt.*;

public class ObstacleFactory extends GameObjectFactory {

    Point position;

    @Override
    public GameObject createGameObject() {
        ObstacleSprite obstacleSprite;

        GameObjectViewFactory gameObjectViewFactory = new ObstacleViewFactory();

        obstacleSprite = new ObstacleSprite((ObstacleView) gameObjectViewFactory.createGameObjectView());

        position = PositionRandomizer.getRandomPosition();

        obstacleSprite.setX(position.x);
        obstacleSprite.setY(position.y);

        return new Obstacle(obstacleSprite);
    }
}
