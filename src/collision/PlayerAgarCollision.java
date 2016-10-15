package collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import listeners.AgarEatenListener;

import java.util.ArrayList;


public class PlayerAgarCollision extends BasicCollisionGroup {


    ArrayList<AgarEatenListener> agarEatenListeners = new ArrayList<>();

    public PlayerAgarCollision() {
        pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        fireAgarEaten(sprite1);
    }


    public void fireAgarEaten(Sprite agarSprite) {
        for (AgarEatenListener agarEatenListener : agarEatenListeners) {
            agarEatenListener.agarEaten(agarSprite);
        }
    }

    public void addAgarEatenListener(AgarEatenListener agarEatenListener) {
        agarEatenListeners.add(agarEatenListener);
    }
}
