package com.paulograbin.sudoku;

public class NakedSinglesStrategy implements SolvingStrategy {

    @Override
    public boolean apply(SudokuBoard board) {
        boolean madeProgress = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getValueAt(i, j) == 0) {
                    if (solveCell(board, i, j)) {
                        madeProgress = true;
                    }
                }
            }
        }

        return madeProgress;
    }

    private boolean solveCell(SudokuBoard board, int row, int col) {
        int candidates = board.computeCandidates(row, col);
        board.setCandidatesForCell(row, col, candidates);

        if (Integer.bitCount(candidates) == 1) {
            int value = Integer.numberOfTrailingZeros(candidates) + 1;
            board.setValueAt(value, row, col);
            return true;
        }

        return false;
    }
}
