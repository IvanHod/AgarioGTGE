package collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class PlayerAgarCollision extends BasicCollisionGroup {

    public PlayerAgarCollision() {
        pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        sprite1.setActive(false);
        sprite1 = null;
    }
}
