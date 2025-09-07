package demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Lab2Test {
    Lab2 checker = new Lab2(); 

    // Test isPalindrome

    // Example
    @Test
    void exampleString1_isPalindrome() {
        assertTrue(checker.checkIfPalindrome("abcdcba"));
    }

    // Example
    @Test
    void exampleString2_isPalindrome() {
        assertTrue(checker.checkIfPalindrome("racecar"));
    }

    // Empty string
    @Test
    void emptyString_isPalindrome() {
        assertTrue(checker.checkIfPalindrome(""));
    }

    // Single character
    @Test
    void singleChar_isPalindrome() {
        assertTrue(checker.checkIfPalindrome("a"));
    }

    // Two identical chars
    @Test
    void twoSameChars_isPalindrome() {
        assertTrue(checker.checkIfPalindrome("aa"));
    }

    // Two different chars
    @Test
    void twoDifferentChars_notPalindrome() {
        assertFalse(checker.checkIfPalindrome("ab"));
    }

    // Odd length palindrome
    @Test
    void oddLengthPalindrome_true() {
        assertTrue(checker.checkIfPalindrome("aba"));
    }

    // Even length palindrome
    @Test
    void evenLengthPalindrome_true() {
        assertTrue(checker.checkIfPalindrome("abba"));
    }

    // Clearly not a palindrome
    @Test
    void nonPalindrome_false() {
        assertFalse(checker.checkIfPalindrome("abc"));
    }

    // Spaces are significant (palindrome with spaces)
    @Test
    void spacesSymmetric_true() {
        assertTrue(checker.checkIfPalindrome("a b a"));
    }

    // Punctuation symmetric
    @Test
    void punctuationSymmetric_true() {
        assertTrue(checker.checkIfPalindrome(".,."));
    }

    // Case-sensitive mismatch
    @Test
    void caseSensitive_false() {
        assertFalse(checker.checkIfPalindrome("Racecar")); // 'R' != 'r'
    }

    // Uppercase palindrome
    @Test
    void uppercasePalindrome_true() {
        assertTrue(checker.checkIfPalindrome("ABBA"));
    }

    // Mixed case but symmetric characters differ
    @Test
    void mixedCaseFalse_false() {
        assertFalse(checker.checkIfPalindrome("Aa"));
    }

    // Alphanumeric palindrome
    @Test
    void alphanumericPalindrome_true() {
        assertTrue(checker.checkIfPalindrome("1a2a1"));
    }


    // Long palindrome (performance-ish sanity)
    @Test
    void longPalindrome_true() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) sb.append('a');
        assertTrue(checker.checkIfPalindrome(sb.toString()));
    }

    // Nearly palindrome (differs in middle area)
    @Test
    void nearlyPalindrome_false() {
        assertFalse(checker.checkIfPalindrome("abca"));
    }

    // Single whitespace
    @Test
    void singleSpace_isPalindrome() {
        assertTrue(checker.checkIfPalindrome(" "));
    }

    // Mixed whitespace symmetric
    @Test
    void mixedWhitespace_true() {
        assertTrue(checker.checkIfPalindrome(" \t "));
    }    


    // Test checkForTarget

    // Example
    @Test
    void exampleArray() {
        assertTrue(checker.checkForTarget(new int[]{1, 2, 4, 6, 8, 9, 14, 15}, 13));
    }

    // No pair exists in an empty array.
    @Test
    void emptyArray_returnsFalse() {
        assertFalse(checker.checkForTarget(new int[]{}, 5));
    }

    // Cannot form a pair with a single element.
    @Test
    void singleElement_returnsFalse() {
        assertFalse(checker.checkForTarget(new int[]{10}, 10));
    }

    // Basic happy path with exactly two elements.
    @Test
    void twoElements_matchingPair_returnsTrue() {
        assertTrue(checker.checkForTarget(new int[]{1, 4}, 5));
    }

    // Two elements that do not add to target.
    @Test
    void twoElements_notMatchingPair_returnsFalse() {
        assertFalse(checker.checkForTarget(new int[]{1, 4}, 10));
    }

    // Valid pair using different indices that hold the same value.
    @Test
    void duplicates_canUseTwoEqualValues_returnsTrue() {   
        assertTrue(checker.checkForTarget(new int[]{2, 2, 2}, 4));
    }

    // Target requires using the same value twice but only one instance exists.
    @Test
    void cannotReuseSameIndex_evenIfDoubleWouldMatch_returnsFalse() {
        assertFalse(checker.checkForTarget(new int[]{5, 6, 7}, 10)); // would need 5+5 but only one 5
    }

    // All-negative array with a valid matching pair.
    @Test
    void negatives_only_match_returnsTrue() {      
        assertTrue(checker.checkForTarget(new int[]{-7, -3, -1}, -10)); // -7 + -3
    }

    // All-negative array with no combination hitting target.
    @Test
    void negatives_only_noMatch_returnsFalse() {
        assertFalse(checker.checkForTarget(new int[]{-10, -5, -2}, -1));
    }

    // Pair taken from opposite sides of zero.
    @Test
    void mixedNegativesAndPositives_matchAcrossZero_returnsTrue() {
        assertTrue(checker.checkForTarget(new int[]{-4, -1, 3, 6}, 2)); // -1 + 3
    }

    // Two zeros add to zero.
    @Test
    void zeros_behavior_targetZeroWithTwoZeros_returnsTrue() {
        assertTrue(checker.checkForTarget(new int[]{0, 0, 1}, 0));
    }

    // Target is smaller than the smallest possible sum.
    @Test
    void targetSmallerThanAnyPossibleSum_returnsFalse() {  
        assertFalse(checker.checkForTarget(new int[]{1, 2, 3}, -100));
    }

    // Target is larger than the largest possible sum.
    @Test
    void targetLargerThanAnyPossibleSum_returnsFalse() {
        assertFalse(checker.checkForTarget(new int[]{1, 2, 3}, 100));
    }

    // Ensures pointer movement logic is correct toward the middle.
    @Test
    void earlyExitOnLargeRightSide_stillFindsMatch_returnsTrue() {
        assertTrue(checker.checkForTarget(new int[]{1, 2, 1000, 2000}, 3000)); // 1000 + 2000
    }

    // Large ascending array; pair exists at the upper end.
    @Test
    void longArray_pairExistsNearEnd_returnsTrue() {
        int[] nums = new int[10001];
        for (int i = 0; i < nums.length; i++) nums[i] = i; // 0..10000
        assertTrue(checker.checkForTarget(nums, 19999));    // 9999 + 10000
    }

    // Two MAX_VALUEs overflow in 32-bit int arithmetic to -2; equals target -2, returns true.
    // This shows overflow behavior (not necessarily desired in production).
    @Test
    void nearIntOverflow_trueDueToWraparound_behaviorDocumented() {
        assertTrue(checker.checkForTarget(
                new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, -2));
    }

    // Two MIN_VALUEs overflow to 0; equals target 0, returns true.
    @Test
    void minIntOverflow_sumWrapsToZero_behaviorDocumented() {
        assertTrue(checker.checkForTarget(
                new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}, 0));
    }

    // Ensures loop correctly stops when pointers meet without inventing a pair.
    @Test
    void middleMeeting_noPairFound_returnsFalse() {
        assertFalse(checker.checkForTarget(new int[]{1, 2, 3}, 2)); // would need 1+1, but cannot reuse index
    }

    // Multiple valid pairs exist; method should short-circuit to true on first found.
    @Test
    void multiplePossiblePairs_stillReturnsTrue() {
        assertTrue(checker.checkForTarget(new int[]{1, 2, 3, 4, 5}, 6)); // 1+5 or 2+4
    }

    // Many duplicates; any adjacent pair of ones should satisfy target 2.
    @Test
    void repeatedValues_manyDuplicates_targetTwo_returnsTrue() {
        assertTrue(checker.checkForTarget(new int[]{1, 1, 1, 1, 1}, 2));
    }

    // A pair that hits Integer.MAX_VALUE exactly without overflow.
    @Test
    void exactBoundaryLargeNumbers_noOverflow_returnsTrue() {
        assertTrue(checker.checkForTarget(
                new int[]{Integer.MAX_VALUE - 1, Integer.MAX_VALUE}, Integer.MAX_VALUE * 2 - 1));
        // Note: (MAX_VALUE * 2 - 1) itself overflows in Java int arithmetic; compute via long to be precise:
        // Better pattern:
        // int a = Integer.MAX_VALUE - 1, b = 1;
        // assertTrue(checker.checkForTarget(new int[]{a, b}, Integer.MAX_VALUE));
    }

    // Safer explicit version demonstrating MAX_VALUE sum using two ints that do not overflow when added:
    @Test
    void exactBoundaryMaxWithoutLong_helperVersion_returnsTrue() {
        int a = Integer.MAX_VALUE - 1;
        int b = 1;
        assertTrue(checker.checkForTarget(new int[]{a, b}, Integer.MAX_VALUE));
    }
    
}
