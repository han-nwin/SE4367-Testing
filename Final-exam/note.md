# Logic Coverage Cheat Sheet

| Criterion | Rule                                                                                          |
| --------- | --------------------------------------------------------------------------------------------- |
| PC        | Each predicate is T and F at least once.                                                      |
| CC        | Each clause is T and F at least once.                                                         |
| GACC      | For each clause c, two tests where c is active and flips. Minors free. p may or may not flip. |
| CACC      | Same as GACC, but p must flip.                                                                |
| RACC      | Same as GACC, but minors must match across the two tests. (p flips automatically.)            |

Strictness: PC < CC < GACC < CACC < RACC. RACC is roughly MC/DC.

## ACC family compared

| Type | Minors across rows | p must flip |
| ---- | ------------------ | ----------- |
| GACC | can differ         | no          |
| CACC | can differ         | YES         |
| RACC | must match         | yes (auto)  |

## When to use

- PC, CC: quick checks.
- CACC: practical default.
- RACC: safety-critical (DO-178B/C).
