package gameobject;

import view.AgarView;

/**
 * Агар
 */
public class Agar extends Bacteria {

    /**
     * Конструктор класса с параметром
     *
     * @param agarView представление Агара
     */
    public Agar(AgarView agarView) {

        gameObjectView = agarView;
    }
}
