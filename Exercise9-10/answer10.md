# Exercise 10 - Active Clause Coverage

## p1 = a ^ b

### Truth Table

| Row | a | b | p1 |
|-----|---|---|-----|
| 1   | T | T | T   |
| 2   | T | F | F   |
| 3   | F | T | F   |
| 4   | F | F | F   |

### Definitional Method

**Clause a** (minor: b):
- p_a=T = T ^ b = b
- p_a=F = F ^ b = F
- a determines p1 when b != F, i.e., **b = T**
- Determining rows: {1, 3}

**Clause b** (minor: a):
- p_b=T = a ^ T = a
- p_b=F = a ^ F = F
- b determines p1 when a != F, i.e., **a = T**
- Determining rows: {1, 2}

### (A) GACC

| Major clause | Pairs   |
|--------------|---------|
| a            | (1, 3)  |
| b            | (1, 2)  |

### (B) CACC

| Major clause | Pairs   |
|--------------|---------|
| a            | (1, 3)  |
| b            | (1, 2)  |

### (C) RACC

| Major clause | Pairs   |
|--------------|---------|
| a            | (1, 3)  |
| b            | (1, 2)  |

---

## p2 = a XOR b

### Truth Table

| Row | a | b | p2 |
|-----|---|---|-----|
| 1   | T | T | F   |
| 2   | T | F | T   |
| 3   | F | T | T   |
| 4   | F | F | F   |

### Definitional Method

**Clause a** (minor: b):
- p_a=T = T XOR b = !b
- p_a=F = F XOR b = b
- a determines p2 when !b != b, which is **always true**
- Determining rows: {1, 2, 3, 4}

**Clause b** (minor: a):
- p_b=T = a XOR T = !a
- p_b=F = a XOR F = a
- b determines p2 when !a != a, which is **always true**
- Determining rows: {1, 2, 3, 4}

### (A) GACC

| Major clause | Pairs                          |
|--------------|--------------------------------|
| a            | (1, 3), (1, 4), (2, 3), (2, 4) |
| b            | (1, 2), (1, 4), (2, 3), (3, 4) |

### (B) CACC

| Major clause | Pairs          |
|--------------|----------------|
| a            | (1, 3), (2, 4) |
| b            | (1, 2), (3, 4) |

### (C) RACC

| Major clause | Pairs          |
|--------------|----------------|
| a            | (1, 3), (2, 4) |
| b            | (1, 2), (3, 4) |

---

## p3 = a ^ (!b v c)

### Truth Table

| Row | a | b | c | !b | !b v c | p3 |
|-----|---|---|---|-----|--------|-----|
| 1   | T | T | T | F   | T      | T   |
| 2   | T | T | F | F   | F      | F   |
| 3   | T | F | T | T   | T      | T   |
| 4   | T | F | F | T   | T      | T   |
| 5   | F | T | T | F   | T      | F   |
| 6   | F | T | F | F   | F      | F   |
| 7   | F | F | T | T   | T      | F   |
| 8   | F | F | F | T   | T      | F   |

### Definitional Method

**Clause a** (minors: b, c):
- p_a=T = T ^ (!b v c) = !b v c
- p_a=F = F ^ (!b v c) = F
- a determines p3 when !b v c != F, i.e., **!b v c = T**
- Determining rows: {1, 3, 4, 5, 7, 8}

**Clause b** (minors: a, c):
- p_b=T = a ^ (F v c) = a ^ c
- p_b=F = a ^ (T v c) = a ^ T = a
- b determines p3 when a ^ c != a, i.e., **a = T and c = F**
- Determining rows: {2, 4}

**Clause c** (minors: a, b):
- p_c=T = a ^ (!b v T) = a ^ T = a
- p_c=F = a ^ (!b v F) = a ^ !b
- c determines p3 when a != a ^ !b, i.e., **a = T and b = T**
- Determining rows: {1, 2}

### (A) GACC

| Major clause | Pairs                                                              |
|--------------|--------------------------------------------------------------------|
| a            | (1, 5), (1, 7), (1, 8), (3, 5), (3, 7), (3, 8), (4, 5), (4, 7), (4, 8) |
| b            | (2, 4)                                                              |
| c            | (1, 2)                                                              |

### (B) CACC

| Major clause | Pairs                                                              |
|--------------|--------------------------------------------------------------------|
| a            | (1, 5), (1, 7), (1, 8), (3, 5), (3, 7), (3, 8), (4, 5), (4, 7), (4, 8) |
| b            | (2, 4)                                                              |
| c            | (1, 2)                                                              |

### (C) RACC

| Major clause | Pairs              |
|--------------|--------------------|
| a            | (1, 5), (3, 7), (4, 8) |
| b            | (2, 4)              |
| c            | (1, 2)              |

---

## p4 = a -> (b -> c)

Note: a -> (b -> c) = !a v (!b v c) = !a v !b v c

### Truth Table

| Row | a | b | c | b -> c | p4 = a -> (b -> c) |
|-----|---|---|---|--------|---------------------|
| 1   | T | T | T | T      | T                   |
| 2   | T | T | F | F      | F                   |
| 3   | T | F | T | T      | T                   |
| 4   | T | F | F | T      | T                   |
| 5   | F | T | T | T      | T                   |
| 6   | F | T | F | F      | T                   |
| 7   | F | F | T | T      | T                   |
| 8   | F | F | F | T      | T                   |

### Definitional Method

**Clause a** (minors: b, c):
- p_a=T = !b v c
- p_a=F = T
- a determines p4 when !b v c != T, i.e., **b = T and c = F**
- Determining rows: {2, 6}

**Clause b** (minors: a, c):
- p_b=T = !a v c
- p_b=F = !a v T = T
- b determines p4 when !a v c != T, i.e., **a = T and c = F**
- Determining rows: {2, 4}

**Clause c** (minors: a, b):
- p_c=T = !a v !b v T = T
- p_c=F = !a v !b
- c determines p4 when !a v !b != T, i.e., **a = T and b = T**
- Determining rows: {1, 2}

### (A) GACC

| Major clause | Pairs  |
|--------------|--------|
| a            | (2, 6) |
| b            | (2, 4) |
| c            | (1, 2) |

### (B) CACC

| Major clause | Pairs  |
|--------------|--------|
| a            | (2, 6) |
| b            | (2, 4) |
| c            | (1, 2) |

### (C) RACC

| Major clause | Pairs  |
|--------------|--------|
| a            | (2, 6) |
| b            | (2, 4) |
| c            | (1, 2) |

---

## p5 = (a ^ b) v (b ^ c) v (a ^ c)

### Truth Table

| Row | a | b | c | a ^ b | b ^ c | a ^ c | p5 |
|-----|---|---|---|-------|-------|-------|----|
| 1   | T | T | T | T     | T     | T     | T  |
| 2   | T | T | F | T     | F     | F     | T  |
| 3   | T | F | T | F     | F     | T     | T  |
| 4   | T | F | F | F     | F     | F     | F  |
| 5   | F | T | T | F     | T     | F     | T  |
| 6   | F | T | F | F     | F     | F     | F  |
| 7   | F | F | T | F     | F     | F     | F  |
| 8   | F | F | F | F     | F     | F     | F  |

### Definitional Method

**Clause a** (minors: b, c):
- p_a=T = (T ^ b) v (b ^ c) v (T ^ c) = b v (b ^ c) v c = b v c
- p_a=F = (F ^ b) v (b ^ c) v (F ^ c) = b ^ c
- a determines p5 when (b v c) != (b ^ c), i.e., **b != c**
- Determining rows: {2, 3, 6, 7}

**Clause b** (minors: a, c):
- p_b=T = a v c v (a ^ c) = a v c
- p_b=F = (a ^ c)
- b determines p5 when (a v c) != (a ^ c), i.e., **a != c**
- Determining rows: {2, 4, 5, 7}

**Clause c** (minors: a, b):
- p_c=T = (a ^ b) v b v a = a v b
- p_c=F = (a ^ b)
- c determines p5 when (a v b) != (a ^ b), i.e., **a != b**
- Determining rows: {3, 4, 5, 6}

### (A) GACC

| Major clause | Pairs                          |
|--------------|--------------------------------|
| a            | (2, 6), (2, 7), (3, 6), (3, 7) |
| b            | (2, 4), (2, 7), (5, 4), (5, 7) |
| c            | (3, 4), (3, 6), (5, 4), (5, 6) |

### (B) CACC

| Major clause | Pairs                          |
|--------------|--------------------------------|
| a            | (2, 6), (2, 7), (3, 6), (3, 7) |
| b            | (2, 4), (2, 7), (5, 4), (5, 7) |
| c            | (3, 4), (3, 6), (5, 4), (5, 6) |

### (C) RACC

| Major clause | Pairs          |
|--------------|----------------|
| a            | (2, 6), (3, 7) |
| b            | (2, 4), (5, 7) |
| c            | (3, 4), (5, 6) |
