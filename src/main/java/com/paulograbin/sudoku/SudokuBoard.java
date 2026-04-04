package com.paulograbin.sudoku;

public class SudokuBoard {

    private int[][] board;
    private int[][] candidates;

    public SudokuBoard(int[][] game) {
        board = game;
        candidates = new int[9][9];

        for (int j = 0; j < candidates.length; j++) {
            int[] candidate = candidates[j];
            Arrays.fill(candidate, 0b111111111);
        }

        printBoard();
    }

    public int getValueAt(int row, int column) {
        return board[row][column];
    }

    public int[] getRow(int row) {
        int[] rowElements = new int[9];

        System.arraycopy(board[row], 0, rowElements, 0, 9);

        return rowElements;
    }

    public int[] getColumn(int columnId) {
        int[] columnElements = new int[9];

        for(int i = 0; i < 9; i++)
            columnElements[i] = board[i][columnId];

        return columnElements;
    }

    public int[] getBlock(int row, int column) {
        int[] blockElements = new int[9];
        int count = 0;

        int initialX = getInitialX(row);
        int initialY = getInitialY(column);

        int finalX = initialX + 2;
        int finalY = initialY + 2;

        for(int i = initialX; i <= finalX; i++)
            for(int j = initialY; j <= finalY; j++) {
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
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setValueAt(Integer value, int cellRow, int cellColumn) {
        board[cellRow][cellColumn] = value;
    }
}
