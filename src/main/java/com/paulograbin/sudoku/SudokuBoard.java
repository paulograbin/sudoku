package com.paulograbin.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SudokuBoard {

    private final int[][] board;
    private final int[][] candidates;


    public SudokuBoard(int[][] game) {
        board = game;
        candidates = new int[9][9];

        for (int j = 0; j < candidates.length; j++) {
            int[] candidate = candidates[j];
            Arrays.fill(candidate, 0b111111111);
        }

        printBoard();
    }

    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (getValueAt(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getValueAt(int row, int column) {
        return board[row][column];
    }

    public int getCandidatesForCell(int row, int column) {
        return candidates[row][column];
    }

    public void setCandidatesForCell(int row, int column, int newCandidates) {
        candidates[row][column] = newCandidates;
    }

    public int computeCandidates(int row, int col) {
        int candidates = getCandidatesForCell(row, col);

        for (int v : getNumbersFromRow(row)) {
            candidates &= ~(1 << (v - 1));
        }
        for (int v : getNumbersFromColumn(col)) {
            candidates &= ~(1 << (v - 1));
        }
        for (int v : getNumbersFromBlock(row, col)) {
            candidates &= ~(1 << (v - 1));
        }

        return candidates;
    }

    public int[] getRow(int row) {
        int[] rowElements = new int[9];

        System.arraycopy(board[row], 0, rowElements, 0, 9);

        return rowElements;
    }


    public int[] getColumn(int columnId) {
        int[] columnElements = new int[9];

        for (int i = 0; i < 9; i++)
            columnElements[i] = board[i][columnId];

        return columnElements;
    }

    public Set<Integer> getNumbersFromRow(int row) {
        return Arrays.stream(board[row])
                .filter(i -> i > 0)
                .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }

    public Set<Integer> getNumbersFromColumn(int columnId) {
        Set<Integer> numbersInColumn = new HashSet<>(9);

        for (int i = 0; i < 9; i++) {
            if (board[i][columnId] > 0) {
                numbersInColumn.add(board[i][columnId]);
            }
        }

        return numbersInColumn;
    }

    public Set<Integer> getNumbersFromBlock(int row, int column) {
        int initialX = getInitialX(row);
        int initialY = getInitialY(column);

        int finalX = initialX + 2;
        int finalY = initialY + 2;

        Set<Integer> numbersInBlock = new HashSet<>(9);

        for (int i = initialX; i <= finalX; i++)
            for (int j = initialY; j <= finalY; j++) {

                if (board[i][j] > 0) {
                    numbersInBlock.add(board[i][j]);
                }
            }

        return numbersInBlock;
    }

    /**
     * Returns an int  array of size 9 with all the numbers already present in the block.
     * Non filled numbers are 0
     * <p>
     * Deprecated in favor of getNumbersFromBlock() which does not return 0
     */
    @Deprecated(since = "April 4, 2026", forRemoval = true)
    public int[] getBlock(int row, int column) {
        int[] blockElements = new int[9];
        int count = 0;

        int initialX = getInitialX(row);
        int initialY = getInitialY(column);

        int finalX = initialX + 2;
        int finalY = initialY + 2;

        for (int i = initialX; i <= finalX; i++)
            for (int j = initialY; j <= finalY; j++) {
                blockElements[count] = board[i][j];
                count = count + 1;
            }

        return blockElements;
    }

    private int getInitialY(int column) {
        return getInitialX(column);
    }

    private int getInitialX(int row) {
        return switch (row) {
            case 0, 1, 2 -> 0;
            case 3, 4, 5 -> 3;
            case 6, 7, 8 -> 6;
            default -> 0;
        };
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i > 0) {
                System.out.println("------+-------+------");
            }

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }

                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setValueAt(Integer value, int cellRow, int cellColumn) {
        board[cellRow][cellColumn] = value;
    }
}
