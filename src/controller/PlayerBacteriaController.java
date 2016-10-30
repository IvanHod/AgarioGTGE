package controller;

import java.awt.*;

import gamemodel.GameModel;
import gameobject.PlayerBacteria;
import utils.GameMath;

/**
 * Контроллер для управления движением Бактерии игрока на игровом поле
 */
public class PlayerBacteriaController extends BacteriaController {

    /**
     * Конструктор класса с параметрами
     *
     * @param playerBacteria Бактерия игрока
     */
    public PlayerBacteriaController(PlayerBacteria playerBacteria) {
        bacteria = playerBacteria;
    }

    /**
     * Обновляет контроллер Бактерии игрока
     *
     * @param mousePosition позиция курсора на игровом поле
     * @return успех выполнения движения в определенную точку
     */
    @Override
    public boolean update(Point mousePosition) {

        // Находится ли Бактерия игрока на крае игрового поля

        super.update(mousePosition);

        // Позиция Бактерии игрока на игровом поле

        Point playerBacteriaPos = bacteria.getPosition();

        // Сделать так, чтобы центр спрайта находился под указателем мыши

        playerBacteriaPos.x = playerBacteriaPos.x + bacteria.sprite().getWidth() / 2;
        playerBacteriaPos.y = playerBacteriaPos.y + bacteria.sprite().getHeight() / 2;

        // Выбрать направление в сторону позиции указателя мыши на поле ...

        int angle = GameMath.angle(playerBacteriaPos, mousePosition);

        // ... двигаться по выбранному направлению

        bacteria.setDirection(angle);

        // Убрать "дерганье" спрайта при достижении указателя мыши

        if (mousePosition.y == playerBacteriaPos.y && mousePosition.x == playerBacteriaPos.x)
            bacteria.setSpeed(0);
        else
            bacteria.setSpeed(GameModel.PLAYER_SPEED);

        // Dummy-значение, которое не используется

        return true;
    }
}
