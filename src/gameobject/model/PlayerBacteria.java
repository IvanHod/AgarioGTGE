package gameobject.model;

import sprite.PlayerBacteriaSprite;
import utils.GameMath;

import java.awt.*;

public class PlayerBacteria extends GameObject implements MovableGameObject {

    int angle = 0;

    double speed = 0;

    PlayerBacteriaSprite playerBacteriaSprite;

    public PlayerBacteria(PlayerBacteriaSprite playerBacteriaSprite) {
        this.playerBacteriaSprite = playerBacteriaSprite;
    }

    public PlayerBacteriaSprite sprite() {
        return playerBacteriaSprite;
    }

    @Override
    public void setPosition(Point position) {
        this.playerBacteriaSprite.setX(position.getX() - 1 / 2);
        this.playerBacteriaSprite.setY(position.getY() - 1 / 2);
    }

    @Override
    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (playerBacteriaSprite.getX());
        position.y = (int) (playerBacteriaSprite.getY());
        return position;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
        playerBacteriaSprite.setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        playerBacteriaSprite.setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    @Override
    public double getSpeed() {
        return speed;
    }


    @Override
    public void setDirection(int angle) {
        this.angle = angle;
        playerBacteriaSprite.setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        playerBacteriaSprite.setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    @Override
    public int getDirection() {
        return angle;
    }
}
