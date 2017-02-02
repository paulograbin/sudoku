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
}
