package daohuei.leetcodelizard;

import java.util.HashSet;
import java.util.Stack;

/*
 * 84. Largest Rectangle in Histogram
 * Link: https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {
    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n): 3 O(n) loops
     * @space: O(n): for the left less min and the right less min
     */
    public int iteration(int[] heights) {
        // empty case
        if (heights.length == 0) {
            return 0;
        }
        // the left first least less height for each height
        int[] leftLessMin = new int[heights.length];
        leftLessMin[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            // less height pointer, start with the left position of the current height
            int l = i - 1;
            // if l is still in the bound and not less than current height
            while (l >= 0 && heights[l] >= heights[i]) {
                // use the previous left less height
                l = leftLessMin[l];
            }
            // update current left less height
            leftLessMin[i] = l;
        }

        // same as above
        int[] rightLessMin = new int[heights.length];
        rightLessMin[heights.length - 1] = heights.length;
        for (int i = heights.length - 2; i >= 0; i--) {
            int r = i + 1;
            while (r <= heights.length - 1 && heights[r] >= heights[i]) {
                r = rightLessMin[r];
            }
            rightLessMin[i] = r;
        }

        // calculate the area with the left less and right less at each position
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = (rightLessMin[i] - leftLessMin[i] - 1) * heights[i];
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(nlog(n)): mid area is binary search + recur 2n times
     * @space: O(log(n)): recursion stack that using binary search
     */
    public int recursion(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        return getMaxArea(heights, 0, heights.length - 1);
    }

    private int getMaxArea(int[] heights, int left, int right) {
        if (left == right) {
            return heights[left];
        }
        int mid = left + (right - left) / 2;
        // recur with left search
        int area1 = getMaxArea(heights, left, mid);
        // recur with right search
        int area2 = getMaxArea(heights, mid + 1, right);
        // middle search
        int area3 = getMidArea(heights, left, mid, right);
        // get the largest area
        return Math.max(Math.max(area1, area2), area3);
    }

    private int getMidArea(int[] heights, int left, int mid, int right) {
        int i = mid;
        int j = mid + 1;
        int minH = Math.min(heights[i], heights[j]);
        int area = minH * 2;
        // expand from mid to the left and to the right
        while (i >= left && j <= right) {
            // get the min height
            minH = Math.min(minH, Math.min(heights[i], heights[j]));
            // update the max area
            area = Math.max(area, minH * (j - i + 1));
            // if i reach the left
            if (i == left) {
                j++;
                // if j reach the right
            } else if (j == right) {
                i--;
                // if left next is larger than right next
            } else if (heights[i - 1] >= heights[j + 1]) {
                i--;
                // reversely...
            } else {
                j++;

            }
        }
        return area;
    }

    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(n^2): n for heights length
     * @space: O(n): for heights set storing all heights
     */
    public int brutalForce(int[] heights) {
        HashSet<Integer> heightsSet = new HashSet<Integer>();
        // add all height in the height set
        for (int i = 0; i < heights.length; i++) {
            heightsSet.add(heights[i]);
        }
        int maxArea = 0;
        // go through every height
        for (int h : heightsSet) {
            int width = 0;
            int maxWidth = 1;
            // search every height with order
            for (int i = 0; i < heights.length; i++) {
                // if current height is larger than height in set
                if (heights[i] >= h) {
                    // add 1 to the width
                    width++;
                } else {
                    // if meet a height that less than height in set
                    // get the max width and reset width
                    maxWidth = Math.max(width, maxWidth);
                    width = 0;
                }
            }
            // get the final max width
            maxWidth = Math.max(width, maxWidth);
            // get the max area
            maxArea = Math.max(maxArea, h * maxWidth);
        }
        return maxArea;
    }

    /**
     * @author: daohuei
     * @description: stack
     * @time: O(n): every height only pushed into the stack at most 2 times
     * @space: O(n): for the size of the stack
     */
    public int stackMethod(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        // the index for heights
        int p = 0;
        while (p < heights.length) {
            if (stack.isEmpty()) {
                stack.push(p);
                // p move forward
                p++;
            } else {
                // check the top index
                int top = stack.peek();
                // if the current height larger than the top height in the stack
                if (heights[p] >= heights[top]) {
                    // stack the p and move forward
                    stack.push(p);
                    p++;
                } else {
                    // if smaller
                    // pop the top height
                    int height = heights[stack.pop()];
                    // the next one will be the left less min height of top height
                    int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                    // the current with be the right less min height of top height
                    int RightLessMin = p;
                    // calculate the area
                    int area = (RightLessMin - leftLessMin - 1) * height;
                    // get the max area
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        while (!stack.isEmpty()) {
            // if the stack is not empty yet
            // calculate the max area of the rest with the method that same as above
            int height = heights[stack.pop()];
            int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
            int RightLessMin = heights.length;
            int area = (RightLessMin - leftLessMin - 1) * height;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}