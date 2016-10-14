package game.model;

import controller.PlayerBacteriaController;
import dish.model.Dish;
import factory.GameObjectFactory;
import factory.model.AgarFactory;
import factory.model.ObstacleFactory;
import factory.model.PlayerBacteriaFactory;
import gameobject.model.Agar;
import gameobject.model.Obstacle;
import gameobject.model.PlayerBacteria;
import listeners.AgarEatenListener;
import listeners.RevealAgarListener;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameModel implements AgarEatenListener{

    private final int MAX_OBSTACLES_COUNT = 50;

    private final int MAX_AGAR_COUNT = 200;

    private int agarEatenCount = 0;

    private ArrayList<RevealAgarListener> revealAgarListeners = new ArrayList<>();

    GameObjectFactory playerBacteriaFactory = new PlayerBacteriaFactory();

    GameObjectFactory obstacleFactory = new ObstacleFactory();

    GameObjectFactory agarFactory = new AgarFactory();

    PlayerBacteriaController playerBacteriaController;

    Dish dish;

    public GameModel(Dish dish) throws IOException {

        this.dish = dish;

        PlayerBacteria playerBacteria = (PlayerBacteria) playerBacteriaFactory.createGameObject();

        playerBacteriaController = new PlayerBacteriaController(playerBacteria);

        dish.addPlayerBacteria(playerBacteria);

        for (int i = 0; i < MAX_OBSTACLES_COUNT; i++) {
            dish.addObstacle((Obstacle) obstacleFactory.createGameObject());
        }

        for(int i = 0; i < MAX_AGAR_COUNT; i++) {
            dish.addAgar(((Agar) agarFactory.createGameObject()));
        }

        fireRevealAgar();
    }

    public void update(Point mousePosition) {

        playerBacteriaController.update(mousePosition);
    }

    private void fireRevealAgar() {

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {

            int agarRevealedCount;

            @Override
            public void run() {

                for (RevealAgarListener l : revealAgarListeners) {
                    l.revealAgar();
                }

                agarRevealedCount += 10;

                if(agarRevealedCount == MAX_AGAR_COUNT) {
                    exec.shutdown();
                }
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    public void addAgargeneratedListener(RevealAgarListener revealAgarListener) {
        revealAgarListeners.add(revealAgarListener);
    }

    @Override
    public void agarEaten() {
        agarEatenCount++;
    }
}

