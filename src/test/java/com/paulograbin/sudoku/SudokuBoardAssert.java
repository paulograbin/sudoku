package com.paulograbin.sudoku;

import org.assertj.core.api.AbstractAssert;

public class SudokuBoardAssert extends AbstractAssert<SudokuBoardAssert, SudokuBoard> {

    protected SudokuBoardAssert(SudokuBoard actual) {
        super(actual, SudokuBoardAssert.class);
    }

    public static SudokuBoardAssert assertThat(SudokuBoard actual) {
        return new SudokuBoardAssert(actual);
    }

    public SudokuBoardAssert isSolved() {
        isNotNull();
        if (!actual().isSolved()) {
            failWithMessage("Expected board to be solved but it was not");
        }

        return this;
    }

    public SudokuBoardAssert isNotSolved() {
        isNotNull();
        if (actual().isSolved()) {
            failWithMessage("Expected board to be solved but it was not");
        }

        return this;
    }

    public SudokuBoardAssert hasCellValue(int row, int col, int value) {
        isNotNull();
        int actualValue = actual.getValueAt(row, col);
        if (actualValue != value) {
            failWithMessage("Expected cell (%d,%d) to be <%d> but was <%d>", row, col, value, actualValue);
        }
        return this;
    }
}
