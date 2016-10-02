package collision;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerObstacleCollision extends BasicCollisionGroup {

    public PlayerObstacleCollision() {
        pixelPerfectCollision = true;
    }

    public void collided(Sprite s1, Sprite s2) {

        // s1 = player sprite
        // s2 = enemy sprite

        s1.setX(s1.getOldX());
        s1.setY(s1.getOldY());
    }

}