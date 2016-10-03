package factory.model;

import factory.GameObjectFactory;
import factory.GameObjectViewFactory;
import factory.view.AgarViewFactory;
import gameobject.model.Agar;
import gameobject.model.GameObject;
import gameobject.model.Obstacle;
import sprite.AgarSprite;
import view.AgarView;
import view.GameView;

import java.util.concurrent.ThreadLocalRandom;


public class AgarFactory extends GameObjectFactory {

    int randomX;

    int randomY;

    @Override
    public GameObject createGameObject() {

        AgarSprite agarSprite = null;

        GameObjectViewFactory gameObjectViewFactory = new AgarViewFactory();

        agarSprite = new AgarSprite((AgarView) gameObjectViewFactory.createGameObjectView());

        randomX = ThreadLocalRandom.current().nextInt(20, (int) GameView.viewport().getWidth());
        randomY = ThreadLocalRandom.current().nextInt(20, (int) GameView.viewport().getHeight());

        if(randomX == GameView.initialPlayerPosition.x)
            randomX += 100;
        if(randomY == GameView.initialPlayerPosition.y)
            randomY -= 100;

        agarSprite.setX(randomX);
        agarSprite.setY(randomY);

        return new Agar(agarSprite);
    }
}
