
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import game.GameView;
import screen.GameOverScreen;
import screen.StartScreen;

/**
 * Класс загружает все игровые ресурсы и выбирает, какой экран показывать игроку
 */
public class AgarIOGame extends GameEngine {

    /**
     * Выбирает экран игры
     *
     * @param gameID ID экрана
     * @return экран
     */
    @Override
    public GameObject getGame(int gameID) {
        GameObject game = null;
        switch (gameID) {
            case 0:
                game = new StartScreen(this);
                break;
            case 1:
                game = new GameView(this);
                break;
            case 2:
                game = new GameOverScreen(this);
                break;
        }
        return game;
    }
}
