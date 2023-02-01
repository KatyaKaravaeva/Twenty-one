package HonestPlayer;

import Deck.Deck;
import StreamingForSynchronization.StreamingForSynchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс, который представляет честного игрока.
 * Он не может жульничать, поэтому всегда вытягивает карту из колоды.
 */
public class HonestPlayer implements Runnable {
    // Общее количество очков у игрока.
    int totalBalance;
    // Статический список всех честных игроков.
    static public List<HonestPlayer> honestArr = new ArrayList<HonestPlayer>();
    // Объект Random для случайной генерации.
    Random random;
    // Имя игрока.
    public int name;

    public HonestPlayer(int name) {
        totalBalance = 0;
        this.name = name;
        random = new Random();
    }

    /**
     * Метод для просмотра общего количества очков.
     * @return общее количество очков у игрока
     */
    public int getTotal() {
        return totalBalance;
    }

    /**
     * Метод, который позволяет шулеру красть очки у честного игрока.
     * @param value количество очков, которые крадет шулер
     */
    public void shulerCheating(int value) {
        totalBalance -= value;
    }

    /**
     * Метод, который описывает логику игры честного игрока.
     */
    @Override
    public void run() {
        // Если игра еще не началась, то начинаем ее.
        if (StreamingForSynchronization.wasStarted) {
            // Запускаем время.
            StreamingForSynchronization.start();
        }
        // Пока игра идет - играем.
        while (StreamingForSynchronization.check()) {
            synchronized (Deck.objForSynchronization) {
                // Честный игрок берет карточку из колоды.
                totalBalance += Deck.getCard();
            }
            try {
                // Честный игрок отдыхает.
                int millis = random.nextInt(200 - 100 + 1) + 100;
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
