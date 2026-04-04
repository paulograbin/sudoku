package com.paulograbin.sudoku;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HiddenSinglesStrategyTest {

    private final HiddenSinglesStrategy strategy = new HiddenSinglesStrategy();

    @Test
    public void testHiddenSingleInRow() {
        // Row 0 is missing 1 and 4 at positions (0,0) and (0,3).
        // Column 3 already has a 1 at row 4, so 1 can only go in (0,0).
        int[][] game = new int[][]{
                {0, 2, 3, 0, 5, 6, 7, 8, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        SudokuBoard board = new SudokuBoard(game);
        boolean result = strategy.apply(board);

        assertTrue(result);
        assertEquals(1, board.getValueAt(0, 0));
        assertEquals(4, board.getValueAt(0, 3));
    }

    @Test
    public void testHiddenSingleInColumn() {
        // Column 0: value 9 can only go in one empty cell
        int[][] game = new int[][]{
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 0, 0, 0, 0, 0, 0, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        SudokuBoard board = new SudokuBoard(game);
        boolean result = strategy.apply(board);

        assertTrue(result);
        assertEquals(9, board.getValueAt(8, 0));
    }

    @Test
    public void testHiddenSingleInBlock() {
        // In block (0,0): value 9 can only go in cell (2,2)
        // because other empty cells in the block have 9 blocked by row/column
        int[][] game = new int[][]{
                {1, 2, 3, 0, 0, 0, 0, 0, 0},
                {4, 5, 6, 0, 0, 0, 0, 0, 0},
                {7, 8, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        SudokuBoard board = new SudokuBoard(game);
        boolean result = strategy.apply(board);

        assertTrue(result);
        assertEquals(9, board.getValueAt(2, 2));
    }

    @Test
    public void testNoProgressWhenNothingToSolve() {
        // Board where hidden singles can't make progress
        int[][] game = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        SudokuBoard board = new SudokuBoard(game);
        boolean result = strategy.apply(board);

        assertFalse(result);
    }
}
