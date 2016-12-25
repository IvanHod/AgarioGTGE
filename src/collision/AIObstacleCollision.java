package collision;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import gamemodel.Dish;
import gameobject.Bacteria;
import java.util.ArrayList;
import listeners.GameObjectEatenListener;

/**
 * Менеджер коллизий Бактерии игрока и Препятствия
 */
public class AIObstacleCollision extends BasicCollisionGroup {
      Dish dish = null;
    
    public AIObstacleCollision (Dish _dish) {
        dish = _dish;
        pixelPerfectCollision = true;
    }

    /**
     * Метод выполняется при коллизии Бактерией игрока с Препятствием
     *
     * @param s1 спрайт Бактерии игрока
     * @param s2 спрайт Препятствия
     */
    @Override
    public void collided(Sprite s1, Sprite s2) {

        // При столкновении Бактерии игрока с Препятствием  - переместить Бактерию игрока на старую позицию,
        // до столкновения с Препятствием
        Bacteria p1 = dish.aiBacteria(s1);
        int angle = p1.getDirection()- (int)(180 + Math.random()*(10)); 
        p1.setDirection(angle);
        p1.setSteps((int)(10 + Math.random()*(50)));
    }

}