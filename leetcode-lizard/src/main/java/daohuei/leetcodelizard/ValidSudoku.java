package daohuei.leetcodelizard;

import java.util.HashSet;
import java.util.Set;

/*
 * 36. Valid Sudoku
 * Link: https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {
    /**
     * @author: daohuei
     * @description: hashset
     * @time: O(n): n = all position in sudoku board
     * @space: O(n): for hashset of every position
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<String>();
        // go through the board
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                // if not empty point
                if (number != '.')
                    // put the number in the row/column/block with certain index
                    // and if already exist, then invalid
                    if (!seen.add(number + " in row " + i) || !seen.add(number + " in column " + j)
                            || !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }
}