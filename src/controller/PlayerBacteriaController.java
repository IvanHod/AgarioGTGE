package controller;

import gameobject.PlayerBacteria;
import utils.GameMath;

import java.awt.*;

public class PlayerBacteriaController extends MovableObjectController {


    public PlayerBacteriaController(PlayerBacteria playerBacteria) {
        movableGameObject = playerBacteria;
    }

    public void update(Point mousePosition) {
        super.update(mousePosition);
        int angle = GameMath.angle(movableGameObject.getPosition(), mousePosition);
        movableGameObject.setDirection(angle);
    }
}
