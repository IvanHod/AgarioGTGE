package controller;


import java.awt.*;

import game.GameView;
import gameobject.Bacteria;

/**
 * Абстрактный контроллер за игровым полем
 */
public abstract class BacteriaController {

    /**
     * Контролируемые Бактерии
     */
    protected Bacteria bacteria, otherBacteria;

    /**
     * Проверяет, находится ли Бактерия на краю игрового поля или нет
     *
     * @param mousePosition позиция курсора на игровом поле
     * @return находится ли Бактерия на краю игрового поля или нет
     */
    public boolean update(Point mousePosition) {
        boolean isOnEdge = false;

        // Проверка левой стороны игрового поля

        if (bacteria.sprite().getX() <= 0 && bacteria.sprite().getHorizontalSpeed() < 0) {
            bacteria.sprite().setX(bacteria.sprite().getOldX());
            isOnEdge = true;
        }

        // Проверка правой стороны игрового поля

        if (bacteria.sprite().getX() + bacteria.sprite().getWidth() >=
                GameView.GAME_FIELD_SIZE.getWidth() && bacteria.sprite().getHorizontalSpeed() > 0) {
            bacteria.sprite().setX(bacteria.sprite().getOldX());
            isOnEdge = true;
        }

        // Проверка верхней стороны игрового поля

        if (bacteria.sprite().getY() <= 0 && bacteria.sprite().getVerticalSpeed() < 0) {
            bacteria.sprite().setY(bacteria.sprite().getOldY());
            isOnEdge = true;
        }

        // Проверка нижней стороны игрового поля

        if (bacteria.sprite().getY() + bacteria.sprite().getHeight() >=
                GameView.GAME_FIELD_SIZE.getHeight() && bacteria.sprite().getVerticalSpeed() > 0) {
            bacteria.sprite().setY(bacteria.sprite().getOldY());
            isOnEdge = true;
        }

        return isOnEdge;
    }

}
