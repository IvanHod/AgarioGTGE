package gameobject.model;

import sprite.PlayerSprite;
import utils.GameMath;

import java.awt.*;

/**
 * Created by yura on 10/2/16.
 */
public class PlayerBacteria extends GameObject {

    int angle = 0;

    double speed = 0;

    PlayerSprite playerSprite;

    public PlayerBacteria(PlayerSprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public PlayerSprite sprite() {
        return playerSprite;
    }

    @Override
    public void setPosition(Point position) {
        this.playerSprite.setX(position.getX() - 1 / 2);
        this.playerSprite.setY(position.getY() - 1 / 2);
    }

    @Override
    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (playerSprite.getX());
        position.y = (int) (playerSprite.getY());
        return position;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
        playerSprite.setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        playerSprite.setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    @Override
    public double getSpeed() {
        return speed;
    }


    @Override
    public void setDirection(int angle) {
        this.angle = angle;
        playerSprite.setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        playerSprite.setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    @Override
    public int getDirection() {
        return angle;
    }
}
