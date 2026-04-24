package com.paulograbin.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.paulograbin.sudoku.CandidatesTest.VALUES_1_AND_9_BITMASK;
import static com.paulograbin.sudoku.CandidatesTest.VALUE_9_BITMASK;
import static org.assertj.core.api.Assertions.assertThat;

class CandidateHelperTest {

    @Nested
    class SingleCandidate {
        @Test
        void one() {
            int candidate = 0b000000001;

            int i = CandidateHelper.makeValueFromSingleCandidate(candidate);

            assertThat(i).isEqualTo(1);
        }

        @Test
        void two() {
            int candidate = 0b000000010;

            int i = CandidateHelper.makeValueFromSingleCandidate(candidate);

            assertThat(i).isEqualTo(2);
        }

        @Test
        void four() {
            int candidate = 0b000001000;

            int i = CandidateHelper.makeValueFromSingleCandidate(candidate);

            assertThat(i).isEqualTo(4);
        }

        @Test
        void nine() {
            int i = CandidateHelper.makeValueFromSingleCandidate(VALUE_9_BITMASK);

            assertThat(i).isEqualTo(9);
        }


        @Test
        void twoCandidates() {
            int i = CandidateHelper.makeValueFromSingleCandidate(VALUES_1_AND_9_BITMASK);

            assertThat(i).isEqualTo(0);
        }
    }


    @Test
    void name() {
        assertThat(CandidateHelper.containsCandidate(0, 0)).isFalse();
    }

    @Test
    void name2() {
        assertThat(CandidateHelper.containsCandidate(511, 1)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 2)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 3)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 4)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 5)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 6)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 7)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 8)).isTrue();
        assertThat(CandidateHelper.containsCandidate(511, 9)).isTrue();
    }

    @Test
    void name3() {
        assertThat(CandidateHelper.containsCandidate(1, 1)).isTrue();
        assertThat(CandidateHelper.containsCandidate(1, 2)).isFalse();
        assertThat(CandidateHelper.containsCandidate(1, 3)).isFalse();
    }


    @Nested
    class StringOperations {

        @Test
        void name() {
            int candidates = 511;

            String result = CandidateHelper.makeCandidateString(candidates);
            Assertions.assertEquals("123456789", result);
        }

        @Test
        void name2() {
            int candidates = 1;

            String result = CandidateHelper.makeCandidateString(candidates);
            Assertions.assertEquals("1........", result);
        }

        @Test
        void name3() {
            int candidates = 8;

            String result = CandidateHelper.makeCandidateString(candidates);
            Assertions.assertEquals("...4.....", result);
        }

        @Test
        void name4() {
            int candidates = 0;

            String result = CandidateHelper.makeCandidateString(candidates);
            Assertions.assertEquals("    .    ", result);
        }
    }
}
