package daohuei.leetcodelizard;

/*
 * 174. Dungeon Game
 * Link: https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): all cells in the dungeon
     * @space: O(n): for dp array
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) {
            return 1;
        }

        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] f = new int[m][n];
        // the last cell's minimum health
        f[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        // for every cell in last column (backward)
        for (int i = m - 2; i >= 0; i--) {
            // calculate the minimum health (using next cell's minimum health and current
            // cell's effect)
            // next cell's minimum - current health effect
            f[i][n - 1] = Math.max(1, f[i + 1][n - 1] - dungeon[i][n - 1]);
        }

        // for every cell in last row (backward)
        for (int j = n - 2; j >= 0; j--) {
            // same as above
            f[m - 1][j] = Math.max(1, f[m - 1][j + 1] - dungeon[m - 1][j]);
        }

        // go through every other cell (backward)
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // calculate the min health with the less one of next cell(indicate safer path
                // toward the end)
                // as next min health
                // then same as above
                f[i][j] = Math.max(1, Math.min(f[i][j + 1], f[i + 1][j]) - dungeon[i][j]);
            }
        }
        // return initial point's min health
        return f[0][0];
    }
}