package daohuei.leetcodelizard;

/*
 * 167. Two Sum II - Input array is sorted
 * Link: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 */
public class TwoSumTwo {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n): n for length of numbers, and n is the worst case
     * @space: O(1): not using any
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        // search with 2 pointer
        while (i < j) {
            // if found
            if (numbers[i] + numbers[j] == target) {
                return new int[] { i + 1, j + 1 };
            } else if (numbers[i] + numbers[j] < target) {
                // too small
                // smaller one move forward
                i++;
            } else {
                // too large
                // larger one move backward
                j--;
            }
        }

        return new int[] { -1, -1 };
    }
}