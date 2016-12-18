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
import java.util.ArrayList;
import listeners.GameObjectEatenListener;
import utils.GameMath;
import view.AIBacteriaView;

/**
 *
 * @author VIKA
 */
public class AIAICollision extends BasicCollisionGroup {

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
        //boolean firstBigest = ((AIBacteriaView)s1).getParticle().getSize() > ((AIBacteriaView)s2).getParticle().getSize();
        //Particle p1 = firstBigest ? ((SpriteView)s1).getParticle() : ((SpriteView)s2).getParticle();
        //Particle p2 = firstBigest ? ((SpriteView)s2).getParticle() : ((SpriteView)s1).getParticle();
        
        
        Bacteria p1 = dish.aiBacteria(s1);
        Bacteria p2 = dish.aiBacteria(s2);
        if( p1.level() > p2.level()) {
            fireBacteriaEaten(s1, s2);
        }
        else if( p1.level() < p2.level()){
            fireBacteriaEaten(s2, s1);
        }
        else{
            int angle = p1.getDirection()- (int)(100 + Math.random()*(60)); 
            p1.setDirection(angle);
            p1.setSteps((int)(10 + Math.random()*(50)));
            
            int angle1 = p2.getDirection() - (int)(100 + Math.random()*(60)); 
            p2.setDirection(angle);
            p2.setSteps((int)(100 + Math.random()*(50)));
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
