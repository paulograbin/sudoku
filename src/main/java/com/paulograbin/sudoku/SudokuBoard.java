package com.paulograbin.sudoku;

public class SudokuBoard {

    private int[][] board;

    public void setBoard(int[][] board) {
        this.board = board;
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
        switch (column) {
            case 0:
            case 1:
            case 2:
                return 0;

            case 3:
            case 4:
            case 5:
                return 3;

            case 6:
            case 7:
            case 8:
                return 6;

        }

        return 0;
    }

    private int getInitialX(int row) {
        switch (row) {
            case 0:
            case 1:
            case 2:
                return 0;

            case 3:
            case 4:
            case 5:
                return 3;

            case 6:
            case 7:
            case 8:
                return 6;

        }

        return 0;
    }
}
