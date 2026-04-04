package com.paulograbin.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SudokuBoard_getRowTest {

    private SudokuBoard board;
    private final List<Integer> POSSIBILITIES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @BeforeEach
    void setUp() {

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

        board = new SudokuBoard(game);
    }



    @Test
    public void testGetLine2() {
        int[] rowElements = board.getRow(2);

        List<Integer> row = fetchIntegerFromIntArray(rowElements);

        assertHasOnlyThisElements(row, 3, 5, 6, 4);
    }

    @Test
    public void testGetLine1() {
        int[] rowElements = board.getRow(1);

        List<Integer> row = fetchIntegerFromIntArray(rowElements);

        assertHasOnlyThisElements(row, 8, 4, 5, 1, 2);
    }

    @Test
    public void testGetLine0() {
        int[] rowElements = board.getRow(0);

        List<Integer> row = fetchIntegerFromIntArray(rowElements);
        

        assertHasOnlyThisElements(row, 2, 4, 5, 6);
    }

    private void assertHasOnlyThisElements(List<Integer> row, int ... args) {
        List<Integer> localPossibilities = new ArrayList<>(POSSIBILITIES);

        for(int i : args) {
            assertTrue(row.contains(i));
            localPossibilities.remove(Integer.valueOf(i));

        }

        for(Integer i : localPossibilities) {
            assertFalse(row.contains(i));
        }
    }

    private List<Integer> fetchIntegerFromIntArray(int[] rowElements) {
        List<Integer> elements = new ArrayList<>();

        for(int i : rowElements) {
            if(i > 0)
                elements.add(i);
        }

        return elements;
    }
}