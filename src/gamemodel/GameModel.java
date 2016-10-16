package gamemodel;

import com.golden.gamedev.object.Sprite;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import controller.AIBacteriaController;
import controller.MovableObjectController;
import controller.PlayerBacteriaController;
import factory.GameObjectFactory;
import factory.model.AIBacteriaFactory;
import factory.model.AgarFactory;
import factory.model.ObstacleFactory;
import factory.model.PlayerBacteriaFactory;
import game.GameView;
import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.Obstacle;
import gameobject.PlayerBacteria;
import listeners.AgarEatenListener;
import listeners.LevelUpListener;
import listeners.RevealAgarListener;


public class GameModel implements AgarEatenListener {

    final int MAX_OBSTACLES_COUNT = 30;

    final int MAX_AGAR_COUNT = 500;

    final int MAX_AI_BACTERIA_COUNT = 30;

    final double PLAYER_SPEED = 0.3;

    final double AI_SPEED = 0.1;

    final int LEVEL_MULTIPLICATOR = 5;

    int agarEatenByPlayerCount;

    ArrayList<RevealAgarListener> revealAgarListeners = new ArrayList<>();

    ArrayList<LevelUpListener> levelUpListeners = new ArrayList<>();

    GameObjectFactory playerBacteriaFactory = new PlayerBacteriaFactory();

    GameObjectFactory obstacleFactory = new ObstacleFactory();

    GameObjectFactory agarFactory = new AgarFactory();

    GameObjectFactory aiBacteraiFactory = new AIBacteriaFactory();

    ArrayList<MovableObjectController> movableObjectControllers = new ArrayList<>();

    Dish dish;

    public GameModel(Dish dish) throws IOException {

        this.dish = dish;

        PlayerBacteria playerBacteria = (PlayerBacteria) playerBacteriaFactory.createGameObject();

        movableObjectControllers.add(new PlayerBacteriaController(playerBacteria));

        playerBacteria.setSpeed(PLAYER_SPEED);

        playerBacteria.setPosition(GameView.initialPlayerPosition);

        dish.addPlayerBacteria(playerBacteria);

        for (int i = 0; i < MAX_OBSTACLES_COUNT; i++) {
            dish.addObstacle((Obstacle) obstacleFactory.createGameObject());
        }

        for (int i = 0; i < MAX_AGAR_COUNT; i++) {
            dish.addAgar(((Agar) agarFactory.createGameObject()));
        }

        for (int i = 0; i < MAX_AI_BACTERIA_COUNT; i++) {
            AIBacteria aiBacteria = (AIBacteria) aiBacteraiFactory.createGameObject();

            aiBacteria.setSpeed(AI_SPEED);

            aiBacteria.setDirection(ThreadLocalRandom.current().nextInt(0, 360));

            movableObjectControllers.add(new AIBacteriaController(playerBacteria, aiBacteria, dish.agar()));

            dish.addAiBacteria(aiBacteria);
        }

        fireRevealAgar();
    }

    public void update(Point mousePosition) {

        for (MovableObjectController movableObjectController : movableObjectControllers) {
            movableObjectController.update(mousePosition);
        }

        System.out.println(dish.playerBacteria().level());

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

                if (agarRevealedCount == MAX_AGAR_COUNT) {
                    exec.shutdown();
                }
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    void fireLevelUp(Sprite movableGameObjectSprite) {
        for (LevelUpListener levelUpListener : levelUpListeners) {
            levelUpListener.levelIncreased(movableGameObjectSprite);
        }
    }

    public void addLevelUpListener(LevelUpListener levelUpListener) {
        levelUpListeners.add(levelUpListener);
    }

    public void addAgarGeneratedListener(RevealAgarListener revealAgarListener) {
        revealAgarListeners.add(revealAgarListener);
    }

    @Override
    public void agarEaten(Sprite movableGameObjectSprite, Sprite agarSprite) {

        if (dish.playerBacteria().sprite() == movableGameObjectSprite) {
            dish.playerBacteria().increaseEatenAgarAmount();
            agarEatenByPlayerCount++;
            if (dish.playerBacteria().agarEatenCount() % LEVEL_MULTIPLICATOR == 0) {
                dish.playerBacteria().leveUp();
                fireLevelUp(movableGameObjectSprite);
            }
        } else {
            for (AIBacteria aiBacteria : dish.aiBacterias()) {
                if (aiBacteria.sprite() == movableGameObjectSprite) {
                    aiBacteria.increaseEatenAgarAmount();

                    if (aiBacteria.agarEatenCount() % LEVEL_MULTIPLICATOR == 0) {
                        aiBacteria.leveUp();
                        fireLevelUp(movableGameObjectSprite);
                    }
                }
            }
        }

    }

    public int getAgarEatenCount() {
        return agarEatenByPlayerCount;
    }
}

