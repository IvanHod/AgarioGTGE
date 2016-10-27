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
import listeners.GameObjectEatenListener;
import listeners.GameOverListener;
import listeners.LevelUpListener;
import listeners.SpawnGameObjectListener;


public class GameModel implements GameObjectEatenListener {

    final int MAX_OBSTACLES_COUNT = 30;

    final int MAX_AGAR_COUNT = 500;

    final int MAX_AI_BACTERIA_COUNT = 100;

    final double PLAYER_SPEED = 0.3;

    final double AI_SPEED = 0.1;

    final int LEVEL_MULTIPLICATOR = 5;

    int agarEatenByPlayerCount;

    ArrayList<SpawnGameObjectListener> spawnGameObjectListeners = new ArrayList<>();

    ArrayList<LevelUpListener> levelUpListeners = new ArrayList<>();

    ArrayList<GameOverListener> gameOverListeners = new ArrayList<>();

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

            dish.addAIBacteria(aiBacteria);
        }

        fireSpawnAgar();
    }

    public void update(Point mousePosition) {

        for (MovableObjectController movableObjectController : movableObjectControllers) {
            movableObjectController.update(mousePosition);
        }
    }

    public int getAgarEatenCount() {

        return agarEatenByPlayerCount;
    }

    void fireSpawnAgar() {

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {

            int agarSpawnedCount;

            @Override
            public void run() {

                for (SpawnGameObjectListener l : spawnGameObjectListeners) {
                    l.spawnAgar();
                }

                agarSpawnedCount += 10;
                if (agarSpawnedCount == MAX_AGAR_COUNT) {
                    exec.shutdown();
                }
            }
        }, 1, 2, TimeUnit.SECONDS);

    }

    void fireSpawnAI() {
        for(SpawnGameObjectListener l : spawnGameObjectListeners) {
            l.spawnAI();
        }

    }

    void fireLevelUp(Sprite movableGameObjectSprite) {
        for (LevelUpListener levelUpListener : levelUpListeners) {
            levelUpListener.levelIncreased(movableGameObjectSprite);
        }
    }

    void fireGameOver() {
        for(GameOverListener gameOverListener : gameOverListeners) {
            gameOverListener.gameOver();
        }
    }

    public void addLevelUpListener(LevelUpListener levelUpListener) {
        levelUpListeners.add(levelUpListener);
    }

    public void addSpawnGameObjectListener(SpawnGameObjectListener spawnGameObjectListener) {
        spawnGameObjectListeners.add(spawnGameObjectListener);
    }

    public void addGameOverListener(GameOverListener gameOverListener) {
        gameOverListeners.add(gameOverListener);
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

            AIBacteria aiBacteria = dish.aiBacteria(movableGameObjectSprite);

            aiBacteria.increaseEatenAgarAmount();

                if (aiBacteria.agarEatenCount() % LEVEL_MULTIPLICATOR == 0) {
                    aiBacteria.leveUp();
                    fireLevelUp(movableGameObjectSprite);
                }
        }

    }


    @Override
    public void movableObjectEaten(Sprite playerBacteria, Sprite aiBacteria) {
        if(dish.playerBacteria().level() > dish.aiBacteria(aiBacteria).level()) {
            dish.playerBacteria().leveUp();
            dish.playerBacteria().increaseEatenAICount();

            fireLevelUp(playerBacteria);

            if(dish.playerBacteria().getEatenAiCount() % 5 == 0) {
                fireSpawnAI();
            }



        }
        else {
            fireGameOver();
        }
    }

}

