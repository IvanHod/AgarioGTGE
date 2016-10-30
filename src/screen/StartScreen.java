package screen;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Стартовый экран игры
 */
public class StartScreen extends GameObject {

    /**
     * Путь к фоновому изображению стартового экрана игры
     */
    private final static String SCREEN_BG = "assets/screens/start_screen.png";

    /**
     * Фон стартового экрана игры
     */
    private Background bg;

    /**
     * Конструктор класса с параметром
     *
     * @param gameEngine игровой движок
     */
    public StartScreen(GameEngine gameEngine) {
        super(gameEngine);
        bg = new ImageBackground(getImage(SCREEN_BG));
    }

    /**
     * Инициализирует ресурсы
     */
    @Override
    public void initResources() {

        // Убрать курсор

        hideCursor();
    }

    /**
     * Обновляет ресурсы
     *
     * @param elapsedTime время, прошедшее с момента последнего обновления
     */
    @Override
    public void update(long elapsedTime) {
        bg.update(elapsedTime);

        // Если нажат Enter ...

        if (keyPressed(KeyEvent.VK_ENTER)) {

            // ... переключится на экран с игрой

            parent.nextGameID = 1;

            // ... закрыть текущий экран
            finish();
        }

    }

    /**
     * Рендерит ресурсы
     *
     * @param g графический контекст
     */
    @Override
    public void render(Graphics2D g) {
        bg.render(g);
    }
}
