/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import controller.AIBacteriaController;
import gamemodel.Dish;
import gameobject.Bacteria;
import java.awt.Point;
import java.util.ArrayList;
import listeners.GameObjectEatenListener;
import utils.GameMath;
import view.AIBacteriaView;

/**
 *
 * @author VIKA
 */
public class AIAICollision extends BasicCollisionGroup {

    
    /**
     * Максимальное расстояние между Бактериями, при достижении которого возможно "поедание" одной
     * бактерией другой
     */
    private final static int DISTANCE_TO_EAT = 50;
    
    Dish dish = null;
     /**
     * Слушатели сигнала GameObjectEaten говорящего о том, что был съеден какой-либо объект игры
     */
    private ArrayList<GameObjectEatenListener> gameObjectEatenListeners = new ArrayList<>();
    
    public AIAICollision (Dish _dish) {
        dish = _dish;
    }
    
    @Override
    public void collided(Sprite s1, Sprite s2) {
        
        Bacteria b1 = dish.aiBacteria(s1);
        Bacteria b2 = dish.aiBacteria(s2);
        Point p1 = b1.getPosition(),
                p2 = b2.getPosition();
        double distance = GameMath.distance(p1, p2);
        if(distance <= DISTANCE_TO_EAT) {
            if( b1.level() > b2.level()) {
                fireAIBacteriaEaten(s1, s2);
            }
            else if( b1.level() < b2.level()){
                fireAIBacteriaEaten(s2, s1);
            }
            else {
                int angle = b1.getDirection()- (int)(180 + Math.random()*(10)); 
                b1.setDirection(angle);
                b1.setSteps((int)(10 + Math.random()*(50)));

                int angle1 = b2.getDirection() - (int)(180 + Math.random()*(10)); 
                b2.setDirection(angle1);
                b2.setSteps((int)(10 + Math.random()*(50)));
            }   
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
    
    /**
     * Сообщает всем слушателям о том, что одна бактерия съела другую
     *
     * @param playerBacteria спрайт Бактерии игрока
     * @param aiBacteria     спрайт ИИБактерии
     */
    private void fireAIBacteriaEaten(Sprite bacteria_1, Sprite bacteria_2) {
        for (GameObjectEatenListener gameObjectEatenListener : gameObjectEatenListeners) {
            gameObjectEatenListener.aiBacteriaEaten(bacteria_1, bacteria_2);
        }
    }
    
}
