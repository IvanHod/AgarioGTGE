package gameobject;


import view.AIBacteriaView;

/**
 * ИИБактерия
 */
public class AIBacteria extends Bacteria {
    
    /**
     * Количество съеденных ИИБактерий
     */
    private int eatenAICount;
    
    /**
     * Конструктор класса с параметром
     *
     * @param aiBacteriaView представление ИИБактерии
     */
    public AIBacteria(AIBacteriaView aiBacteriaView, int _level) {
        gameObjectView = aiBacteriaView;
        level = _level;
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
