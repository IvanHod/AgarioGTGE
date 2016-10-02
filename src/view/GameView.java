package view;

import collision.PlayerObstacleCollision;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import game.model.GameModel;
import gameobject.model.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GameView extends Game {

    private final String DISH_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";

    ImageBackground bg;

    GameModel gm;

    SpriteGroup playerGroup;

    SpriteGroup obstacleGroup;

    CollisionManager collision;

    @Override
    public void initResources() {

        try {
            gm = new GameModel();

            this.bg = new ImageBackground(ImageIO.read(new File(DISH_BACKGROUND_IMAGE_PATH)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = new PlayerObstacleCollision();

        playerGroup = new SpriteGroup("Player Group");

        obstacleGroup = new SpriteGroup("Obstacle Group");


        bg.setClip(0, 0, (int) dimension().getWidth(),(int) dimension().getHeight());

        playerGroup.add(gm.dish().getPlayerBacteria().sprite());

        playerGroup.setBackground(bg);

        for (Obstacle obstacle : gm.dish().getObstacles()) {
            obstacleGroup.add(obstacle.sprite());
        }

        obstacleGroup.setBackground(bg);

        collision.setCollisionGroup(playerGroup, obstacleGroup);




        gm.dish().getPlayerBacteria().setSpeed(0.3);

        gm.dish().getPlayerBacteria()
                    .setPosition(new Point((int) (dimension().getWidth() / 2),
                        (int) (dimension().getHeight() / 2)));

    }

    @Override
    public void update(long elapsedTime) {
        bg.update(elapsedTime);
        obstacleGroup.update(elapsedTime);
        playerGroup.update(elapsedTime);

        gm.update(mousePosition());

        collision.checkCollision();
    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);
        obstacleGroup.render(g);
        playerGroup.render(g);

        bg.setToCenter(gm.dish().getPlayerBacteria().sprite());
    }


    public static Dimension dimension() {
        return new Dimension(1280, 720);
    }

    public Dimension viewport() {
        return new Dimension(5000, 5000);
    }

    public Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }

}
