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

| Characteristic ID | Characteristic Name             | Variable | Description                                |
| ----------------- | ------------------------------- | -------- | ------------------------------------------ |
| C1                | Stack is non-empty              | S        | Whether the stack has at least one element  |
| C2                | Value is non-null               | X        | Whether the pushed/popped value is non-null |

## (c) Partition Characteristics into Blocks

| Characteristic | Block T (true)                | Block F (false)            |
| -------------- | ----------------------------- | -------------------------- |
| C1             | Stack has at least one element | Stack is empty (size = 0) |
| C2             | Value is non-null              | Value is null             |

**Base block for each characteristic:** T (true)

## (d) Designate Base Blocks

| Characteristic          | Base Block  | Rationale                              |
| ----------------------- | ----------- | -------------------------------------- |
| C1 (Stack is non-empty) | **T**       | Most typical usage, stack has content  |
| C2 (Value is non-null)  | **T**       | Normal, expected input                 |

## (e) Define Values for Each Block

| Characteristic | Block | Concrete Value                                  |
| -------------- | ----- | ----------------------------------------------- |
| C1             | T     | S = [5] (push one Integer before test action)   |
| C1             | F     | S = [] (new stack, no pushes)                   |
| C2             | T     | X = 42 (non-null Integer)                       |
| C2             | F     | X = null                                        |

## (f) Base Choice Coverage (BCC) Test Set

### Step 1: Abstract Model

| Method name      | Return type | Possible values | Exception           | Characteristic Name    | Characteristic ID |
| ---------------- | ----------- | --------------- | ------------------- | ---------------------- | ----------------- |
| GenericStack()   | —           | —               |                     | —                      | —                 |
| push(Object X)   | void        | —               |                     | stack is non-empty     | C1                |
|                  |             |                 |                     | X is non-null          | C2                |
| pop()            | Object E    | E, null         | EmptyStackException | stack is non-empty     | C1                |
|                  |             |                 |                     | return value is non-null | C2              |
| isEmpty()        | boolean     | true, false     |                     | stack is non-empty     | C1                |

### Step 2: BCC Tables

**push(Object X):**

| C1 | C2 |          |
| -- | -- | -------- |
| T  | T  | base case |
| F  | T  |          |
| T  | F  |          |

**pop():**

| C1 | C2 |                                  |
| -- | -- | -------------------------------- |
| T  | T  | base case                        |
| F  | T  | infeasible (empty stack throws)  |
| T  | F  |                                  |

Revised:

| C1 | C2 |                                  |
| -- | -- | -------------------------------- |
| T  | T  | base case                        |
| F  | F  | revised (empty stack → exception) |
| T  | F  |                                  |

**isEmpty():**

| C1 |           |
| -- | --------- |
| T  | base case |
| F  |           |

### Step 3: Abstract Test Requirements

**push(Object X):**

| Test      | C1 | C2 |                   |
| --------- | -- | -- | ----------------- |
| T2 (Base) | T  | T  | base case         |
| T3        | F  | T  |                   |
| T4        | T  | F  |                   |

**pop():**

| Test      | C1 | C2 |                                    |
| --------- | -- | -- | ---------------------------------- |
| T5 (Base) | T  | T  | base case                          |
| T6        | F  | T  | infeasible (empty stack throws)    |
| T7        | T  | F  |                                    |

Revised:

| Test      | C1 | C2 |                                     |
| --------- | -- | -- | ----------------------------------- |
| T5 (Base) | T  | T  | base case                           |
| T6        | F  | F  | revised (empty stack → exception)   |
| T7        | T  | F  |                                     |

**isEmpty():**

| Test      | C1 |           |
| --------- | -- | --------- |
| T8 (Base) | T  | base case |
| T9        | F  |           |

### Step 4: Concrete Tests

```java
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

public class GenericStackTest {
    private GenericStack stack;

    @Before
    public void setUp() {
        stack = new GenericStack();
    }

    // T1: constructor
    @Test
    public void test_constructor() {
        assertTrue(stack.isEmpty());
    }

    // T2: push() base case: [C1, C2] = [T, T]
    @Test
    public void test_push_base() {
        stack.push(5);
        stack.push(42);
        assertEquals(42, stack.pop());
        assertEquals(5, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // T3: push() [C1, C2] = [F, T]
    @Test
    public void test_push_C1() {
        stack.push(42);
        assertEquals(42, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // T4: push() [C1, C2] = [T, F]
    @Test
    public void test_push_C2() {
        stack.push(5);
        stack.push(null);
        assertNull(stack.pop());
        assertEquals(5, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // T5: pop() base case: [C1, C2] = [T, T]
    @Test
    public void test_pop_base() {
        stack.push(5);
        assertEquals(5, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // T6: pop() (revised from [F, T]): [C1, C2] = [F, F]
    @Test(expected = EmptyStackException.class)
    public void test_pop_C1() {
        stack.pop();
    }

    // T7: pop() [C1, C2] = [T, F]
    @Test
    public void test_pop_C2() {
        stack.push(null);
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    // T8: isEmpty() base case: [C1] = [T]
    @Test
    public void test_isEmpty_base() {
        stack.push(5);
        assertFalse(stack.isEmpty());
    }

    // T9: isEmpty() [C1] = [F]
    @Test
    public void test_isEmpty_C1() {
        assertTrue(stack.isEmpty());
    }
}
```
