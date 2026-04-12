package com.paulograbin.sudoku;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CandidatesTest {

    public static final int VALUE_9_BITMASK = 0b100000000;
    public static final int VALUES_1_AND_9_BITMASK = 0b100000001;
    public static final int ALL_VALUES_BITMASK = 0b111111111;
    private static final int VALUE_1_BITMASK = 0b1;

    @Test
    void emptyBoard() {
        SudokuBoard board = new SudokuBoard(BoardRepository.EMPTY_BOARD);

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int candidates = board.getCandidates(row, column);
                int computedCandidates = board.getCandidates(row, column);
                assertThat(computedCandidates).isEqualTo(candidates);
                assertThat(candidates).isEqualTo(ALL_VALUES_BITMASK);
            }
        }
    }

    @Test
    void emptyBoard__afterStep() {
        SudokuBoard board = new SudokuBoard(BoardRepository.EMPTY_BOARD);

        board.setValueAt(1, 0, 0);
        int firstCellCandidates = getBothCandidates(board, 0, 0);
        CandidateValuesAssert.assertThat(firstCellCandidates).hasValueAlreadySet__noCandidates();
        board.printBoard();
//        board.printCandidatesText();

        int secondCell = getBothCandidates(board, 0, 1);
        CandidateValuesAssert.assertThat(secondCell).doesNotHaveCandidate(1);
//
        int closeCell = getBothCandidates(board, 1, 0);
        CandidateValuesAssert.assertThat(closeCell).doesNotHaveCandidate(1);

        IO.println(" *************************************************** ");
        IO.println(" *************************************************** ");
        IO.println(" *************************************************** ");
        IO.println(" *************************************************** ");

        board.setValueAt(2, 0, 1);
        int secondCellCandidates = getBothCandidates(board, 0, 1);
        board.printBoard();
//        board.printCandidatesText();

        IO.println(" *************************************************** ");
        IO.println(" *************************************************** ");
        IO.println(" *************************************************** ");
        IO.println(" *************************************************** ");


        board.setValueAt(3, 0, 2);
        int thirdCellCandidates = getBothCandidates(board, 0, 2);
        board.printBoard();
//        board.printCandidatesText();
    }

    private int getBothCandidates(SudokuBoard board, int row, int column) {
        int candidates = board.getCandidates(row, column);
        int computedCandidates = board.getCandidates(row, column);

        if (candidates != computedCandidates) {
            IO.println("Fetched candidate and computed candidated are different!!!!" + candidates + " and " + computedCandidates);
        }

        return candidates;
    }

    @Test
    void finishedBoard() {
        SudokuBoard board = new SudokuBoard(BoardRepository.EASY_FIRST_SOLUTION);

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int candidates = board.getCandidates(row, column);
                int computedCandidates = board.getCandidates(row, column);
                assertThat(computedCandidates).isEqualTo(candidates);

                CandidateValuesAssert.assertThat(candidates).hasValueAlreadySet__noCandidates();
            }
        }
    }

    @Test
    void testCellWithSingleCandidate() {
        int[][] game = new int[][]{
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 2, 3, 4, 5, 6, 7, 8, 1}, // 0
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
                {0, 0, 0, 0, 0, 0, 0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        int candidate = board.getCandidates(0, 0);
        int computedCandidates = board.getCandidates(0, 0);
        assertThat(candidate).isEqualTo(computedCandidates);

        CandidateValuesAssert.assertThat(candidate).hasOnlyOneCandidate();
        CandidateValuesAssert.assertThat(candidate).hasCandidate(9);
        CandidateValuesAssert.assertThat(candidate)
                .doesNotHaveCandidate(1)
                .doesNotHaveCandidate(2)
                .doesNotHaveCandidate(3)
                .doesNotHaveCandidate(4);
        assertThat(candidate).isEqualTo(VALUE_9_BITMASK);
    }

    @Test
    void anotherTestCellWithSingleCandidate() {
        int[][] game = new int[][]{
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {9, 2, 3, 4, 5, 6, 7, 8, 0}, // 0
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
                {0, 0, 0, 0, 0, 0, 0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        int candidate = board.getCandidates(0, 8);
        int computedCandidates = board.getCandidates(0, 8);
        assertThat(candidate).isEqualTo(computedCandidates);

        CandidateValuesAssert.assertThat(candidate).hasOnlyOneCandidate();
        CandidateValuesAssert.assertThat(candidate).hasCandidate(1);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(9)
                .doesNotHaveCandidate(2)
                .doesNotHaveCandidate(3)
                .doesNotHaveCandidate(4);
        assertThat(candidate).isEqualTo(VALUE_1_BITMASK);
    }

    @Test
    void testRowNoCandidates() {
        int[][] game = new int[][]{
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {9, 2, 3, 4, 5, 6, 7, 8, 1}, // 0
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
                {0, 0, 0, 0, 0, 0, 0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        for (int column = 0; column < 9; column++) {

            int candidate = board.getCandidates(0, column);
            int computedCandidates = board.getCandidates(0, column);
            assertThat(candidate).isEqualTo(computedCandidates);

            CandidateValuesAssert.assertThat(candidate).hasValueAlreadySet__noCandidates();
        }
    }

    @Test
    void testSimpleBlock() {
        int[][] game = new int[][]{
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {1, 2, 3, 0, 0, 0, 0, 0, 0}, // 0
                {4, 0, 6, 0, 0, 0, 0, 0, 0}, // 1
                {7, 8, 9, 0, 0, 0, 0, 0, 0}, // 2

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
                {0, 0, 0, 0, 0, 0, 0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);

        int candidate = board.getCandidates(1, 1);
        int computedCandidates = board.getCandidates(1, 1);
        assertThat(candidate).isEqualTo(computedCandidates);

        CandidateValuesAssert.assertThat(candidate).hasOnlyOneCandidate();
        CandidateValuesAssert.assertThat(candidate).hasCandidate(5);
    }

    @Test
    void testCellWithTwoCandidates() {
        int[][] game = new int[][]{
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 2, 3, 4, 5, 6, 7, 8, 0}, // 0
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5

                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
                {0, 0, 0, 0, 0, 0, 0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        int candidate = board.getCandidates(0, 0);
        int computedCandidates = board.getCandidates(0, 0);
        assertThat(candidate).isEqualTo(computedCandidates);

        CandidateValuesAssert.assertThat(candidate).hasCountOfCandidatesEqualTo(2);
        CandidateValuesAssert.assertThat(candidate).hasCandidate(9)
                .hasCandidate(1);
        assertThat(candidate).isEqualTo(VALUES_1_AND_9_BITMASK);
    }
}