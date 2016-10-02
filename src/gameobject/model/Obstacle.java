package gameobject.model;

import com.golden.gamedev.object.Sprite;
import sprite.ObstacleSprite;

import java.awt.*;

/**
 * Created by yura on 10/2/16.
 */
public class Obstacle extends GameObject {

    ObstacleSprite obstacleSprite;

    public Obstacle(ObstacleSprite obstacleSprite) {
        this.obstacleSprite = obstacleSprite;
    }

    @Override
    public ObstacleSprite sprite() {
        return obstacleSprite;
    }
}
