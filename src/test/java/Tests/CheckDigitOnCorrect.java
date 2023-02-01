package Tests;
import Main.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckDigitOnCorrect {
    @Test
    void CheckDigitOnCorrect() {
        assertTrue(Main.tryParse("1"));
        assertFalse(Main.tryParse("2edf"));
    }

}
