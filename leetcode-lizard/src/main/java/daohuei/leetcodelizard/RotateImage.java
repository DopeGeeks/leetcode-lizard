package daohuei.leetcodelizard;

/*
 * 48. Rotate Image
 * Link: https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {

    /**
     * @author: daohuei
     * @description: exchange method
     * @time: O(n^2) m*m + m * n/2, m is length of row, n is length of column
     * @space: O(1) not using any
     */
    public void exchange(int[][] matrix) {

        // exchange all element on diagnal
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    continue;
                }
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // exchange columns on middle line
        for (int i = 0, j = matrix.length - 1; i < matrix.length / 2; i++, j--) {
            for (int k = 0; k < matrix.length; k++) {
                int temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }
    }

    /**
     * @author: daohuei
     * @description: find pattern of each element's next position
     * @time: O(n^2) since using 2 layer nested loop and depends on length n
     * @space: O(1) not using any
     */
    public void pattern(int[][] matrix) {
        int n = matrix.length;
        // since we only look for a layer(round? circle?) of matrix
        // we only need to look for it n/2 times
        for (int i = 0; i < n / 2; i++)
            // then for each layer, we only search until the previous one of the end element
            for (int j = i; j < n - i - 1; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
    }
}
