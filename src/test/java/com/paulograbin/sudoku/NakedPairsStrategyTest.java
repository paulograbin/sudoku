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

        board.printCandidatesBoard();
    }

    @Test
    void easyGame__columns__oneStep() {
        SudokuBoard board = new SudokuBoard(makeEasyBoard());
        int[] candidateColumnBefore = board.getCandidateColumn(3);
        System.out.println(Arrays.toString(candidateColumnBefore));

        for (int i = 0; i < candidateColumnBefore.length; i++) {
            int j = candidateColumnBefore[i];
            if (j > 0)
                System.out.print(i + ": "+ CandidateHelper.makeCandidateString(j).trim() + ", ");
        }

        Assertions.assertThat(candidateColumnBefore).containsExactly(0, 130, 0, 0, 258, 258, 0, 160, 0);

        boolean apply = strategy.apply(board);

        int[] candidateColumn = board.getCandidateColumn(3);
        for (int i = 0; i < candidateColumn.length; i++) {
            int j = candidateColumn[i];
            if (j > 0)
                System.out.print(i + ": "+ CandidateHelper.makeCandidateString(j).trim() + ", ");
        }

        Assertions.assertThat(candidateColumn).containsExactly(0b000000000, 0b010000000, 0b000000000, 0b000000000, 0b100000010, 0b100000010, 0b000000000, 0b010100000, 0b000000000);

        Assertions.assertThat(apply).isTrue();
    }


    @Test
    @Disabled
    void easy() {
        SudokuBoard board = new SudokuBoard(makeEasyBoard());

        while (!board.isSolved()) {
            strategy.apply(board);
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
    @Disabled
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
