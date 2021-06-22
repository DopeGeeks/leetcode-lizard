package daohuei.leetcodelizard;

/*
 * 79. Word Search
 * Link: https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(m*n) numbers of all characters(*4 for up/down/right/left)
     * @space: O(t): the recursion stack needs space of length of target
     */
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        // empty case
        if (rows == 0) {
            return false;
        }
        int cols = board[0].length;
        // go through the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // find the word
                if (existRecursive(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existRecursive(char[][] board, int row, int col, String word, int index) {
        // if out of bound
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        // if current search not match the target
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        // if the index already reach the end, then it should be true true
        if (index == word.length() - 1) {
            return true;
        }
        char temp = board[row][col];
        // replace examined place with $ sign
        // for avoiding loopy search in the recursion
        board[row][col] = '$';

        // search up down left right, if one of them is right, then current search is
        // true
        boolean up = existRecursive(board, row - 1, col, word, index + 1);
        if (up) {
            return true;
        }
        boolean down = existRecursive(board, row + 1, col, word, index + 1);
        if (down) {
            return true;
        }
        boolean left = existRecursive(board, row, col - 1, word, index + 1);
        if (left) {
            return true;
        }
        boolean right = existRecursive(board, row, col + 1, word, index + 1);
        if (right) {
            return true;
        }
        // restore the examined place
        board[row][col] = temp;
        return false;
    }
}