package dish.model;

import com.golden.gamedev.object.background.ImageBackground;
import view.DishView;


public class Dish {

    ImageBackground imageBackground;

    public Dish(DishView dishView) {
        this.imageBackground = dishView.getBackground();
    }

    public ImageBackground background() {
        return imageBackground;
    }

}
