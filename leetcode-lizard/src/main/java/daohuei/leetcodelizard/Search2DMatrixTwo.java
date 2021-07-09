package daohuei.leetcodelizard;

/*
 * 240. Search a 2D Matrix II
 * Link: https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class Search2DMatrixTwo {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(mlogn): if m x n matrix
     * @space: O(1): not using any
     */
    public boolean binarySearchMethod(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // go through the row
        for (int i = 0; i < matrix.length; i++) {
            // if first number of the current row is larger than the target
            if (matrix[i][0] > target) {
                // nah
                break;
            }
            // if the last number of current row is smaller than target
            if (matrix[i][matrix[i].length - 1] < target) {
                // skip this row
                continue;
            }
            // binary search until the answer
            int col = binarySearch(matrix[i], target);
            // if found it
            if (col != -1) {
                return true;
            }
        }
        return false;
    }

    // binary search
    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * @author: daohuei
     * @description: remove row and column by examining the right top number
     * @time: O(m + n): if m x n matrix
     * @space: O(1): not using any
     */
    public boolean removeRowAndCol(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // get the right top index
        int row = 0;
        int col = matrix[0].length - 1;
        // while not out of bound
        while (row < matrix.length && col >= 0) {
            // if target larger, remove the row
            if (target > matrix[row][col]) {
                row++;
            } else if (target < matrix[row][col]) {
                // if target smaller, remove the column
                col--;
            } else {
                // found it, ya
                return true;
            }
        }
        return false;
    }

    /**
     * @author: daohuei
     * @description: binary search with removing left top or right bottom
     * @time: O(log(mn)): if m x n matrix
     * @space: O(k): for recursion stack, not sure how to calculate the result
     */
    public boolean advancedBinarySearchMethod(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return searchMatrixHelper(matrix, 0, 0, matrix[0].length - 1, matrix.length - 1, matrix[0].length - 1,
                matrix.length - 1, target);

    }

    private boolean searchMatrixHelper(int[][] matrix, int x1, int y1, int x2, int y2, int xMax, int yMax, int target) {
        // input top left and right bottom to the helper
        // also check x bound and y bound as xMax and yMax

        // check out of bound
        if (x1 > xMax || y1 > yMax) {
            return false;
        }

        // if same point => the target
        if (x1 == x2 && y1 == y2) {
            return matrix[y1][x1] == target;
        }

        // get the middle point
        int m1 = (x1 + x2) >>> 1;
        int m2 = (y1 + y2) >>> 1;

        // if the middle point is the ans
        if (matrix[m2][m1] == target) {
            return true;
        }

        // if smaller
        if (matrix[m2][m1] < target) {
            // remove left top matrix
            // right top
            return searchMatrixHelper(matrix, m1 + 1, y1, x2, m2, x2, y2, target) ||
            // left bottom
                    searchMatrixHelper(matrix, x1, m2 + 1, m1, y2, x2, y2, target) ||
                    // right bottom
                    searchMatrixHelper(matrix, m1 + 1, m2 + 1, x2, y2, x2, y2, target);

        } else {
            // if larger
            // remove right bottom
            // right top
            return searchMatrixHelper(matrix, m1 + 1, y1, x2, m2, x2, y2, target) ||
            // left bottom
                    searchMatrixHelper(matrix, x1, m2 + 1, m1, y2, x2, y2, target) ||
                    // left top
                    searchMatrixHelper(matrix, x1, y1, m1, m2, x2, y2, target);
        }
    }

}