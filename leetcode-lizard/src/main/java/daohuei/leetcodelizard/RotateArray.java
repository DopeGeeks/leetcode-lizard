package daohuei.leetcodelizard;

/*
 * 189. Rotate Array
 * Link: https://leetcode.com/problems/rotate-array/
 */
public class RotateArray {
    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(kn): no explain
     * @space: O(1): not using any
     */
    public void brutalForce(int[] nums, int k) {
        int n = nums.length;
        // get the remainder
        k = k % n;
        for (int i = 0; i < k; i++) {
            // take out the last number
            int temp = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                // move every number backward
                nums[j] = nums[j - 1];
            }
            // put last number to the first
            nums[0] = temp;
        }
        // do it k times
    }

    /**
     * @author: daohuei
     * @description: directly move the number to the target, and move the target to
     *               the next target, until it reach the start point
     * @time: O(n): no explain
     * @space: O(1): not using any
     */
    public void loopMethod(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) {
            return;
        }
        int count = 0;
        int start = 0;
        int current = start;
        int pre = nums[current];
        while (true) {
            do {
                // the target position
                int next = (current + k) % n;
                // take out old number
                int temp = nums[next];
                // put new number
                nums[next] = pre;
                // make old number as next moving target
                pre = temp;
                // current move forward
                current = next;
                // count one
                count++;
                // if count reach the length, done
                if (count == n) {
                    return;
                }
            } while (start != current);
            // if start is back to the current
            // start add one as next start point
            start++;
            current = start;
            pre = nums[current];
        }
    }

    /**
     * @author: daohuei
     * @description: reverse method
     * @time: O(n): since reverse need n/2, so O(n)
     * @space: O(1): not using any
     */
    public void reverseMethod(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // reverse all
        reverse(nums, 0, n - 1);
        // reverse start to target point
        reverse(nums, 0, k - 1);
        // reverse target to the end
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            // reversing, n/2 steps
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}