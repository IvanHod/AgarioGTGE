package collision;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import java.util.ArrayList;

import listeners.GameObjectEatenListener;

public class AIAgarCollision extends BasicCollisionGroup {

    ArrayList<GameObjectEatenListener> gameObjectEatenListeners = new ArrayList<>();

    public AIAgarCollision() {
        pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {
        fireAgarEaten(sprite, sprite1);
    }

    void fireAgarEaten(Sprite movableGameObjectSprite, Sprite agarSprite) {
        for (GameObjectEatenListener gameObjectEatenListener : gameObjectEatenListeners) {
            gameObjectEatenListener.agarEaten(movableGameObjectSprite, agarSprite);
        }
    }

    public void addAgarEatenListener(GameObjectEatenListener gameObjectEatenListener) {
        gameObjectEatenListeners.add(gameObjectEatenListener);
    }
}
