package demo;

public class Lab4 {

    /**
     * Returns the distance (number of steps) from a given starting index
     * to the next occurrence of the target value in the array.
     * 
     * The search wraps around the array if needed.
     * 
     * @param nums       the array of integers to search
     * @param startIndex the index from which to start the search
     * @param target     the integer value to search for
     * @return the number of steps from startIndex to the target value,
     *         or -1 if the target is not found in the array
     */

    public int distanceToTarget(int[] nums, int startIndex, int target) {
        // you fill in here







        return 0; 
    }

    /**
     * Computes the DJB2 hash of a given string.
     * 
     * DJB2 is a simple, fast hash function created by
     * Daniel J. Bernstein. It starts with an initial seed (5381) and, for
     * each character, multiplies the current hash value by 33 and adds the
     * character's Unicode value.
     *
     * The result is returned as a 32-bit signed integer (int).
     *
     * @param str the input string to hash
     * @return the hash value of the string, as a signed int
     */
    public int djb2(String str) {
        // you fill in here


        

        return 0;
    }

    // Simple tester
    public static void main(String[] args) {
        Lab4 lab = new Lab4();

        // Simple Tests for distanceToTarget
        int[] nums = { 4, 7, 9, 2, 7 };
        System.out.println(lab.distanceToTarget(nums, 2, 7)); // 2 (wraps around: from index 2 → 3 → 4)
        System.out.println(lab.distanceToTarget(nums, 4, 4)); // 1 (wraps: from index 4 → 0)
        System.out.println(lab.distanceToTarget(nums, 1, 2)); // 2 (index 1 → 2 → 3)
        System.out.println(lab.distanceToTarget(nums, 0, 5)); // -1 (target not found)

        // Simple Tests for kjb2 hashing
        System.out.println("Hash of \"hello\": " + lab.djb2("hello"));
        System.out.println("Hash of \"world\": " + lab.djb2("world"));
        System.out.println("Hash of \"\": " + lab.djb2("")); // empty string
        System.out.println("Hash of \"a\": " + lab.djb2("a"));
        System.out.println("Hash of \"abc\": " + lab.djb2("abc"));

        // example of collisions
        System.out.println("Hash of 'ab': " + lab.djb2("ab"));
        System.out.println("Hash of 'bA': " + lab.djb2("bA"));
        System.out.println("Hash of 'af': " + lab.djb2("af"));
        System.out.println("Hash of 'bE': " + lab.djb2("bE"));
    }
}