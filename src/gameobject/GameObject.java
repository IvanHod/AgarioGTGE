package gameobject;

import com.golden.gamedev.object.Sprite;
import view.GameObjectView;

public abstract class GameObject {

    GameObjectView gameObjectView;

    public Sprite sprite() {
        return gameObjectView.getObjectSprite();
    }
}
