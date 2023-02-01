package Deck;

/**
 * Класс, который представляет собой карточную колоду.
 */
public class Deck {
    // Объект для синхронизации потоков.
    public static final Object objForSynchronization = new Object();

    /**
     * Игрок может брать карту из колоды с помощью данного метода.
     * @return карточка из колоды
     */
    public static int getCard() {
        int minCard = 1;
        int maxCard = 10;
        return minCard + (int) (Math.random() * maxCard);
    }
}
