package listeners;


/**
 * Слушатель сигнала спавна объектов игры
 */
public interface SpawnGameObjectListener {

    /**
     * Сигнал спавна Агара на игровом поле
     *
     * Отправляет:
     * - GameModel
     *
     * Слушает:
     * - GameView
     */
    void spawnAgar();

    /**
     * Сигнал спавна ИИБактерии на игровом поле
     *
     * Отправляет:
     * - GameModel
     *
     * Слушает:
     * - GameView
     */
    void spawnAI();
}
