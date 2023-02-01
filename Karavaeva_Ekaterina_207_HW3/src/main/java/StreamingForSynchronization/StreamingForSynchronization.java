package StreamingForSynchronization;

/**
 * Класс для того, чтобы Крупье знал, когда заканчивать игру.
 */
public class StreamingForSynchronization {
    static long currentTime;
    static long endTime;
    public static boolean wasStarted = true;

    /**
     * Метод, который позволяет Крупье запустить игру.
     */
    public static void start() {
        currentTime = System.currentTimeMillis();
        endTime = currentTime + 10000;
        wasStarted = false;
    }

    /**
     * Метод для проверки того, что игра идет 10 секунд.
     * @return true - игра идет, false - нет
     */
    public static boolean check() {
        return System.currentTimeMillis() < endTime;
    }
}
