import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Exercise 5: Triangle TDD Test
 *
 * Test-Driven Development (TDD) approach for the Triangle classifier.
 * Each step below follows the Red-Green-Refactor cycle.
 */
public class TriangleTDDTest {

    // =========================================================================
    // STEP 1 (RED): Write a test for equilateral triangles.
    //   We expect that three equal positive sides produce "Equilateral".
    //   Before any implementation exists, this test would FAIL (RED).
    // STEP 1 (GREEN): Implement the simplest code to return "Equilateral"
    //   when s1 == s2 == s3. Test now PASSES (GREEN).
    // STEP 1 (REFACTOR): Only one if-statement exists. Code is trivially
    //   simple — no refactoring needed.
    // =========================================================================
    @Test
    public void step1_equilateral_allSidesEqual() {
        assertEquals("Equilateral", Triangle.classify(5, 5, 5));
    }

    @Test
    public void step1_equilateral_differentEqualValues() {
        assertEquals("Equilateral", Triangle.classify(1, 1, 1));
        assertEquals("Equilateral", Triangle.classify(100, 100, 100));
    }

    // =========================================================================
    // STEP 2 (RED): Write a test for isosceles triangles.
    //   We expect exactly two equal sides to produce "Isosceles".
    //   The current code only handles equilateral, so this FAILS (RED).
    // STEP 2 (GREEN): Add an else-if branch checking s1==s2 || s2==s3 || s1==s3.
    //   Test now PASSES (GREEN).
    // STEP 2 (REFACTOR): Two branches now (equilateral + isosceles). Logic is
    //   straightforward with no duplication — no refactoring needed.
    // =========================================================================
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

    // =========================================================================
    // STEP 3 (RED): Write a test for scalene triangles.
    //   Three different sides that form a valid triangle should return "Scalene".
    //   No branch handles this yet, so this FAILS (RED).
    // STEP 3 (GREEN): Add an else branch that returns "Scalene".
    //   Test now PASSES (GREEN).
    // STEP 3 (REFACTOR): Classification logic is complete (equilateral, isosceles,
    //   scalene). The if/else-if/else chain is clear — no refactoring needed.
    // =========================================================================
    @Test
    public void step3_scalene_allSidesDifferent() {
        assertEquals("Scalene", Triangle.classify(3, 4, 5));
    }

    @Test
    public void step3_scalene_anotherValid() {
        assertEquals("Scalene", Triangle.classify(7, 10, 5));
    }

    // =========================================================================
    // STEP 4 (RED): Write tests for invalid input - zero or negative sides.
    //   Sides <= 0 should return "Invalid".
    //   Current code has no validation, so this FAILS (RED).
    // STEP 4 (GREEN): Add a guard clause at the top: if any side <= 0, return "Invalid".
    //   Test now PASSES (GREEN).
    // STEP 4 (REFACTOR): Guard clause is placed before classification logic,
    //   following early-return pattern. Clean structure — no refactoring needed.
    // =========================================================================
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

    // =========================================================================
    // STEP 5 (RED): Write tests for the triangle inequality violation.
    //   If the sum of any two sides <= the third side, it cannot form a triangle.
    //   Current code doesn't check this, so this FAILS (RED).
    // STEP 5 (GREEN): Add a second guard clause checking triangle inequality.
    //   Test now PASSES (GREEN).
    // STEP 5 (REFACTOR): The classify method is now complete. Review code for
    //   clarity — the guard clauses are at the top, classification logic below.
    //   Structure is clean; no refactoring needed.
    // =========================================================================
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
