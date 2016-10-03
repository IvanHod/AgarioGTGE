package gameobject.model;


import sprite.ObstacleSprite;

public class Obstacle extends GameObject {

    ObstacleSprite obstacleSprite;

    public Obstacle(ObstacleSprite obstacleSprite) {
        this.obstacleSprite = obstacleSprite;
    }

    @Override
    public ObstacleSprite sprite() {
        return obstacleSprite;
    }
}
