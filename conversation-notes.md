# Sudoku Solver - Conversation Notes (updated 2026-05-05)

## Codebase Overview
- Java project with a strategy-pattern Sudoku solver
- `SudokuBoard` stores a 9x9 grid and bitmask-based candidates (`0b111111111` = all 1-9 possible)
- `SudokuSolver` loops through strategies until solved or stuck
- `SolvingStrategy` interface with `boolean apply(SudokuBoard board)`

### Implemented strategies
1. **NakedSinglesStrategy** - finds cells with only one candidate remaining (bitCount == 1)
2. **HiddenSinglesStrategy** - scans rows/columns/blocks for values that can only go in one cell

### Current state
- Solves easy puzzles fully
- Hard puzzle gets partially solved (strategies run out before completion — needs more advanced techniques)
- Nightmare puzzle also stalls (expected)

## Issues found & fixed
1. ~~`EXTREME_GAME` and `EASY_FIRST_GAME` in BoardRepository have duplicate 9 in row 6 (columns 2 and 5) - invalid boards~~ ✅ Fixed
2. ~~`printCandidates` line 170 had operator precedence bug (parentheses needed around `numberOfTrailingZeros + 1`)~~ ✅ Fixed
3. ~~Candidates aren't recomputed for peers after placing a value~~ ✅ Fixed — `setValueAt` now recomputes all candidates
4. ~~`makeHardBoard()` had duplicate 4 in block (3,3): cells (4,3) and (5,5) both had value 4~~ ✅ Fixed — removed 4 from (4,3)

## Bitmask Operations Reference
```java
// Full candidate set
int candidates = 0b111111111; // 511

// Remove value
candidates &= ~(1 << (value - 1));

// Check if value is candidate
boolean isCandidate = (candidates & (1 << (value - 1))) != 0;

// Count candidates
int count = Integer.bitCount(candidates);

// Get value when single candidate
int value = Integer.numberOfTrailingZeros(candidates) + 1;

// Add value back
candidates |= (1 << (value - 1));

// Intersect / Union
candidates = a & b;  // intersect
candidates = a | b;  // union

// Iterate over set candidates
int temp = candidates;
while (temp != 0) {
    int value = Integer.numberOfTrailingZeros(temp) + 1;
    temp &= temp - 1; // clear lowest set bit
}
```

## Implemented: Naked Pairs ✅

Implemented for rows and columns. Finds cells with identical 2-candidate bitmasks and eliminates those values from peers. Also sets values directly when elimination leaves a cell with a single candidate.

**Current gap:** Not yet implemented for blocks (3x3 boxes). Adding block-level naked pairs may help solve more of the hard puzzle.

### Strategy progression
1. Naked Singles ✅
2. Hidden Singles ✅
3. Naked Pairs ✅ (rows & columns — blocks still TODO)
4. Pointing Pairs / Box-Line Reduction ← next
5. Naked Triples
6. Hidden Pairs/Triples
7. X-Wing
8. Backtracking (catch-all)

## Next steps
1. **Naked Pairs for blocks** — extend `NakedPairsStrategy` to also scan 3x3 boxes
2. **Pointing Pairs / Box-Line Reduction** — if a candidate in a block is confined to one row/column, eliminate it from the rest of that row/column
3. **Board validation** — detect invalid initial boards (duplicate values in row/column/block) before solving, and detect zero-candidate cells during solving to report unsolvable state early
4. **Hard puzzle goal** — the hard board (now fixed) still has unsolved cells after all current strategies run; next strategies should close the gap

## Status
All tests passing (6/6). Hard board data fixed. Naked Pairs implemented for rows/columns.
