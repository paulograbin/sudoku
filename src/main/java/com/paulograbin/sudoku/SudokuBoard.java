package com.paulograbin.sudoku;

public class SudokuBoard {

    int[][] board;

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getValueAt(int row, int column) {
        return board[row][column];
    }

    public int[] getRow(int row) {
        int[] rowElements = new int[9];

        for(int i = 0; i < 9; i++)
            rowElements[i] = board[0][i];

        return rowElements;
    }
}
