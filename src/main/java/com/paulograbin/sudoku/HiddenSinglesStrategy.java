package com.paulograbin.sudoku;

import java.util.Set;

import static com.paulograbin.sudoku.SudokuBoard.POSSIBLE_VALUES;

public class HiddenSinglesStrategy implements SolvingStrategy {

    @Override
    public boolean apply(SudokuBoard board) {
        System.out.println("Applying strategy: " + this.getClass().getSimpleName());

        boolean madeProgress = false;

        for (int i = 0; i < 9; i++) {
            if (scanRow(board, i)) madeProgress = true;
            if (scanColumn(board, i)) madeProgress = true;
        }

        for (int blockRow = 0; blockRow < 9; blockRow += 3) {
            for (int blockCol = 0; blockCol < 9; blockCol += 3) {
                if (scanBlock(board, blockRow, blockCol)) madeProgress = true;
            }
        }

        return madeProgress;
    }

    private boolean scanRow(SudokuBoard board, int row) {
        boolean madeProgress = false;

        for (int candidate : POSSIBLE_VALUES) {
            Set<Integer> numbersAlreadyInRow = board.getNumbersFromRow(row);

            if (numbersAlreadyInRow.contains(candidate))
                continue;

            int count = 0;
            int lastCol = -1;

            for (int col = 0; col < 9; col++) {
                int i = board.getCandidates(row, col);

                if (board.getValueAt(row, col) == 0 && isCandidate(i, candidate)) {
                    count++;
                    lastCol = col;
                }
            }

            if (count == 1) {
                board.setValueAt(candidate, row, lastCol);
                System.out.println("Set value " + candidate + " to row " + row + " column " + lastCol);
                board.printBoard();
                madeProgress = true;
            }
        }

        return madeProgress;
    }

    private boolean isCandidate(int candidates, int value) {
        return (candidates & (1 << (value -1))) != 0;
    }

    private boolean scanColumn(SudokuBoard board, int col) {
        boolean madeProgress = false;

        for (int candidate : POSSIBLE_VALUES) {
            if (columnContains(board, col, candidate)) continue;

            int count = 0;
            int lastRow = -1;

            for (int row = 0; row < 9; row++) {
                if (board.getValueAt(row, col) == 0 && canPlace(board, row, col, candidate)) {
                    count++;
                    lastRow = row;
                }
            }

            if (count == 1) {
                System.out.println("Add value " + candidate + " to row " + lastRow + " column " + col);
                board.setValueAt(candidate, lastRow, col);
                board.printBoard();
                madeProgress = true;
            }
        }

        return madeProgress;
    }

    private boolean scanBlock(SudokuBoard board, int blockRow, int blockCol) {
        boolean madeProgress = false;

        for (int candidate : POSSIBLE_VALUES) {
            if (blockContains(board, blockRow, blockCol, candidate))
                continue;

            int count = 0;
            int lastRow = -1;
            int lastCol = -1;

            for (int r = blockRow; r < blockRow + 3; r++) {
                for (int c = blockCol; c < blockCol + 3; c++) {
                    if (board.getValueAt(r, c) == 0 && canPlace(board, r, c, candidate)) {
                        count++;
                        lastRow = r;
                        lastCol = c;
                    }
                }
            }

            if (count == 1) {
                board.setValueAt(candidate, lastRow, lastCol);
                System.out.println("Add value " + candidate + " to row " + lastRow + " column " + lastCol);
                madeProgress = true;
            }
        }

        return madeProgress;
    }

    private boolean canPlace(SudokuBoard board, int row, int col, int value) {
        return !rowContains(board, row, value)
                && !columnContains(board, col, value)
                && !blockContains(board, row, col, value);
    }

    private boolean rowContains(SudokuBoard board, int row, int value) {
//        System.out.println("Checking row contains " + value + " in row " + row);

        return board.getNumbersFromRow(row).contains(value);
    }

    private boolean columnContains(SudokuBoard board, int col, int value) {
//        System.out.println("Checking column contains " + value + " in column " + col);

        return board.getNumbersFromColumn(col).contains(value);
    }

    private boolean blockContains(SudokuBoard board, int row, int col, int value) {
//        System.out.println("Checking block contains " + value + " in row " + row + " column " + col);

        return board.getNumbersFromBlock(row, col).contains(value);
    }
}
