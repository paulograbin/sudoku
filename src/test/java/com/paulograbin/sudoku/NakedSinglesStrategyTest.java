package com.paulograbin.sudoku;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.paulograbin.sudoku.BoardRepository.*;
import static org.assertj.core.api.Assertions.assertThat;


public class NakedSinglesStrategyTest {

    private final SolvingStrategy strategy = new NakedSinglesStrategy();


    @Test
    void easy() {
        SudokuBoard board = new SudokuBoard(EASY_GAME);

        while (!board.isSolved()) {
            boolean apply = strategy.apply(board);

            if (!apply) {
                Assertions.fail("This strategy was not able to solve the board");
            }
        }

        assertThat(board.isSolved()).isTrue();
    }

    @Test
    void hard() {
        SudokuBoard board = new SudokuBoard(HARD_GAME);

        boolean keepGoing = true;

        while (keepGoing) {
            keepGoing = strategy.apply(board);
        }

        board.printBoard();
        assertThat(board.isSolved()).isFalse();
    }

    @Test
    void nightmare() {
        SudokuBoard board = new SudokuBoard(NIGHTMARE_GAME);

        boolean keepGoing = true;

        while (keepGoing) {
            keepGoing = strategy.apply(board);
        }

        board.printBoard();
        assertThat(board.isSolved()).isFalse();
    }
}
