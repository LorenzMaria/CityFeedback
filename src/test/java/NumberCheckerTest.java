import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class NumberCheckerTest {

    @Test
    public void testEvenNumber() {
        NumberChecker checker = new NumberChecker();

        assertTrue(checker.isEven(4));
    }

    @Test
    public void testOddNumber() {
        NumberChecker checker = new NumberChecker();

        assertFalse(checker.isEven(5));
    }
}
