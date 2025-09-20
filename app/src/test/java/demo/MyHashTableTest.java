package demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class MyHashTableTest {

    private MyHashTable table;

    @BeforeEach
    void setUp() {
        table = new MyHashTable(16); // min capacity is forced to 16
    }

    @Test
    void putAndGetSingleKey() {
        table.put("apple", 100);
        assertEquals(100, table.get("apple"));
    }

    @Test
    void getReturnsNullForMissingKey() {
        table.put("apple", 100);
        assertNull(table.get("banana"));
    }

    @Test
    void updateValueForExistingKey() {
        table.put("apple", 100);
        table.put("apple", 200);
        assertEquals(200, table.get("apple"));
    }

    @Test
    void multipleKeysStoredAndRetrievedIndependently() {
        table.put("apple", 1);
        table.put("banana", 2);
        table.put("cherry", 3);

        assertEquals(1, table.get("apple"));
        assertEquals(2, table.get("banana"));
        assertEquals(3, table.get("cherry"));
    }

    @Test
    void collision_twoKeysSameBucket_bothRetrievable() {
        String[] pair = findSameIndexPair(table, 4); // two keys with same indexFor()
        table.put(pair[0], "first");
        table.put(pair[1], "second");

        assertEquals("first", table.get(pair[0]));
        assertEquals("second", table.get(pair[1]));
    }

    @Test
    void collision_chainOfKeys_allRetrievable() {
        var keys = findSameIndexKeys(table, 5, 5); // 5 keys colliding to same bucket
        for (int i = 0; i < keys.size(); i++) {
            table.put(keys.get(i), "v" + i);
        }
        for (int i = 0; i < keys.size(); i++) {
            assertEquals("v" + i, table.get(keys.get(i)));
        }
    }

    @Test
    void collision_updateOneKey_doesNotAffectOthers() {
        var keys = findSameIndexKeys(table, 3, 4);
        table.put(keys.get(0), "A");
        table.put(keys.get(1), "B");
        table.put(keys.get(2), "C");

        table.put(keys.get(1), "B2"); // update middle key

        assertEquals("A", table.get(keys.get(0)));
        assertEquals("B2", table.get(keys.get(1)));
        assertEquals("C", table.get(keys.get(2)));
    }

    @Test
    void collision_wrapAroundWorks() {
        int targetIdx = table._capacity - 1;
        var keys = findKeysForIndex(table, targetIdx, 2, 5);
        table.put(keys.get(0), "end");
        table.put(keys.get(1), "wrap"); // must probe to index 0

        assertEquals("end", table.get(keys.get(0)));
        assertEquals("wrap", table.get(keys.get(1)));
    }

    /* ------------------ Helpers to generate colliding keys ------------------ */

    private static String[] findSameIndexPair(MyHashTable tbl, int maxLen) {
        Map<Integer, String> seen = new HashMap<>();
        for (String s : generateStrings("abc01", maxLen)) {
            int idx = tbl.indexFor(s);
            String prev = seen.putIfAbsent(idx, s);
            if (prev != null && !prev.equals(s)) {
                return new String[]{prev, s};
            }
        }
        throw new IllegalStateException("Could not find a collision pair");
    }

    private static List<String> findSameIndexKeys(MyHashTable tbl, int needed, int maxLen) {
        List<String> out = new ArrayList<>();
        Integer targetIdx = null;
        for (String s : generateStrings("abc01", maxLen)) {
            int idx = tbl.indexFor(s);
            if (targetIdx == null) targetIdx = idx;
            if (idx == targetIdx) out.add(s);
            if (out.size() == needed) return out;
        }
        throw new IllegalStateException("Could not find enough colliding keys");
    }

    private static List<String> findKeysForIndex(MyHashTable tbl, int targetIdx, int needed, int maxLen) {
        List<String> out = new ArrayList<>();
        for (String s : generateStrings("xyzAB", maxLen)) {
            if (tbl.indexFor(s) == targetIdx) {
                out.add(s);
                if (out.size() == needed) return out;
            }
        }
        return out;
    }

    private static List<String> generateStrings(String alphabet, int maxLen) {
        List<String> out = new ArrayList<>();
        for (int len = 1; len <= maxLen; len++) {
            build("", len, alphabet, out);
        }
        return out;
    }

    private static void build(String prefix, int remaining, String alphabet, List<String> out) {
        if (remaining == 0) {
            out.add(prefix);
            return;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            build(prefix + alphabet.charAt(i), remaining - 1, alphabet, out);
        }
    }
}
