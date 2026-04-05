package com.paulograbin.sudoku;

import java.util.List;

public class SudokuSolver {

    private final SudokuBoard board;
    private final List<SolvingStrategy> strategies = List.of(
            new NakedSinglesStrategy(),
            new HiddenSinglesStrategy()
    );

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public void solve() {
        while (!isSolved()) {
            System.out.println("Ready to work!");
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

    public boolean isSolved() {
        return board.isSolved();
    }
}
