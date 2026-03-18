Homework3 SE 4367: Software Testing, Verification, Validation and QA
A test that reveals a bug has succeeded, not failed. -Boris Beizer
Class, Section __________________
Total Points (Out of 100 points) __________________
Instruction:
1. Answer to the problem on a PDF file (PDF file only) and save it as HW#_YOUR-Name#.PDF
2. Submit the PDF file to eLearning before the due date
Derive input space partitioning test inputs for the GenericStack class with the following method signatures:
public GenericStack();
public void push(Object X);
public Object pop();
public boolean isEmpty();
Assume the usual semantics for the GenericStack. Try to keep your partitioning simple and choose a small
number of partitions and blocks.
(a) (20 points) List all of the input variables, including the state variables.
(b) (15 points) Definite characteristics of the input variables. Make sure you cover all input variables.
(c) (15 points) Partition the characteristics into blocks.
(d) (10 points) Designate one block in each partition as the "Base" block.
(e) (20 points) Define values for each block.
(f) (20 points) Define a test set that satisfies Base Choice Coverage (BCC). Write your tests with the values from the
previous step. Be sure to include the test oracles
