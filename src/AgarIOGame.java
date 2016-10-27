import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

import game.GameView;
import screen.GameOverScreen;
import screen.StartScreen;

public class AgarIOGame extends GameEngine {

    @Override
    public GameObject getGame(int gameID) {
        switch (gameID) {
            case 0:
                return new StartScreen(this);
            case 1:
                return new GameView(this);
            case 2:
                return new GameOverScreen(this);
        }
        return null;
    }
}