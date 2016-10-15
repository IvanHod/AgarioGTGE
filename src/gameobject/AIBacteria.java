package gameobject;


import com.golden.gamedev.object.Sprite;
import sprite.AIBacteriaSprite;

import java.awt.*;

public class AIBacteria extends GameObject implements MovableGameObject {

    int angle;

    double speed;

    AIBacteriaSprite aiBacteriaSprite;

    public AIBacteria(AIBacteriaSprite aiBacteriaSprite) {
        this.aiBacteriaSprite = aiBacteriaSprite;
    }

    @Override
    public Sprite sprite() {
        return aiBacteriaSprite;
    }

    @Override
    public void setPosition(Point position) {

    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public void setSpeed(double speed) {

    }

    @Override
    public double getSpeed() {
        return 0;
    }

    @Override
    public void setDirection(int angle) {

    }

    @Override
    public int getDirection() {
        return 0;
    }
}
