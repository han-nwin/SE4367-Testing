# Homework 4 — SE 4367: Software Testing, Verification, Validation and QA

> *A test that reveals a bug has succeeded, not failed.* —Boris Beizer

Class, Section __________________
Total Points (Out of 100 points) __________________

---

## 1. Use the method `printPrimes()` for questions a–f below.

```java
 5  private static void printPrimes (int n)
 6  {
 7      int curPrime;            // Value currently considered for primeness
 8      int numPrimes;           // Number of primes found so far.
 9      boolean isPrime;         // Is curPrime prime?
10      int [] primes = new int [100]; // The list of prime numbers.
11
12      // Initialize 2 into the list of primes.
13      primes [0] = 2;
14      numPrimes = 1;
15      curPrime  = 2;
16      while (numPrimes < n)
17      {
18          curPrime++;   // next number to consider ...
19          isPrime = true;
20          for (int i = 0; i <= numPrimes-1; i++)
21          {   // for each previous prime.
22              if (isDivisible (primes[i], curPrime))
23              {   // Found a divisor, curPrime is not prime.
24                  isPrime = false;
25                  break; // out of loop through primes.
26              }
27          }
28          if (isPrime)
29          {   // save it!
30              primes[numPrimes] = curPrime;
31              numPrimes++;
32          }
33      }   // End while
34
35      // Print all the primes out.
36      for (int i = 0; i <= numPrimes-1; i++)
37      {
38          System.out.println ("Prime: " + primes[i]);
39      }
40  }   // end printPrimes
```

**a.** (5 points) Draw the control flow graph for the `printPrimes()` method.

**b.** (10 points) Consider test cases t1 = (n = 3) and t2 = (n = 5). Although these tour the same prime paths in `printPrimes()`, they do not necessarily find the same faults. Design a simple fault that t2 would be more likely to discover than t1 would.

**c.** (10 points) For `printPrimes()`, find a test case such that the corresponding test path visits the edge that connects the beginning of the while statement to the for statement without going through the body of the while loop.

**d.** (10 points) List the test requirements for Node Coverage, Edge Coverage, and Prime Path Coverage.

**e.** (10 points) List test paths that achieve Node Coverage but not Edge Coverage on the graph.

**f.** (10 points) List test paths that achieve Edge Coverage but not Prime Path Coverage on the graph.

---

## 2. Use the following program fragment for questions a–e below.

```
w = x;           // node 1
if (m > 0)
{
    w++;          // node 2
}
else
{
    w=2*w;        // node 3
}
// node 4 (no executable statement)
if (y <= 10)
{
    x = 5*y;      // node 5
}
else
{
    x = 3*y+5;    // node 6
}
z = w + x;        // node 7
```

**a.** (5 points) Draw a control flow graph for this program fragment. Use the node numbers given above.

**b.** (10 points) Which nodes have defs for variable `w`?

**c.** (10 points) Which nodes have uses for variable `w`?

**d.** (10 points) Are there any du-paths with respect to variable `w` from node 1 to node 7? If not, explain why not. If any exist, show one.

**e.** (10 points) List all of the du-paths for variables `w` and `x`.
