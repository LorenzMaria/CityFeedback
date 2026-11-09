package beschwerdemanagement.domain.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
