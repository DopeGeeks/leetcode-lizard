package daohuei.leetcodelizard;

import java.util.Random;

/*
 * 384. Shuffle an Array
 * Link: https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleAnArray {
    /**
     * @author: daohuei
     * @description: Knuth's shuffle
     * @time: O(n): for shuffling
     * @space: O(n): for nums
     */
    class Solution {

        private int[] nums;
        private Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            // just return original nums
            return nums;
        }

        /** Returns a random shuffling of the array. */
        // Knuth shuffle:
        // https://yjk94.wordpress.com/2017/03/17/%E6%B4%97%E7%89%8C%E7%9A%84%E6%AD%A3%E7%A1%AE%E5%A7%BF%E5%8A%BF-knuth-shuffle%E7%AE%97%E6%B3%95/
        // we have to make sure every possibility have same probability appears
        public int[] shuffle() {
            // empty case
            if (nums == null)
                return null;
            // copy the nums
            int[] a = nums.clone();
            for (int j = 1; j < a.length; j++) {
                int i = random.nextInt(j + 1);
                swap(a, i, j);
            }
            return a;
        }

        private void swap(int[] a, int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
}
