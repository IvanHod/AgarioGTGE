package controller;


import gamemodel.Dish;
import java.awt.*;

import gameobject.AIBacteria;
import gameobject.Bacteria;
import gameobject.PlayerBacteria;
import utils.GameMath;
import utils.PositionRandomizer;

/**
 * Контроллер для управления движением ИИБактерии на игровом поле
 */
public class AIBacteriaController extends BacteriaController {

    /**
     * Максимальное расстояние, при котором ИИБактерия начинает следовать за Бактерией игрока
     * (агрессивно себя вести)
     */
    private final static int AGGRO_DISTANCE = 300;

    /**
     * Максимальное расстояние до конечной точки движения, при достижении которого
     * ИИБактерия меняет свою конечную точку движения
     * Сделано для случаев, когда конечная точка попадает на непроходимое Препятствие
     */
    private final static int DESIRED_POSITION_BOUND = 50;
    
    /**
     * чаша Петри
     */
    private Dish dish;
    
    /**
     * Бактерия для контроллера
     */
    private AIBacteria bacteria;
    
    /**
     * Конструктор класса с параметрами
     *
     * @param dish чаша Петри
     */
    public AIBacteriaController(Dish _dish, AIBacteria _bacteria) {
        dish = _dish;
        bacteria = _bacteria;
    }

    /**
     * Обновляет контроллер ИИБактерии
     *
     * @param mousePosition позиция курсора на игровом поле
     * @return успех выполнения движения в определенную точку
     */
    @Override
    public boolean update(Point mousePosition) {
        
        
        // выбрать цель
        Bacteria _bacteria = chooseTarget();
        if(_bacteria != null) {
                boolean toRun = bacteria.level() < _bacteria.level();
            int angle = GameMath.angle(bacteria.getPosition(), _bacteria.getPosition()) + (toRun ? 180 : 0);
            System.out.println("position: " + _bacteria.getPosition().getLocation() + ", " + " + angle" + angle);
            bacteria.setDirection(angle);
        }
        
        // определить коллизию
        detectCollision();
        
        // Находится ли ИИБактерия на крае игрового поля
        detectBorder();
        
        return true;
    }
    
    public Bacteria chooseTarget() {
        Bacteria bigOne = dish.getBigNearestBacteria(bacteria);
        Bacteria smallOne = dish.getSmallNearestBacteria(bacteria);
        Bacteria _bacteria = null;
        //Если на поле поблизости есть бактерии больше меня
        if (bigOne != null) {
           // Если бактерий меньше меня нет
            if(smallOne == null) {
                _bacteria = bigOne;
            }
            // Если на поле есть бактерии больше и меньше меня
            else {
                double distanceToBig = GameMath.distance(bacteria.getPosition(), bigOne.getPosition());
                double distanceToSmall = GameMath.distance(bacteria.getPosition(), smallOne.getPosition());
                // Если агара нет или есть но расстояние до маленькой частица больше чем до большой
                _bacteria = distanceToBig < distanceToSmall ? bigOne : smallOne;
            }
        } else {
            _bacteria = dish.getAgarNearestBacteria(bacteria);
        }
        return _bacteria;
    }
    
    public void detectCollision() {
        
    }
    
    private void detectBorder() {
        
    }
}