package screen;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import java.awt.*;
import java.awt.event.KeyEvent;


public class StartScreen extends GameObject {

    final static String SCREEN_BG = "assets/screens/start_screen.png";

    Background bg;


    public StartScreen(GameEngine gameEngine) {
        super(gameEngine);
        bg = new ImageBackground(getImage(SCREEN_BG));
    }

    @Override
    public void initResources() {
        hideCursor();
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
