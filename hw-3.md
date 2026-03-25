**Name:** Han Nguyen\
**NetID:** Txn200004

# Homework 3

## Input Space Partitioning for GenericStack

Methods under test:

- `public GenericStack();`
- `public void push(Object X);`
- `public Object pop();`
- `public boolean isEmpty();`

## (a) Input Variables (including state variables)

| Variable | Type            | Description                                                          |
| -------- | --------------- | -------------------------------------------------------------------- |
| **S**    | State variable  | The current state of the stack (its contents and number of elements) |
| **X**    | Input parameter | The object passed to `push(Object X)`                                |

Notes:

- `pop()` and `isEmpty()` take no explicit parameters, they operate only on the state variable **S**.
- The constructor `GenericStack()` takes no parameters; it initializes **S** to an empty stack.
- **S** is modified by `push` and `pop`, and read by `pop` and `isEmpty`.

## (b) Characteristics of the Input Variables

| Characteristic                      | Variable | Description                                 |
| ----------------------------------- | -------- | ------------------------------------------- |
| q1: Number of elements in the stack | S        | How many elements the stack currently holds |
| q2: Nullness of X                   | X        | Whether the value pushed is null or not     |
| q3: Type of X                       | X        | The runtime type of the object being pushed |

## (c) Partition Characteristics into Blocks

**q1: Number of elements in the stack (S):**

| Block | Description                                |
| ----- | ------------------------------------------ |
| b1    | Stack is empty (size = 0)                  |
| b2    | Stack has one element (size = 1)           |
| b3    | Stack has more than one element (size > 1) |

**q2: Nullness of X:**

| Block | Description   |
| ----- | ------------- |
| b1    | X is null     |
| b2    | X is non-null |

**q3: Type of X:**

| Block | Description      |
| ----- | ---------------- |
| b1    | X is an Integer  |
| b2    | X is a Double    |
| b3    | X is a String    |
| b4    | X is a Character |

## (d) Designate Base Blocks

| Characteristic     | Base Block           | Rationale                                                |
| ------------------ | -------------------- | -------------------------------------------------------- |
| q1 (Stack size)    | **b2** (one element) | Most typical usage, stack has some content but is simple |
| q2 (Nullness of X) | **b2** (non-null)    | Normal, expected input                                   |
| q3 (Type of X)     | **b1** (Integer)     | Common, simple object type                               |

**Base choice:** (q1=b2, q2=b2, q3=b1)

## (e) Define Values for Each Block

**q1: Stack size:**

| Block              | Concrete Value                        |
| ------------------ | ------------------------------------- |
| b1 (empty)         | S = [] (new stack, no pushes)         |
| b2 (one element)   | S = [5] (push one Integer)            |
| b3 (more than one) | S = [5, 10, 15] (push three Integers) |

**q2: Nullness of X:**

| Block         | Concrete Value |
| ------------- | -------------- |
| b1 (null)     | X = null       |
| b2 (non-null) | X = 42         |

**q3: Type of X:**

| Block          | Concrete Value |
| -------------- | -------------- |
| b1 (Integer)   | X = 42         |
| b2 (Double)    | X = 3.14       |
| b3 (String)    | X = "hello"    |
| b4 (Character) | X = 'A'        |

## (f) Base Choice Coverage (BCC) Test Set

Formula: Number of tests = 1 + sum of (Bi - 1) for each characteristic = 1 + (3-1) + (2-1) + (4-1) = **7 tests**

**Base test:** q1=b2, q2=b2, q3=b1 → Stack has one element, push a non-null Integer

| Test      | q1               | q2            | q3                 | Changed | Setup & Actions                                     | Oracle (Expected Result)                     |
| --------- | ---------------- | ------------- | ------------------ | ------- | --------------------------------------------------- | -------------------------------------------- |
| T1 (Base) | b2 (one elem)    | b2 (non-null) | b1 (Integer)       | —       | Create stack; push(5); push(42)                     | pop() → 42; pop() → 5; isEmpty() → true      |
| T2        | **b1 (empty)**   | b2 (non-null) | b1 (Integer)       | q1      | Create stack; push(42)                              | pop() → 42; isEmpty() → true                 |
| T3        | **b3 (>1 elem)** | b2 (non-null) | b1 (Integer)       | q1      | Create stack; push(5); push(10); push(15); push(42) | pop() → 42; pop() → 15; isEmpty() → false    |
| T4        | b2 (one elem)    | **b1 (null)** | b1 (Integer)       | q2      | Create stack; push(5); push(null)                   | pop() → null; pop() → 5; isEmpty() → true    |
| T5        | b2 (one elem)    | b2 (non-null) | **b2 (Double)**    | q3      | Create stack; push(5); push(3.14)                   | pop() → 3.14; pop() → 5; isEmpty() → true    |
| T6        | b2 (one elem)    | b2 (non-null) | **b3 (String)**    | q3      | Create stack; push(5); push("hello")                | pop() → "hello"; pop() → 5; isEmpty() → true |
| T7        | b2 (one elem)    | b2 (non-null) | **b4 (Character)** | q3      | Create stack; push(5); push('A')                    | pop() → 'A'; pop() → 5; isEmpty() → true     |

### Oracle Rationale

- **LIFO order**: `pop()` must always return the most recently pushed element.
- **isEmpty()**: Returns `true` only when the stack has zero elements; `false` otherwise.
- **Constructor**: `new GenericStack()` creates an empty stack, so `isEmpty()` → `true` immediately after construction.
- Each test verifies correct behavior by checking return values of `pop()` and `isEmpty()` against expected LIFO semantics.
