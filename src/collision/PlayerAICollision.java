package collision;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import java.awt.*;
import java.util.ArrayList;

import listeners.GameObjectEatenListener;
import utils.GameMath;

/**
 * Менеджер коллизий Бактерии игрока и ИИБактерии
 */
public class PlayerAICollision extends BasicCollisionGroup {

    /**
     * Максимальное расстояние между Бактериями, при достижении которого возможно "поедание" одной
     * бактерией другой
     */
    private final static int DISTANCE_TO_EAT = 50;

    /**
     * Слушатели сигнала GameObjectEaten говорящего о том, что был съеден какой-либо объект игры
     */
    private ArrayList<GameObjectEatenListener> gameObjectEatenListeners = new ArrayList<>();

    /**
     * Конструктор класса менеджера коллизий
     */
    public PlayerAICollision() {

        pixelPerfectCollision = true;
    }

    /**
     * Метод выполняется при коллизии Бактерии игрока с ИИБактерией
     *
     * @param sprite  спрайт Бактерии игрока
     * @param sprite1 спрайт ИИБактерии
     */
    @Override
    public void collided(Sprite sprite, Sprite sprite1) {

        // Позиции Бактерии игрока и ИИБактерии на поле во время коллизии

        Point playerPosition = new Point((int) sprite.getX(), (int) sprite.getY());
        Point aiPosition = new Point((int) sprite1.getX(), (int) sprite1.getY());

        // Если расстояние между бактериями позволяет одной Бактерии съесть другую...

        if (GameMath.distance(playerPosition, aiPosition) <= DISTANCE_TO_EAT) {

            // ... отправить сигнал о том, что одна Бактерия съела другую

            fireBacteriaEaten(sprite, sprite1);
        }

    }

    /**
     * Добавляет слушателя сигнала GameObjectEaten
     *
     * @param bacteriaEatenListener слушатель сигнала GameObjectEaten
     */
    public void addPlayerEatenListener(GameObjectEatenListener bacteriaEatenListener) {
        gameObjectEatenListeners.add(bacteriaEatenListener);
    }

    /**
     * Сообщает всем слушателям о том, что одна бактерия съела другую
     *
     * @param playerBacteria спрайт Бактерии игрока
     * @param aiBacteria     спрайт ИИБактерии
     */
    private void fireBacteriaEaten(Sprite playerBacteria, Sprite aiBacteria) {
        for (GameObjectEatenListener gameObjectEatenListener : gameObjectEatenListeners) {
            gameObjectEatenListener.bacteriaEaten(playerBacteria, aiBacteria);
        }

    }
}
