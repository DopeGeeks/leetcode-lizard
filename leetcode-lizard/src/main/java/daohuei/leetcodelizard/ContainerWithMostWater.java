package daohuei.leetcodelizard;

/*
 * Question: Container With Most Water
 * Description: Given n non-negative integers a1, a2, ..., an , 
 * where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). 
 * Find two lines, which, together with the x-axis forms a container, 
 * such that the container contains the most water.
 * 
 * Notice that you may not slant the container.
 * 
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
 * In this case, the max area of water (blue section) the container can contain is 49.
 * 
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 * 
 * Example 3:
 * Input: height = [4,3,2,1,4]
 * Output: 16
 * 
 * Example 4:
 * Input: height = [1,2,1]
 * Output: 2
 */
public class ContainerWithMostWater {
    /**
     * Brutal Force Method
     * 
     * @author daohuei
     * @description: just walkthrough the heights array with nested loop, and find
     *               the max area
     * @time: O(n^2), nested loop for heights
     * @space: O(1), not using any
     * 
     */
    public static int maxAreaBrutalForce(int[] height) {
        int maxValue = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int currentArea = Math.min(height[i], height[j]) * (j - i);
                maxValue = Math.max(maxValue, currentArea);
            }
        }
        return maxValue;
    }

    /**
     * Closure Method
     * 
     * @author daohuei
     * @description: set 2 pointer from the start and end, and search by reaching
     *               the middle. Since the width can only be smaller, so we only
     *               need to consider the height. So every time we move forward, we
     *               will discard the shorter side and move the pointer.
     * @time: O(n), each time move once, until start and end pointer met. So it walk
     *        through the array once only.
     * @space: O(1), not using any
     * 
     */
    public static int maxAreaClosureMethod(int[] height) {
        int maxValue = 0, start = 0, end = height.length - 1;
        while (start < end) {
            int left = height[start], right = height[end];
            int area = Math.min(left, right) * (end - start);
            maxValue = Math.max(maxValue, area);
            if (left < right) {
                start++;
            } else {
                end--;
            }
        }
        return maxValue;
    }
}
