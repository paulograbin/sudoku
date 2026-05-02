package com.paulograbin.sudoku;

import static com.paulograbin.sudoku.CandidateHelper.makeCandidateString;

public class NakedPairsStrategy implements SolvingStrategy {


    private SudokuBoard board;

    @Override
    public boolean apply(SudokuBoard board) {
        this.board = board;

        System.out.println("Applying strategy: " + this.getClass().getSimpleName());

        boolean rowMadeProgress = false;

        System.out.println("Doing rows...");

        for (int row = 0; row < 9; row++) { // each row
//            System.out.println("Working on row " + row);

            for (int i = 0; i < 8; i++) { // start with left most cell, then compare with the next one
                for (int j = i + 1; j < 9; j++) { // fetch the next ones
                    int candidatesForFirstCell = board.getCandidates(row, i);
                    int candidatesForSecondCell = board.getCandidates(row, j);


                    if (Integer.bitCount(candidatesForFirstCell) == 2 && Integer.bitCount(candidatesForSecondCell) == 2 && candidatesForFirstCell == candidatesForSecondCell) {
                        System.out.printf("Found the same two candidates %s at At %d/%d and %d/%d%n", Integer.toBinaryString(candidatesForFirstCell), row, i, row, j);

                        int firstvalue = Integer.numberOfTrailingZeros(candidatesForFirstCell) + 1;
                        candidatesForFirstCell &= candidatesForFirstCell - 1;
                        int secondValue = Integer.numberOfTrailingZeros(candidatesForFirstCell) + 1;

                        for (int k = 0; k < 9; k++) {
                            if (k != i && k != j) {
                                if (board.getValueAt(row, k) != 0) {
                                    continue;
                                }

                                if (board.containsCandidate(row, k, firstvalue)) {
                                    board.eliminateCandidate(row, k, firstvalue);
                                    rowMadeProgress = true;
                                    System.out.printf("Eliminating value %s from cell %s/%s \n", firstvalue, row, k);
                                }

                                if (board.containsCandidate(row, k, secondValue)) {
                                    board.eliminateCandidate(row, k, secondValue);
                                    rowMadeProgress = true;
                                    System.out.printf("Eliminating values %s from cell %s/%s \n", secondValue, row, k);
                                }
                            }
                        }
                    }
                }

//                System.out.println("New row");
            }
        }

        var columnMadeProgress = workOnColumns();


        return rowMadeProgress || columnMadeProgress;
    }

    private boolean workOnColumns() {
        boolean madeProgress = false;

        System.out.println("Doing columns...");

        for (int column = 0; column < 9; column++) { // each column
            for (int i = 0; i < 8; i++) { // start with left most cell, then compare with the next one
                for (int j = i + 1; j < 9; j++) { // fetch the next ones

                    int candidatesForFirstCell = board.getCandidates(i, column);
                    int candidatesForSecondCell = board.getCandidates(j, column);

                    if (Integer.bitCount(candidatesForFirstCell) == 2 && Integer.bitCount(candidatesForSecondCell) == 2 && candidatesForFirstCell == candidatesForSecondCell) {
                        System.out.printf("Found the same two candidates %s at At %d/%d and %d/%d%n", makeCandidateString(candidatesForFirstCell), i, column, j, column);

                        int aux = candidatesForFirstCell;

                        int firstvalue = Integer.numberOfTrailingZeros(aux) + 1;
                        aux &= aux - 1;
                        int secondValue = Integer.numberOfTrailingZeros(aux) + 1;

                        System.out.println("Now we check every other cell than these two, and remove those candidates from them");
                        for (int k = 0; k < 9; k++) {

                            if (k != i && k != j) {
                                if (board.getValueAt(k, column) != 0) {
                                    System.out.printf("Cell is %d / %d is already set with value %d, skipping it \n", k, column, board.getValueAt(k, column));
                                    continue;
                                }

                                if (!board.containsCandidate(k, column, firstvalue) && !board.containsCandidate(k, column, secondValue)) {
                                    System.out.printf("Cell is %d / %d does not have the candidate we're looking for (%s), move to next \n", k, column, CandidateHelper.makeCandidateString(board.getCandidates(k, column)));
                                    continue;
                                }

                                if (board.containsCandidate(k, column, firstvalue)) {
                                    System.out.printf("Cell is %d / %d has candidate (%s) value %s, removing it \n", k, column, CandidateHelper.makeCandidateString(board.getCandidates(k, column)), firstvalue);

                                    board.eliminateCandidate(k, column, firstvalue);
                                    madeProgress = true;

                                }

                                if (board.containsCandidate(k, column, secondValue)) {
                                    System.out.printf("Cell is %d / %d has candidate (%s) value %s, removing it \n", k, column, CandidateHelper.makeCandidateString(board.getCandidates(k, column)), secondValue);

                                    board.eliminateCandidate(k, column, secondValue);
                                    madeProgress = true;
                                }

                            } else {
                                System.out.printf("Skipping cell is %d / %d because it has a naked pair \n", k, column);
                            }
                        }
                    }
                }
            }

            System.out.println();
        }

        return madeProgress;
    }
}
