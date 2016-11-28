/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.util.ArrayList;
import listeners.GameObjectEatenListener;

/**
 *
 * @author 999
 */
public class AIAICollision extends BasicCollisionGroup {
    
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
    public AIAICollision() {

        pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        System.out.println("ai ai collezed:" + sprite.getX() + " - " + sprite1.getX());
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
