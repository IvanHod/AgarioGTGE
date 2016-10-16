package gamemodel;


import java.util.ArrayList;

import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.Obstacle;
import gameobject.PlayerBacteria;


public class Dish {

    PlayerBacteria playerBacteria;

    ArrayList<Obstacle> obstacles = new ArrayList<>();

    ArrayList<Agar> agars = new ArrayList<>();

    ArrayList<AIBacteria> aiBacterias = new ArrayList<>();

    public void addObstacle(Obstacle obstacle) {

        obstacles.add(obstacle);
    }

    public ArrayList<Obstacle> obstacles() {

        return obstacles;
    }

    public void addAgar(Agar agar) {

        agars.add(agar);
    }

    public ArrayList<Agar> agar() {

        return agars;
    }

    public void addPlayerBacteria(PlayerBacteria playerBacteria) {

        this.playerBacteria = playerBacteria;
    }

    public PlayerBacteria playerBacteria() {

        return playerBacteria;
    }

    public void addAiBacteria(AIBacteria aiBacteria) {
        aiBacterias.add(aiBacteria);
    }

    public ArrayList<AIBacteria> aiBacterias() {
        return aiBacterias;
    }


}
