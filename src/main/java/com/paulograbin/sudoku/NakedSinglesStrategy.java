package com.paulograbin.sudoku;

import static com.paulograbin.sudoku.CandidateHelper.doesCellHaveOnlyASingleCandidate;
import static com.paulograbin.sudoku.CandidateHelper.makeValueFromSingleCandidate;

public class NakedSinglesStrategy implements SolvingStrategy {

    @Override
    public boolean apply(SudokuBoard board) {
        System.out.println("Applying strategy: " + this.getClass().getSimpleName());

        boolean madeProgress = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getValueAt(i, j) == 0) { // Only solve cell if it has not already been solved
                    if (solveCell(board, i, j)) {
                        madeProgress = true;
                    }
                }
            }
        }

        return madeProgress;
    }

    private boolean solveCell(SudokuBoard board, int row, int column) {
        int candidates = board.getCandidates(row, column);

        if (doesCellHaveOnlyASingleCandidate(candidates)) {
            int value = makeValueFromSingleCandidate(candidates);
            board.setValueAt(value, row, column);

            System.out.println("Found value for " + row + "/" + column + " is " + value);

            return true;
        }

        return false;
    }
}
