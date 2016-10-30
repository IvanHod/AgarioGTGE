package gameobject;

import view.AgarView;

/**
 * Агар
 */
public class Agar extends GameObject {

    /**
     * Конструктор класса с параметром
     *
     * @param agarView представление Агара
     */
    public Agar(AgarView agarView) {

        gameObjectView = agarView;
    }
}
