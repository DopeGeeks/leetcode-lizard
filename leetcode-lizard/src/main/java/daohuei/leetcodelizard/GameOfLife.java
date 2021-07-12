package daohuei.leetcodelizard;

/*
 * 289. Game of Life
 * Link: https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {
    /**
     * @author: daohuei
     * @description: replace and restore
     * @time: O(n): n for all cells in the board
     * @space: O(1): not using any
     */
    public void replaceRestore(int[][] board) {
        int rows = board.length;
        if (rows == 0) {
            return;
        }
        int cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // get ones around current cell
                int count = getOnes(r, c, rows, cols, board);

                // current cell = 0, count = 3 => revive
                // 3 => 0 to 1
                if (board[r][c] == 0) {
                    if (count == 3) {
                        board[r][c] = 3;
                    }
                }

                // current cell = 1, count < 2 => dead
                // current cell = 1, count > 3 => dead
                // 2 => 1 to 0
                if (board[r][c] == 1) {
                    if (count < 2 || count > 3) {
                        board[r][c] = 2;
                    }
                }

            }
        }

        // restore 2 & 3
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 0 -> 0
                // 1 -> 1
                // 2 -> 0
                // 3 -> 1
                board[r][c] %= 2;
            }
        }

    }

    // 8 type of movements
    static int d[][] = { { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

    // function to get count of ones of 8 neighbors
    private int getOnes(int r, int c, int rows, int cols, int[][] board) {
        int count = 0;
        for (int k = 0; k < 8; k++) {
            // row and col movement
            int x = d[k][0] + r;
            int y = d[k][1] + c;
            // if out of bound
            if (x < 0 || x >= rows || y < 0 || y >= cols) {
                continue;
            }
            // if it is 1 or 2(1 -> 0), since previously is 1, so count it
            if (board[x][y] == 1 || board[x][y] == 2) {
                count++;
            }
        }
        return count;
    }

    /**
     * @author: daohuei
     * @description: count with 2 method
     * @time: O(n): n for all cells in the board
     * @space: O(1): not using any
     */
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        if (rows == 0) {
            return;
        }
        int cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // right
                check(r, c, r, c + 1, rows, cols, board);
                // right down
                check(r, c, r + 1, c + 1, rows, cols, board);
                // down
                check(r, c, r + 1, c, rows, cols, board);
                // left down
                check(r, c, r + 1, c - 1, rows, cols, board);
                // 5 => 1, and have 2 neighbors 1, safe
                // 6 => 0, and have 3 neighbors 1, revive
                // 7 => 1, and have 3 neighbors 1, safe
                // 5, 6, 7 => 1
                if (board[r][c] >= 5 && board[r][c] <= 7) {
                    board[r][c] = 1;
                } else {
                    // others are just died
                    board[r][c] = 0;
                }

            }
        }
    }

    private void check(int rCur, int cCur, int rNext, int cNext, int rows, int cols, int[][] board) {
        if (rNext < 0 || cNext < 0 || rNext >= rows || cNext >= cols) {
            return;
        }
        // if current is 1 or previous 1
        if (board[rCur][cCur] % 2 == 1) {
            // update next with 2
            board[rNext][cNext] += 2;
        }
        // if next is 1 or previous 1
        if (board[rNext][cNext] % 2 == 1) {
            // update current with 2
            board[rCur][cCur] += 2;
        }
    }
}