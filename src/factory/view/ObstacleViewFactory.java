package factory.view;

import factory.GameObjectViewFactory;
import view.GameObjectView;
import view.ObstacleView;

import java.io.IOException;

/**
 * Created by yura on 10/2/16.
 */
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
