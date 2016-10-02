import com.golden.gamedev.GameLoader;
import game.model.GameModel;
import view.GameView;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        GameView gw = new GameView();
        game.setup(gw, gw.dimension(), false);
        game.start();
    }
}
