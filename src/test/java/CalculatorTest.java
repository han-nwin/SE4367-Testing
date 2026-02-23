import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-1, 1));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calc.subtract(3, 2));
        assertEquals(-2, calc.subtract(-1, 1));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, calc.multiply(2, 3));
        assertEquals(0, calc.multiply(5, 0));
    }

    @Test
    public void testDivide() {
        assertEquals(2.5, calc.divide(5, 2), 0.001);
        assertEquals(3.0, calc.divide(9, 3), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        calc.divide(1, 0);
    }
}
