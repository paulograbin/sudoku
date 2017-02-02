package com.paulograbin.sudoku;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;


public class SudokuBoard_getRowTest {

    SudokuBoard board;

    @BeforeMethod
    public void setUp() throws Exception {
        board = new SudokuBoard();

        int[][] game = new int[][] {
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 0, 2,   0, 0, 0,   4, 6, 5}, // 0
                {8, 0, 4,   5, 0, 1,   0, 2, 0}, // 1
                {0, 3, 5,   6, 0, 4,   0, 0, 0}, // 2

                {5, 0, 6,   0, 9, 7,   0, 0, 0}, // 3
                {1, 0, 8,   3, 0, 6,   7, 0, 0}, // 4
                {0, 0, 0,   0, 0, 2,   6, 5, 1}, // 5

                {0, 0, 0,   0, 6, 0,   0, 7, 2}, // 6
                {0, 5, 0,   0, 0, 0,   0, 0, 8}, // 7
                {2, 0, 3,   4, 0, 5,   9, 7, 0}  // 8
        };

        board.setBoard(game);
    }



    @Test
    public void testGetLine2() throws Exception {
        int[] rowElements = board.getRow(2);

        List<Integer> row = fetchIntegerFromIntArray(rowElements);
        System.out.println(row);

        assertHasElements(row, 3, 5, 6, 4);
    }

    @Test
    public void testGetLine1() throws Exception {
        int[] rowElements = board.getRow(1);

        List<Integer> row = fetchIntegerFromIntArray(rowElements);
        System.out.println(row);

        assertHasElements(row, 8, 4, 5, 1, 2);
    }

    @Test
    public void testGetLine0() throws Exception {
        int[] rowElements = board.getRow(0);

        List<Integer> row = fetchIntegerFromIntArray(rowElements);
        System.out.println(row);

        assertHasElements(row, 2, 4, 5, 6);
    }

    private void assertHasElements(List<Integer> row, int ... args) {
        for(int i : args) {
            assertTrue(row.contains(new Integer(i)));
        }
    }

    private List<Integer> fetchIntegerFromIntArray(int[] rowElements) {
        List<Integer> elements = new ArrayList<Integer>();

        for(int i : rowElements) {
            if(i > 0)
                elements.add(new Integer(i));
        }

        return elements;
    }
}