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
import controller.BacteriaController;
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

/**
 * Модель игры (содержит правила игры и её ход)
 */
public class GameModel implements GameObjectEatenListener {

    /**
     * Скорость Бактерии Игрока
     */
    public final static double PLAYER_SPEED = 0.3;
    /**
     * Количество Агара заспавненного за 1 раз
     */
    public final static int AGAR_SPAWN_PORTION = 10;
    /**
     * Количество ИИБактерий заспавненных за 1 раз
     */
    public final static int MAX_AIBACTERIA_SPAWN_PORTION = 20;
    /**
     * Максимальное количество Препятствий
     */
    private final static int MAX_OBSTACLES_COUNT = 30;
    /**
     * Максимальное количество Агара
     */
    private final static int MAX_AGAR_COUNT = 100;
    /**
     * Промежуток в секундах, по истечении которого Агар спавнится на поле
     */
    private final static int AGAR_SPAWN_TIMEOUT = 2;
    /**
     * Максимальное количество ИИБактерий
     */
    private final static int MAX_AIBACTERIA_COUNT = 10;
    /**
     * Скорость ИИБактерии
     */
    private final static double AI_SPEED = 0.2;

    /**
     * Количество Агара, которое нужно съесть Бактерии для достижения следующего уровня
     */
    private final static int AGAR_EATEN_COUNT_TO_NEXT_LEVEL = 5;

    /**
     * Количество ИИБактерий, которое нужно съесть Бактерии игрока для достижения следующего уровня
     */
    private final static int AIBACTERIA_EATEN_COUNT_TO_NEXT_LEVEL = 3;

    /**
     * Количество ИИБактерий, которые нужно съесть, чтобы заспавнились нвоые ИИБактерии
     */
    private final static int EATEN_AIBACTERIA_TO_NEXT_SPAWN = 5;

    /**
     * Слушатели сигнала SpawnGameObject, говорящего о том, что нужно заспавнить определенное
     * количество игровых объектов
     */
    private ArrayList<SpawnGameObjectListener> spawnGameObjectListeners = new ArrayList<>();

    /**
     * Слушатели сигнала LevelUp, говорящего о том, что нужно повысить уровень определенным объектам
     */
    private ArrayList<LevelUpListener> levelUpListeners = new ArrayList<>();

    /**
     * Слушатели сигнала GameOver, говорящего о том, нужно закончить игру
     */
    private ArrayList<GameOverListener> gameOverListeners = new ArrayList<>();

    /**
     * Фабрика объектов Игрока
     */
    private GameObjectFactory playerBacteriaFactory = new PlayerBacteriaFactory();

    /**
     * Фабрика объектов Препятствий
     */
    private GameObjectFactory obstacleFactory = new ObstacleFactory();

    /**
     * Фабрика объектов Агара
     */
    private GameObjectFactory agarFactory = new AgarFactory();

    /**
     * Фабрика объектов ИИБактерий
     */
    private GameObjectFactory aiBacteraiFactory = new AIBacteriaFactory();

    /**
     * Контроллеры Бактерий
     */
    private ArrayList<BacteriaController> bacteriaControllers = new ArrayList<>();

    /**
     * Чашка Петри
     */
    private Dish dish;

    /**
     * Конструктор класса с параметрами
     *
     * @param dish Чашка Петри
     */
    public GameModel(Dish dish) throws IOException {

        this.dish = dish;

        // Создать Бактерию игрока ...

        PlayerBacteria playerBacteria = (PlayerBacteria) playerBacteriaFactory.createGameObject();

        // ... добавить ей контроллер

        bacteriaControllers.add(new PlayerBacteriaController(playerBacteria));

        // ... установить скорость

        playerBacteria.setSpeed(PLAYER_SPEED);

        // ... установить начальную позицию

        playerBacteria.setPosition(GameView.initialPlayerPosition);

        // ... добавить в Чашку Петри

        dish.addPlayerBacteria(playerBacteria);

        // Создать Агар и добавить его в Чашку Петри

        for (int i = 0; i < MAX_OBSTACLES_COUNT; i++) {
            dish.addObstacle((Obstacle) obstacleFactory.createGameObject());
        }

        // Создать препятствия и добавить их в Чашку Петри

        for (int i = 0; i < MAX_AGAR_COUNT; i++) {
            dish.addAgar(((Agar) agarFactory.createGameObject()));
        }

        // Для ИИБактерии выполнить аналогичную Бактерии Игрока процедуру

        for (int i = 0; i < MAX_AIBACTERIA_COUNT; i++) {
            AIBacteria aiBacteria = (AIBacteria) aiBacteraiFactory.createGameObject();

            aiBacteria.setSpeed(AI_SPEED);

            aiBacteria.setDirection(ThreadLocalRandom.current().nextInt(0, 360));

            bacteriaControllers.add(new AIBacteriaController(dish, aiBacteria));

            dish.addAIBacteria(aiBacteria);
        }

        // Отправить сигнал спавна Агара
        fireSpawnAgar();
    }

    /**
     * Обновляет все контроллеры игры
     *
     * @param mousePosition позиция мыши на поле
     */
    public void update(Point mousePosition) {

        for (BacteriaController bacteriaController : bacteriaControllers) {
            bacteriaController.update(mousePosition);
        }
    }

    /**
     * Добавляет слушателя сигнала LevelUp
     *
     * @param levelUpListener слушатель сигнала LevelUp
     */
    public void addLevelUpListener(LevelUpListener levelUpListener) {
        levelUpListeners.add(levelUpListener);
    }

    /**
     * Добавляет слушателя сигнала SpawnGameObject
     *
     * @param spawnGameObjectListener слушатель сигнала SpawnGameObject
     */
    public void addSpawnGameObjectListener(SpawnGameObjectListener spawnGameObjectListener) {
        spawnGameObjectListeners.add(spawnGameObjectListener);
    }

    /**
     * Добавляет слушателя сигнала GameOver
     *
     * @param gameOverListener слушатель сигнала GameOver
     */
    public void addGameOverListener(GameOverListener gameOverListener) {
        gameOverListeners.add(gameOverListener);
    }

    /**
     * Принимает сигнал AgarEaten (Агар съеден Бактерией)
     *
     * @param bacteriaSprite спрайт Бактерии игрока
     * @param agarSprite     спрайт Агара
     */
    @Override
    public void agarEaten(Sprite bacteriaSprite, Sprite agarSprite) {

        // Если Бактерия игрока съела Агар ...

        if (dish.playerBacteria().sprite() == bacteriaSprite) {

            // ... увеличить кол-во съеденного Бактерией игрока Агара

            dish.playerBacteria().increaseEatenAgarAmount();

            // ... если Бактерия игрока съела достаточно Агара для перехода на следующий уровень ...

            if (dish.playerBacteria().agarEatenCount() % AGAR_EATEN_COUNT_TO_NEXT_LEVEL == 0) {

                // ... повысить уровень Бактерии игрока и отправить об этом сигнал

                dish.playerBacteria().levelUp();
                fireLevelUp(bacteriaSprite);
            }
        }

        // ... иначе ...

        else {

            AIBacteria aiBacteria = dish.aiBacteria(bacteriaSprite);

            // ... увеличить кол-во съеденного ИИБактерией Агара

            aiBacteria.increaseEatenAgarAmount();

            // ... если ИИБактерия съела достаточно Агара для перехода на следующий уровень ...

            if (aiBacteria.agarEatenCount() % AGAR_EATEN_COUNT_TO_NEXT_LEVEL == 0) {

                // ... повысить уровень Бактерии игрока и отправить об этом сигнал

                aiBacteria.levelUp();
                fireLevelUp(bacteriaSprite);
            }
        }

        // Удалить съеденный Агар из Чашки Петри

        dish.removeAgar(agarSprite);
    }

    /**
     * Принимает сигнал BacteriaEaten (одна Бактерия съела другую Бактерию)
     *
     * @param playerBacteria спрайт Бактерии игрока
     * @param aiBacteria     спрайт ИИБактерии
     */
    @Override
    public void bacteriaEaten(Sprite playerBacteria, Sprite aiBacteria) {

        // Если Бактерия игрока съела ИИБактерию (уровень Бактерии игрока больше
        // уровня ИИБактерии) ...

        if (dish.playerBacteria().level() > dish.aiBacteria(aiBacteria).level()) {

            // ... увеличиить кол-во съеденных Бактерией игрока ИИБактерий

            dish.playerBacteria().increaseEatenAICount();

            // ... если Бактерия игрока съела достаточно ИИБактерий для перехода на следующий уровень ...

            if (dish.playerBacteria().getEatenAiCount() % AIBACTERIA_EATEN_COUNT_TO_NEXT_LEVEL == 0) {

                // ... повысить уровень Бактерии игрока и отправить об этом сигнал

                dish.playerBacteria().levelUp();
                fireLevelUp(playerBacteria);
            }

            // ... если Бактерия игрока съела достаточно ИИБактерий для следующего спавна ...
            if (dish.playerBacteria().getEatenAiCount() % EATEN_AIBACTERIA_TO_NEXT_SPAWN == 0) {

                // ... отправить сигнал спавна ИИБактерий

                fireSpawnAI();
            }

            // Удалить съеденную ИИБактерию из Чашки Петри

            dish.removeAIBacteria(aiBacteria);
        }

        // ... иначе ...
        else {

            // ... бактерию игрока съели, поэтому пускам сигнал завершения игры

            fireGameOver();
        }
    }

    /**
     * Периодически (каждые 2 секунды) отправляет сигнал спавна Агара
     */
    private void fireSpawnAgar() {

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {

            int agarSpawnedCount;

            @Override
            public void run() {

                for (SpawnGameObjectListener l : spawnGameObjectListeners) {
                    l.spawnAgar();
                }

                agarSpawnedCount += AGAR_SPAWN_PORTION;

                if (agarSpawnedCount == MAX_AGAR_COUNT) {
                    exec.shutdown();
                }
            }
        }, 1, AGAR_SPAWN_TIMEOUT, TimeUnit.SECONDS);

    }

    /**
     * Отправляет сигнал спавна ИИБактерий
     */
    private void fireSpawnAI() {
        for (SpawnGameObjectListener l : spawnGameObjectListeners) {
            l.spawnAI();
        }

    }

    /**
     * Отправляет сигнал повышения уровня Бактерии
     *
     * @param bacteriaSprite спрайт Бактерии, дял которой следует повысить уровень
     */
    private void fireLevelUp(Sprite bacteriaSprite) {
        for (LevelUpListener levelUpListener : levelUpListeners) {
            levelUpListener.levelIncreased(bacteriaSprite);
        }
    }

    /**
     * Отправляет сигнал завершения игры
     */
    private void fireGameOver() {
        for (GameOverListener gameOverListener : gameOverListeners) {
            gameOverListener.gameOver();
        }
    }

}

