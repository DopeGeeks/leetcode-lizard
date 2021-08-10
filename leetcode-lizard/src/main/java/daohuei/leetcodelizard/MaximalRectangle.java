package daohuei.leetcodelizard;

import java.util.Stack;

/*
 * 85. Maximal Rectangle
 * Link: https://leetcode.com/problems/maximal-rectangle/description/
 */
public class MaximalRectangle {
    /**
     * @author: daohuei
     * @description: dp for init histogram for each row, and stack for find max
     *               rectangle in histogram
     * @time: O(mn): go through every position
     * @space: O(mn): for dp heightmap
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] heightMap = new int[m][n];

        // Prepare height map
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    // if 0 just give 0
                    heightMap[i][j] = 0;
                } else {
                    // only considering height(top to bottom), dp store with previous record
                    // it is like constructing every height for each row
                    heightMap[i][j] = i == 0 ? 1 : heightMap[i - 1][j] + 1;
                }
            }
        }
        // in each row
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            // calculate the max area with find rect in histogram solution
            maxArea = Math.max(maxArea, findLargestRectInHistogram(heightMap[i]));
        }

        return maxArea;
    }

    private int findLargestRectInHistogram(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int m = height.length;
        int max = 0;
        for (int i = 0; i <= m; i++) {
            int currHeight = i == m ? -1 : height[i];
            // if the height is valid (smaller or equal)
            while (!stack.isEmpty() && currHeight <= height[stack.peek()]) {
                // get the height in stack
                int peekHeight = height[stack.pop()];
                // calculate the width (stack is empty means already reach the first column)
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                // get the max area
                max = Math.max(max, peekHeight * width);
            }
            // add position into the stack
            stack.push(i);
        }
        return max;
    }
}