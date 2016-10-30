package listeners;

/**
 * Слушатель сигнала окончания игры
 */
public interface GameOverListener {

    /**
     * Сигнал окончания игры
     *
     * Отправляет:
     * - GameModel
     *
     * Слушает:
     * - GameView
     */
    void gameOver();
}
