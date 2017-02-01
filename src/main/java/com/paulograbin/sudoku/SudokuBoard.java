package com.paulograbin.sudoku;

public class SudokuBoard {

    int[][] board;

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getValueAt(int row, int column) {
        return board[row][column];
    }
}
