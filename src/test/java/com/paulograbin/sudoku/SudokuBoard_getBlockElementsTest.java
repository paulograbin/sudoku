package com.paulograbin.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


public class SudokuBoard_getBlockElementsTest {

    private SudokuBoard board;
    private final List<Integer> POSSIBILITIES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @BeforeEach
    void setUp() {
        int[][] game = new int[][]{
//               0, 1, 2,   3, 4, 5,   6, 7, 8
                {0, 0, 2, 0, 0, 0, 4, 6, 5}, // 0
                {8, 0, 4, 5, 0, 1, 0, 2, 0}, // 1
                {0, 3, 5, 6, 0, 4, 0, 0, 0}, // 2

                {5, 0, 6, 0, 9, 7, 0, 0, 0}, // 3
                {1, 0, 8, 3, 0, 6, 7, 0, 0}, // 4
                {0, 0, 0, 0, 0, 2, 6, 5, 1}, // 5

                {0, 0, 0, 0, 6, 0, 0, 7, 2}, // 6
                {0, 5, 0, 0, 0, 0, 0, 0, 8}, // 7
                {2, 0, 3, 4, 0, 5, 9, 7, 0}  // 8
        };

        board = new SudokuBoard(game);
    }

    @Test
    public void testGetBlockOfCell66() {
        var block = board.getNumbersFromBlock(6, 6);

        assertHasOnlyThisElements(block, 2, 7, 8, 9);
    }


    @Test
    public void testGetBlockOfCell33() {
        var block = board.getNumbersFromBlock(3, 3);

        assertHasOnlyThisElements(block, 2, 3, 6, 7, 9);
    }

    @Test
    public void testGetBlockOfCell44() {
        var blockElements = board.getNumbersFromBlock(4, 4);

        assertHasOnlyThisElements(blockElements, 2, 3, 6, 7, 9);
    }


    @Test
    public void testGetBlockOfCell55() {
        var blockElements = board.getNumbersFromBlock(5, 5);

        assertHasOnlyThisElements(blockElements, 2, 3, 6, 7, 9);
    }


    @Test
    public void testGetBlockOfCell00() {
        var blockElements = board.getNumbersFromBlock(0, 0);

        assertHasOnlyThisElements(blockElements, 2, 3, 5, 4, 8);
    }

    @Test
    public void testGetBlockOfCell11() {
        var blockElements = board.getNumbersFromBlock(1, 1);

        assertHasOnlyThisElements(blockElements, 2, 3, 5, 4, 8);
    }

    @Test
    public void testGetBlockOfCell22() {
        Set<Integer> numbersFromBlock = board.getNumbersFromBlock(2, 2);

        assertHasOnlyThisElements(numbersFromBlock, 2, 3, 5, 4, 8);
    }

    private void assertHasOnlyThisElements(Set<Integer> row, int... args) {
        var localPossibilities = new HashSet<>(POSSIBILITIES);

        for (int i : args) {
            assertThat(row.contains(i)).isTrue();
            localPossibilities.remove(i);
        }

        for (Integer i : localPossibilities) {
            assertThat(row.contains(i)).isFalse();
        }
    }
}