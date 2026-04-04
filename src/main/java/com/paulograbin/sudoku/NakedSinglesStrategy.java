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

    private boolean solveCell(SudokuBoard board, int row, int column) {
        int candidates = board.computeCandidates(row, column);

        String bitmask = String.format("%9s", Integer.toBinaryString(candidates)).replace(' ', '0');

        if (Integer.bitCount(candidates) == 1) {
            int value = Integer.numberOfTrailingZeros(candidates) + 1;
            board.setValueAt(value, row, column);

            System.out.println("Found value for row: " + row + " column: " + column + " are: " + bitmask + "/" + Integer.toBinaryString(candidates) + ", value " + (Integer.numberOfTrailingZeros(candidates) + 1));

            return true;
        } else {
//            System.out.println("There are " + Integer.bitCount(candidates) + " Candidates for row: " + row + " column: " + column + " are: " + bitmask + "/" + Integer.toBinaryString(candidates));
        }

        return false;
    }
}
