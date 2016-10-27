import com.golden.gamedev.GameLoader;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new AgarIOGame(), new Dimension(1280, 720), false);
        game.start();
    }
}
