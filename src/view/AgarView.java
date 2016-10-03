package view;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AgarView extends GameObjectView {


    private final String AGAR_SPRITE_IMAGE_PATH = "assets/sprites/agar/agar.png";

    private BufferedImage agarImage;

    public AgarView() throws IOException {

        this.agarImage = ImageIO.read(new File(AGAR_SPRITE_IMAGE_PATH));
    }

    @Override
    public BufferedImage getObjectImage() {
        return agarImage;
    }
}
