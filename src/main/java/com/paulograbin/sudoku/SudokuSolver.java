package com.paulograbin.sudoku;

import java.util.List;

public class SudokuSolver {

    private SudokuBoard board;
    private final List<SolvingStrategy> strategies = List.of(
            new NakedSinglesStrategy(),
            new HiddenSinglesStrategy()
    );

    public void setBoard(SudokuBoard board) {
        this.board = board;
    }

    public void solve() {
        while (!isSolved()) {
            boolean madeProgress = false;

            for (SolvingStrategy strategy : strategies) {
                if (strategy.apply(board)) {
                    madeProgress = true;
                    break;
                }
            }

            if (!madeProgress) {
                System.out.println(" ================== ");
                System.out.println(" NO STRATEGY COULD MAKE PROGRESS ");
                board.printBoard();
                return;
            }
        }

        System.out.println(" ================== ");
        System.out.println(" SOLUTION FOUND ");
        board.printBoard();
    }

    private boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getValueAt(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
