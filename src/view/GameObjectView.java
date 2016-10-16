package view;

import com.golden.gamedev.object.Sprite;

import java.awt.image.BufferedImage;


public abstract class GameObjectView {

    BufferedImage gameObjectImage;

    Sprite gameObjectSprite;

    protected GameObjectView() {
        gameObjectSprite = new Sprite();
    }

    public BufferedImage getObjectImage() {
        return gameObjectImage;
    }

    public Sprite getObjectSprite() {
        return gameObjectSprite;
    }
}
