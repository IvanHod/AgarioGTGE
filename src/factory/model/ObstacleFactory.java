package factory.model;


import factory.GameObjectFactory;
import factory.GameObjectViewFactory;
import factory.view.ObstacleViewFactory;
import gameobject.model.GameObject;
import gameobject.model.Obstacle;
import sprite.ObstacleSprite;
import view.GameView;
import view.ObstacleView;

import java.util.concurrent.ThreadLocalRandom;

public class ObstacleFactory extends GameObjectFactory {

    int randomX;
    int randomY;

    @Override
    public GameObject createGameObject() {
        ObstacleSprite obstacleSprite = null;

        GameObjectViewFactory gameObjectViewFactory = new ObstacleViewFactory();

        obstacleSprite = new ObstacleSprite((ObstacleView) gameObjectViewFactory.createGameObjectView());

        randomX = ThreadLocalRandom.current().nextInt(20, (int) GameView.viewport().getWidth());
        randomY = ThreadLocalRandom.current().nextInt(20, (int) GameView.viewport().getHeight());
        
        obstacleSprite.setX(randomX);
        obstacleSprite.setY(randomY);

        return new Obstacle(obstacleSprite);
    }
}