package com.paulograbin.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NakedSinglesStrategy implements SolvingStrategy {

    private static final List<Integer> POSSIBILITIES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

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

    private boolean solveCell(SudokuBoard board, int cellRow, int cellColumn) {
        List<Integer> candidates = new ArrayList<>(POSSIBILITIES);

        for (int i : board.getBlock(cellRow, cellColumn)) {
            if (i != 0)
                candidates.remove(Integer.valueOf(i));
        }

        for (int i : board.getRow(cellRow)) {
            if (i != 0)
                candidates.remove(Integer.valueOf(i));
        }

        for (int i : board.getColumn(cellColumn)) {
            if (i != 0)
                candidates.remove(Integer.valueOf(i));
        }

        if (candidates.size() == 1) {
            board.setValueAt(candidates.get(0), cellRow, cellColumn);
            return true;
        }

        return false;
    }
}
