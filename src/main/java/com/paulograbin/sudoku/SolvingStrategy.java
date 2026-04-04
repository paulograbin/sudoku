package com.paulograbin.sudoku;

public interface SolvingStrategy {

    /**
     * Attempts to make progress on the board using this strategy.
     *
     * @return true if at least one cell was filled
     */
    boolean apply(SudokuBoard board);
}
