package com.paulograbin.sudoku;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.paulograbin.sudoku.BoardRepository.*;
import static com.paulograbin.sudoku.SudokuBoardAssert.assertThat;

class NakedPairsStrategyTest {


    private final SolvingStrategy strategy = new NakedPairsStrategy();


    @Test
    void easyGame__oneStep() {
        SudokuBoard board = new SudokuBoard(makeEasyBoard());
        int[] candidateRowBefore = board.getCandidateRow(5);
        System.out.println(Arrays.toString(candidateRowBefore));

        Assertions.assertThat(candidateRowBefore).containsExactly(0, 264, 4, 258, 261, 0, 258, 0, 291);

        boolean apply = strategy.apply(board);

        int[] candidateRow = board.getCandidateRow(5);

        System.out.println(Arrays.toString(candidateRow));

        Assertions.assertThat(candidateRow).containsExactly(0b000000000, 0b000001000, 0b000000100, 0b100000010, 5, 0b000000000, 0b100000010, 0b000000000, 0b000100001);

        Assertions.assertThat(apply).isTrue();

        strategy.apply(board);
    }


    @Test
    @Disabled
    void easy() {
        SudokuBoard board = new SudokuBoard(makeEasyBoard());

        while (!board.isSolved()) {
            boolean apply = strategy.apply(board);

            if (!apply) {
                Assertions.fail("This strategy was not able to solve the board");
            }
        }

        assertThat(board).isNotSolved();
        boardHasNoCandidates(board);
    }

    private void boardHasNoCandidates(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getCandidates(i, j) != 0) {
                    Assertions.fail("Should have no candidates");
                }
            }
        }
    }

    @Test
    @Disabled
    void hard() {
        SudokuBoard board = new SudokuBoard(makeHardBoard());

        boolean keepGoing = true;

        while (keepGoing) {
            keepGoing = strategy.apply(board);
        }

        board.printBoard();
        assertThat(board).isSolved();
    }

    @Test
    void nightmare() {
        SudokuBoard board = new SudokuBoard(makeNightMareGame());

        boolean keepGoing = true;

        while (keepGoing) {
            keepGoing = strategy.apply(board);
        }

        board.printBoard();
        assertThat(board).isNotSolved();
    }
}
