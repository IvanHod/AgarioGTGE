package sprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import com.golden.gamedev.object.Timer;


public class AgarSpriteGroup extends SpriteGroup{

    Timer timer = new Timer(1000);

    int updatedSpriteCounter = 0;

    public AgarSpriteGroup(String s) {
        super(s);
    }

    public void update(long elapsedTime) {
        if(timer.action(elapsedTime)) {
            updatedSpriteCounter = 0;
            for(Sprite sprite : this.getSprites()) {
                if(updatedSpriteCounter <= 10){
                    if(!sprite.isActive()) {
                        sprite.setActive(true);
                        updatedSpriteCounter++;
                    }
                }
                else
                    break;
            }
        }
    }

}
