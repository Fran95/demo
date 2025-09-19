package demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Lab4Test {

    private final Lab4 sut = new Lab4();

    /* ===================== distanceToTarget tests ===================== */
    @DisplayName("distanceToTarget: table-driven cases")
    @ParameterizedTest(name = "{index} — {0}")
    @CsvSource({
            // caseName, array, start, target, expected
            "'target at start',                           '{7,2,3,4}',       0,     7,      0",
            "'target in middle',                          '{5,1,9,3}',       0,     9,      2",
            "'target at end',                             '{1,2,3,4,9}',     0,     9,      4",
            "'wrap-around simple',                        '{4,5,6,7}',       3,     5,      2",
            "'not found',                                 '{1,2,3}',         0,     99,    -1",
            "'single element — found',                    '{42}',            0,     42,     0",
            "'single element — not found',                '{42}',            0,     7,     -1",
            "'multiple targets — pick nearest forward',   '{2,5,2,5,2}',     0,     5,      1",
            "'multiple targets only after wrap',          '{8,8,3,8}',       2,     8,      1",
            "'startIndex == length behaves like 0',       '{3,6,9}',         3,     9,      2",
            "'startIndex > length (modulo)',              '{10,20,30,40}',   10,    10,     2",
            "'zeros & negatives (hit zero)',              '{-3,0,-1,5}',     0,     0,      1",
            "'zeros & negatives (already on -1)',         '{-3,0,-1,5}',     2,    -1,      0",
            "'zeros & negatives (not found)',             '{-3,0,-1,5}',     1,     7,     -1",
            "'nearest before wrap with repeats',          '{1,2,1,2,1,2}',   3,     1,      1",
            "'start near end then wrap to first',         '{7,8,9,1}',       3,     8,      2",
            "'distance is steps, not abs index diff',     '{5,6,7,8}',       2,     6,      3",
            "'very large startIndex (modulo stress)',     '{2,4,6,8,10}',    123456,10,     3",
            "'empty array',                                '{}',              0,     5,     -1"
    })
    void distanceToTarget_tableDriven(
            String caseName,
            String arrayStr,
            int startIndex,
            int target,
            int expected) {
        int[] nums = parseArray(arrayStr);
        assertEquals(expected, sut.distanceToTarget(nums, startIndex, target), caseName);
    }

    /* ===================== Helpers ===================== */
    // Turns "{1,2,3}" or " { 1 , 2 , 3 } " into int[]{1,2,3}; "{}" -> new int[0]
    private static int[] parseArray(String s) {
        if (s == null)
            return null;
        s = s.trim();
        if (s.equals("{}"))
            return new int[0];
        s = s.replaceAll("[{}\\s]", "");
        if (s.isEmpty())
            return new int[0];
        String[] parts = s.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    /* ===================== djb2 tests ===================== */

    @Test
    void testEmptyString() {
        assertEquals(5381, sut.djb2(""), "Empty string should return initial hash");
    }

    @Test
    void testSingleCharacter() {
        int hashA = sut.djb2("a");
        int hashB = sut.djb2("b");
        assertNotEquals(hashA, hashB, "Different chars should give different hashes");
    }

    @Test
    void testSimpleWords() {
        assertEquals(sut.djb2("hello"), sut.djb2("hello"), "Hash should be deterministic");
        assertNotEquals(sut.djb2("hello"), sut.djb2("world"), "Different strings should not hash the same (usually)");
    }

    @Test
    void testCaseSensitivity() {
        int lower = sut.djb2("hello");
        int upper = sut.djb2("HELLO");
        assertNotEquals(lower, upper, "Hash should be case-sensitive");
    }

    @Test
    void testUnicodeCharacters() {
        int hash1 = sut.djb2("こんにちは"); // Japanese
        int hash2 = sut.djb2("你好"); // Chinese
        int hash3 = sut.djb2("안녕"); // Korean
        assertAll(
                () -> assertNotEquals(0, hash1, "Should compute a valid hash"),
                () -> assertNotEquals(0, hash2, "Should compute a valid hash"),
                () -> assertNotEquals(0, hash3, "Should compute a valid hash"));
    }

    
    @Test
    void testSpecialCharacters() {
        int hash1 = sut.djb2("!@#$%^&*()");
        int hash2 = sut.djb2("1234567890");
        assertNotEquals(hash1, hash2, "Special chars and numbers should hash differently");
    }

    @Test
    void testLongStringConsistency() {
        String str = "a".repeat(1000);
        int h1 = sut.djb2(str);
        int h2 = sut.djb2(str);
        assertEquals(h1, h2, "Long repeated string should hash consistently");
    }

    @Test
    void testPotentialCollisionStability() {
        // Not asserting they collide—just that each is stable
        String s1 = "Aa";
        String s2 = "BB";
        int h1 = sut.djb2(s1);
        int h2 = sut.djb2(s2);
        assertAll(
                () -> assertEquals(h1, sut.djb2(s1), "Hash of s1 is stable"),
                () -> assertEquals(h2, sut.djb2(s2), "Hash of s2 is stable"));
    }

}
