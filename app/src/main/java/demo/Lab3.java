package demo;

import java.util.Arrays;

public class Lab3 {

    /**
     * Finds the minimum number of hours per day required to finish all homework
     * within the given number of days using binary search.
     *
     * The method searches for the smallest daily work hours such that the
     * total days needed to complete all homework does not exceed the limit.
     *
     * @param homeworks an array where each element represents the hours needed
     *                  to complete one homework assignment
     * @param days      the maximum number of days allowed to finish all homework
     *                  (must be at least as many as the number of homework items)
     * @return the minimum number of hours per day needed to complete all homework
     *
     *     
     */

    int limit;
    public int minWorkingDays(int[] homeworks, int days) {
        limit = days;
        int left = 1;
        int right = 0;

        // find the homework that takes the most time.
        for (int h : homeworks) {
            right = Math.max(right, h);
        }

        // you fill in here:

        return left;
    }

    public boolean check(int k, int[] homeworks) {
        int days = 0;
        for (int h : homeworks) {
            days += (h + k - 1) / k; // get the ceiling
        }

        return days <= limit;
    }

    /**
     * Performs a recursive binary search on a sorted integer array.
     *
     * @param arr    the sorted array to search
     * @param target the value to find
     * @return the index of the target if found, otherwise -1
     *
     *         The method works by repeatedly dividing the search range in half:
     *         - If the middle element matches the target, return its index.
     *         - If the target is smaller, search the left half.
     *         - If the target is larger, search the right half.
     */

    public int binarySearchRecursive(int[] arr, int target) {
        return search(arr, target, 0, arr.length - 1);
    }

    private int search(int[] arr, int target, int left, int right) {
        if (left > right) {
            // base case: target not found
            // you fill in here
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            // found it
            // you fill in here
        }

        if (arr[mid] > target) {
            // search left half
            // you fill in here
        } else {
            // search right half
            // you fill in here
        }
        // IMPORTANT!!! remove the placeholder statement below !!!
        return -1;
    }

    // Simple tester
    public static void main(String[] args) {
        Lab3 lab = new Lab3();

        System.out.println("=== Quick tests for minWorkingDays ===");
        System.out.println(Arrays.toString(new int[] { 5 }) + ", days=1 -> " +
                lab.minWorkingDays(new int[] { 5 }, 1) + " (expected 5)");
        System.out.println(Arrays.toString(new int[] { 5, 7, 8, 9 }) + ", days=5 -> " +
                lab.minWorkingDays(new int[] { 5, 7, 8, 9 }, 5) + " (expected 8)");
        System.out.println(Arrays.toString(new int[] { 1, 1, 1, 100 }) + ", days=10 -> " +
                lab.minWorkingDays(new int[] { 1, 1, 1, 100 }, 10) + " (expected 15)");

        System.out.println("\n=== Quick tests for binarySearchRecursive ===");
        System.out.println(Arrays.toString(new int[] { 1, 3, 5, 7 }) + ", target=5 -> " +
                lab.binarySearchRecursive(new int[] { 1, 3, 5, 7 }, 5) + " (expected 2)");
        System.out.println(Arrays.toString(new int[] { -10, -5, 0, 5, 10 }) + ", target=0 -> " +
                lab.binarySearchRecursive(new int[] { -10, -5, 0, 5, 10 }, 0) + " (expected 2)");
        System.out.println(Arrays.toString(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }) + ", target=11 -> " +
                lab.binarySearchRecursive(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 11) + " (expected -1)");
    }
}
