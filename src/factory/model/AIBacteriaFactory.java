package factory.model;


import factory.GameObjectFactory;

import factory.GameObjectViewFactory;
import factory.view.AIBacteriaViewFactory;
import gameobject.AIBacteria;
import gameobject.GameObject;
import sprite.AIBacteriaSprite;
import utils.PositionRandomizer;
import view.AIBacteriaView;


import java.awt.*;

public class AIBacteriaFactory extends GameObjectFactory{

    Point position;

    @Override
    public GameObject createGameObject() {

        AIBacteriaSprite aiBacteriaSprite;

        GameObjectViewFactory gameObjectViewFactory = new AIBacteriaViewFactory();

        aiBacteriaSprite = new AIBacteriaSprite((AIBacteriaView) gameObjectViewFactory.createGameObjectView());

        position = PositionRandomizer.getRandomPosition();

        aiBacteriaSprite.setX(position.x);
        aiBacteriaSprite.setY(position.y);

        return new AIBacteria(aiBacteriaSprite);
    }
}
