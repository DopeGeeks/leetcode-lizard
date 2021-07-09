package daohuei.leetcodelizard;

/*
 * 74. Search a 2D Matrix
 * Link: https://leetcode.com/problems/search-a-2d-matrix/
 */
public class Search2DMatrix {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(logn): binary search, no need to argue
     * @space: O(1): not using any
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        // left and right will be the first and last index and let it be in 1D indexes
        int left = 0;
        int right = rows * cols - 1;
        while (left <= right) {
            // binary search
            int mid = (left + right) / 2;
            // get the mid point of the matrix
            // mid / cols => row
            // mid % cols => col
            int temp = matrix[mid / cols][mid % cols];
            if (temp == target) {
                return true;
            } else if (temp < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}