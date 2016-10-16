package factory.model;

import factory.GameObjectFactory;
import gameobject.Agar;
import gameobject.GameObject;
import utils.PositionRandomizer;
import view.AgarView;
import view.GameObjectView;

import java.awt.*;
import java.io.IOException;

public class AgarFactory extends GameObjectFactory {

    Point position;

    AgarView agarView;

    @Override
    public GameObject createGameObject() {

        try {

            agarView = new AgarView();

        } catch (IOException e) {
            e.printStackTrace();
        }

        position = PositionRandomizer.getRandomPosition();

        agarView.getObjectSprite().setX(position.x);
        agarView.getObjectSprite().setY(position.y);

        return new Agar(agarView);
    }
}
