package Tests;

import HonestPlayer.HonestPlayer;
import Shuler.Shuler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersTests {
    @Test
    void checkingTheGameLaunch() {
        // Создаем честного игрока.
        HonestPlayer honestPlayer = new HonestPlayer(0);
        // Проверяем, что его баланс равен 0.
        assertEquals(honestPlayer.getTotal(), 0);
        // Разрешаем шулеру украсть 0 очков.
        honestPlayer.shulerCheating(0);
        // Проверяем, что количество очкой у честного игрока равно 0.
        assertEquals(honestPlayer.getTotal(), 0);
        // Создаем шулера.
        Shuler shuler = new Shuler(0);
        // Проверяем, что баланс шулера равен 0.
        assertEquals(shuler.getTotal(), 0);
    }

}
