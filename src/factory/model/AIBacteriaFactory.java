package factory.model;


import java.awt.*;
import java.io.IOException;

import factory.GameObjectFactory;
import gameobject.AIBacteria;
import gameobject.GameObject;
import utils.PositionRandomizer;
import view.AIBacteriaView;

public class AIBacteriaFactory extends GameObjectFactory {

    Point position;

    AIBacteriaView aiBacteriaView;

    @Override
    public GameObject createGameObject() {

        try {
            aiBacteriaView = new AIBacteriaView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        position = PositionRandomizer.getRandomPosition();

        aiBacteriaView.getObjectSprite().setX(position.x);
        aiBacteriaView.getObjectSprite().setY(position.y);

        return new AIBacteria(aiBacteriaView);
    }
}
