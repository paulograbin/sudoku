package com.paulograbin.sudoku;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver {

    private SudokuBoard board;
    private final List<Integer> POSSIBILITIES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);



    public void setBoard(SudokuBoard board) {
        this.board = board;
    }

    public void solve() {
        int a = 9 * 9;
        int count = 0;

        while(true) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board.getValueAt(i, j) == 0) {
                        solveCell(i, j);
                        count = 0;
                    } else {
                        count = count + 1;
                    }
                }

                if(count >= a) {
                    System.out.println(" ================== ");
                    System.out.println(" SOLUTION FOUND ");
                    board.printBoard();
                    return;
                }
            }
//            System.out.println(" ================== ");
//            board.printBoard();
        }

    }

    private void solveCell(int cellRow, int cellColumn) {
        List<Integer> localPossibilities = new ArrayList<>();
        localPossibilities.addAll(POSSIBILITIES);

        int cellValue = board.getValueAt(cellRow, cellColumn);

        for(int i : board.getBlock(cellRow, cellColumn)) {
            if(i != 0)
                localPossibilities.remove(new Integer(i));
        }

        for(int i : board.getRow(cellRow)) {
            if(i != 0)
                localPossibilities.remove(new Integer(i));
        }

        for(int i : board.getColumn(cellColumn)) {
            if(i != 0)
                localPossibilities.remove(new Integer(i));
        }

        if(localPossibilities.size() == 1) {
            board.setValueAt(localPossibilities.get(0), cellRow, cellColumn);
        }
    }
}
