import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;


public class MainTest {
    private final InputStream originalIn = System.in;

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Before
    public void setUp() {
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(originalIn);
    }

    @Test(expected = NoSuchElementException.class)
    public void testUserInputSequence_() {
        provideInput("1\n999\n");
    }

}
