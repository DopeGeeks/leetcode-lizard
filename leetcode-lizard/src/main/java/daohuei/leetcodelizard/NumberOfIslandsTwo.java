package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 305. Number of Islands II
 * Link: https://leetcode.com/problems/number-of-islands-ii/description/
 */
/*
A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.
Example:
Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]
*/
public class NumberOfIslandsTwo {

    private int[] dx = { 1, -1, 0, 0 };
    private int[] dy = { 0, 0, 1, -1 };

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(mnk): for each cell and all position which are nested looping
     *        together, will lead to timeout
     * @space: O(mn): for recursion stack, the worst case make lead to mn => all
     *         cells
     */
    public List<Integer> dfsMethod(int m, int n, int[][] positions) {
        final List<Integer> result = new ArrayList<>();
        // empty case
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return result;
        }
        // the island grid
        char[][] grid = new char[m][n];
        // init with 0 (water)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '0';
            }
        }
        // update the island
        for (int i = 0; i < positions.length; i++) {
            grid[positions[i][0]][positions[i][1]] = '1';
            // count the island number and add to the result
            result.add(numIslands(grid));
        }
        return result;
    }

    private int numIslands(char[][] grid) {
        // empty case
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // make a new grid for counting
        char[][] localGrid = new char[grid.length][grid[0].length];
        for (int i = 0; i < localGrid.length; i++) {
            for (int j = 0; j < localGrid[i].length; j++) {
                // copy all the cell
                localGrid[i][j] = grid[i][j];
            }
        }

        // count it with dfs
        int count = 0;
        for (int i = 0; i < localGrid.length; i++) {
            for (int j = 0; j < localGrid[i].length; j++) {
                // if find a island piece, count it, and then search and update for its
                // surrounding island
                if (localGrid[i][j] == '1') {
                    count++;
                    dfs(localGrid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y) {
        // if not out of bound and not 0
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') {
            return;
        }
        // update the visited cell to be 0
        grid[x][y] = '0';
        // search for 4 directions
        for (int i = 0; i < dx.length; i++) {
            dfs(grid, x + dx[i], y + dy[i]);
        }
    }

    /**
     * @author: daohuei
     * @description: union find method
     * @time: O(k logm*n): union(x,y) time is log(m*n), k for looping through all
     *        positions
     * @space: O(mn): union find array
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> rst = new ArrayList<>();
        // empty cases
        if (validateInput(m, n, positions)) {
            return rst;
        }

        int[][] grid = new int[m][n];
        UnionFind unionFind = new UnionFind(m * n);

        // go through every position
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0], y = positions[i][1];
            // if it is the water
            if (grid[x][y] == 0) {
                // update to the island
                grid[x][y] = 1;
                // count it
                unionFind.increaseCount();
                // go through every direction
                for (int j = 0; j < dx.length; j++) {
                    // next position
                    int movedX = x + dx[j], movedY = y + dy[j];
                    // if valid and is an island piece
                    if (validateBorder(grid, movedX, movedY, m, n)) {
                        // union current position and the new position
                        // since it is array, so we need to x*n first then sum with y
                        unionFind.union(x * n + y, movedX * n + movedY);
                    }
                }
            }
            // directly return unionFind's count
            rst.add(unionFind.query());
        }

        return rst;
    }

    // check if it is in the bound and is an island piece
    private boolean validateBorder(int[][] grid, int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1;
    }

    // for checking empty cases
    private boolean validateInput(int m, int n, int[][] positions) {
        return m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0] == null
                || positions[0].length == 0;
    }

    class UnionFind {
        int[] father;
        int[] rank;
        int count;

        // init the union find object
        public UnionFind(int x) {
            father = new int[x];
            rank = new int[x];
            count = 0;
            // init fathers with themselves
            for (int i = 0; i < x; i++)
                father[i] = i;
        }

        // union method
        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            // if their root is not the same
            // means they are not union yet
            // make them union
            if (rootX != rootY) {
                // if x more rank, means x is more prior, make it y's father
                if (rank[rootX] > rank[rootY])
                    father[rootY] = rootX;
                // other way
                else if (rank[rootX] < rank[rootY])
                    father[rootX] = rootY;
                // if their ranks are same, make x be father and increase x's rank
                else {
                    father[rootY] = rootX;
                    rank[rootX]++;
                }
                // since we union them, we have to decrease the count by 1
                count--;
                return;
            }
        }

        public int query() {
            return count;
        }

        public void increaseCount() {
            count++;
        }

        // find the root that contains position x
        private int find(int x) {
            // if found
            if (father[x] == x)
                return x;
            // if not, go deeper
            return father[x] = find(father[x]);
        }
    }
}
