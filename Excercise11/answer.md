# Exercise 11 – Logic Coverage on `twoPred()`

## Predicates

There are two predicates in `twoPred()`:

| # | Line | Predicate                | Clauses                       |
|---|------|--------------------------|-------------------------------|
| p1 | 2   | `x < y`                  | `a = (x < y)`                  |
| p2 | 6   | `z && x+y == 10`         | `a = z`, `b = (x+y == 10)`     |

---

## Truth Tables

### Predicate p1 = `a` (line 2)

| Row | a (x < y) | p1 |
|-----|-----------|-----|
| 1   | T         | T   |
| 2   | F         | F   |

### Predicate p2 = `a ∧ b` (line 6)

| Row | a (z) | b (x+y==10) | p2 |
|-----|-------|-------------|-----|
| 1   | T     | T           | T   |
| 2   | T     | F           | F   |
| 3   | F     | T           | F   |
| 4   | F     | F           | F   |

---

## Definitional Method (when each clause determines the predicate)

**p1 = a**
- p_a = p1(a=T) ⊕ p1(a=F) = T ⊕ F = **true** (a always determines p1).

**p2 = a ∧ b**
- p_a = p2(a=T) ⊕ p2(a=F) = (T∧b) ⊕ (F∧b) = b ⊕ F = **b**
  → clause `a` determines p2 when `b = T`.
- p_b = p2(b=T) ⊕ p2(b=F) = (a∧T) ⊕ (a∧F) = a ⊕ F = **a**
  → clause `b` determines p2 when `a = T`.

---

## (A) Row pairs satisfying Predicate Coverage (PC)

PC requires each predicate to evaluate both T and F.

- **p1:** (Row 1, Row 2)
- **p2:** (Row 1, Row 2), (Row 1, Row 3), (Row 1, Row 4)

## (B) Values that satisfy PC

Choose tests so each predicate hits T and F.

| Test | x | y | p1 (x<y) | z | p2 (z ∧ x+y==10) | return |
|------|---|---|----------|---|-------------------|--------|
| T1   | 1 | 9 | T        | T | T  (1+9=10)       | "A"    |
| T2   | 5 | 3 | F        | F | F                 | "B"    |

p1: T (T1), F (T2). p2: T (T1), F (T2). ✔ PC satisfied for both.

---

## (C) Row pairs satisfying Clause Coverage (CC)

CC requires every clause to take both T and F.

**p1** (single clause `a`):
- Pair: (Row 1, Row 2)

**p2** – clause `a (z)` (a=T in rows 1,2; a=F in rows 3,4):
- (1,3), (1,4), (2,3), (2,4)

**p2** – clause `b (x+y==10)` (b=T in rows 1,3; b=F in rows 2,4):
- (1,2), (1,4), (3,2), (3,4)

## (D) Values that satisfy CC

Each clause needs both T and F.

| Test | x | y | x<y (a of p1) | z (a of p2) | x+y==10 (b of p2) |
|------|---|---|---------------|-------------|--------------------|
| T1   | 1 | 9 | T             | T           | T                  |
| T2   | 5 | 3 | F             | F           | F                  |

- p1.a: T,F ✔
- p2.a: T,F ✔
- p2.b: T,F ✔

---

## (E) Row pairs satisfying CACC

For each major clause, the minor clauses must make it **active** (determine the predicate) and the major clause flips between the two rows. Under CACC, the predicate is allowed/expected to change too (here it must, since `a ∧ b` has only one cofactor).

**p1** (only one clause; CACC = PC = CC):
- (Row 1, Row 2)

**p2** – clause `a` is active when `b = T` (rows 1 and 3):
- (Row 1, Row 3)   [a flips T→F, p2 flips T→F]

**p2** – clause `b` is active when `a = T` (rows 1 and 2):
- (Row 1, Row 2)   [b flips T→F, p2 flips T→F]

## (F) Values that satisfy CACC

We need:
- p1: a row with `x<y` true and one with `x<y` false.
- p2 clause `a` active (b=T, i.e. `x+y==10`): one with z=T, one with z=F.
- p2 clause `b` active (a=T, i.e. `z=T`, i.e. `x<y`): one with `x+y==10`, one with `x+y≠10`.

| Test | x | y | p1 row | p2 row | Notes (active clause)         | return |
|------|---|---|--------|--------|-------------------------------|--------|
| T1   | 1 | 9 | 1 (T)  | 1 (TT) | covers a-active & b-active T  | "A"    |
| T2   | 5 | 5 | 2 (F)  | 3 (FT) | a-active, a flips → CACC for a| "B"    |
| T3   | 1 | 2 | 1 (T)  | 2 (TF) | b-active, b flips → CACC for b| "B"    |

Verification:
- **p1 CACC:** T1 gives a=T, T2 gives a=F. ✔
- **p2 CACC for a:** T1 (a=T,b=T,p2=T) and T2 (a=F,b=T,p2=F); b=T in both, a flips, p2 flips. ✔
- **p2 CACC for b:** T1 (a=T,b=T,p2=T) and T3 (a=T,b=F,p2=F); a=T in both, b flips, p2 flips. ✔
