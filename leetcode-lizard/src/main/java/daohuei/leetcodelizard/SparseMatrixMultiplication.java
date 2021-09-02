package daohuei.leetcodelizard;

/*
 * 311. Sparse Matrix Multiplication
 * Link: https://leetcode.com/problems/sparse-matrix-multiplication/description/
 */
/*
Given two sparse matrices A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.
Example:
Input:
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]
B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]
Output:
     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/
public class SparseMatrixMultiplication {
    /**
     * @author: daohuei
     * @description: accumulated difference with binary search tree
     * @time: O(mnk): m -> A row, n -> B col, k-> A col and B row numbers
     * @space: O(1): not using any actually
     */

    public int[][] multiply(int[][] A, int[][] B) {
        // invalid then return empty matrix
        if (validate(A, B)) {
            return new int[][] {};
        }

        // iterate over A, B, create setA, setB for reduce row/col
        int m = A.length, n = B[0].length, index = B.length;
        int[][] rst = new int[m][n];

        // go through A rows
        for (int i = 0; i < m; i++) {
            // index = A col number or B row number
            for (int ind = 0; ind < index; ind++) {
                // we skip the 0 since it wont affect the sum
                if (A[i][ind] == 0)
                    continue;
                // go through columns in B
                for (int j = 0; j < n; j++) {
                    // we skip the 0 since it wont affect the sum
                    if (B[ind][j] == 0)
                        continue;
                    // multiply and sum
                    rst[i][j] += A[i][ind] * B[ind][j];
                }
            }
        }

        return rst;
    }

    // check if A and B multiplication is not valid
    private boolean validate(int[][] A, int[][] B) {
        if (A == null || B == null)
            return true;
        if (A[0].length != B.length)
            return true;
        return false;
    }
}
