package com.paulograbin.sudoku;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuBoard_getElementTest {

    private SudokuBoard board;

    @BeforeEach
    public void setUp() {
        int[][] game = new int[][] {
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 7, 8,   0, 3, 0,   6, 0, 0}, // 0
                {0, 0, 3,   0, 9, 0,   7, 8, 0}, // 1
                {0, 9, 0,   1, 7, 0,   1, 0, 0}, // 2

                {3, 0, 6,   0, 8, 4,   0, 0, 0}, // 3
                {9, 4, 0,   1, 0, 0,   0, 6, 0}, // 4
                {0, 2, 0,   0, 6, 0,   3, 1, 0}, // 5

                {5, 0, 9,   6, 0, 0,   0, 7, 0}, // 6
                {4, 6, 0,   0, 0, 0,   8, 0, 3}, // 7
                {0, 0, 2,   3, 1, 0,   4, 0, 0}  // 8
        };

        board = new SudokuBoard(game);
    }

    @Test
    public void testGetElementAt03() {
        assertEquals(0, board.getValueAt(0, 3));
    }

    @Test
    public void testGetElementAt00() {
        assertEquals(0, board.getValueAt(0, 0));
    }

    @Test
    public void testGetElementAt01() {
        assertEquals(7, board.getValueAt(0, 1));
    }

    @Test
    public void testGetElementAt02() {
        assertEquals(8, board.getValueAt(0, 2));
    }

    @Test
    public void testGetElementAt10() {
        assertEquals(0, board.getValueAt(1, 0));
    }

    @Test
    public void testGetElementAt11() {
        assertEquals(0, board.getValueAt(1, 1));
    }

    @Test
    public void testGetElementAt12() {
        assertEquals(3, board.getValueAt(1, 2));
    }

    @Test
    public void testGetElementAt20() {
        assertEquals(0, board.getValueAt(2, 0));
    }

    @Test
    public void testGetElementAt21() {
        assertEquals(9, board.getValueAt(2, 1));
    }

    @Test
    public void testGetElementAt22() {
        assertEquals(0, board.getValueAt(2, 2));
    }
}
