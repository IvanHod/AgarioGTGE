package factory.view;

import factory.GameObjectViewFactory;
import view.AgarView;
import view.GameObjectView;

import java.io.IOException;


public class AgarViewFactory extends GameObjectViewFactory {

    @Override
    public GameObjectView createGameObjectView() {
        try {
            return new AgarView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
