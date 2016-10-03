package view;

import collision.PlayerAgarCollision;
import collision.PlayerObstacleCollision;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import game.model.GameModel;
import gameobject.model.Agar;
import gameobject.model.Obstacle;
import sprite.AgarSpriteGroup;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GameView extends Game {

    private final String DISH_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";

    public static Point initialPlayerPosition = new Point((int) (dimension().getWidth() / 2),
            (int) (dimension().getHeight() / 2));

    ImageBackground bg;

    GameModel gm;

    SpriteGroup playerGroup;

    SpriteGroup obstacleGroup;

    SpriteGroup agarGroup;

    CollisionManager playerObstacleCollision;

    CollisionManager playerAgarCollision;

    @Override
    public void initResources() {

        try {
            gm = new GameModel();

            this.bg = new ImageBackground(ImageIO.read(new File(DISH_BACKGROUND_IMAGE_PATH)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        playerObstacleCollision = new PlayerObstacleCollision();

        playerAgarCollision = new PlayerAgarCollision();

        playerGroup = new SpriteGroup("Player Group");

        obstacleGroup = new SpriteGroup("Obstacle Group");

        agarGroup = new AgarSpriteGroup("Agar Group");

        bg.setClip(0, 0, (int) dimension().getWidth(),(int) dimension().getHeight());

        playerGroup.add(gm.dish().getPlayerBacteria().sprite());

        playerGroup.setBackground(bg);

        for (Obstacle obstacle : gm.dish().getObstacles()) {
            obstacleGroup.add(obstacle.sprite());
        }

        obstacleGroup.setBackground(bg);

        for(Agar agar : gm.dish().getAgars()) {
            agar.sprite().setActive(false);
            agarGroup.add(agar.sprite());
        }

        agarGroup.setBackground(bg);

        playerObstacleCollision.setCollisionGroup(playerGroup, obstacleGroup);

        playerAgarCollision.setCollisionGroup(playerGroup, agarGroup);


        gm.dish().getPlayerBacteria().setSpeed(0.3);

        gm.dish().getPlayerBacteria()
                    .setPosition(initialPlayerPosition);

    }

    @Override
    public void update(long elapsedTime) {
        bg.update(elapsedTime);
        obstacleGroup.update(elapsedTime);
        playerGroup.update(elapsedTime);
        agarGroup.update(elapsedTime);

        gm.update(mousePosition());


        playerObstacleCollision.checkCollision();
        playerAgarCollision.checkCollision();
    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);
        obstacleGroup.render(g);
        playerGroup.render(g);
        agarGroup.render(g);

        bg.setToCenter(gm.dish().getPlayerBacteria().sprite());
    }


    public static Dimension dimension() {
        return new Dimension(1280, 720);
    }

    public static Dimension viewport() {
        return new Dimension(3000, 3000);
    }

    public Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }

}
