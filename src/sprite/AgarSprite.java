package sprite;

import com.golden.gamedev.object.Sprite;
import view.AgarView;


public class AgarSprite extends Sprite {

    public AgarSprite(AgarView agarView){
        this.setImage(agarView.getObjectImage());
    }
}
