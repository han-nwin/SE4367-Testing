# Homework 5 — Individual

**SE 4367: Software Testing, Verification, Validation and QA**

> *A test that reveals a bug has succeeded, not failed.* — Boris Beizer

---

**Class, Section:** __________________

**Total Points (Out of 100 points):** __________________

---

## Instructions

1. Answer the problem on a PDF file (PDF file only) and save it as `HW#_YOUR-Name#.PDF`
2. Submit the PDF file to eLearning before the due date

---

## Assignment

Design, implement, and run **PC** (Predicate Coverage), **CC** (Clause Coverage), and **CACC** (Correlated Active Clause Coverage) tests for the method `turnHeaterOn` in class `Thermostat.java`.

### 1. Predicates (5 points)

List all predicates in the method. How many have one clause, two clauses, three clauses, and more than three?

### 2. Predicate Coverage (PC)

For each predicate, write the truth table and choose rows from the truth tables that satisfy PC.

- **a. (10 points)** Submit the truth tables and clearly mark which rows will be used. These are your abstract tests.
- **b. (5 points)** For each abstract test, create input values that satisfy the truth assignments and that reach the predicate.
- **c. (10 points)** Implement each test in the JUnit file `ThermostatTest_PC.java`. Include comments that state which predicate and which truth assignment (row in the truth table) is being implemented.
- **d. (5 points)** Run your tests.

### 3. Clause Coverage (CC)

For each predicate, write the truth table and choose rows from the truth tables that satisfy CC.

- **a. (10 points)** Submit the truth tables and clearly mark which rows will be used. These are your abstract tests.
- **b. (5 points)** For each abstract test, create input values that satisfy the truth assignments and that reach the predicate.
- **c. (10 points)** Implement each test in the JUnit file `ThermostatTest_CC.java`. Include comments that state which predicate and which truth assignment (row in the truth table) is being implemented.
- **d. (5 points)** Run your tests.

### 4. Correlated Active Clause Coverage (CACC)

For each predicate, write the truth table and choose rows from the truth tables that satisfy CACC.

- **a. (10 points)** Submit the truth tables and clearly mark which rows will be used. These are your abstract tests.
- **b. (5 points)** For each abstract test, create input values that satisfy the truth assignments and that reach the predicate.
- **c. (10 points)** Implement each test in the JUnit file `ThermostatTest_CACC.java`. Include comments that state which predicate and which truth assignment (row in the truth table) is being implemented.
- **d. (5 points)** Run your tests.

---

## Submission

Please complete the following tasks and submit them in hard copy:

1. Clearly answer the questions in the PDF file format, ensuring proper numbering and formatting.
2. Include screenshots displaying the results of executing all your JUnit tests within the PDF.
3. Submit the files `ThermostatTest_PC.java`, `ThermostatTest_CC.java`, and `ThermostatTest_CACC.java` through the eLearning platform.
