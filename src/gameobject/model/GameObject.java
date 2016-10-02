package gameobject.model;

import java.awt.*;

/**
 * Created by yura on 10/2/16.
 */
public abstract class GameObject {

    public abstract void setPosition(Point position);

    public abstract Point getPosition();

    public abstract void setSpeed(double speed);

    public abstract double getSpeed();

    public abstract void setDirection(int angle);

    public abstract int getDirection();
}
