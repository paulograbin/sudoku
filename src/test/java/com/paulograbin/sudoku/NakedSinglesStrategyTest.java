package com.paulograbin.sudoku;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.paulograbin.sudoku.BoardRepository.*;
import static com.paulograbin.sudoku.SudokuBoardAssert.assertThat;


public class NakedSinglesStrategyTest {

    private final SolvingStrategy strategy = new NakedSinglesStrategy();


    @Test
    void easy() {
        SudokuBoard board = new SudokuBoard(makeEasyBoard());

        while (!board.isSolved()) {
            boolean apply = strategy.apply(board);

            if (!apply) {
                Assertions.fail("This strategy was not able to solve the board");
            }
        }

        assertThat(board).isSolved();
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
    void hard() {
        SudokuBoard board = new SudokuBoard(makeHardBoard());

        boolean keepGoing = true;

        while (keepGoing) {
            keepGoing = strategy.apply(board);
        }

        board.printBoard();
        assertThat(board).isNotSolved();
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
