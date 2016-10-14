package view;

import collision.PlayerAgarCollision;
import collision.PlayerObstacleCollision;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import dish.model.Dish;
import game.model.GameModel;
import gameobject.model.Agar;
import gameobject.model.Obstacle;
import listeners.RevealAgarListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GameView extends Game implements RevealAgarListener {

    private final String DISH_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";

    public static Point initialPlayerPosition = new Point((int) (dimension().getWidth() / 2),
            (int) (dimension().getHeight() / 2));

    ImageBackground bg;

    PlayField pf;

    GameModel gm;

    Dish dish;

    SpriteGroup playerGroup, obstacleGroup, agarGroup;


    @Override
    public void initResources() {

        try {

            this.dish = new Dish();

            this.gm = new GameModel(this.dish);

            gm.addAgargeneratedListener(this);

            this.bg = new ImageBackground(ImageIO.read(new File(DISH_BACKGROUND_IMAGE_PATH)));

            this.bg.setClip(0, 0, (int) dimension().getWidth(),(int) dimension().getHeight());

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        pf = new PlayField(bg);

        playerGroup = pf.addGroup(new SpriteGroup("Player Group"));

        obstacleGroup = pf.addGroup(new SpriteGroup("Obstacle Group"));

        agarGroup = pf.addGroup((new SpriteGroup("Agar Group")));

        playerGroup.add(dish.playerBacteria().sprite());

        for (Obstacle obstacle : dish.obstacles()) {
            obstacleGroup.add(obstacle.sprite());
        }

        for (Agar agar : dish.agar()) {
            agar.sprite().setActive(false);
            agarGroup.add(agar.sprite());
        }

        pf.addCollisionGroup(playerGroup, obstacleGroup, new PlayerObstacleCollision());

        pf.addCollisionGroup(playerGroup, agarGroup, new PlayerAgarCollision());

        dish.playerBacteria().setSpeed(0.3);

        dish.playerBacteria().setPosition(initialPlayerPosition);

    }

    @Override
    public void update(long elapsedTime) {
        pf.update(elapsedTime);
        gm.update(mousePosition());
    }

    @Override
    public void render(Graphics2D g) {
        pf.render(g);
        bg.setToCenter(dish.playerBacteria().sprite());
    }


    public static Dimension dimension() {

        return new Dimension(1280, 720);
    }

    public static Dimension viewport() {

        return new Dimension(3000, 3000);
    }

    private Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }

    @Override
    public void revealAgar() {

        int revealedAgar = 0;

        for (Sprite agarSprite : agarGroup.getSprites()) {
            if(!agarSprite.isActive()) {
                agarSprite.setActive(true);
                revealedAgar++;
            }

            if(revealedAgar == 10){
                break;
            }

        }
    }
}
