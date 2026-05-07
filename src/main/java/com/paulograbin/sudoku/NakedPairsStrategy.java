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

//        for (int row = 0; row < 9; row++) { // each row
//            for (int comparisonRoot = 0; comparisonRoot < 8; comparisonRoot++) { // start with left most cell, then compare with the next one
//                for (int comparisonNext = comparisonRoot + 1; comparisonNext < 9; comparisonNext++) { // fetch the next ones
//                    int candidatesForFirstCell = board.getCandidates(row, comparisonRoot);
//                    int candidatesForSecondCell = board.getCandidates(row, comparisonNext);
//
//                    if (Integer.bitCount(candidatesForFirstCell) == 2 && Integer.bitCount(candidatesForSecondCell) == 2 && candidatesForFirstCell == candidatesForSecondCell) {
//                        System.out.printf("Found the same two candidates %s at At %d/%d and %d/%d%n", CandidateHelper.makeCandidateString(candidatesForFirstCell), row, comparisonRoot, row, comparisonNext);
//
//                        int firstvalue = Integer.numberOfTrailingZeros(candidatesForFirstCell) + 1;
//                        candidatesForFirstCell &= candidatesForFirstCell - 1;
//                        int secondValue = Integer.numberOfTrailingZeros(candidatesForFirstCell) + 1;
//
//                        System.out.println("Now we check every cell in the row other than these two, and remove those candidates from them");
//
//                        for (int otherComparison = 0; otherComparison < 9; otherComparison++) {
//                            if (otherComparison != comparisonRoot && otherComparison != comparisonNext) {
//                                if (board.getValueAt(row, otherComparison) != 0) {
//                                    System.out.printf("Cell is %d / %d is already set with value %d, skipping it \n", row, otherComparison, board.getValueAt(row, otherComparison));
//                                    continue;
//                                }
//
//                                if (board.containsCandidate(row, otherComparison, firstvalue)) {
//                                    System.out.printf("Cell is %d / %d has candidate (%s) value %s, removing it \n", row, otherComparison, CandidateHelper.makeCandidateString(board.getCandidates(row, otherComparison)), firstvalue);
//
//                                    board.eliminateCandidate(row, otherComparison, firstvalue);
//                                    rowMadeProgress = true;
//                                }
//
//                                if (board.containsCandidate(row, otherComparison, secondValue)) {
//                                    System.out.printf("Cell is %d / %d has candidate (%s) value %s, removing it \n", row, otherComparison, CandidateHelper.makeCandidateString(board.getCandidates(row, otherComparison)), secondValue);
//
//                                    board.eliminateCandidate(row, otherComparison, secondValue);
//                                    rowMadeProgress = true;
//                                }
//
//                                int candidates = board.getCandidates(row, otherComparison);
//                                if (CandidateHelper.doesCellHaveOnlyASingleCandidate(candidates)) {
//                                    System.out.println("Cell is left with a single candidate, let's set the value!!!!!!!!!!!!!");
//
//                                    int valueToSet = CandidateHelper.makeValueFromSingleCandidate(candidates);
//
//                                    board.setValueAt(valueToSet, row, otherComparison);
//                                }
//                            } else {
//                                System.out.printf("Skipping cell is %d / %d because it has a naked pair \n", row, otherComparison);
//                            }
//                        }
//
//                    }
//                }
//            }
//        }

//        var columnMadeProgress = workOnColumns();
        var columnMadeProgress = false;
//
        var blockProgress = workOnBlocks();

        return rowMadeProgress || columnMadeProgress || blockProgress;
    }

    private boolean workOnBlocks() {
        for (int block = 0; block < 9; block++) {
            System.out.println("Working on block " + block);
        }

        return false;
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

                        System.out.println("Now we check every cell in the column other than these two, and remove those candidates from them");
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

                                int candidates = board.getCandidates(k, column);
                                if (CandidateHelper.doesCellHaveOnlyASingleCandidate(candidates)) {
                                    System.out.println("Cell is left with a single candidate, let's set the value");

                                    int valueToSet = CandidateHelper.makeValueFromSingleCandidate(candidates);

                                    board.setValueAt(valueToSet, k, column);
                                }

                            } else {
                                System.out.printf("Skipping cell is %d / %d because it has a naked pair \n", k, column);
                            }
                        }
                    }
                }
            }
        }

        return madeProgress;
    }
}
