package game.model;

import controller.PlayerBacteriaController;
import dish.model.Dish;
import factory.GameObjectFactory;
import factory.model.ObstacleFactory;
import factory.model.PlayerBacteriaFactory;
import gameobject.model.GameObject;
import gameobject.model.Obstacle;
import gameobject.model.PlayerBacteria;


import java.awt.*;
import java.io.IOException;


public class GameModel {

    private final int MAX_OBSTACLES_COUNT = 10;


    GameObjectFactory playerBacteriaFactory = new PlayerBacteriaFactory();

    GameObjectFactory obstacleFactory = new ObstacleFactory();

    PlayerBacteriaController playerBacteriaController;

    Dish dish;

    public GameModel() throws IOException {

        dish = new Dish();

        PlayerBacteria playerBacteria = (PlayerBacteria) playerBacteriaFactory.createGameObject();

        playerBacteriaController = new PlayerBacteriaController(playerBacteria);

        dish.addPlayerBacteria(playerBacteria);

        for (int i = 0; i < MAX_OBSTACLES_COUNT; i++) {
            dish.addObstacle((Obstacle) obstacleFactory.createGameObject());
        }

    }

    public void update(Point mousePosition) {
        playerBacteriaController.update(mousePosition);
    }


    public Dish dish() {
        return dish;
    }

}
