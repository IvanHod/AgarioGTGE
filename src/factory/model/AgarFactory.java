package factory.model;

import factory.GameObjectFactory;
import factory.GameObjectViewFactory;
import factory.view.AgarViewFactory;
import gameobject.Agar;
import gameobject.GameObject;
import sprite.AgarSprite;
import utils.PositionRandomizer;
import view.AgarView;

import java.awt.*;

public class AgarFactory extends GameObjectFactory {

    Point position;

    @Override
    public GameObject createGameObject() {

        AgarSprite agarSprite;

        GameObjectViewFactory gameObjectViewFactory = new AgarViewFactory();

        agarSprite = new AgarSprite((AgarView) gameObjectViewFactory.createGameObjectView());

        position = PositionRandomizer.getRandomPosition();

        agarSprite.setX(position.x);
        agarSprite.setY(position.y);

        return new Agar(agarSprite);
    }
}
