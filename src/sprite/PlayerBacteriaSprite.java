package sprite;


import com.golden.gamedev.object.Sprite;
import view.PlayerBacteriaView;


public class PlayerBacteriaSprite extends Sprite {

    public PlayerBacteriaSprite(PlayerBacteriaView playerBacteriaView) {
        this.setImage(playerBacteriaView.getObjectImage());
    }

}
