package com.paulograbin.sudoku;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.paulograbin.sudoku.BoardRepository.makeEasyBoard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SudokuBoardTest {

    @Test
    void eliminateCandidate() {
        SudokuBoard sudokuBoard = new SudokuBoard(makeEasyBoard());

        int valueAt = sudokuBoard.getValueAt(0, 2);
        assertThat(valueAt).isEqualTo(7);

        int candidates = sudokuBoard.getCandidates(0, 2);
        assertThat(candidates).isEqualTo(0);
    }

    @Nested
    class TestEliminateCandidates {

        @Test
        void eliminateThreeCandidates() {
            SudokuBoard sudokuBoard = new SudokuBoard(makeEasyBoard());

            int valueAt = sudokuBoard.getValueAt(5, 4);
            assertThat(valueAt).isEqualTo(0);

            assertThat(sudokuBoard.getCandidates(5, 4)).isEqualTo(261);
            CandidateValuesAssert.assertThat(sudokuBoard.getCandidates(5, 4)).hasCountOfCandidatesEqualTo(3);

            sudokuBoard.eliminateCandidate(5, 4, 3);
            CandidateValuesAssert.assertThat(sudokuBoard.getCandidates(5, 4)).hasCountOfCandidatesEqualTo(2);

            sudokuBoard.eliminateCandidate(5, 4, 1);
            CandidateValuesAssert.assertThat(sudokuBoard.getCandidates(5, 4)).hasCountOfCandidatesEqualTo(1);

            sudokuBoard.eliminateCandidate(5, 4, 9);
            assertThat(sudokuBoard.getCandidates(5, 4)).isEqualTo(0);
            CandidateValuesAssert.assertThat(sudokuBoard.getCandidates(5, 4)).hasCountOfCandidatesEqualTo(0);
        }
    }
}
