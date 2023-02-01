package Main;

import HonestPlayer.HonestPlayer;
import Shuler.Shuler;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Количество честных игроков.
        int honestPlayers;
        // Количество шулеров.
        int numberOfCheaters;
        System.out.print("Enter the number of honest players from 1 to 99:\n");
        System.out.print("Remember, the maximum number of participants in the game is 100\n");
        System.out.print("Without cheaters only 99\n");
        // Запрашиваем количество честных игроков у пользователя.
        honestPlayers = numberIntEntry(1, 99);
        int maxValueOfCheaters = 100 - honestPlayers;
        System.out.print("Enter the number of cheaters from 0 to " + maxValueOfCheaters + ":\n");
        // Запрашиваем количество шулеров игроков у пользователя.
        numberOfCheaters = numberIntEntry(0, maxValueOfCheaters);
        System.out.print("The game has begun!\n");
        // Создаем необходимое количество честных игроков.
        for (int i = 0; i < honestPlayers; i++) {
            HonestPlayer honestPlayer = new HonestPlayer(i);
            HonestPlayer.honestArr.add(honestPlayer);
            Thread honest = new Thread(honestPlayer);
            honest.start();
        }
        // Создаем необходимое количество шулеров.
        for (int i = honestPlayers; i < numberOfCheaters + honestPlayers; i++) {
            Shuler shuler = new Shuler(i);
            Shuler.shulerArr.add(shuler);
            Thread myThready = new Thread(shuler);
            myThready.start();
        }
        // Крупье засекает 10 секунд игры.
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showRes(honestPlayers,numberOfCheaters);

    }

    public static void showRes(int honestPlayers, int numberOfCheaters) {
        int winnerName = 0;
        int winnerSum = 0;

        System.out.print("Game results: \n");

        // Выводим количество очков у каждого игрока.
        for (int i = 0; i < honestPlayers; i++) {
            System.out.print("Honest player named = " + HonestPlayer.honestArr.get(i).name + " collected =  " +
                    HonestPlayer.honestArr.get(i).getTotal() + " points \n");
            if (HonestPlayer.honestArr.get(i).getTotal() > winnerSum) {
                winnerSum = HonestPlayer.honestArr.get(i).getTotal();
                winnerName = HonestPlayer.honestArr.get(i).name;
            }
        }
        for (int i = 0; i < numberOfCheaters; i++) {
            System.out.print("Shuler named = " + Shuler.shulerArr.get(i).name + " collected =  " +
                    Shuler.shulerArr.get(i).getTotal() + " points \n");
            if (Shuler.shulerArr.get(i).getTotal() > winnerSum) {
                winnerSum = Shuler.shulerArr.get(i).getTotal();
                winnerName = Shuler.shulerArr.get(i).name;
            }
        }
        System.out.print("The winner is player " + winnerName + ". He has " + winnerSum + " points");
    }


    /**
     * Конвертируем строку в число.
     *
     * @param number строка
     * @return true если строку можно сконвертироать в число, иначе - false
     */
    public static boolean tryParse(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Метод ввода числа.
     * Tакже проверяем, что число неотрицательно
     *
     * @return корректное число
     */
    public static int numberIntEntry(int min, int max) {
        System.out.print("Enter a number: ");
        String input = scanner.nextLine();
        while (!tryParse(input) || Integer.parseInt(input) < min || Integer.parseInt(input) > max) {
            System.out.print("You entered the number incorrectly :(  Try again... \n");
            System.out.print("Enter a number: ");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }
}
