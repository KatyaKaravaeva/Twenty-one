package Tests;

import Deck.Deck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    void isIntegetNumberFrom1To10(){
        boolean flag;
        int card = Deck.getCard();
        if (card >= 1 && card <= 10){
            flag = true;
        }
        else {
            flag = false;
        }
        assertEquals(flag, true);
    }
}
