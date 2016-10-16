package factory.model;

import factory.GameObjectFactory;
import gameobject.GameObject;
import gameobject.PlayerBacteria;
import view.PlayerBacteriaView;

import java.io.IOException;


public class PlayerBacteriaFactory extends GameObjectFactory {

    @Override
    public GameObject createGameObject() {

        try {
            return new PlayerBacteria(new PlayerBacteriaView());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
