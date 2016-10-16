package controller;


import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import gameobject.AIBacteria;
import gameobject.PlayerBacteria;
import utils.GameMath;
import utils.PositionRandomizer;

public class AIBacteriaController extends MovableObjectController {

    final int AGGRO_DISTANCE = 300;

    Point desiredPosition;


    public AIBacteriaController(PlayerBacteria playerBacteria, AIBacteria aiBacteria) {
        otherMovableGameObject = playerBacteria;
        movableGameObject = aiBacteria;
        desiredPosition = PositionRandomizer.getRandomPosition();
    }

    public void update(Point mousePosition) {
        super.update(mousePosition);

        Point playerPos = otherMovableGameObject.getPosition();
        Point aiPos = movableGameObject.getPosition();

        double distanceToPlayer = GameMath.distance(aiPos, playerPos);

        if (distanceToPlayer < AGGRO_DISTANCE) {
            int toPlayer = GameMath.angle(movableGameObject.getPosition(), otherMovableGameObject.getPosition());

            movableGameObject.setDirection(toPlayer);
        }

        else {

            if (GameMath.distance(aiPos, desiredPosition) <= 50) {
                desiredPosition = PositionRandomizer.getRandomPosition();
            }

            movableGameObject.setDirection(GameMath.angle(movableGameObject.getPosition(), desiredPosition));
        }
    }


}