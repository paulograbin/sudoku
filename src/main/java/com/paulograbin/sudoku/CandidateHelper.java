package com.paulograbin.sudoku;

import java.util.IllegalFormatCodePointException;

public class CandidateHelper {

    public static boolean doesCellHaveOnlyASingleCandidate(int bitmask) {
        return Integer.bitCount(bitmask) == 1;
    }


    public static int makeValueFromSingleCandidate(int bitmask) {
        if (!doesCellHaveOnlyASingleCandidate(bitmask)) {
            System.err.println("Bitmask has more than one candidate");
            return 0;
        }

        return Integer.numberOfTrailingZeros(bitmask) + 1;
    }

    public static boolean containsCandidate(int candidateMask, int value) {
        if (candidateMask == 0) {
            return false;
        }

        return (candidateMask & (1 << (value -1))) != 0;
    }

    public static String makeCandidateString(int candidateMask) {
        if (candidateMask == 0) {
            return "    .    ";
        }

        StringBuilder a = new StringBuilder(9);
        for (int i = 0; i < 9; i++) {
            if ((candidateMask & (1 << i)) != 0) {
                a.append(i + 1);
            } else {
                a.append('.');
            }
        }

        return a.toString();
    }

}
