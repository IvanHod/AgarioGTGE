package utils;


import game.GameView;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class PositionRandomizer {

    public static Point getRandomPosition() {

        Point randomPosition = new Point();

        randomPosition.x = ThreadLocalRandom.current().nextInt(20, (int) GameView.VIEWPORT.getWidth());
        randomPosition.y = ThreadLocalRandom.current().nextInt(20, (int) GameView.VIEWPORT.getHeight());

        if(randomPosition.x == GameView.initialPlayerPosition.x)
            randomPosition.x += 500;
        if(randomPosition.y == GameView.initialPlayerPosition.y)
            randomPosition.y -= 500;

        return randomPosition;
    }
}
