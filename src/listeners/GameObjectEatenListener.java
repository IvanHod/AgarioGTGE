package listeners;


import com.golden.gamedev.object.Sprite;

public interface GameObjectEatenListener {
    void agarEaten(Sprite movableGameObjectSprite, Sprite agarSprite);

    void movableObjectEaten(Sprite playerBacteria, Sprite aiBacteria);
}
