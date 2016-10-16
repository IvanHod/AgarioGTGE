package gameobject;


import java.awt.*;

import utils.GameMath;

public abstract class MovableGameObject extends GameObject {

    int angle;

    double speed;

    int level = 1;

    int agarEaten;

    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (this.sprite().getX());
        position.y = (int) (this.sprite().getY());
        return position;
    }

    public void setPosition(Point position) {
        this.sprite().setX(position.getX() - 1 / 2);
        this.sprite().setY(position.getY() - 1 / 2);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        this.sprite().setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        this.sprite().setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    public int getDirection() {
        return angle;
    }

    public void setDirection(int angle) {
        this.angle = angle;
        this.sprite().setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        this.sprite().setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    public int level() {
        return level;
    }

    public int agarEatenCount() {
        return agarEaten;
    }

    public void increaseEatenAgarAmount(){
        agarEaten++;
    }

    public void leveUp() {
        level++;
    }

}
