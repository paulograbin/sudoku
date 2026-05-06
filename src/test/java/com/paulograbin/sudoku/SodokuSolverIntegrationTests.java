package com.paulograbin.sudoku;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.paulograbin.sudoku.BoardRepository.*;
import static com.paulograbin.sudoku.SudokuBoardAssert.assertThat;


public class SodokuSolverIntegrationTests {

    @Test
    public void learning() {
        int[][] game = new int[][] {
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 2, 3,   0, 0, 0,   0, 0, 0}, // 0
                {6, 5, 4,   0, 0, 0,   0, 0, 0}, // 1
                {7, 8, 9,   0, 0, 0,   0, 0, 0}, // 2

                {0, 0, 0,   0, 0, 0,   0, 0, 0}, // 3
                {0, 0, 0,   0, 0, 0,   0, 0, 0}, // 4
                {0, 0, 0,   0, 0, 0,   0, 0, 0}, // 5

                {0, 0, 0,   0, 0, 0,   0, 0, 0}, // 6
                {0, 0, 0,   0, 0, 0,   0, 0, 0}, // 7
                {0, 0, 0,   0, 0, 0,   0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        board.onePrintToRuleThemAll();
        var solver = new SudokuSolver(board);
        solver.solve();

        assertThat(board).isNotSolved();
    }

    @Test
    public void solvedBoard() {
         int[][] game = new int[][] {
        //               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 1, 2,   9, 8, 3,   4, 6, 5}, // 0
                {8, 6, 4,   5, 7, 1,   3, 2, 9}, // 1
                {9, 3, 5,   6, 2, 4,   8, 1, 7}, // 2

                {5, 4, 6,   1, 9, 7,   2, 8, 3}, // 3
                {1, 2, 8,   3, 5, 6,   7, 9, 4}, // 4
                {3, 7, 9,   8, 4, 2,   6, 5, 1}, // 5

                {4, 9, 1,   7, 6, 8,   5, 3, 2}, // 6
                {6, 5, 7,   2, 3, 9,   1, 4, 8}, // 7
                {2, 8, 3,   4, 1, 5,   9, 7, 6}  // 8
            };


        SudokuBoard board = new SudokuBoard(game);
        var solver = new SudokuSolver(board);
        solver.solve();

        assertThat(board).isSolved();
        boardHasNoCandidates(board);
    }

    @Test
    public void testName() {
        int[][] game = new int[][] {
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 0, 2,   0, 0, 0,   4, 6, 5}, // 0
                {8, 0, 4,   5, 0, 1,   0, 2, 0}, // 1
                {0, 3, 5,   6, 0, 4,   0, 0, 0}, // 2

                {5, 0, 6,   0, 9, 7,   0, 0, 0}, // 3
                {1, 0, 8,   3, 0, 6,   7, 0, 0}, // 4
                {0, 0, 0,   0, 0, 2,   6, 5, 1}, // 5

                {0, 0, 0,   0, 6, 0,   0, 0, 2}, // 6
                {0, 5, 0,   0, 0, 0,   0, 0, 8}, // 7
                {2, 0, 3,   4, 0, 5,   9, 7, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        var solver = new SudokuSolver(board);
        solver.solve();

        Assertions.assertThat(board.isSolved()).isTrue();
    }

    @Test
    public void easy() {
        SudokuBoard board = new SudokuBoard(makeEasyBoard());
        var solver = new SudokuSolver(board);
        solver.solve();

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
    public void anotherEasy() {
        SudokuBoard board = new SudokuBoard(makeEasyGame__firstBoard());
        var solver = new SudokuSolver(board);
        solver.solve();

        assertThat(board).isNotSolved();
        board.onePrintToRuleThemAll();
    }

    @Test
    public void hard() {
        SudokuBoard board = new SudokuBoard(makeHardBoard());
        board.onePrintToRuleThemAll();

        var solver = new SudokuSolver(board);
        solver.solve();

        assertThat(board).isNotSolved();
    }

    @Test
    public void nightmare() {
        SudokuBoard board = new SudokuBoard(makeNightMareGame());
        var solver = new SudokuSolver(board);
        solver.solve();

        assertThat(board).isNotSolved();
    }
}
