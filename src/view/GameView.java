package view;

import collision.PlayerAgarCollision;
import collision.PlayerObstacleCollision;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;
import dish.model.Dish;
import game.model.GameModel;
import gameobject.model.Agar;
import gameobject.model.Obstacle;
import listeners.AgarEatenListener;
import listeners.RevealAgarListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameView extends Game implements RevealAgarListener, AgarEatenListener {

    private final String DISH_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";

    public static Point initialPlayerPosition = new Point((int) (dimension().getWidth() / 2),
            (int) (dimension().getHeight() / 2));

    ImageBackground bg;

    PlayField pf;

    GameModel gm;

    Dish dish;

    SpriteGroup playerGroup, obstacleGroup, agarGroup;

    PlayerAgarCollision agarPlayerCollision;

    GameFont gameFont;


    @Override
    public void initResources() {

        try {

            this.dish = new Dish();

            this.gm = new GameModel(this.dish);

            gm.addAgarGeneratedListener(this);

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

        agarPlayerCollision = new PlayerAgarCollision();

        agarPlayerCollision.addAgarEatenListener(this);

        agarPlayerCollision.addAgarEatenListener(gm);

        pf.addCollisionGroup(playerGroup, agarGroup, agarPlayerCollision);

        dish.playerBacteria().setSpeed(0.3);

        dish.playerBacteria().setPosition(initialPlayerPosition);

        gameFont = fontManager.getFont(getImage("assets/font/font.fnt"));

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

        gameFont.drawString(g, "AGAR COUNT: " + String.valueOf(gm.getAgarEatenCount()), 9, 9);
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

    @Override
    public void agarEaten(Sprite agarSprite) {

        agarGroup.remove(agarSprite);
        playSound("assets/sound/agar.wav");
    }
}
