package com.paulograbin.sudoku;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CandidatesTest {

    public static final int VALUE_9_BITMASK = 0b100000000;
    public static final int VALUES_1_AND_9_BITMASK = 0b100000001;
    public static final int ALL_VALUES_BITMASK = 0b111111111;
    private static final int VALUE_1_BITMASK = 0b1;

    @Test
    void initialBoard() {
        int[][] game = new int[][]{
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
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

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int candidates = board.getCandidates(row, column);
                int computedCandidates = board.computeCandidates(row, column);
                assertThat(computedCandidates).isEqualTo(candidates);
                assertThat(candidates).isEqualTo(ALL_VALUES_BITMASK);
            }
        }
    }

    @Test
    void finishedBoard() {
        SudokuBoard board = new SudokuBoard(BoardRepository.EASY_FIRST_SOLUTION);

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int candidates = board.getCandidates(row, column);
                int computedCandidates = board.computeCandidates(row, column);
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
        int computedCandidates = board.computeCandidates(0, 0);
        assertThat(candidate).isEqualTo(computedCandidates);

        CandidateValuesAssert.assertThat(candidate).hasOnlyOneCandidate();
        CandidateValuesAssert.assertThat(candidate).hasCandidate(9);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(1);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(2);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(3);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(4);
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
        int computedCandidates = board.computeCandidates(0, 8);
        assertThat(candidate).isEqualTo(computedCandidates);

        CandidateValuesAssert.assertThat(candidate).hasOnlyOneCandidate();
        CandidateValuesAssert.assertThat(candidate).hasCandidate(1);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(9);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(2);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(3);
        CandidateValuesAssert.assertThat(candidate).doesNotHaveCandidate(4);
        assertThat(candidate).isEqualTo(VALUE_1_BITMASK);
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
        int computedCandidates = board.computeCandidates(0, 0);
        assertThat(candidate).isEqualTo(computedCandidates);

        CandidateValuesAssert.assertThat(candidate).hasCountOfCandidatesEqualTo(2);
        CandidateValuesAssert.assertThat(candidate).hasCandidate(9);
        CandidateValuesAssert.assertThat(candidate).hasCandidate(1);
        assertThat(candidate).isEqualTo(VALUES_1_AND_9_BITMASK);
    }

}