import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    // Test 1: All sides positive and equal - Equilateral
    @Test
    public void testEquilateral() {
        assertEquals("Equilateral", Triangle.classify(5, 5, 5));
    }

    // Test 2: Two sides equal - Isosceles
    @Test
    public void testIsosceles() {
        assertEquals("Isosceles", Triangle.classify(5, 5, 3));
        assertEquals("Isosceles", Triangle.classify(5, 3, 5));
        assertEquals("Isosceles", Triangle.classify(3, 5, 5));
    }

    // Test 3: All sides different - Scalene
    @Test
    public void testScalene() {
        assertEquals("Scalene", Triangle.classify(3, 4, 5));
    }

    // Test 4: Zero or negative sides - Invalid
    @Test
    public void testZeroOrNegativeSides() {
        assertEquals("Invalid", Triangle.classify(0, 4, 5));
        assertEquals("Invalid", Triangle.classify(-1, 4, 5));
        assertEquals("Invalid", Triangle.classify(3, 0, 5));
        assertEquals("Invalid", Triangle.classify(3, -2, 5));
        assertEquals("Invalid", Triangle.classify(3, 4, 0));
        assertEquals("Invalid", Triangle.classify(3, 4, -1));
    }

    // Test 5: Sum of two sides equals third - Invalid
    @Test
    public void testSumEqualToThird() {
        assertEquals("Invalid", Triangle.classify(2, 3, 5));
        assertEquals("Invalid", Triangle.classify(5, 2, 3));
        assertEquals("Invalid", Triangle.classify(3, 5, 2));
    }

    // Test 6: Sum of two sides less than third - Invalid
    @Test
    public void testSumLessThanThird() {
        assertEquals("Invalid", Triangle.classify(1, 2, 4));
        assertEquals("Invalid", Triangle.classify(4, 1, 2));
        assertEquals("Invalid", Triangle.classify(2, 4, 1));
    }
}
