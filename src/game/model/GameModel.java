package game.model;

import com.golden.gamedev.object.SpriteGroup;
import controller.PlayerBacteriaController;
import dish.model.Dish;
import factory.GameObjectFactory;
import factory.PlayerBacteriaFactory;
import gameobject.model.PlayerBacteria;
import sprite.PlayerSprite;
import view.DishView;
import view.GameView;


import java.awt.*;
import java.io.IOException;


public class GameModel {

    GameObjectFactory gameObjectFactory;

    PlayerBacteria playerBacteria;

    PlayerBacteriaController playerBacteriaController;

    SpriteGroup playerSpriteGroup;

    public GameModel() throws IOException {

        gameObjectFactory = new PlayerBacteriaFactory();

        playerBacteria = (PlayerBacteria) gameObjectFactory.createGameObject();

        playerBacteriaController = new PlayerBacteriaController(playerBacteria);


    }

    public void update(Point p) {
        playerBacteriaController.update(p);
    }

    public PlayerBacteria getPlayerBacteria() {
        return playerBacteria;
    }
}
