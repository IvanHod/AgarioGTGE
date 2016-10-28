package controller;


import java.awt.*;
import java.util.ArrayList;

import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.PlayerBacteria;
import utils.GameMath;
import utils.PositionRandomizer;

public class AIBacteriaController extends MovableObjectController {

    final int AGGRO_DISTANCE = 300;

    Point desiredPosition;

    ArrayList<Agar> agars;


    public AIBacteriaController(PlayerBacteria playerBacteria, AIBacteria aiBacteria, ArrayList<Agar> agars) {
        otherMovableGameObject = playerBacteria;
        movableGameObject = aiBacteria;
        desiredPosition = PositionRandomizer.getRandomPosition();

        this.agars = agars;
    }

    public boolean update(Point mousePosition) {

        boolean isOnEdge = super.update(mousePosition);

        Point playerPos = otherMovableGameObject.getPosition();
        Point aiPos = movableGameObject.getPosition();

        double distanceToPlayer = GameMath.distance(aiPos, playerPos);

        if (distanceToPlayer < AGGRO_DISTANCE) {

            int angle;

            if (otherMovableGameObject.level() <= movableGameObject.level()) {
                angle = GameMath.angle(aiPos, playerPos);
            } else {
                angle = GameMath.angle(aiPos, GameMath.getOppositePoint(playerPos, aiPos));
            }

            movableGameObject.setDirection(angle);

        } else {


            if (GameMath.distance(aiPos, desiredPosition) <= 50) {
                desiredPosition = PositionRandomizer.getRandomPosition();
            }

            if (isOnEdge) {
                desiredPosition = PositionRandomizer.getRandomPosition();
            }

            movableGameObject.setDirection(GameMath.angle(aiPos, desiredPosition));
        }
        return true;
    }

}