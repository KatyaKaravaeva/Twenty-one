package Shuler;

import Deck.Deck;
import HonestPlayer.HonestPlayer;
import StreamingForSynchronization.StreamingForSynchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс, который представляет игроков - шулеров.
 * Их особенность заключается в том, что они могут жульничать.
 */
public class Shuler implements Runnable {
    // Статический список всех шулеров, которые есть в игре.
    static public List<Shuler> shulerArr = new ArrayList<Shuler>();
    // Объект класса random для случайной генерации.
    Random random;
    // Общий баланс шулера, изначально он равен 0.
    int totalBalance;
    // Имя шулера.
    public int name;

    public Shuler(int name) {
        totalBalance = 0;
        this.name = name;
        random = new Random();
    }

    /**
     * Метод для доступа к полю totalBalance.
     * @return общий баланс игрока
     */
    public int getTotal() {
        return totalBalance;
    }

    /**
     * Метод, который описывает игру шулера.
     */
    @Override
    public void run() {
        // Смотрим, что мы запустили счетчик, если нет, то запускаем.
        if (StreamingForSynchronization.wasStarted) {
            StreamingForSynchronization.start();
        }
        // Пока не прошло 10 секунд, игра продолжается.
        while (StreamingForSynchronization.check()) {
            // Флаг для того, чтобы понять время сна шулера.
            int flag;
            synchronized (Deck.objForSynchronization) {
                // Если вероятность 40 процентов, то шулер начинает жульничать.
                if (Math.random() < 0.4) {
                    // Смотрим общее количество честных игроков.
                    int max = HonestPlayer.honestArr.size();
                    // Выбираем случайного игрока.
                    int randomIndex = random.nextInt(max);
                    // Выбираем сколько очков шулер украдет.
                    int randomSumToSteal = random.nextInt(9);
                    // Смотрим сколько вообще очков у честного игрока (у которого мы хотим украсть).
                    int totalHonestSum = HonestPlayer.honestArr.get(randomIndex).getTotal();
                    //Если у игрока можно украсть выбранное число очков, то делаем это.
                    if (totalHonestSum - randomSumToSteal > 0) {
                        HonestPlayer.honestArr.get(randomIndex).shulerCheating(randomSumToSteal);
                        // Не забываем увеличить баланс шулера.
                        this.totalBalance += randomSumToSteal;
                    } else {
                        // Если у игрока нелостаточно очков, которые хотел украсть шулер, то воруем все что у него есть.
                        HonestPlayer.honestArr.get(randomIndex).shulerCheating(totalHonestSum);
                        // Не забываем увеличить баланс шулера.
                        this.totalBalance += totalHonestSum;
                    }
                    // Если шулер ворует, то он спит от 180 до 300 мс.
                    flag = random.nextInt(300 - 180 + 1) + 180;
                } else {
                    // Иначе играем честно, берем карточку.
                    totalBalance += Deck.getCard();
                    // Спим от 100 до 200 мс.
                    flag = random.nextInt(200 - 100 + 1) + 100;

                }
            }
            // Шулер уходит на отдых.
            try {
                Thread.sleep(flag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}