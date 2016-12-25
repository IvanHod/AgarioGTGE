package listeners;


import com.golden.gamedev.object.Sprite;

/**
 * Слушатель сигнала "поедания" бактерии
 */
public interface GameObjectEatenListener {

    /**
     * Сигнал о том, что Агар был съеден бактерией
     *
     * Отправляет:
     * - PlayerAgarCollision
     *
     * Слушает:
     * - GameModel
     * - GameView
     *
     * @param bacteriaSprite спрайт Бактерии игрока
     * @param agarSprite     спрайт Агара
     */
    void agarEaten(Sprite bacteriaSprite, Sprite agarSprite);

    /**
     * Сигнал о том, что одна бактерия съела другую
     *
     * Отправляет:
     * - PlayerAICollision
     *
     * Слушает:
     * - GameModel
     * - GameView
     *
     * @param playerBacteria спрайт Бактерии игрока
     * @param aiBacteria     спрайт ИИБактерии
     */
    void bacteriaEaten(Sprite playerBacteria, Sprite aiBacteria);
    
    /**
     * Сигнал о том, что одна бактерия съела другую
     *
     * Отправляет:
     * - PlayerAICollision
     *
     * Слушает:
     * - GameModel
     * - GameView
     *
     * @param playerBacteria спрайт Бактерии игрока
     * @param aiBacteria     спрайт ИИБактерии
     */
    void aiBacteriaEaten(Sprite playerBacteria, Sprite aiBacteria);
}
