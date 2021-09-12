package daohuei.leetcodelizard;

/*
 * 375. Guess Number Higher or Lower II
 * Link: https://leetcode.com/problems/guess-number-higher-or-lower-ii/description/
 */
public class GuessNumberHigherOrLowerTwo {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(n^2): go through every combination
     * @space: O(n^2): for the dp array
     */
    public int getMoneyAmount(int n) {

        if (n < 2) {
            return 0;
        }

        // dp array
        table = new int[n + 1][n + 1];
        return dp(1, n);
    }

    private int[][] table;

    // calculate the min value => which is the min of worst cost among
    // all the picking between the range
    private int dp(int start, int end) {
        // base case, if start and end meet up
        // means there is only 1 choice: no penalty cuz that must be the ans
        if (start >= end) {
            return 0;
        }
        // memorization
        if (table[start][end] != 0) {
            return table[start][end];
        }
        int min = Integer.MAX_VALUE;
        // for every guessing
        for (int i = start; i <= end; i++) {
            // the result should be current cost and
            // the max possible cost of
            // previous choice and the next choice
            int res = i + Math.max(dp(start, i - 1), dp(i + 1, end));
            // choose the min one
            min = Math.min(min, res);
        }
        // among them, assign the min one the the dp array
        table[start][end] = min;

        return min;
    }
}