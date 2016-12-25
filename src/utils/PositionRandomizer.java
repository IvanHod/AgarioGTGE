package utils;


import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import game.GameView;

/**
 * Генератор случайной позиции объектов на игровом поле
 */
public class PositionRandomizer {

    /**
     * Генерирует случайную позицию на поле
     *
     * @return случайная позиция на поле
     */
    public static Point getRandomPosition() {

        Point randomPosition = new Point();

        randomPosition.x = ThreadLocalRandom.current().nextInt(20, (int) GameView.GAME_FIELD_SIZE.getWidth());
        randomPosition.y = ThreadLocalRandom.current().nextInt(20, (int) GameView.GAME_FIELD_SIZE.getHeight());

        // Отодвинуть позицию спавна какого-либо объекта игры от позиции спавна Бактерии игрока

        /*if (randomPosition.x == GameView.initialPlayerPosition.x)
            randomPosition.x += 500;
        if (randomPosition.y == GameView.initialPlayerPosition.y)
            randomPosition.y -= 500;*/

        return randomPosition;
    }

    /**
     * Генерирует случайную позицию на поле в заданных пределах
     *
     * @param min верхний предел
     * @param max нижний предел
     * @return случайная позиция на поле
     */
    public static Point getRandomPosition(int min, int max) {

        Point randomPosition = new Point();

        randomPosition.x = ThreadLocalRandom.current().nextInt(min, max);
        randomPosition.y = ThreadLocalRandom.current().nextInt(min, max);

        return randomPosition;
    }

}
