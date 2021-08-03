package daohuei.leetcodelizard;

/*
 * 52. N-Queens II
 * Link: https://leetcode.com/problems/n-queens-ii/description/
 */
public class NQueensTwo {
    /**
     * @author: daohuei
     * @description: backtracking method
     * @time: O(n!): go through every combination of queens
     * @space: O(n): the recursion stack at most n
     */
    public int totalNQueens(int n) {
        if (n <= 0)
            return 0;
        // search through the dfs
        return dfs(new int[n], 0);
    }

    private int dfs(int[] columns, int row) {
        // if it exceeds end row, means found an answer, count 1
        if (row == columns.length)
            return 1;
        int count = 0;
        // go through every position of column
        for (int col = 0; col < columns.length; col++) {
            // check if valid of current column and row
            if (validate(columns, row, col)) {
                // store or replace the queen's location
                columns[row] = col;
                // recur with next row
                count += dfs(columns, row + 1);
            }
        }
        return count;
    }

    // Validate the prior row, colomn & diagnal
    private boolean validate(int[] columns, int row, int col) {
        for (int newRow = 0; newRow < row; newRow++) {
            // search all previous rows
            int newCol = columns[newRow];
            // if the column have been taken or some queen placed in the diagonal position
            if (col == newCol || Math.abs(row - newRow) == Math.abs(col - newCol)) {
                // invalid
                return false;
            }
        }
        // valid
        return true;
    }
}