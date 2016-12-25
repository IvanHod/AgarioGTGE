package game;

import collision.AIAICollision;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import collision.AIAgarCollision;
import collision.AIObstacleCollision;
import collision.PlayerAICollision;
import collision.PlayerAgarCollision;
import collision.PlayerObstacleCollision;
import gamemodel.Dish;
import gamemodel.GameModel;
import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.Obstacle;
import java.util.ArrayList;
import listeners.GameObjectEatenListener;
import listeners.GameOverListener;
import listeners.LevelUpListener;
import listeners.SpawnGameObjectListener;
import utils.ImageScaler;

/**
 * Представление игры (отображение игры и её результатов)
 */
public class GameView extends GameObject implements SpawnGameObjectListener, GameObjectEatenListener, LevelUpListener, GameOverListener {

    /**
     * Размер игрового поля
     */
    public static final Dimension GAME_FIELD_SIZE = new Dimension(3000, 3000);

    /**
     * Размер окна с игрой
     */
    public static final Dimension GAME_WINDOW = new Dimension(900, 620);

    /**
     * Начальная позиция игрока
     */
    public static final Point initialPlayerPosition
            //= new Point((int) (GAME_WINDOW.getWidth() / 2), (int) (GAME_WINDOW.getHeight() / 2));
            = new Point(500, 200);

    /**
     * Путь к изображению фона игрового поля
     */
    private final static String DISH_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";

    /**
     * Путь к звуку, проигрываемому при поедании Агара
     */
    private final static String AGAR_EATEN_SOUND = "assets/sound/agar.wav";

    /**
     * Путь к звуку, проигрываемому при поедании ИИБактерии
     */
    private final static String AIBACTERIA_EATEN_SOUND = "assets/sound/ai.wav";

    /**
     * Путь к звуку, проигрываемому при повышении уровня
     */
    private final static String LEVELUP_SOUND = "assets/sound/levelup.wav";

    /**
     * Путь к звуку, проигрываемому при уонце игры
     */
    private final static String GAME_OVER_SOUND = "assets/sound/gameover.wav";

    /**
     * Коэффициент масштабирования изображения по X при повышении уровня
     */
    private final static int SCALE_IMAGE_X = 30;

    /**
     * Коэффициент масштабирования изображения по Y при повышении уровня
     */
    private final static int SCALE_IMAGE_Y = 30;

    /**
     * Изображения игрового поля
     */
    private ImageBackground bg;

    /**
     * Игровое поле
     */
    private PlayField pf;

    /**
     * Модель игры
     */
    private GameModel gm;

    /**
     * Чашка Петри
     */
    private Dish dish;

    /**
     * Группы спрайтов: Бактерии игрока, Препятствий, Агара, ИИБактерий
     */
    private SpriteGroup playerGroup, obstacleGroup, agarGroup;
    
    private ArrayList<SpriteGroup> aiBacteriaGroups = new ArrayList<>();
    
    /**
     * Шрифт для надписей на игровом поле
     */
    private GameFont gameFont;

    /**
     * Конструктор класса с параметром
     *
     * @param gameEngine игровой движок
     */
    public GameView(GameEngine gameEngine) {
        super(gameEngine);
    }

    /**
     * Инициализирует ресурсы
     */
    @Override
    public void initResources() {

        // Показать курсор

        showCursor();

        try {

            // Создать Чашку Петри

            dish = new Dish();

            // Создать Модель игры

            gm = new GameModel(this.dish);

            // Добавить слушателей для сигналов посылаемых Моделью игры

            gm.addSpawnGameObjectListener(this);
            gm.addLevelUpListener(this);
            gm.addGameOverListener(this);
            
            gm.startGame();

            // Создать фон для поля игры

            this.bg = new ImageBackground(ImageIO.read(new File(DISH_BACKGROUND_IMAGE_PATH)));
            this.bg.setClip(0, 0, (int) GAME_WINDOW.getWidth(), (int) GAME_WINDOW.getHeight());

            // Создать поле игры

            pf = new PlayField(bg);

            // Добавить на игровое поле группу игрока
            playerGroup = pf.addGroup(new SpriteGroup("Player"));
            playerGroup.add(dish.playerBacteria().sprite());
            
            // Добавить на игровое поле группы препятствия
            obstacleGroup = pf.addGroup(new SpriteGroup("Obstacle"));
            for (Obstacle obstacle : dish.obstacles()) {
                obstacleGroup.add(obstacle.sprite());
            }
            pf.addCollisionGroup(playerGroup, obstacleGroup, new PlayerObstacleCollision());
            
            // Добавить на игровое поле группы агаров
            agarGroup = pf.addGroup(new SpriteGroup("Agar Group"));
            for (Agar agar : dish.agar()) {
                agar.sprite().setImmutable(true);
                agar.sprite().setActive(false);
                agarGroup.add(agar.sprite());
            }
            
            PlayerAgarCollision playerAgarCollision = new PlayerAgarCollision();

            // Добавить слушателей для сигналов посылаемых коллизией Бактерия игрока - Агар
            playerAgarCollision.addAgarEatenListener(this);
            playerAgarCollision.addAgarEatenListener(gm);

            pf.addCollisionGroup(playerGroup, agarGroup, playerAgarCollision);
            
            int aiIndex = 0;
            for (AIBacteria aiBacteria : dish.aiBacterias()) {
                aiBacteria.sprite().setImmutable(true);
                aiBacteria.sprite().setActive(false);
                
                SpriteGroup aiBacteriaGroup = new SpriteGroup("ai_" + aiIndex);
                aiBacteriaGroup.add(aiBacteria.sprite());
                
                // Создадим коллизии для ботов с ботами
                for(SpriteGroup AIgroup: aiBacteriaGroups) {
                    // Добавить на игровое поле коллизию Бактерия игрока - ИИБактерия
                    AIAICollision AIAICollision = new AIAICollision(dish);

                    // Добавить слушателей для сигналов посылаемых коллизией ИИ Бактерия - ИИБактерия

                    AIAICollision.addPlayerEatenListener(this);
                    AIAICollision.addPlayerEatenListener(gm);

                    pf.addCollisionGroup(AIgroup, aiBacteriaGroup, AIAICollision);
                }
                
                aiBacteriaGroups.add(aiBacteriaGroup);
                pf.addGroup(aiBacteriaGroup);
                // Добавить на игровое поле коллизию Бактерия игрока - ИИБактерия
                PlayerAICollision playerAICollision = new PlayerAICollision();

                // Добавить слушателей для сигналов посылаемых коллизией Бактерия игрока - ИИБактерия

                playerAICollision.addPlayerEatenListener(this);
                playerAICollision.addPlayerEatenListener(gm);

                pf.addCollisionGroup(playerGroup, aiBacteriaGroup, playerAICollision);
                
                // Добавить на игровое поле коллизию ИИБактерия - Агар
                AIAgarCollision aiAgarCollision = new AIAgarCollision();

                // Добавить слушателей для сигналов посылаемых коллизией ИИБактерия - Агар
                aiAgarCollision.addAgarEatenListener(this);
                aiAgarCollision.addAgarEatenListener(gm);

                pf.addCollisionGroup(aiBacteriaGroup, agarGroup, aiAgarCollision);
                
                pf.addCollisionGroup(aiBacteriaGroup, obstacleGroup, new AIObstacleCollision(dish));
                aiIndex++;
                
            }
            
            // Добавить шрифт для надписей на игровом поле

            gameFont = fontManager.getFont(getImage("assets/font/font.fnt"));

            // Заспавнить ИИБактерии

            spawnAI();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Обновляет ресурсы
     *
     * @param elapsedTime время, прошедшее с момента последнего обновления
     */
    @Override
    public void update(long elapsedTime) {
        pf.update(elapsedTime);
        gm.update(mousePosition());
    }

    /**
     * Рендерит ресурсы
     *
     * @param g графический контекст
     */
    @Override
    public void render(Graphics2D g) {
        pf.render(g);

        // Установить Бактерию игрока по центру окна

        bg.setToCenter(dish.playerBacteria().sprite());

        // Вывести на игровое поле информацию о количестве съеденного Агара и уровню
        // Бактерии игрока

        gameFont.drawString(g, "AGAR COUNT: " + dish.playerBacteria().agarEatenCount() +
                "  PLAYER LEVEL: " + dish.playerBacteria().level(), 9, 9);
    }

    /**
     * Принимает сигнал SpawnAgar (Заспавнить Агар)
     */
    @Override
    public void spawnAgar() {

        int spawnedAgar = 0;

        for (Sprite agarSprite : agarGroup.getSprites()) {

            // Если спрайт Агара невидим ...

            if (agarSprite != null && !agarSprite.isActive()) {

                // ... сделать его видимым

                agarSprite.setActive(true);
                spawnedAgar++;

                if (spawnedAgar == GameModel.AGAR_SPAWN_PORTION) {
                    break;
                }
            }
        }
    }

    /**
     * Принимает сигнал SpawnAI (Заспавнить ИИБактерии)
     */
    @Override
    public void spawnAI() {

        int spawnedAI = 0;

        for (SpriteGroup aiSpriteGroup : aiBacteriaGroups) {

            // Если спрайт ИИБактерии невидим ...
            Sprite aiSprite = aiSpriteGroup.getSprites()[0];

            if (aiSprite != null && !aiSprite.isActive()) {

                // ... сделать его видимым
                aiSprite.setActive(true);
                spawnedAI++;

                if (spawnedAI == GameModel.MAX_AIBACTERIA_SPAWN_PORTION) {
                    break;
                }
            }
        }
    }

    /**
     * Принимает сигнал AgarEaten (Агар съеден Бактерией)
     *
     * @param bacteriaSprite спрайт Бактерии игрока
     * @param agarSprite     спрайт Агара
     */
    @Override
    public void agarEaten(Sprite bacteriaSprite, Sprite agarSprite) {

        // Удалить Агар из группы спрайтов

        agarSprite.setImmutable(false);
        agarGroup.remove(agarSprite);

        // Если Бактерия игрока съела Агар ...

        if (dish.playerBacteria().sprite() == bacteriaSprite)

            // ... проиграть звук

            playSound(AGAR_EATEN_SOUND);
    }

    /**
     * Принимает сигнал LevelUp (урвоень какой-либо Бактерии увеличился)
     *
     * @param bacteriaSprite спрайт бактерии, которой требуется повысить уровень
     */
    @Override
    public void levelIncreased(Sprite bacteriaSprite) {

        // Если уровень Бактерии игрока увеличился ...

        if (dish.playerBacteria().sprite() == bacteriaSprite)

            // ... проиграть звук

            playSound(LEVELUP_SOUND);

        // Увеличить изображение Бактерии

        BufferedImage currentSpriteImage = bacteriaSprite.getImage();
        bacteriaSprite.setImage(ImageScaler.scaleImage(currentSpriteImage, SCALE_IMAGE_X, SCALE_IMAGE_Y));
        //currentSpriteImage.setData(raster);
    }

    /**
     * Принимает сигнал LevelUp (урвоень какой-либо Бактерии увеличился)
     *
     * @param bacteriaSprite спрайт бактерии, которой требуется повысить уровень
     */
    @Override
    public void levelIncreasedCount(Sprite bacteriaSprite, int levelCount) {

        // Увеличить изображение Бактерии
        int scale_x = SCALE_IMAGE_X,
                scale_y = SCALE_IMAGE_Y;
        
        for(int i = 0; i < levelCount; i++) {
            scale_x += scale_x;
            scale_y += scale_y;
        }
        System.out.print("levelINCR "+scale_x+" "+scale_y+"\n");
        BufferedImage currentSpriteImage = bacteriaSprite.getImage();
        bacteriaSprite.setImage(ImageScaler.scaleImage(currentSpriteImage, scale_x, scale_y));
    }

    /**
     * Принимает сигнал BacteriaEaten (одна Бактерия съела другую Бактерию)
     *
     * @param playerBacteria спрайт Бактерии игрока
     * @param aiBacteria     спрайт ИИБактерии
     */
    @Override
    public void bacteriaEaten(Sprite playerBacteria, Sprite aiBacteria) {

        // Если Бактерия игрока съела ИИБактерию

        if (dish.playerBacteria().level() > dish.aiBacteria(aiBacteria).level()) {

            // ... удалить ИИБактерию из группы спрайтов

            aiBacteria.setImmutable(false);
            for(SpriteGroup aiBacteriaGroup : aiBacteriaGroups)
                aiBacteriaGroup.remove(aiBacteria);

            // ... проиграть звук
            playSound(AIBACTERIA_EATEN_SOUND);
        }
    }
    
    /**
     * Принимает сигнал BacteriaEaten (одна Бактерия съела другую Бактерию)
     *
     * @param bacteria_1 спрайт Бактерии игрока
     * @param bacteria_2     спрайт ИИБактерии
     */
    @Override
    public void aiBacteriaEaten(Sprite bacteria_1, Sprite bacteria_2) {
        
        if (dish.aiBacteria(bacteria_1).level() > dish.aiBacteria(bacteria_2).level()) {

            // удалить ИИБактерию из группы спрайтов
            bacteria_2.setImmutable(false);
            for(SpriteGroup aiBacteriaGroup : aiBacteriaGroups)
                aiBacteriaGroup.remove(bacteria_2);
        }
        else if (dish.aiBacteria(bacteria_2).level() > dish.aiBacteria(bacteria_1).level()) {

            // удалить ИИБактерию из группы спрайтов
            bacteria_1.setImmutable(false);
            for(SpriteGroup aiBacteriaGroup : aiBacteriaGroups)
                aiBacteriaGroup.remove(bacteria_1);
        }
    }

    /**
     * Принимает сигнал GameOver
     */
    @Override
    public void gameOver() {

        // Проиграть звук

        playSound(GAME_OVER_SOUND);

        // Переключится на экран с игрой

        parent.nextGameID = 2;

        // Переключится на экран с игрой

        finish();
    }

    /**
     * Возвращает позицию курсора на игровом поле
     *
     * @return позиция курсора на игровом поле
     */
    private Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }
}
