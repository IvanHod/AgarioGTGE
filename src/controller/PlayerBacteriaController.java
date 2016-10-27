package controller;

import java.awt.*;

import gameobject.PlayerBacteria;
import utils.GameMath;

public class PlayerBacteriaController extends MovableObjectController {


    public PlayerBacteriaController(PlayerBacteria playerBacteria) {
        movableGameObject = playerBacteria;
    }

    public boolean update(Point mousePosition) {
        super.update(mousePosition);
        Point movableObjectPos = movableGameObject.getPosition();
        movableObjectPos.x = movableObjectPos.x + movableGameObject.sprite().getWidth() / 2;
        movableObjectPos.y = movableObjectPos.y + movableGameObject.sprite().getHeight() / 2;
        int angle = GameMath.angle(movableObjectPos, mousePosition);
        movableGameObject.setDirection(angle);

        if (mousePosition.y == movableObjectPos.y && mousePosition.x == movableObjectPos.x)
            movableGameObject.setSpeed(0);
        else
            movableGameObject.setSpeed(0.3);
        return true;
    }
}
