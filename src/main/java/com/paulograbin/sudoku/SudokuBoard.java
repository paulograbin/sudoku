package com.paulograbin.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

     To track the candidates for a given cell we use a bitmask like 111111111.
     Initially the candidate for every cell starts with value 111111111, meaning that all values from 1 to 9 are still valid candidates.

     A bit set means that value N+1 is a candidate, for instance:
        Bit 0 represents the value 1,,
        Bit 1 represents the value 2,
        ...
        Bit 8 represents the value 9.

        Thus, a bitmask 000000001 means that only the value 1 is possible on a given cell, and so
        100000000 means that only value 9 is possible
        100000001 means that possible vlaues are 1 and 9
        100010001 means that possible vlaues are 1, 5 and 9
        110010001 means that possible vlaues are 1, 5, 8 and 9

        To count how manu candidates are present we use Integer.bitCount()
        To remove a value from the candidates we use = ~(1 << ( vvalue -1 ) )

        We can also represent the bitmasks as int, so for instance:
        0b111111111 is equal to 511
        0b000000001 is equal to 1

      When a single bit is set in the bitmask we know for sure there is only one possible value for a cell,
      so we use Integer.numberOfTrailingZeros(candidates) + 1 to get the decimal value.
 */
public class SudokuBoard {

    private final int[][] board;
    private final int[][] candidates;

    public static final List<Integer> POSSIBLE_VALUES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);


    int getCandidates(int row, int column) {
        return candidates[row][column];
    }

    void eliminateCandidate(int row, int column, int value) {
        candidates[row][column] &= ~(1 << (value - 1));
    }

    public void setValueAt(Integer value, int row, int column) {
        IO.println("Set value " + value + " at row " + row + " column " + column);

        board[row][column] = value;
        candidates[row][column] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                computeCandidatesInternal(i, j);
            }
        }

//        printCandidates();
    }

    public SudokuBoard(int[][] game) {
        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            board[i] = game[i].clone();
        }
        candidates = new int[9][9];

        for (int[] candidate : candidates) {
            Arrays.fill(candidate, 0b111111111);
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                computeCandidatesInternal(row, col);
            }
        }

//        printBoard();
//        printCandidates();
    }

    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (getValueAt(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getValueAt(int row, int column) {
        return board[row][column];
    }

    private void computeCandidatesInternal(int row, int column) {
        if (board[row][column] != 0) {
            candidates[row][column] = 0;
            return;
        }

        int newCandidates = candidates[row][column];

        Set<Integer> alreadyFilledNumbers = new HashSet<>(getNumbersFromBlock(row, column));
        alreadyFilledNumbers.addAll(getNumbersFromRow(row));
        alreadyFilledNumbers.addAll(getNumbersFromColumn(column));

        for (int v : alreadyFilledNumbers) {
            newCandidates &= ~(1 << (v - 1));
        }

        candidates[row][column] = newCandidates;
    }

    public Set<Integer> getNumbersFromRow(int row) {
        return Arrays.stream(board[row])
                .filter(i -> i > 0)
                .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }

    public Set<Integer> getNumbersFromColumn(int columnId) {
        Set<Integer> numbersInColumn = new HashSet<>(9);

        for (int i = 0; i < 9; i++) {
            if (board[i][columnId] > 0) {
                numbersInColumn.add(board[i][columnId]);
            }
        }

        return numbersInColumn;
    }

    public Set<Integer> getNumbersFromBlock(int row, int column) {
        int initialX = getInitialX(row);
        int initialY = getInitialY(column);

        int finalX = initialX + 2;
        int finalY = initialY + 2;

        Set<Integer> numbersInBlock = new HashSet<>(9);

        for (int i = initialX; i <= finalX; i++)
            for (int j = initialY; j <= finalY; j++) {

                if (board[i][j] > 0) {
                    numbersInBlock.add(board[i][j]);
                }
            }

        return numbersInBlock;
    }

    private int getInitialY(int column) {
        return getInitialX(column);
    }

    private int getInitialX(int row) {
        return switch (row) {
            case 0, 1, 2 -> 0;
            case 3, 4, 5 -> 3;
            case 6, 7, 8 -> 6;
            default -> 0;
        };
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i > 0) {
                System.out.println("------+-------+------");
            }

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }

                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printCandidatesBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i > 0) {
                System.out.println("------+-------+------");
            }

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }

                System.out.print(candidates[i][j] == 0 ? ". " : Long.toBinaryString(candidates[i][j]) + "/" + (Integer.bitCount(candidates[i][j]) + " "));
            }
            System.out.println();
        }
    }

    public void printCandidatesText() {
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                var text = String.format("Cell [%s][%s] has bitmask %s, and %s candidates", i, j, Long.toBinaryString(candidates[i][j]), Integer.bitCount(candidates[i][j]));

                IO.println(text);
            }
        }
    }
}
