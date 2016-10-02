package factory.view;

import factory.GameObjectViewFactory;
import view.GameObjectView;
import view.PlayerBacteriaView;

import java.io.IOException;

public class PlayerBacteriaViewFactory extends GameObjectViewFactory {

    @Override
    public GameObjectView createGameObjectView() {
        try {
            return new PlayerBacteriaView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
