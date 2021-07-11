package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 279. Perfect Squares
 * Link: https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {
    /**
     * @author: daohuei
     * @description: backtracking with hash map
     * @time: O(n): search all possible numbers in n
     * @space: O(n): for hashmap, and recursion stack
     */
    public int backTracking(int n) {
        return numSquaresHelper(n, new HashMap<Integer, Integer>());
    }

    private int numSquaresHelper(int n, HashMap<Integer, Integer> map) {
        if (n == 0) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int count = Integer.MAX_VALUE;
        // iters with square number
        for (int i = 1; i * i <= n; i++) {
            // given number minus the current square
            // and use it to recur
            count = Math.min(count, numSquaresHelper(n - i * i, map) + 1);
        }
        // store the result in the map
        map.put(n, count);
        return count;
    }

    /**
     * @author: daohuei
     * @description: dp
     * @time: O(root(n)*n): the length of root(n)
     * @space: O(n): for dp array
     */
    public int dp(int n) {
        // init dp array with max value
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // but index 0 is 0
        dp[0] = 0;
        // solve from 1 to n
        for (int i = 1; i <= n; i++) {
            // from j to i but in square
            for (int j = 1; j * j <= i; j++) {
                // get the previous ans and add 1
                // compare with current to find the min one
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n): search all nodes
     * @space: O(h): height of the tree
     */
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int level = 0;
        queue.add(n);
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            // generate next layer
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int j = 1; j * j <= cur; j++) {
                    // minus every square
                    int next = cur - j * j;
                    // if next is zero, found it
                    if (next == 0) {
                        return level;
                    }
                    // if not visit before
                    if (!visited.contains(next)) {
                        // put into queue and mark visited
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
    }
}