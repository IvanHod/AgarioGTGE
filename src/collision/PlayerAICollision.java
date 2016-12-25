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

        Point playerPosition = new Point((int) (sprite.getX() + sprite.getWidth()/2), 
                (int) (sprite.getY() + sprite.getHeight()/2));
        Point aiPosition = new Point((int) (sprite1.getX() + sprite1.getWidth()/2), 
                (int) (sprite1.getY() + sprite1.getWidth()/2));
        
        double distance = GameMath.distance(playerPosition, aiPosition);

        // Если расстояние между бактериями позволяет одной Бактерии съесть другую...

        if (distance <= DISTANCE_TO_EAT) {

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
