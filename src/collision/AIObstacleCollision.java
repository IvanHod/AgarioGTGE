package collision;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * Менеджер коллизий Бактерии игрока и Препятствия
 */
public class PlayerObstacleCollision extends BasicCollisionGroup {

    /**
     * Конструктор класса менеджера коллизий
     */
    public PlayerObstacleCollision() {

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

        s1.setX(s1.getOldX());
        s1.setY(s1.getOldY());

    }

}