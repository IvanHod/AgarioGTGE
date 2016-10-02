package view;

import com.golden.gamedev.Game;
import dish.model.Dish;
import game.model.GameModel;

import java.awt.*;
import java.io.IOException;


public class GameView extends Game {

    Dish dish;

    GameModel gm;

    @Override
    public void initResources() {

        try {

            gm = new GameModel();

            dish = new Dish(new DishView());

        } catch (IOException e) {

            e.printStackTrace();

        }

        dish.background().setClip(0, 0, (int) dimension().getWidth(),(int) dimension().getHeight());

        gm.getPlayerBacteria()
                .sprite()
                .setBackground(dish.background());

        gm.getPlayerBacteria()
                .setSpeed(0.3);

        gm.getPlayerBacteria()
                .setPosition(new Point((int) (dimension().getWidth() / 2),
                        (int) (dimension().getHeight() / 2)));

    }

    @Override
    public void update(long l) {
        dish.background().update(l);
        gm.getPlayerBacteria().sprite().update(l);
        gm.update(mousePosition());
    }


    @Override
    public void render(Graphics2D g) {
        dish.background().render(g);
        gm.getPlayerBacteria().sprite().render(g);

        dish.background()
                .setToCenter(gm.getPlayerBacteria().sprite());
    }


    public Dimension dimension() {
        return new Dimension(1280, 720);
    }

    public Dimension viewport() {
        return new Dimension(5000, 5000);
    }

    public Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += dish.background().getX();
        p.y += dish.background().getY();
        return p;
    }

}
