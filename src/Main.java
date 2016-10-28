import com.golden.gamedev.GameLoader;

import game.GameView;


public class Main {

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new AgarIOGame(), GameView.DIMENSION, false);
        game.start();
    }
}
