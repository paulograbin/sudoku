package com.paulograbin.sudoku;

public class NakedPairsStrategy implements SolvingStrategy {

    @Override
    public boolean apply(SudokuBoard board) {
        System.out.println("Applying strategy: " + this.getClass().getSimpleName());

        return false;
    }
}
