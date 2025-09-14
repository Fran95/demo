package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Lab3Test {

    private final Lab3 sut = new Lab3();

    @DisplayName("minWorkingDays: parameterized cases")
    @ParameterizedTest(name = "[{index}] days={1}, homeworks={0} -> expected {2}")
    @MethodSource("cases")
    void testMinWorkingDays(int[] homeworks, int days, int expected) {
        assertEquals(expected, sut.minWorkingDays(homeworks, days),
            () -> "homeworks=" + Arrays.toString(homeworks) +
                  ", days=" + days + ", expected=" + expected);
    }

    static Stream<Arguments> cases() {
        return Stream.of(
            // Single / tiny
            Arguments.of(new int[]{5}, 1, 5),
            Arguments.of(new int[]{20}, 5, 4),
            Arguments.of(new int[]{20}, 20, 1),

            // Small arrays
            Arguments.of(new int[]{3, 7}, 2, 7),
            Arguments.of(new int[]{5, 7, 8, 9}, 5, 8),
            Arguments.of(new int[]{2, 2, 2, 2}, 6, 2),
            Arguments.of(new int[]{6, 6, 6, 6, 6}, 7, 6),

            // Patterns / sequences
            Arguments.of(new int[]{1,2,3,4,5,6,7,8,9}, 9, 9),
            Arguments.of(new int[]{9,1,9,1,9,1}, 6, 9),
            Arguments.of(new int[]{9,9,9,9}, 8, 5),
            Arguments.of(new int[]{8,8,8}, 4, 8),

            // Outliers / long tail
            Arguments.of(new int[]{1,1,1,100}, 10, 15),
            Arguments.of(new int[]{100,1,1,1,1,1}, 15, 10),
            Arguments.of(new int[]{1,100}, 10, 12),

            // Mixed sets
            Arguments.of(new int[]{4,15,6}, 7, 4),
            Arguments.of(new int[]{7,3,11,2}, 6, 6),
            Arguments.of(new int[]{2,3,5,7,11,13,17,19}, 12, 10),
            Arguments.of(new int[]{2,30,4,60}, 12, 10),
            Arguments.of(new int[]{12,5,7,3}, 8, 4),
            Arguments.of(new int[]{12,5,7,3}, 5, 7),

            // Many small
            Arguments.of(new int[]{1,1,1,1,1,1,1,1,1,1}, 12, 1),
            Arguments.of(new int[]{2,2,2,2,2}, 5, 2),
            Arguments.of(new int[]{2,2,2,2,2}, 10, 1),
            Arguments.of(new int[]{4,4,4,4,4,4,4,4}, 8, 4),
            Arguments.of(new int[]{4,4,4,4,4,4,4,4}, 16, 2),

            // Large values
            Arguments.of(new int[]{40,40}, 6, 14),
            Arguments.of(new int[]{100,100,100,100}, 20, 20),
            Arguments.of(new int[]{100,50,25,12}, 12, 17),

            // “n == days” and nearby
            Arguments.of(new int[]{3,3,3,3}, 4, 3),
            Arguments.of(new int[]{3,3,3,3}, 6, 3),

            // A few more varied
            Arguments.of(new int[]{10,10,10}, 6, 5),
            Arguments.of(new int[]{10,10,10}, 3, 10)
        );
    }

  
    // Tests for binarySearchRecursive
   
    @DisplayName("binarySearchRecursive: 20+ parameterized cases")
    @ParameterizedTest(name = "[{index}] target={1}, arr={0} -> expected index {2}")
    @MethodSource("casesForBinarySearch")
    void testBinarySearchRecursive(int[] arr, int target, int expectedIndex) {
        assertEquals(expectedIndex, sut.binarySearchRecursive(arr, target),
            () -> "arr=" + Arrays.toString(arr) +
                  ", target=" + target + ", expected=" + expectedIndex);
    }

    static Stream<Arguments> casesForBinarySearch() {
        return Stream.of(
            // single element
            Arguments.of(new int[]{1}, 1, 0),
            Arguments.of(new int[]{1}, 2, -1),

            // empty array
            Arguments.of(new int[]{}, 10, -1),

            // small arrays
            Arguments.of(new int[]{1, 3}, 1, 0),
            Arguments.of(new int[]{1, 3}, 3, 1),
            Arguments.of(new int[]{1, 3}, 2, -1),
            Arguments.of(new int[]{1, 3, 5}, 3, 1),
            Arguments.of(new int[]{1, 3, 5}, 6, -1),
            Arguments.of(new int[]{1, 3, 5}, 0, -1),

            // general presence (even/odd length)
            Arguments.of(new int[]{1, 3, 5, 7}, 1, 0),
            Arguments.of(new int[]{1, 3, 5, 7}, 7, 3),
            Arguments.of(new int[]{1, 3, 5, 7}, 5, 2),
            Arguments.of(new int[]{2, 4, 6, 8, 10}, 6, 2),

            // negatives and zero
            Arguments.of(new int[]{-10, -5, 0, 5, 10}, -10, 0),
            Arguments.of(new int[]{-10, -5, 0, 5, 10}, 0, 2),
            Arguments.of(new int[]{-10, -5, 0, 5, 10}, 7, -1),

            // duplicates
            Arguments.of(new int[]{1, 2, 2, 2, 3}, 2, 2),
            Arguments.of(new int[]{1, 1, 1, 1}, 1, 1),
            Arguments.of(new int[]{1, 1, 1, 1}, 2, -1),

            // larger array boundaries
            Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 10, 9),
            Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 1, 0),
            Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 11, -1),

            // extreme ints
            Arguments.of(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, Integer.MAX_VALUE, 4),
            Arguments.of(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, Integer.MIN_VALUE, 0)
        );
    }
}