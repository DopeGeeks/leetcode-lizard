package daohuei.leetcodelizard;

/*
 * 73. Set Matrix Zeroes
 * Link: https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {
    /**
     * @author: daohuei
     * @description: first row/column flag
     * @time: O(m*n): at most go through all elements
     * @space: O(1): not using any
     */
    public void firstRowColumnFlags(int[][] matrix) {
        // for checking if the first col need to be set zeros
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;
        for (int i = 0; i < R; i++) {
            // if there is any element in first column is zero
            if (matrix[i][0] == 0) {
                isCol = true;
            }
            // find 0
            for (int j = 1; j < C; j++) {
                if (matrix[i][j] == 0) {
                    // if found, set their first row and column element to zero
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // according to first row/column is zero to set the following zeros
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // since if any of first row is zero, (0,0) must be zero
        // in the other word, the first row should be all zero
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // set the first column to zero if isCol is true
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}