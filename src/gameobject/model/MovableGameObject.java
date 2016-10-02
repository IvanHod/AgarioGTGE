package gameobject.model;

import java.awt.*;

/**
 * Created by yura on 10/2/16.
 */
public interface MovableGameObject {

    void setPosition(Point position);

    Point getPosition();

    void setSpeed(double speed);

    double getSpeed();

    void setDirection(int angle);

    int getDirection();
}
