package daohuei.leetcodelizard;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 239. Sliding Window Maximum
 * Link: https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {
    /**
     * @author: daohuei
     * @description: deque method
     * @time: O(n): slide through the windows
     * @space: O(k): only storing at most k numbers
     */
    public int[] dequeMethod(int[] nums, int k) {
        // use deque data structure
        Deque<Integer> max = new ArrayDeque<>();
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        // for storing the result
        int result[] = new int[n - k + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            // if window start moving
            if (i >= k) {
                // check top in the max, if it is the last number getting out of window
                if (max.peekFirst() == nums[i - k]) {
                    // remove it
                    max.removeFirst();
                }
            }
            // clear all last numbers that is smaller than current number
            // this will remove all smaller number that added before
            while (!max.isEmpty() && nums[i] > max.peekLast()) {
                max.removeLast();
            }

            // put current number in the last
            max.addLast(nums[i]);
            // if the window if formed
            if (i >= k - 1) {
                result[index++] = max.peekFirst();
            }
        }
        return result;
    }
}