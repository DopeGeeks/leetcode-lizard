package daohuei.leetcodelizard;

/*
 * 361. Bomb Enemy
 * Link: https://leetcode.com/problems/bomb-enemy/description/
 */
/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), 
return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point
until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.
Example:
For the given grid
0 E 0 0
E 0 W E
0 E 0 0
return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/
public class BombEnemy {
    /**
     * @author: daohuei
     * @description: dp method
     * @time: O(n^2): nested iteration
     * @space: O(n): for col sum array
     */
    public int maxKilledEnemies(char[][] grid) {
        // empty case
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;

        int[] colSum = new int[n];
        int max = 0;
        int row = 0;
        // for every grids
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // if it is the first col or the cell in previous column is a wall
                if (j == 0 || grid[i][j - 1] == 'W') {
                    row = 0;
                    // from here to the end col or encounter wall
                    // get the enemy number
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        row += grid[i][k] == 'E' ? 1 : 0;
                    }
                }
                // if it is the first row or the cell in the previous row is a wall
                if (i == 0 || grid[i - 1][j] == 'W') {
                    // sum it up with memorization
                    colSum[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colSum[j] += grid[k][j] == 'E' ? 1 : 0;
                    }
                }
                // get the max value of temp sum if it is a valid place to put the bomb
                if (grid[i][j] == '0')
                    max = Math.max(max, colSum[j] + row);
            }
        }
        return max;
    }
}
