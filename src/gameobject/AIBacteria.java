package gameobject;


import view.AIBacteriaView;

/**
 * ИИБактерия
 */
public class AIBacteria extends Bacteria {
    
    /**
     * Конструктор класса с параметром
     *
     * @param aiBacteriaView представление ИИБактерии
     */
    public AIBacteria(AIBacteriaView aiBacteriaView) {
        gameObjectView = aiBacteriaView;
    }

}
