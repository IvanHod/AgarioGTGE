package gameobject;


import com.golden.gamedev.object.Sprite;
import sprite.AgarSprite;

public class Agar extends GameObject{

    AgarSprite agarSprite;

    public Agar(AgarSprite agarSprite) {
        this.agarSprite = agarSprite;
    }

    @Override
    public Sprite sprite() {
        return agarSprite;
    }
}
