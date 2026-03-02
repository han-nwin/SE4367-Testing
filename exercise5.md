## Exercise 5: Triangle TDD

### Overview

Using Test-Driven Development (TDD), I wrote JUnit tests for the `Triangle.classify(s1, s2, s3)` method, which classifies a triangle as Equilateral, Isosceles, Scalene, or Invalid given three side lengths. Each step follows the **Red-Green-Refactor** cycle:

- **Red**: Write a failing test for new behavior
- **Green**: Write the minimum code to pass the test
- **Refactor**: Clean up the code while keeping all tests passing

---

### Step 1: Equilateral Triangle

**Red**: Wrote tests asserting that three equal positive sides (e.g., 5,5,5) return `"Equilateral"`. With no implementation, the tests fail.

**Green**: Implemented `classify()` with a single check: `if (s1 == s2 && s2 == s3) return "Equilateral"`. Tests pass.

**Refactor**: Only one if-statement exists. Code is trivially simple — no refactoring needed.

**Tests written**:
- `(5, 5, 5)` → Equilateral
- `(1, 1, 1)` → Equilateral
- `(100, 100, 100)` → Equilateral

---

### Step 2: Isosceles Triangle

**Red**: Wrote tests asserting that exactly two equal sides (e.g., 5,5,3) return `"Isosceles"`. The current code only handles equilateral, so these tests fail.

**Green**: Added an `else if` branch: `s1 == s2 || s2 == s3 || s1 == s3`. Tests pass.

**Refactor**: Two branches now. Logic is straightforward with no duplication — no refactoring needed.

**Tests written**:
- `(5, 5, 3)` → Isosceles (first two equal)
- `(3, 5, 5)` → Isosceles (last two equal)
- `(5, 3, 5)` → Isosceles (first and last equal)

---

### Step 3: Scalene Triangle

**Red**: Wrote tests asserting that three different valid sides (e.g., 3,4,5) return `"Scalene"`. No branch handles this case yet, so these tests fail.

**Green**: Added a final `else` branch returning `"Scalene"`. Tests pass.

**Refactor**: The if/else-if/else classification chain is clear and complete — no refactoring needed.

**Tests written**:
- `(3, 4, 5)` → Scalene
- `(7, 10, 5)` → Scalene

---

### Step 4: Invalid — Zero or Negative Sides

**Red**: Wrote tests asserting that zero or negative side lengths return `"Invalid"`. The current code has no input validation, so these tests fail.

**Green**: Added a guard clause at the top of `classify()`: `if (s1 <= 0 || s2 <= 0 || s3 <= 0) return "Invalid"`. Tests pass.

**Refactor**: Guard clause follows the early-return pattern and sits cleanly before the classification logic — no refactoring needed.

**Tests written**:
- `(0, 4, 5)`, `(4, 0, 5)`, `(4, 5, 0)` → Invalid (zero side)
- `(-1, 4, 5)`, `(4, -2, 5)`, `(4, 5, -3)` → Invalid (negative side)
- `(-1, -1, -1)` → Invalid (all negative)

---

### Step 5: Invalid — Triangle Inequality Violation

**Red**: Wrote tests asserting that when the sum of any two sides is less than or equal to the third, the result is `"Invalid"`. The current code does not check the triangle inequality, so these tests fail.

**Green**: Added a second guard clause: `if (s1 + s2 <= s3 || s2 + s3 <= s1 || s3 + s1 <= s2) return "Invalid"`. Tests pass.

**Refactor**: The method is now complete. Guard clauses handle validation at the top, classification logic follows below. Structure is clean — no refactoring needed.

**Tests written**:
- `(2, 3, 5)`, `(5, 2, 3)`, `(3, 5, 2)` → Invalid (sum equals third)
- `(1, 2, 4)`, `(4, 1, 2)`, `(2, 4, 1)` → Invalid (sum less than third)
- `(1, 1, 10)` → Invalid (one side far too long)

---

### Final Implementation

After all 5 TDD steps, the resulting `Triangle.classify()` method:

```java
public static String classify(int s1, int s2, int s3) {
    if (s1 <= 0 || s2 <= 0 || s3 <= 0)
        return "Invalid";
    if (s1 + s2 <= s3 || s2 + s3 <= s1 || s3 + s1 <= s2)
        return "Invalid";
    if (s1 == s2 && s2 == s3)
        return "Equilateral";
    else if (s1 == s2 || s2 == s3 || s1 == s3)
        return "Isosceles";
    else
        return "Scalene";
}
```

All 14 test cases pass across the 5 TDD steps.
