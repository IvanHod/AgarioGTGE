package sprite;


import com.golden.gamedev.object.Sprite;
import view.AIBacteriaView;

public class AIBacteriaSprite extends Sprite {

    public AIBacteriaSprite(AIBacteriaView aiBacteriaView) {
        this.setImage(aiBacteriaView.getObjectImage());
    }
}
