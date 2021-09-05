package daohuei.leetcodelizard;
/*
 * 317. Shortest Distance from All Buildings
 * Link: https://leetcode.com/problems/shortest-distance-from-all-buildings/description/
 */

import java.util.LinkedList;
import java.util.Queue;

// You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
// You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

// Each 0 marks an empty land which you can pass by freely.
// Each 1 marks a building which you cannot pass through.
// Each 2 marks an obstacle which you cannot pass through.
// For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

// 1 - 0 - 2 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0
// The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

// Note:
// There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
public class ShortestDistanceFromAllBuildings {
    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n*k): for each cell they traverse till finding all buildings
     * @space: O(n + k): k for queue, the max size of a level, n for visited array
     *         for all cells
     */
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int totalBuildings = countBuildings(grid);
        int minSteps = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // if empty:
                // do the bfs to get the total steps to all buildings of each empty cell
                // then take the minimum
                if (grid[i][j] == 0) {
                    minSteps = Math.min(minSteps, bfs(grid, i, j, totalBuildings));
                }
            }
        }
        // if nothing find: return -1
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }

    // count building amount => n^2
    private int countBuildings(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    res++;
            }
        }
        return res;
    }

    private int bfs(int[][] grid, int i, int j, int totalBuildings) {
        // empty case
        if (totalBuildings == 0)
            return Integer.MAX_VALUE;
        int m = grid.length, n = grid[0].length;
        int[][] visited = new int[m][n];
        Queue<Integer> queue = new LinkedList<Integer>();
        // the index for the cell
        queue.add(i * n + j);
        int step = 0, foundBuildings = 0, totalSteps = 0, size = queue.size();
        while (!queue.isEmpty()) {
            // poll out the pending cell
            int val = queue.poll();
            size--;
            // get the x and y from the index
            int x = val / n, y = val % n;
            // if not visited before
            if (visited[x][y] == 0) {
                // mark it visited
                visited[x][y] = 1;
                // if the cell is empty
                if (grid[x][y] == 0) {
                    // if in the bound, put the next cell of four directions into the queue
                    if (x - 1 >= 0)
                        queue.offer((x - 1) * n + y);
                    if (y - 1 >= 0)
                        queue.offer(x * n + y - 1);
                    if (x + 1 < m)
                        queue.offer((x + 1) * n + y);
                    if (y + 1 < n)
                        queue.offer(x * n + y + 1);
                }
                // if building found
                else if (grid[x][y] == 1) {
                    // count the found buildings
                    foundBuildings++;
                    // add to the total steps
                    totalSteps += step;
                    // if all building found
                    if (foundBuildings == totalBuildings)
                        // this is the answer for the root cell
                        return totalSteps;
                }
            }
            // size is the layer nodes number
            // if it reach the zero
            // means we move to next layer
            if (size == 0) {
                // move to next layer
                step++;
                // update next layer size
                size = queue.size();
            }
        }
        return Integer.MAX_VALUE;
    }
}
