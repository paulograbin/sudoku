package com.paulograbin.sudoku;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class SudokuBoard_getBlockElementsTest {

    private SudokuBoard board;
    private final List<Integer> POSSIBILITIES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

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
    public void testGetBlockOfCell66() throws Exception {
        int[] blockElements = board.getBlock(6, 6);

        List<Integer> block = fetchIntegerFromIntArray(blockElements);
        System.out.println(block);

        assertHasOnlyThisElements(block, 2, 7, 8, 9);
    }


    @Test
    public void testGetBlockOfCell33() throws Exception {
        int[] blockElements = board.getBlock(3, 3);

        List<Integer> block = fetchIntegerFromIntArray(blockElements);
        System.out.println(block);

        assertHasOnlyThisElements(block, 2, 3, 6, 7, 9);
    }

    @Test
    public void testGetBlockOfCell44() throws Exception {
        int[] blockElements = board.getBlock(4, 4);

        List<Integer> block = fetchIntegerFromIntArray(blockElements);
        System.out.println(block);

        assertHasOnlyThisElements(block, 2, 3, 6, 7, 9);
    }


    @Test
    public void testGetBlockOfCell55() throws Exception {
        int[] blockElements = board.getBlock(5, 5);

        List<Integer> block = fetchIntegerFromIntArray(blockElements);
        System.out.println(block);

        assertHasOnlyThisElements(block, 2, 3, 6, 7, 9);
    }




    @Test
    public void testGetBlockOfCell00() throws Exception {
        int[] blockElements = board.getBlock(0, 0);

        List<Integer> block = fetchIntegerFromIntArray(blockElements);
        System.out.println(block);

        assertHasOnlyThisElements(block, 2, 3, 5, 4, 8);
    }

    @Test
    public void testGetBlockOfCell11() throws Exception {
        int[] blockElements = board.getBlock(1, 1);

        List<Integer> block = fetchIntegerFromIntArray(blockElements);
        System.out.println(block);

        assertHasOnlyThisElements(block, 2, 3, 5, 4, 8);
    }

    @Test
    public void testGetBlockOfCell22() throws Exception {
        int[] blockElements = board.getBlock(2, 2);

        List<Integer> block = fetchIntegerFromIntArray(blockElements);
        System.out.println(block);

        assertHasOnlyThisElements(block, 2, 3, 5, 4, 8);
    }

    private void assertHasOnlyThisElements(List<Integer> row, int ... args) {
        List<Integer> localPossibilities = new ArrayList<>();
        localPossibilities.addAll(POSSIBILITIES);

        for(int i : args) {
            assertTrue(row.contains(i));
            localPossibilities.remove(new Integer(i));

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