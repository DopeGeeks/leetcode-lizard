package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    /**
     * @author: ballm06m06
     * @Time_Complexity: O(M*N)
     * @Space_Complexity: O(M*N)
     */
    // Using bfs
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int isIslandCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // grid[i][j] == 1 means land
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(i, j, grid, visited, direction);
                    isIslandCount++;
                }
            }
        }
        return isIslandCount;
    }

    public void bfs(int i, int j, char[][] grid, boolean[][] visited, int[][] direction) {
        int m = grid.length;
        int n = grid[0].length;
        // Initialize a queue
        Queue<int[]> queue = new LinkedList<>();
        // offer means insert
        queue.offer(new int[] { i, j });
        // set (i,j) this position to true, means we've been there before
        visited[i][j] = true;

        // while there're some elements in the queue
        while (queue.size() > 0) {
            // we dequeue the element first, which we've set to visited(true)
            int[] cur = queue.poll();

            // After dequeue operation, we check the characters around the element we've
            // just dequeued
            for (int[] dir : direction) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];

                // if the characters are inside the boundary
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    // And it is not visited, also its value equals '1'
                    if (!visited[x][y] && grid[x][y] == '1') {
                        // then we enqueue this element
                        queue.offer(new int[] { x, y });
                        // and set it to visited(true)
                        visited[x][y] = true;
                    }
                }
            }
        }
    }

    // Using DFS
    public int numIslands1(char[][] grid) {
        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    public void dfs(char[][] grid, int i, int j) {
        // if i or j is out of bound and the elements equals to 0, then return, cuz
        // they're not we're finding
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0')
            return;

        // set the grid[i][j] from 1 to 0
        grid[i][j] = '0';
        // up
        dfs(grid, i - 1, j);
        // down
        dfs(grid, i + 1, j);
        // left
        dfs(grid, i, j - 1);
        // right
        dfs(grid, i, j + 1);
    }

}