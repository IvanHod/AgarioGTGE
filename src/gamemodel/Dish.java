package gamemodel;


import com.golden.gamedev.object.Sprite;

import java.util.ArrayList;

import gameobject.AIBacteria;
import gameobject.Agar;
import gameobject.Obstacle;
import gameobject.PlayerBacteria;

/**
 * Чашка Петри
 */
public class Dish {

    /**
     * Бактерия игрока
     */
    private PlayerBacteria playerBacteria;

    /**
     * Препятствия
     */
    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    /**
     * Агар
     */
    private ArrayList<Agar> agars = new ArrayList<>();

    /**
     * ИИБактерии
     */
    private ArrayList<AIBacteria> aiBacterias = new ArrayList<>();

    /**
     * Добавляет Препятствие в Чашку Петри
     *
     * @param obstacle Препятствие
     */
    public void addObstacle(Obstacle obstacle) {

        obstacles.add(obstacle);
    }

    /**
     * Возвращает Препятствия хранимые в Чашке Петри
     *
     * @return Препятствия
     */
    public ArrayList<Obstacle> obstacles() {

        return obstacles;
    }

    /**
     * Добавляет Агар в Чашку Петри
     *
     * @param agar Агар
     */
    public void addAgar(Agar agar) {

        agars.add(agar);
    }

    /**
     * Возвращает Агар хранимый в Чашке Петри
     *
     * @return Агар
     */
    public ArrayList<Agar> agar() {

        return agars;
    }

    /**
     * Добавляет Бактерию игрока в Чашку Петри
     */
    public void addPlayerBacteria(PlayerBacteria playerBacteria) {

        this.playerBacteria = playerBacteria;
    }

    /**
     * Возвращает хранимую в Чашке Петри Бактерию игрока
     *
     * @return Бактерия игрока
     */
    public PlayerBacteria playerBacteria() {

        return playerBacteria;
    }

    /**
     * Добавляет ИИБактерию в Чашку Петри
     *
     * @param aiBacteria ИИБактерия
     */
    public void addAIBacteria(AIBacteria aiBacteria) {
        aiBacterias.add(aiBacteria);
    }

    /**
     * Возвращает хранимые в Чашке Петри ИИБактерии
     *
     * @return ИИБактерии
     */
    public ArrayList<AIBacteria> aiBacterias() {
        return aiBacterias;
    }

    /**
     * Возвращает определенную ИИБактерию по её спрайту
     *
     * @param aiBacteriaSprite спрайт ИИБактерии
     * @return ИИБактерия
     */
    public AIBacteria aiBacteria(Sprite aiBacteriaSprite) {

        for (AIBacteria aiBacteria : aiBacterias) {
            if (aiBacteria.sprite() == aiBacteriaSprite) {
                return aiBacteria;
            }
        }
        return null;
    }

    /**
     * Удаляет определенный Агар из Чашки Петри
     *
     * @param agarSprite спрайт Агара
     */
    public void removeAgar(Sprite agarSprite) {
        agars.removeIf(agar -> agar.sprite() == agarSprite);
    }

    /**
     * Удаляет определенную ИИБактерию из Чашки Петри
     *
     * @param aiBacteriaSprite спрайт ИИБактерии
     */
    public void removeAIBacteria(Sprite aiBacteriaSprite) {
        aiBacterias.removeIf(aiBacteria -> aiBacteria.sprite() == aiBacteriaSprite);
    }


}
