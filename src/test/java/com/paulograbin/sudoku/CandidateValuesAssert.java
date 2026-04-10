package com.paulograbin.sudoku;

import org.assertj.core.api.AbstractAssert;

public class CandidateValuesAssert extends AbstractAssert<CandidateValuesAssert, Integer> {

    protected CandidateValuesAssert(Integer actual) {
        super(actual, CandidateValuesAssert.class);
    }

    public static CandidateValuesAssert assertThat(Integer actual) {
        return new CandidateValuesAssert(actual);
    }

    public CandidateValuesAssert isEqualToValue(Integer expected) {
        isNotNull();
        if (actual != expected.intValue()) {
            failWithMessage("Expected board to be solved but it was not");
        }

        return this;
    }

    public CandidateValuesAssert hasOnlyOneCandidate() {
        isNotNull();
        if (Integer.bitCount(actual) != 1) {
            failWithMessage("Expected cell to have a single candidate value, but it has " + Integer.bitCount(actual));
        }

        return this;
    }

    public CandidateValuesAssert hasValueAlreadySet__noCandidates() {
        isNotNull();
        if (Integer.bitCount(actual) != 0) {
            failWithMessage("Expected cell to have no candidates value, but it has " + Integer.bitCount(actual));
        }

        return this;
    }

    public CandidateValuesAssert hasCountOfCandidatesEqualTo(Integer expected) {
        isNotNull();
        if (Integer.bitCount(actual) != expected) {
            failWithMessage("Expected cell to have " + expected + " candidates, but it has " + Integer.bitCount(actual));
        }

        return this;
    }

    public CandidateValuesAssert hasCandidate(Integer expectedCandidate) {
        isNotNull();

        if ((actual & (1 << (expectedCandidate - 1))) != 0) {
            System.out.println("Cell (" + BinaryStringHelper.makeBinaryString(actual) + ") has " + expectedCandidate + " as candidate (" + BinaryStringHelper.makeBinaryString(actual) + ")");
        } else {
            failWithMessage("Expected cell to have candidate " + expectedCandidate + ", " + "but it has " + Integer.numberOfTrailingZeros(actual));
        }

        return this;
    }

    public CandidateValuesAssert doesNotHaveCandidate(Integer expectedCandidate) {
        isNotNull();

        if ((actual & (1 << (expectedCandidate - 1))) != 0) {
            failWithMessage("Expected cell to have NOT candidate " + expectedCandidate + ", " + "but it has " + Integer.numberOfTrailingZeros(actual));
        } else {
            System.out.println("Cell (" + BinaryStringHelper.makeBinaryString(actual) + ") do not have " + expectedCandidate + " as candidate (" + BinaryStringHelper.makeBinaryString(actual) + ")");
        }

        return this;
    }
}
