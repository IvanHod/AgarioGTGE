import com.golden.gamedev.GameLoader;

import game.GameView;


public class Main {

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        GameView gameView = new GameView();
        game.setup(gameView, gameView.DIMENSION, false);
        game.start();
    }
}
