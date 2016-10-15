package gamemodel;

import com.golden.gamedev.object.Sprite;
import controller.PlayerBacteriaController;
import factory.GameObjectFactory;
import factory.model.AIBacteriaFactory;
import factory.model.AgarFactory;
import factory.model.ObstacleFactory;
import factory.model.PlayerBacteriaFactory;
import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.Obstacle;
import gameobject.PlayerBacteria;
import listeners.AgarEatenListener;
import listeners.RevealAgarListener;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameModel implements AgarEatenListener{

    final int MAX_OBSTACLES_COUNT = 30;

    final int MAX_AGAR_COUNT = 200;

    final int MAX_AI_BACTERIA_COUNT = 20;

    int agarEatenCount;

    ArrayList<RevealAgarListener> revealAgarListeners = new ArrayList<>();

    GameObjectFactory playerBacteriaFactory = new PlayerBacteriaFactory();

    GameObjectFactory obstacleFactory = new ObstacleFactory();

    GameObjectFactory agarFactory = new AgarFactory();

    GameObjectFactory aiBacteraiFactory = new AIBacteriaFactory();

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

        for (int i = 0; i< MAX_AI_BACTERIA_COUNT; i++) {
            dish.addAiBacteria((AIBacteria) aiBacteraiFactory.createGameObject());
        }

        fireRevealAgar();
    }

    public void update(Point mousePosition) {

        playerBacteriaController.update(mousePosition);
    }

    void fireRevealAgar() {

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
        }, 1, 5, TimeUnit.SECONDS);
    }

    public void addAgarGeneratedListener(RevealAgarListener revealAgarListener) {
        revealAgarListeners.add(revealAgarListener);
    }

    @Override
    public void agarEaten(Sprite agarSprite) {

        agarEatenCount++;
    }

    public int getAgarEatenCount() {
        return  agarEatenCount;
    }
}

