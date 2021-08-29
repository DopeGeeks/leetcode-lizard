package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 286. Walls and Gates 
 * Link: https://leetcode.com/problems/walls-and-gates/description/
 */
/*
You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 
to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. 
If it is impossible to reach a gate, it should be filled with INF.
Example: 
Given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */
public class WallsAndGates {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(mn): go through every cells
     * @space: O(mn): worst case recursion for all cells
     */

    int[] dx = { 1, -1, 0, 0 };
    int[] dy = { 0, 0, 1, -1 };

    public void wallsAndGatesDFS(int[][] rooms) {
        if (validate(rooms))
            return;

        int m = rooms.length, n = rooms[0].length;
        // go through every room
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // dfs start from the gate
                if (rooms[i][j] == 0) {
                    // start with distance 1
                    dfsHelper(rooms, i, j, 1);
                }
            }
        }
    }

    // mark grid with dist (compare if existed)
    public void dfs(int[][] rooms, int x, int y, int dist) {
        // validate coord
        if (validateCoordinate(rooms, x, y))
            return;
        // if the distance is already larger than the one in the result
        // break the recursion
        if (rooms[x][y] <= dist)
            return;

        // dist < room[x][y], so update rooms[x][y] with smaller dist
        rooms[x][y] = dist;

        // keep searching deeper and count one for the distance
        dfsHelper(rooms, x, y, dist + 1);
    }

    // for go through every direction for dfs
    private void dfsHelper(int[][] rooms, int x, int y, int dist) {
        for (int i = 0; i < dx.length; i++) {
            dfs(rooms, x + dx[i], y + dy[i], dist);
        }
    }

    // for checking coord is valid
    private boolean validateCoordinate(int[][] rooms, int x, int y) {
        // if the position is out of bound or is not the empty room
        return x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] == -1 || rooms[x][y] == 0;
    }

    // for checking room is valid
    private boolean validate(int[][] rooms) {
        return rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0;
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(mn): go through every cells
     * @space: O(mn): worst case for all cells
     */
    public void wallsAndGatesBFS(int[][] rooms) {
        if (validate(rooms))
            return;
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Initi with 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // put all gates into the queue
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        // bfs all queues
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            bfsHelper(rooms, queue, point[0], point[1]);
        }
    }

    private void bfsHelper(int[][] rooms, Queue<int[]> queue, int x, int y) {
        // go through all directions
        for (int i = 0; i < dx.length; i++) {
            int mX = x + dx[i], mY = y + dy[i];
            // ignore invalid coord and if the new distance is larger, ignore it
            if (validateCoordinate(rooms, mX, mY) || rooms[x][y] + 1 > rooms[mX][mY])
                continue;
            // update min distance
            rooms[mX][mY] = rooms[x][y] + 1;
            // put the new coord in the queue for pending search
            queue.offer(new int[] { mX, mY });
        }
    }
}
