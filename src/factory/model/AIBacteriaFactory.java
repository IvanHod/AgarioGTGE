package factory.model;


import java.awt.*;
import java.io.IOException;

import factory.GameObjectFactory;
import gameobject.AIBacteria;
import gameobject.GameObject;
import utils.PositionRandomizer;
import view.AIBacteriaView;

/**
 * Фабрика ИИБактерий
 */
public class AIBacteriaFactory extends GameObjectFactory {

    /**
     * Позиция созданной ИИБактерии на поле
     */
    private Point position;

    /**
     * Представление ИИБактерии
     */
    private AIBacteriaView aiBacteriaView;

    /**
     * Создает объект ИИБактерии
     *
     * @return объект ИИБактерии
     */
    @Override
    public GameObject createGameObject(int level) {

        try {
            aiBacteriaView = new AIBacteriaView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Генерация случайной позиции ИИБактерии на игровом поле

        position = PositionRandomizer.getRandomPosition();
        aiBacteriaView.getObjectSprite().setX(position.x);
        aiBacteriaView.getObjectSprite().setY(position.y);
        
        return new AIBacteria(aiBacteriaView,level);
    }
}
