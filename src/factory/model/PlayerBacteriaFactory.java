package factory.model;

import java.io.IOException;

import factory.GameObjectFactory;
import gameobject.GameObject;
import gameobject.PlayerBacteria;
import view.PlayerBacteriaView;


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
