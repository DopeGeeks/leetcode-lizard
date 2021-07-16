package daohuei.leetcodelizard;

/*
 * 329. Longest Increasing Path in a Matrix
 * Link: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInMatrix {

    /**
     * @author: daohuei
     * @description: dynamic programming + DFS
     * @time: O(m*n): only visit every element once
     * @space: O(k): k for max length
     */
    boolean[][] visited;
    int[][] dp;
    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };

    public int longestIncreasingPath(int[][] matrix) {
        // empty case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        visited = new boolean[m][n];
        dp = new int[m][n];
        int max = 0;
        // search the max count with dfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int x, int y) {
        // if visited before, return its count in dp
        if (visited[x][y]) {
            return dp[x][y];
        }
        // init count with 1
        dp[x][y] = 1;

        // check every direction with (dx, dy) movement
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // check if the coordinate is not out of bound
            // and next step value is larger
            if (validateCoordinate(matrix, x, y, nx, ny)) {
                // update current value with comparing current value the next position's count
                dp[x][y] = Math.max(dp[x][y], dfs(matrix, nx, ny) + 1);
            }
        }
        // make this position visited
        visited[x][y] = true;
        // return the current dp count
        return dp[x][y];
    }

    private boolean validateCoordinate(int[][] matrix, int x, int y, int nx, int ny) {
        // if in the bound and current value is less than the next step
        return nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[x][y] < matrix[nx][ny];
    }
}