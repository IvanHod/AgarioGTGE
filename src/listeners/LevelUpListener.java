package listeners;


import com.golden.gamedev.object.Sprite;

/**
 * Слушатель сигнала повышения уровня объектов игры
 */
public interface LevelUpListener {

    /**
     * Сигнал повышения уровня бактерии
     *
     * Отправляет:
     * - GameModel
     *
     * Слушает:
     * - GameView
     *
     * @param bacteriaSprite спрайт бактерии, которой требуется повысить уровень
     */
    void levelIncreased(Sprite bacteriaSprite);
}
