package sprite;

import com.golden.gamedev.object.Sprite;
import view.ObstacleView;


public class ObstacleSprite extends Sprite {

    public ObstacleSprite(ObstacleView obstacleView) {

        this.setImage(obstacleView.getObjectImage());
    }
}
