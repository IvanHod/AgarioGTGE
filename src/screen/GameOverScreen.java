package screen;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScreen extends GameObject {


    final String SCREEN_BG = "assets/screens/gameover_screen.png";

    Background bg;


    public GameOverScreen(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void initResources() {
        hideCursor();
        bg = new ImageBackground(getImage(SCREEN_BG));
    }

    @Override
    public void update(long elapsedTime) {
        bg.update(elapsedTime);

        if (keyPressed(KeyEvent.VK_ENTER)) {
            parent.nextGameID = 1;
            finish();
        }

    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);
    }
}
