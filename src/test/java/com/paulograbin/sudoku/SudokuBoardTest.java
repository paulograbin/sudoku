package com.paulograbin.sudoku;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class SudokuBoardTest {

    SudokuBoard board;

    @BeforeMethod
    public void setUp() throws Exception {
        board = new SudokuBoard();

        int[][] game = new int[][] {
                {0, 7, 8,   0, 3, 0,   6, 0, 0},
                {0, 0, 3,   0, 9, 0,   7, 8, 0},
                {0, 9, 0,   1, 7, 0,   1, 0, 0},

                {3, 0, 6,   0, 8, 4,   0, 0, 0},
                {9, 4, 0,   1, 0, 0,   0, 6, 0},
                {0, 2, 0,   0, 6, 0,   3, 1, 0},

                {5, 0, 9,   6, 0, 0,   0, 7, 0},
                {4, 6, 0,   0, 0, 0,   8, 0, 3},
                {0, 0, 2,   3, 1, 0,   4, 0, 0}
        };

        board.setBoard(game);
    }

    @Test
    public void testGetElementAt00() throws Exception {
        assertEquals(board.getValueAt(0, 0), 0);
    }

    @Test
    public void testGetElementAt01() throws Exception {
        assertEquals(board.getValueAt(0, 1), 7);
    }

    @Test
    public void testGetElementAt02() throws Exception {
        assertEquals(board.getValueAt(0, 2), 8);
    }

    @Test
    public void testGetElementAt10() throws Exception {
        assertEquals(board.getValueAt(1, 0), 0);
    }

    @Test
    public void testGetElementAt11() throws Exception {
        assertEquals(board.getValueAt(1, 1), 0);
    }

    @Test
    public void testGetElementAt12() throws Exception {
        assertEquals(board.getValueAt(1, 2), 3);
    }

    @Test
    public void testGetElementAt20() throws Exception {
        assertEquals(board.getValueAt(2, 0), 0);
    }

    @Test
    public void testGetElementAt21() throws Exception {
        assertEquals(board.getValueAt(2, 1), 9);
    }

    @Test
    public void testGetElementAt22() throws Exception {
        assertEquals(board.getValueAt(2, 2), 0);
    }
}