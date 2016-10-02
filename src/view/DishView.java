package view;

import com.golden.gamedev.object.background.ImageBackground;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DishView {

    private final String DISH_BACKGROUND_IMAGE_PATH = "assets/bg/bg.png";


    ImageBackground imageBackground;

    public DishView() throws IOException {
        this.imageBackground = new ImageBackground(ImageIO.read(new File(DISH_BACKGROUND_IMAGE_PATH)));
    }

    public ImageBackground getBackground() {
        return this.imageBackground;
    }



}