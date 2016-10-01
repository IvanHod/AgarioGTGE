import com.golden.gamedev.GameLoader;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        GameApplication gameApplication = new GameApplication();
        GameLoader loader = new GameLoader();
        loader.setup(gameApplication, new Dimension(1280, 720), false);
        loader.start();
    }
}
