package view;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AgarView extends GameObjectView {


    final String AGAR_SPRITE_IMAGE_PATH = "assets/sprites/agar/agar.png";

    public AgarView() throws IOException {
        gameObjectImage = ImageIO.read(new File(AGAR_SPRITE_IMAGE_PATH));
        gameObjectSprite.setImage(gameObjectImage);
    }
}
