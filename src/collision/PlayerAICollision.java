package collision;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import java.awt.*;
import java.util.ArrayList;

import listeners.MovableObjectEaten;
import utils.GameMath;

public class PlayerAICollision extends BasicCollisionGroup {

    final int MAX_DISTANCE_TO_EAT = 15;

    ArrayList<MovableObjectEaten> movableObjectEatenListeners = new ArrayList<>();

    public PlayerAICollision() {

        pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {

        Point playerPosition = new Point((int) sprite.getX(), (int) sprite.getY());
        Point aiPosition = new Point((int) sprite1.getX(), (int) sprite1.getY());
        if (GameMath.distance(playerPosition, aiPosition) <= MAX_DISTANCE_TO_EAT) {
            fireMovableObjectEaten(sprite, sprite1);
        }

    }

    void fireMovableObjectEaten(Sprite playerBacteria, Sprite aiBacteria) {
        for (MovableObjectEaten movableObjectEatenListener : movableObjectEatenListeners) {
            movableObjectEatenListener.movableObjectEaten(playerBacteria, aiBacteria);
        }

    }

    public void addPlayerEatenListener(MovableObjectEaten movableObjectEatenListener) {
        movableObjectEatenListeners.add(movableObjectEatenListener);
    }
}
