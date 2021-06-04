package daohuei.leetcodelizard;

/*
 * 59. Spiral Matrix II
 * Link: https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrixTwo {
    /**
     * @author: daohuei
     * @description: moving snake method
     * @time: O(n^2): go through n*n elements
     * @space: O(1): not using any
     */
    public int[][] movingSnake(int n) {
        int[][] ans = new int[n][n];
        int start_x = 0;
        int start_y = 0;
        int direction = 0;
        int top_border = -1;
        int right_border = n;
        int bottom_border = n;
        int left_border = -1;
        // for push the answer number
        int count = 1;
        while (true) {
            // if finish all iterations
            if (count == n * n + 1) {
                return ans;
            }
            // y is row, x is col
            ans[start_y][start_x] = count;
            count++;
            // moving with direction
            // right:0, down:1, left:2, up:3, following the spiral order
            // start with right
            switch (direction) {
                // right
                case 0:
                    // if reach the border
                    if (start_x + 1 == right_border) {
                        // turn down
                        direction = 1;
                        // move down
                        start_y += 1;
                        // narrow the border with one layer
                        top_border += 1;
                    } else {
                        // move right
                        start_x += 1;
                    }
                    break;
                // down
                case 1:
                    // if reach the border
                    if (start_y + 1 == bottom_border) {
                        // turn left
                        direction = 2;
                        // move left
                        start_x -= 1;
                        // narrow the border with one layer
                        right_border -= 1;
                    } else {
                        // move down
                        start_y += 1;
                    }
                    break;
                // left
                case 2:
                    // if reach the border
                    if (start_x - 1 == left_border) {
                        // turn up
                        direction = 3;
                        // move up
                        start_y -= 1;
                        // narrow the border with one layer
                        bottom_border -= 1;
                    } else {
                        // move left
                        start_x -= 1;
                    }
                    break;
                // up
                case 3:
                    // if reach the border
                    if (start_y - 1 == top_border) {
                        // turn right
                        direction = 0;
                        // move right
                        start_x += 1;
                        // narrow the border with one layer
                        left_border += 1;
                    } else {
                        // move up
                        start_y -= 1;
                    }
                    break;
            }
        }
    }
}