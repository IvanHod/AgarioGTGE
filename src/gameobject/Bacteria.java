package gameobject;


import java.awt.*;

import utils.GameMath;

/**
 * Бактерия
 */
public abstract class Bacteria extends GameObject {

    /**
     * Направление Бактерии (0 - 360)
     */
    protected int angle;

    /**
     * Скорость Бактерии
     */
    protected double speed;

    /**
     * Уровень Бактерии
     */
    protected int level = 1;

    /**
     * Количество съеденного Бактерией Агара
     */
    protected int agarEaten;
    
    /**
     * Бактерия вступившая в коллизию с текущей
     */
    public Bacteria collisionBacteria = null;

    public int stepCount = 0;
    
    public void setSteps(int _stepCount){
        stepCount=_stepCount;
    }
    public int getSteps(){
        return stepCount;
    }
    
    /**
     * Возвращает позицию Бактерии на игровом поле
     *
     * @return позиция Бактерии на игровом поле
     */
    public Point getPosition() {
        Point position = new Point();
        position.x = (int) (this.sprite().getX() + this.sprite().getWidth() / 2);
        position.y = (int) (this.sprite().getY() + this.sprite().getHeight() / 2);
        return position;
    }

    /**
     * Устанавливает позицию Бактерии на игровом поле
     *
     * @param position позиция Бактерии на игровом поле
     */
    public void setPosition(Point position) {
        this.sprite().setX(position.getX() - 1 / 2);
        this.sprite().setY(position.getY() - 1 / 2);
    }

    /**
     * Возвращает скорость Бактерии
     *
     * @return скорость Бактерии
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Устанавливает скорость Бактерии
     *
     * @param speed скорость Бактерии
     */
    public void setSpeed(double speed) {
        this.speed = speed;
        this.sprite().setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        this.sprite().setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    /**
     * Возвращает направление Бактерии
     *
     * @return направление Бактерии
     */
    public int getDirection() {
        return angle;
    }

    /**
     * Устанавливает направление Бактерии
     *
     * @param angle направление Бактерии
     */
    public void setDirection(int angle) {
        this.angle = angle;
        this.sprite().setHorizontalSpeed(speed * Math.cos(GameMath.degreesToRadians(angle)));
        this.sprite().setVerticalSpeed(speed * Math.sin(GameMath.degreesToRadians(angle)));
    }

    /**
     * Возвращает уровень Бактерии
     *
     * @return уровень Бактерии
     */
    public int level() {
        return level;
    }

    /**
     * Возвращает количество съеденного Бактерией Агара
     *
     * @return количество съеденного Бактерией Агара
     */
    public int agarEatenCount() {
        return agarEaten;
    }

    /**
     * Увеличивает количество съеденного Бактерией Агара
     */
    public void increaseEatenAgarAmount() {
        agarEaten++;
    }

    /**
     * Увеличивает уровень Бактерии
     */
    public void levelUp() {
        level++;
    }

}