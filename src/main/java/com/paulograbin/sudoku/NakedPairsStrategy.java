package com.paulograbin.sudoku;

public class NakedPairsStrategy implements SolvingStrategy {

    @Override
    public boolean apply(SudokuBoard board) {
        System.out.println("Applying strategy: " + this.getClass().getSimpleName());

        boolean madeProgress = false;

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
                                    madeProgress = true;
                                    System.out.printf("Eliminating value %s from cell %s/%s \n", firstvalue, row, k);
                                }

                                if (board.containsCandidate(row, k, secondValue)) {
                                    board.eliminateCandidate(row, k, secondValue);
                                    madeProgress = true;
                                    System.out.printf("Eliminating values %s from cell %s/%s \n", secondValue, row, k);
                                }


                            }
                        }
                    }
                }

//                System.out.println("New row");
            }
        }


        return madeProgress;
    }
}
