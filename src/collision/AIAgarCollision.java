package collision;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import java.util.ArrayList;

import listeners.AgarEatenListener;

public class AIAgarCollision extends BasicCollisionGroup {

    ArrayList<AgarEatenListener> agarEatenListeners = new ArrayList<>();

    public AIAgarCollision() {
        pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        fireAgarEaten(sprite, sprite1);
    }

    void fireAgarEaten(Sprite movableGameObjectSprite, Sprite agarSprite) {
        for (AgarEatenListener agarEatenListener : agarEatenListeners) {
            agarEatenListener.agarEaten(movableGameObjectSprite, agarSprite);
        }
    }

    public void addAgarEatenListener(AgarEatenListener agarEatenListener) {
        agarEatenListeners.add(agarEatenListener);
    }
}
