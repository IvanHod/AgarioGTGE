package factory;

import gameobject.model.GameObject;
import gameobject.model.MovableGameObject;

public abstract class GameObjectFactory {
    public abstract GameObject createGameObject();
}
