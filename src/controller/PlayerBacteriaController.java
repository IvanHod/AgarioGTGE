package controller;

import gameobject.PlayerBacteria;
import utils.GameMath;

import java.awt.*;

public class PlayerBacteriaController {

    PlayerBacteria playerBacteria;

    public PlayerBacteriaController(PlayerBacteria playerBacteria) {
        this.playerBacteria = playerBacteria;

    }

    public void update(Point mousePosition) {
        int angle = GameMath.angle(playerBacteria.getPosition(), mousePosition);
        playerBacteria.setDirection(angle);
    }
}
