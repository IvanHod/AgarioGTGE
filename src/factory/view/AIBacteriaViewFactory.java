package factory.view;


import factory.GameObjectViewFactory;
import view.AIBacteriaView;
import view.GameObjectView;

import java.io.IOException;

public class AIBacteriaViewFactory extends GameObjectViewFactory {
    @Override
    public GameObjectView createGameObjectView() {
        try {
            return new AIBacteriaView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
