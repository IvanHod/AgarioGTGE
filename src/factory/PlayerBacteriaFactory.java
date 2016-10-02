package factory;

import gameobject.model.GameObject;
import gameobject.model.PlayerBacteria;
import sprite.PlayerSprite;
import view.PlayerBacteriaView;

import java.io.IOException;


public class PlayerBacteriaFactory extends GameObjectFactory {

    @Override
    public GameObject createGameObject() {

        PlayerSprite playerSprite = null;

        try {
            playerSprite = new PlayerSprite(new PlayerBacteriaView());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PlayerBacteria(playerSprite);
    }
}
