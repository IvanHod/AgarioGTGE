package controller;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

        //Point nearestAgarPos = findNearestAgar(aiPos);

        //double distanceToAgar = GameMath.distance(aiPos, nearestAgarPos);
        double distanceToPlayer = GameMath.distance(aiPos, playerPos);

        if (distanceToPlayer < AGGRO_DISTANCE && otherMovableGameObject.level() <= movableGameObject.level()) {
            int toPlayer = GameMath.angle(movableGameObject.getPosition(), otherMovableGameObject.getPosition());

            movableGameObject.setDirection(toPlayer);

        } else {

            /*if(distanceToAgar != 0) {
                desiredPosition = nearestAgarPos;
            }*/

            if (GameMath.distance(aiPos, desiredPosition) <= 50) {
                desiredPosition = PositionRandomizer.getRandomPosition();
            }

            if (isOnEdge) {
                desiredPosition = PositionRandomizer.getRandomPosition();
            }

            movableGameObject.setDirection(GameMath.angle(movableGameObject.getPosition(), desiredPosition));
        }
        return true;
    }

    Point findNearestAgar(Point aiPos) {

        Map<Double, Point> distances = new HashMap<Double, Point>();

        for (Agar agar : agars) {
            distances.put(GameMath.distance(aiPos, agar.getPosition()), agar.getPosition());
        }

        double minDistance = Collections.min(distances.keySet());

        return distances.get(minDistance);

    }


}