Graph:

N={1,2,3,4,5,6}
N0={1}
Nf={6}
E={(1,2),(2,3),(2,6),(3,4),(3,5),(4,5),(5,2)}

def(1)=def(3)=use(3)=use(6)={x}. //Assume the use of x in 3 precedes the def

Test Paths:

t1=[1,2,6]
t2=[1,2,3,4,5,2,3,5,2,6]
t3=[1,2,3,5,2,3,4,5,2,6]
t4=[1,2,3,5,2,6]

Answer the following questions about the graph:

(a) Draw the graph.
(b) List all of the du-paths with respect to x. (Note: Include all-du-paths, even those that are subpaths of some other du-path).
(c) Determine which du-paths each test path tours. Write them in a table with test paths in the first column and the du-paths they cover in the second column.
For this part of the exercise, you should consider both direct touring and sidetrips.
(d) List a minimal test set that satisfies all defs coverage with respect to x. (Direct tours only.) Use the given test paths.
(e) List a minimal test set that satisfies all uses coverage with respect to x. (Direct tours only.) Use the given test paths.
(f) List a minimal test set that satisfies all-du-paths coverage with respect to x. (Direct tours only.) Use the given test paths.
