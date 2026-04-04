package com.paulograbin.sudoku;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class SodokuSolverIntegrationTests {

    @Test
    @Disabled
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
        board.printCandidates();
        var solver = new SudokuSolver(board);
        solver.solve();

        Assertions.assertThat(board.isSolved()).isTrue();
    }

    @Test
    @Disabled
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
        board.printCandidates();
        var solver = new SudokuSolver(board);
        solver.solve();

        Assertions.assertThat(board.isSolved()).isTrue();
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
        int[][] game = new int[][] {
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 0, 7,   5, 0, 0,   6, 0, 3}, // 0
                {4, 3, 0,   0, 0, 6,   0, 0, 5}, // 1
                {6, 0, 8,   1, 0, 9,   0, 2, 7}, // 2

                {2, 0, 6,   4, 5, 0,   0, 0, 0}, // 3
                {0, 0, 1,   0, 6, 0,   3, 4, 0}, // 4
                {7, 0, 0,   0, 0, 8,   0, 5, 0}, // 5

                {8, 0, 0,   7, 0, 0,   1, 3, 0}, // 6
                {0, 7, 4,   0, 2, 0,   5, 9, 0}, // 7
                {1, 0, 9,   3, 0, 5,   0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        var solver = new SudokuSolver(board);
        solver.solve();

        Assertions.assertThat(board.isSolved()).isTrue();
    }

    @Test
    public void hard() {
        int[][] game = new int[][] {
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 0, 2,   0, 0, 0,   0, 0, 0}, // 0
                {0, 9, 8,   1, 0, 0,   7, 3, 4}, // 1
                {0, 0, 0,   5, 0, 0,   2, 0, 9}, // 2

                {0, 0, 0,   0, 0, 7,   3, 4, 0}, // 3
                {0, 2, 7,   4, 1, 0,   5, 0, 0}, // 4
                {0, 8, 0,   0, 0, 4,   0, 0, 0}, // 5

                {0, 7, 0,   3, 4, 5,   0, 2, 0}, // 6
                {0, 4, 9,   7, 0, 6,   0, 0, 1}, // 7
                {0, 0, 0,   0, 0, 0,   0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        var solver = new SudokuSolver(board);
        solver.solve();

        Assertions.assertThat(board.isSolved()).isFalse();
    }

    @Test
    public void nightmare() {
        int[][] game = new int[][] {
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 0, 0,   5, 9, 6,   0, 0, 0}, // 0
                {7, 0, 0,   3, 0, 0,   0, 0, 0}, // 1
                {3, 2, 0,   8, 0, 0,   0, 0, 0}, // 2

                {0, 0, 0,   0, 0, 1,   3, 0, 0}, // 3
                {4, 0, 1,   0, 0, 0,   0, 0, 2}, // 4
                {0, 0, 0,   0, 6, 0,   0, 0, 8}, // 5

                {0, 0, 3,   0, 0, 0,   5, 9, 0}, // 6
                {0, 5, 0,   0, 0, 8,   6, 0, 0}, // 7
                {0, 9, 0,   0, 0, 0,   0, 0, 0}  // 8
        };

        SudokuBoard board = new SudokuBoard(game);
        var solver = new SudokuSolver(board);
        solver.solve();

        Assertions.assertThat(board.isSolved()).isFalse();
    }
}
