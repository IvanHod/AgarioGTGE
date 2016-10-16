package controller;


import gameobject.AIBacteria;
import gameobject.PlayerBacteria;
import utils.GameMath;

import java.awt.*;

public class AIBacteriaController extends MovableObjectController {

    public AIBacteriaController(PlayerBacteria playerBacteria, AIBacteria aiBacteria) {
        otherMovableGameObject = playerBacteria;
        movableGameObject = aiBacteria;
        int angle = GameMath.angle(aiBacteria.getPosition(), playerBacteria.getPosition());
        aiBacteria.setDirection(angle);
    }

    public void update(Point mousePosition) {
        super.update(mousePosition);
        Point playerPos = otherMovableGameObject.getPosition();
        Point spritePos = movableGameObject.getPosition();
        double dist = GameMath.distance(spritePos, playerPos);
        if (dist > 500) {
            int angle = GameMath.angle(movableGameObject.getPosition(), otherMovableGameObject.getPosition());
            movableGameObject.setDirection(angle);
        }
    }

}
