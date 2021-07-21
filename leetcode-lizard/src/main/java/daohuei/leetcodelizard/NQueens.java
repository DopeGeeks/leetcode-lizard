package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 51. N-Queens
 * Link: https://leetcode.com/problems/n-queens/
 */
public class NQueens {
    /**
     * @author: daohuei
     * @description: backtracking method
     * @time: O(n^2): search row and col
     * @space: O(n): for recursion stack
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), ans, n);
        return ans;
    }

    private void backtrack(List<Integer> currentQueen, List<List<String>> ans, int n) {
        // if all queens are set
        if (currentQueen.size() == n) {
            List<String> temp = new ArrayList<>();
            // construct the output
            for (int i = 0; i < n; i++) {
                char[] t = new char[n];
                Arrays.fill(t, '.');
                t[currentQueen.get(i)] = 'Q';
                temp.add(new String(t));
            }
            // add to the answer
            ans.add(temp);
            return;
        }
        // try for every column
        for (int col = 0; col < n; col++) {
            // make sure no queen in the same column
            if (!currentQueen.contains(col)) {
                // make sure no queen in daigonal position
                if (isDiagonalAttack(currentQueen, col)) {
                    continue;
                }
                // add this col into the currentQueen
                currentQueen.add(col);
                // check for next row
                // the current queen length indicate the current row number
                backtrack(currentQueen, ans, n);
                // remove last queen
                // after backtracking, it have searched all possiblities
                currentQueen.remove(currentQueen.size() - 1);
            }

        }

    }

    private boolean isDiagonalAttack(List<Integer> currentQueen, int i) {
        // get the row and col
        int current_row = currentQueen.size();
        int current_col = i;
        // check every row
        for (int row = 0; row < currentQueen.size(); row++) {
            // if abs row diff and abs col diff are the same
            // they are diagonally matched
            if (Math.abs(current_row - row) == Math.abs(current_col - currentQueen.get(row))) {
                return true;
            }
        }
        return false;
    }
}