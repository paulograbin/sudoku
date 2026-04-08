# Sudoku Solver - Conversation Notes (2026-04-05)

## Codebase Overview
- Java project with a strategy-pattern Sudoku solver
- `SudokuBoard` stores a 9x9 grid and bitmask-based candidates (`0b111111111` = all 1-9 possible)
- `SudokuSolver` loops through strategies until solved or stuck
- `SolvingStrategy` interface with `boolean apply(SudokuBoard board)`

### Implemented strategies
1. **NakedSinglesStrategy** - finds cells with only one candidate remaining (bitCount == 1)
2. **HiddenSinglesStrategy** - scans rows/columns/blocks for values that can only go in one cell

### Current state
- Solves easy puzzles
- Stalls on hard/nightmare puzzles (tests assert `isSolved().isFalse()`)

## Issues found
1. `EXTREME_GAME` and `EASY_FIRST_GAME` in BoardRepository have duplicate 9 in row 6 (columns 2 and 5) - invalid boards
2. `printCandidates` line 170 had operator precedence bug (parentheses needed around `numberOfTrailingZeros + 1`)
3. Candidates aren't recomputed for peers after placing a value (strategies call `computeCandidates` on-demand)

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

## Next Strategy: Naked Pairs

### What it does
If two cells in the same row/column/block have the exact same two candidates (e.g., {3,7}), those values must go in those two cells. Eliminate those values from all other cells in that unit.

### Bitmask check
```java
candidates[a] == candidates[b] && Integer.bitCount(candidates[a]) == 2
// Eliminate from other cells:
otherCell &= ~pairMask;
```

### Implementation plan
1. **Add to SudokuBoard:**
   - `getCandidates(row, col)` - returns current bitmask
   - `eliminateCandidate(row, col, value)` - clears a specific bit, returns true if changed

2. **Create `NakedPairsStrategy` implementing `SolvingStrategy`:**
   - For each unit (row, column, block):
     - Collect empty cells and their candidate bitmasks
     - Find pairs where `candidates[a] == candidates[b]` and `bitCount == 2`
     - Eliminate both pair values from all other empty cells in the unit
   - Returns true if any candidate was eliminated

3. **Register in SudokuSolver:** Add after HiddenSinglesStrategy in the strategies list

4. **Add unit test** in `NakedPairsStrategyTest`

### Key design detail
Naked Pairs doesn't place values directly — it only eliminates candidates. This means the solver loop must recognize candidate elimination as "progress" so it re-runs Naked/Hidden Singles afterward. The strategy's `apply()` returns `true` when any candidate was eliminated, which the existing solver loop already handles correctly (it restarts from the first strategy on any progress).

### Files to modify
- `src/main/java/com/paulograbin/sudoku/SudokuBoard.java` — add `getCandidates()` and `eliminateCandidate()`
- `src/main/java/com/paulograbin/sudoku/SudokuSolver.java` — add NakedPairsStrategy to strategy list
- **New:** `src/main/java/com/paulograbin/sudoku/NakedPairsStrategy.java`
- **New:** `src/test/java/com/paulograbin/sudoku/NakedPairsStrategyTest.java`

### Verification
- `mvn test` — all existing tests should pass
- Hard/anotherEasy tests may solve further if naked pairs was the missing technique

### Strategy progression (future)
1. Naked Singles ✅
2. Hidden Singles ✅
3. Naked Pairs ← next
4. Pointing Pairs / Box-Line Reduction
5. Naked Triples
6. Hidden Pairs/Triples
7. X-Wing
8. Backtracking (catch-all)

## Status
Plan was reviewed and ready to implement. Implementation has not started yet.
