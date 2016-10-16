package controller;


import java.awt.*;

import game.GameView;
import gameobject.MovableGameObject;

public abstract class MovableObjectController {

    MovableGameObject movableGameObject, otherMovableGameObject;

    boolean isOnEdge;

    public boolean update(Point mousePosition) {
        isOnEdge = false;

        if (movableGameObject.sprite().getX() <= 0 && movableGameObject.sprite().getHorizontalSpeed() < 0) {
            movableGameObject.sprite().setX(movableGameObject.sprite().getOldX());
            isOnEdge = true;
        }

        if (movableGameObject.sprite().getX() + movableGameObject.sprite().getWidth() >=
                GameView.VIEWPORT.getWidth() && movableGameObject.sprite().getHorizontalSpeed() > 0) {
            movableGameObject.sprite().setX(movableGameObject.sprite().getOldX());
            isOnEdge = true;
        }

        if (movableGameObject.sprite().getY() <= 0 && movableGameObject.sprite().getVerticalSpeed() < 0) {
            movableGameObject.sprite().setY(movableGameObject.sprite().getOldY());
            isOnEdge = true;
        }

        if (movableGameObject.sprite().getY() + movableGameObject.sprite().getHeight() >=
                GameView.VIEWPORT.getHeight() && movableGameObject.sprite().getVerticalSpeed() > 0) {
            movableGameObject.sprite().setY(movableGameObject.sprite().getOldY());
            isOnEdge = true;
        }
        return isOnEdge;
    }

}
