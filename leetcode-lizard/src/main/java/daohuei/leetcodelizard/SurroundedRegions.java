package daohuei.leetcodelizard;

/*
 * 130. Surrounded Regions
 * Link: https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(m * n): since at last it still traverse full board
     * @space: O(m*n): the recursion stack can stack all element at the worst case
     *         (which full of O everywhere)
     */
    public void solve(char[][] board) {
        // empty case
        if (board.length == 0 || board[0].length == 0)
            return;
        // get row and col length
        int m = board.length;
        int n = board[0].length;

        // since the edge must not be surrounded
        // we only need to preserve O that at the edge and the O connect to that edge O

        // go through the first column and the last column
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        // go through the first row and the last row
        for (int j = 1; j < n - 1; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        // make all the remaining 'O' to 'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '*')
                    board[i][j] = 'O';
            }
        }
    }

    // make every 'O' that we meet to '*'
    // It is safe because we always start from the border
    // it check its surrounded "O"
    private void dfs(char[][] board, int i, int j) {
        // if i and j over the bound
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;
        // if traversed before or it is X
        if (board[i][j] == 'X' || board[i][j] == '*')
            return;
        // if it is "O"
        // make it be "*"
        board[i][j] = '*';
        // check it left/right/up/down
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}