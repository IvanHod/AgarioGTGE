package dish.model;



import gameobject.model.GameObject;
import gameobject.model.Obstacle;
import gameobject.model.PlayerBacteria;
import sprite.PlayerBacteriaSprite;

import java.util.ArrayList;


public class Dish {

    PlayerBacteria playerBacteria;

    ArrayList<Obstacle> obstacles = new ArrayList<>();


    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void addPlayerBacteria(PlayerBacteria playerBacteria) {
        this.playerBacteria = playerBacteria;
    }

    public PlayerBacteria getPlayerBacteria() {
        return playerBacteria;
    }


}
