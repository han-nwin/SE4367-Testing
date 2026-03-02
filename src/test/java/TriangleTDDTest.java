import static org.junit.Assert.*;
import org.junit.Test;

public class TriangleTDDTest {

    // Step 1: Equilateral
    @Test
    public void step1_equilateral_allSidesEqual() {
        assertEquals("Equilateral", Triangle.classify(5, 5, 5));
    }

    @Test
    public void step1_equilateral_differentEqualValues() {
        assertEquals("Equilateral", Triangle.classify(1, 1, 1));
        assertEquals("Equilateral", Triangle.classify(100, 100, 100));
    }

    // Step 2: Isosceles
    @Test
    public void step2_isosceles_firstTwoEqual() {
        assertEquals("Isosceles", Triangle.classify(5, 5, 3));
    }

    @Test
    public void step2_isosceles_lastTwoEqual() {
        assertEquals("Isosceles", Triangle.classify(3, 5, 5));
    }

    @Test
    public void step2_isosceles_firstAndLastEqual() {
        assertEquals("Isosceles", Triangle.classify(5, 3, 5));
    }

    // Step 3: Scalene
    @Test
    public void step3_scalene_allSidesDifferent() {
        assertEquals("Scalene", Triangle.classify(3, 4, 5));
    }

    @Test
    public void step3_scalene_anotherValid() {
        assertEquals("Scalene", Triangle.classify(7, 10, 5));
    }

    // Step 4: Invalid - zero or negative sides
    @Test
    public void step4_invalid_zeroSide() {
        assertEquals("Invalid", Triangle.classify(0, 4, 5));
        assertEquals("Invalid", Triangle.classify(4, 0, 5));
        assertEquals("Invalid", Triangle.classify(4, 5, 0));
    }

    @Test
    public void step4_invalid_negativeSide() {
        assertEquals("Invalid", Triangle.classify(-1, 4, 5));
        assertEquals("Invalid", Triangle.classify(4, -2, 5));
        assertEquals("Invalid", Triangle.classify(4, 5, -3));
    }

    @Test
    public void step4_invalid_allNegative() {
        assertEquals("Invalid", Triangle.classify(-1, -1, -1));
    }

    // Step 5: Invalid - triangle inequality violation
    @Test
    public void step5_invalid_sumOfTwoEqualToThird() {
        assertEquals("Invalid", Triangle.classify(2, 3, 5));
        assertEquals("Invalid", Triangle.classify(5, 2, 3));
        assertEquals("Invalid", Triangle.classify(3, 5, 2));
    }

    @Test
    public void step5_invalid_sumOfTwoLessThanThird() {
        assertEquals("Invalid", Triangle.classify(1, 2, 4));
        assertEquals("Invalid", Triangle.classify(4, 1, 2));
        assertEquals("Invalid", Triangle.classify(2, 4, 1));
    }

    @Test
    public void step5_invalid_oneSideTooLong() {
        assertEquals("Invalid", Triangle.classify(1, 1, 10));
    }
}
