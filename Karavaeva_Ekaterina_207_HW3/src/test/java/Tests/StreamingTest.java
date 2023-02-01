package Tests;
import StreamingForSynchronization.StreamingForSynchronization;
import org.junit.jupiter.api.Test;

import static StreamingForSynchronization.StreamingForSynchronization.check;
import static org.junit.jupiter.api.Assertions.*;

public class StreamingTest {
    @Test
    void checkingTheGameLaunch() {
        boolean started;
        StreamingForSynchronization.start();
        started = check();
        assertTrue(started);
    }

}

