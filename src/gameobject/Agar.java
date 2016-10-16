package gameobject;

import java.awt.*;

import view.AgarView;

public class Agar extends GameObject {


    public Agar(AgarView agarView) {

        gameObjectView = agarView;
    }

    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (this.sprite().getX());
        position.y = (int) (this.sprite().getY());
        return position;
    }

}
