package gameobject;

import view.PlayerBacteriaView;

/**
 * Бактерия игрока
 */
public class PlayerBacteria extends Bacteria {

    /**
     * Количество съеденных ИИБактерий
     */
    private int eatenAICount;

    /**
     * Конструктор класса с параметром
     *
     * @param playerBacteriaView представление Бактерии игрока
     */
    public PlayerBacteria(PlayerBacteriaView playerBacteriaView) {
        gameObjectView = playerBacteriaView;
    }

    /**
     * Возвращает количество съеденных ИИБактерий
     *
     * @return количество съеденных ИИБактерий
     */
    public int getEatenAiCount() {
        return eatenAICount;
    }

    /**
     * Увеличивает количество съеденных ИИБактерий
     */
    public void increaseEatenAICount() {
        eatenAICount++;
    }


}
