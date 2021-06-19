package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 42. Trapping Rain Water
 * Link: https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n^2): n = length of list
     * @space: O(1): not using any
     */
    public int iteration(int[] height) {
        int sum = 0;
        // looking for water position
        for (int i = 1; i < height.length - 1; i++) {
            // find the max left of current position
            int max_left = 0;
            // start with the previous of the current position
            for (int j = i - 1; j >= 0; j--) {
                // get the max left
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            // find the max right
            int max_right = 0;
            // start with the next position
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            // get the relatively small one
            int min = Math.min(max_left, max_right);
            // if min is also larger than current height
            // there will be water exist
            if (min > height[i]) {
                // reduce the height for water calculation
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * @author: daohuei
     * @description: dynamic programming
     * @time: O(n): n = length of list
     * @space: O(n): for storing max left and max right wall
     */
    public int dp(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        // get the all max left
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        // get the all max right
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        // same as iteration but using space rather than using for time
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * @author: daohuei
     * @description: 2 pointer
     * @time: O(n): n = length of list
     * @space: O(1): not using any
     */
    public int twoPointer(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        // 2 pointer, left and right
        int left = 1;
        int right = height.length - 2;
        // search from left the right
        for (int i = 1; i < height.length - 1; i++) {
            // if left smaller than right
            if (height[left - 1] < height[right + 1]) {
                // get the max left
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                // if current height below max min wall
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                // left move ahead
                left++;
                // if opposite
            } else {
                // get max right
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                // if current height below max min wall
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    /**
     * @author: daohuei
     * @description: stack
     * @time: O(n): n = length of list
     * @space: O(n): for stack
     */
    public int stackMethod(int[] height) {
        int sum = 0;
        // for storing height position
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            // if:
            // 1. not empty
            // 2. current height larger than first element of the stack
            // which means we are going to remove the closest shorter place
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                // pop the stack value => a potential water place's height
                int h = height[stack.peek()];
                stack.pop();
                // if no more element in stack, just break
                // indicates current is the highest one among all of previous positions
                if (stack.empty()) {
                    break;
                }
                // distance from the current to the previous wall
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            // push current into the stack
            stack.push(current);
            // move forward
            current++;
        }
        return sum;
    }
}