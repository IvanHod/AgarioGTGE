package game;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import collision.AIAgarCollision;
import collision.PlayerAICollision;
import collision.PlayerAgarCollision;
import collision.PlayerObstacleCollision;
import gamemodel.Dish;
import gamemodel.GameModel;
import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.Obstacle;
import listeners.AgarEatenListener;
import listeners.RevealAgarListener;


public class GameView extends Game implements RevealAgarListener, AgarEatenListener {

    public static final Dimension VIEWPORT = new Dimension(3000, 3000);
    public static final Dimension DIMENSION = new Dimension(1280, 720);
    public static Point initialPlayerPosition = new Point((int) (DIMENSION.getWidth() / 2),
            (int) (DIMENSION.getHeight() / 2));
    final String DISH_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";
    ImageBackground bg;

    PlayField pf;

    GameModel gm;

    Dish dish;

    SpriteGroup playerGroup, obstacleGroup, agarGroup, aiBacteriaGroup;

    PlayerAgarCollision agarPlayerCollision;

    GameFont gameFont;


    @Override
    public void initResources() {

        try {

            this.dish = new Dish();

            this.gm = new GameModel(this.dish);

            gm.addAgarGeneratedListener(this);

            this.bg = new ImageBackground(ImageIO.read(new File(DISH_BACKGROUND_IMAGE_PATH)));

            this.bg.setClip(0, 0, (int) DIMENSION.getWidth(), (int) DIMENSION.getHeight());

            pf = new PlayField(bg);

            playerGroup = pf.addGroup(new SpriteGroup("Player Group"));

            obstacleGroup = pf.addGroup(new SpriteGroup("Obstacle Group"));

            agarGroup = pf.addGroup(new SpriteGroup("Agar Group"));

            aiBacteriaGroup = pf.addGroup(new SpriteGroup("AI Bacteria Group"));

            playerGroup.add(dish.playerBacteria().sprite());

            for (Obstacle obstacle : dish.obstacles()) {
                obstacleGroup.add(obstacle.sprite());
            }

            for (Agar agar : dish.agar()) {
                agar.sprite().setActive(false);
                agarGroup.add(agar.sprite());
            }

            for (AIBacteria aiBacteria : dish.aiBacterias()) {
                aiBacteriaGroup.add(aiBacteria.sprite());
            }

            pf.addCollisionGroup(playerGroup, obstacleGroup, new PlayerObstacleCollision());

            pf.addCollisionGroup(playerGroup, aiBacteriaGroup, new PlayerAICollision());

            pf.addCollisionGroup(aiBacteriaGroup, agarGroup, new AIAgarCollision());

            agarPlayerCollision = new PlayerAgarCollision();

            agarPlayerCollision.addAgarEatenListener(this);

            agarPlayerCollision.addAgarEatenListener(gm);

            pf.addCollisionGroup(playerGroup, agarGroup, agarPlayerCollision);

            gameFont = fontManager.getFont(getImage("assets/font/font.fnt"));

        } catch (IOException e) {
            e.printStackTrace();
        }

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

    Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }

    @Override
    public void revealAgar() {

        int revealedAgar = 0;

        for (Sprite agarSprite : agarGroup.getSprites()) {
            if (!agarSprite.isActive()) {
                agarSprite.setActive(true);
                revealedAgar++;
            }

            if (revealedAgar == 10) {
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
