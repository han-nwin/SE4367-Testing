## Exercise 5: Triangle TDD

### Overview

Using Test-Driven Development (TDD), I wrote JUnit tests for the `Triangle.classify(s1, s2, s3)` method, which classifies a triangle as Equilateral, Isosceles, Scalene, or Invalid given three side lengths.

Each step follows the **Red-Green-Refactor** cycle, and applies key TDD concepts from class: **Programming by Intention**, **Triangulation**, and keeping tests **Atomic** and **Isolated**.

---

### Programming by Intention

Before writing any implementation, I designed the API from the client's perspective. I imagined calling:

```java
Triangle.classify(3, 4, 5)  // returns "Scalene"
```

This let me write tests before the method existed, focusing on **what we want the system to look like** rather than how it works internally.

---

### Step 1: Equilateral Triangle

**Red**: Wrote a test asserting `classify(5, 5, 5)` returns `"Equilateral"`. No implementation exists, so the test fails.

**Green**: Implemented `classify()` with the simplest code — even a hardcoded `return "Equilateral"` would pass.

**Triangulation**: Added more inputs `(1,1,1)` and `(100,100,100)` to force a general solution: `if (s1 == s2 && s2 == s3) return "Equilateral"`. Hardcoding is no longer possible.

**Refactor**: Only one if-statement. Too simple to refactor.

**Tests**:
- `(5, 5, 5)` → Equilateral
- `(1, 1, 1)` → Equilateral
- `(100, 100, 100)` → Equilateral

---

### Step 2: Isosceles Triangle

**Red**: Wrote a test asserting `classify(5, 5, 3)` returns `"Isosceles"`. Current code only handles equilateral, so this fails.

**Green**: Added an `else if` branch: `s1 == s2 || s2 == s3 || s1 == s3`.

**Triangulation**: Tested all three pair combinations — `(5,5,3)`, `(3,5,5)`, `(5,3,5)` — to ensure the condition works regardless of which two sides are equal.

**Refactor**: Two branches, no duplication. No refactoring needed.

**Tests**:
- `(5, 5, 3)` → Isosceles (first two equal)
- `(3, 5, 5)` → Isosceles (last two equal)
- `(5, 3, 5)` → Isosceles (first and last equal)

---

### Step 3: Scalene Triangle

**Red**: Wrote a test asserting `classify(3, 4, 5)` returns `"Scalene"`. No branch handles all-different sides, so this fails.

**Green**: Added a final `else` returning `"Scalene"`.

**Triangulation**: Added a second scalene case `(7, 10, 5)` to confirm it generalizes beyond one example.

**Refactor**: The if/else-if/else classification chain is clear and complete. No refactoring needed.

**Tests**:
- `(3, 4, 5)` → Scalene
- `(7, 10, 5)` → Scalene

---

### Step 4: Invalid — Zero or Negative Sides

**Red**: Wrote tests asserting that zero or negative sides return `"Invalid"`. The current code has no input validation, so these fail.

**Green**: Added a guard clause at the top: `if (s1 <= 0 || s2 <= 0 || s3 <= 0) return "Invalid"`.

**Triangulation**: Tested zero in each position, negative in each position, and all-negative — ensuring the guard works for every parameter.

**Refactor**: Guard clause sits cleanly before classification logic using early-return pattern. No refactoring needed.

**Tests**:
- `(0, 4, 5)`, `(4, 0, 5)`, `(4, 5, 0)` → Invalid (zero)
- `(-1, 4, 5)`, `(4, -2, 5)`, `(4, 5, -3)` → Invalid (negative)
- `(-1, -1, -1)` → Invalid (all negative)

---

### Step 5: Invalid — Triangle Inequality Violation

**Red**: Wrote tests asserting that when the sum of two sides is less than or equal to the third, the result is `"Invalid"`. Current code doesn't check this, so the tests fail.

**Green**: Added a second guard clause: `if (s1+s2 <= s3 || s2+s3 <= s1 || s3+s1 <= s2) return "Invalid"`.

**Triangulation**: Tested sum-equals-third `(2,3,5)`, sum-less-than-third `(1,2,4)`, rotating the "long" side through all three positions each time.

**Refactor**: The method is now complete. Guard clauses handle validation at the top, classification logic follows below. Structure is clean — no refactoring needed.

**Tests**:
- `(2, 3, 5)`, `(5, 2, 3)`, `(3, 5, 2)` → Invalid (sum equals third)
- `(1, 2, 4)`, `(4, 1, 2)`, `(2, 4, 1)` → Invalid (sum less than third)
- `(1, 1, 10)` → Invalid (one side far too long)

---

### Test Quality

All tests follow TDD best practices:

- **Atomic**: Each test method tests one specific behavior
- **Isolated**: No shared state, no ordering dependency between tests
- **Observable behavior**: Tests verify what `classify()` returns, not how it works internally

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
