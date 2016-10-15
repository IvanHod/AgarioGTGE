package gameobject;

import java.awt.*;

public interface MovableGameObject {

    void setPosition(Point position);

    Point getPosition();

    void setSpeed(double speed);

    double getSpeed();

    void setDirection(int angle);

    int getDirection();
}
