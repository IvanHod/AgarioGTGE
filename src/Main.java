import com.golden.gamedev.GameLoader;

import game.GameView;

/**
 * Главный класс приложения
 */
public class Main {

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new AgarIOGame(), GameView.GAME_WINDOW, false);
        game.start();
    }
}
