package controller;


import gamemodel.Dish;
import java.awt.*;

import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.Bacteria;
import gameobject.PlayerBacteria;
import utils.GameMath;

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
    
    int angle = 0;
    
    boolean toRun = false;
    
    /**
     * Конструктор класса с параметрами
     *
     * @param _dish чаша Петри
     * @param _bacteria чаша Петри
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
        toRun = false;
        if(bacteria.stepCount != 0){
            bacteria.stepCount--;
            detectBorder();
        }
        else{
            // выбрать цель
            Point pos = chooseTarget();
            if(pos != null) {
                angle = GameMath.angle(bacteria.getPosition(), pos) + (toRun ? 180 : 0);
                bacteria.setDirection(angle);
            }

            // определить коллизию
            detectCollision();

            // Находится ли ИИБактерия на крае игрового поля
            detectBorder();

            
            return true;
    
        }
        return false;
    }
    
    public Point chooseTarget() {
        Bacteria bigOne = dish.getBigNearestBacteria(bacteria);
        Bacteria smallOne = dish.getSmallNearestBacteria(bacteria);
        Point pos = null;
        //Если на поле поблизости есть бактерии больше меня
        if (bigOne != null) {
           // Если бактерий меньше меня нет
            if(smallOne == null) {
                pos = bigOne.getPosition();
                toRun = true;
            }
            // Если на поле есть бактерии больше и меньше меня
            else {
                double distanceToBig = GameMath.distance(bacteria.getPosition(), bigOne.getPosition());
                double distanceToSmall = GameMath.distance(bacteria.getPosition(), smallOne.getPosition());
                // Если агара нет или есть но расстояние до маленькой частица больше чем до большой
                pos = distanceToBig < distanceToSmall ? bigOne.getPosition() : smallOne.getPosition();
                toRun = distanceToBig < distanceToSmall;
            }
        } else if(smallOne != null) {
            pos = smallOne.getPosition();
        } else {
            Agar agar = dish.getAgarNearestBacteria(bacteria);
            
            if(agar != null)
                pos = agar.getPosition();
        }
        bacteria.stepCount = (int)(10 + Math.random()*(50));
        return pos;
    }
    
    public void detectCollision() {
        
    }
    
    private void detectBorder() {
        Point pos = bacteria.getPosition();
        double x = pos.getX(),
                y = pos.getY();
        if(x > 2900 || x < 50 || y > 2900 || y < 50){
            
            int newDir = angle - (int)(180 + Math.random()*(10));
            if(newDir<0){
                newDir=360+newDir;
            }
            bacteria.setDirection(newDir);
            /*if(bacteria.stepCount!=0){
                bacteria.setSteps((int)(10 + Math.random()*(50)));
            }*/
        }
    }
}