package factory.model;

import factory.GameObjectFactory;
import factory.GameObjectViewFactory;
import factory.view.PlayerBacteriaViewFactory;
import gameobject.GameObject;
import gameobject.PlayerBacteria;
import sprite.PlayerBacteriaSprite;
import view.PlayerBacteriaView;


public class PlayerBacteriaFactory extends GameObjectFactory {

    @Override
    public GameObject createGameObject() {

        PlayerBacteriaSprite playerBacteriaSprite = null;

        GameObjectViewFactory playerBacteriaViewFactory = new PlayerBacteriaViewFactory();

        playerBacteriaSprite = new PlayerBacteriaSprite((PlayerBacteriaView) playerBacteriaViewFactory.createGameObjectView());

        return new PlayerBacteria(playerBacteriaSprite);
    }
}
