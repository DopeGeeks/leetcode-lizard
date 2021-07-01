package daohuei.leetcodelizard;

import java.util.Arrays;

/*
 * 198. House Robber
 * Link: https://leetcode.com/problems/house-robber/
 */
public class HouseRubber {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n): will go through each store once
     * @space: O(n): recur to the n level
     */
    public int recursion(int[] nums) {
        int[] map = new int[nums.length + 1];
        Arrays.fill(map, -1);
        return robHelpler(nums, nums.length, map);
    }

    private int robHelpler(int[] nums, int n, int[] map) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        // last index for the answer, and if answer already got
        if (map[n] != -1) {
            return map[n];
        }
        // n-1 is the current store (length of n)
        // we can choose not to rob: so we see the accumulated max of previous
        // store(length of n-1)
        // or steal this store, so we check current store + max of n-2
        int res = Math.max(robHelpler(nums, n - 2, map) + nums[n - 1], robHelpler(nums, n - 1, map));
        map[n] = res;
        return res;
    }

    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): will go through each store once
     * @space: O(1): not using any
     */
    public int dp(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }

        int pre = 0; // for n - 2
        int cur = nums[0]; // for n - 1
        for (int i = 2; i <= n; i++) {
            int temp = cur;
            // get the max of n-2 and current(i-1), and cur(n-1)
            // and cur will be update
            cur = Math.max(pre + nums[i - 1], cur);
            // update n-2
            pre = temp;
        }
        return cur;
    }
}