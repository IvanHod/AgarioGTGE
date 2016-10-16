package gameobject;


import utils.GameMath;

import java.awt.*;

public abstract class MovableGameObject extends GameObject {

    int angle;

    double speed;

    public void setPosition(Point position) {
        this.sprite().setX(position.getX() - 1 / 2);
        this.sprite().setY(position.getY() - 1 / 2);
    }

    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (this.sprite().getX());
        position.y = (int) (this.sprite().getY());
        return position;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        this.sprite().setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        this.sprite().setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    public double getSpeed() {
        return speed;
    }


    public void setDirection(int angle) {
        this.angle = angle;
        this.sprite().setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        this.sprite().setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    public int getDirection() {
        return angle;
    }
}
