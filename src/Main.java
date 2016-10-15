import com.golden.gamedev.GameLoader;
import view.GameView;


public class Main {

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        GameView gw = new GameView();
        game.setup(gw, gw.dimension(), false);
        game.start();
    }
}
