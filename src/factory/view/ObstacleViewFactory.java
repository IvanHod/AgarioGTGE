package factory.view;

import factory.GameObjectViewFactory;
import view.GameObjectView;
import view.ObstacleView;

import java.io.IOException;

public class ObstacleViewFactory extends GameObjectViewFactory {

    @Override
    public GameObjectView createGameObjectView() {
        try {
            return new ObstacleView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
