package sprite;


import com.golden.gamedev.object.Sprite;
import view.PlayerBacteriaView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSprite extends Sprite {

    BufferedImage spriteImage;

    public PlayerSprite(PlayerBacteriaView playerBacteriaView) {
        this.setImage(playerBacteriaView.getObjectImage());
    }

}
