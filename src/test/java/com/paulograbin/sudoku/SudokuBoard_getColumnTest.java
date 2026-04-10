package com.paulograbin.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SudokuBoard_getColumnTest {

    private SudokuBoard board;
    private final List<Integer> POSSIBILITIES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @BeforeEach
    public void setUp() {
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
    public void testGetColumn6() {
        var columnElements = board.getNumbersFromColumn(6);

        assertHasOnlyThisElements(columnElements, 4, 6, 7, 9);
    }

    @Test
    public void testGetColumn5() {
        var columnElements = board.getNumbersFromColumn(5);

        assertHasOnlyThisElements(columnElements, 1, 4, 7, 6, 2, 5);
    }

    @Test
    public void testGetColumn4() {
        var columnElements = board.getNumbersFromColumn(4);

        assertHasOnlyThisElements(columnElements, 6, 9);
    }

    @Test
    public void testGetColumn3() {
        var columnElements = board.getNumbersFromColumn(3);

        assertHasOnlyThisElements(columnElements, 5, 6, 3, 4);
    }

    @Test
    public void testGetColumn2() {
        var columnElements = board.getNumbersFromColumn(2);

        assertHasOnlyThisElements(columnElements, 2, 4, 5, 6, 8, 3);
    }

    @Test
    public void testGetColumn1() {
        var columnElements = board.getNumbersFromColumn(1);


        assertHasOnlyThisElements(columnElements, 3, 5);
    }

    @Test
    public void testGetColumn0() {
        var columnElements = board.getNumbersFromColumn(0);

        assertHasOnlyThisElements(columnElements, 8, 5, 1, 2);
    }

    private void assertHasOnlyThisElements(Set<Integer> column, int ... args) {
        var localPossibilities = new HashSet<>(POSSIBILITIES);

        for(int i : args) {
            assertTrue(column.contains(i));
            localPossibilities.remove(i);
        }

        for(Integer i : localPossibilities) {
            assertFalse(column.contains(i));
        }
    }
}
