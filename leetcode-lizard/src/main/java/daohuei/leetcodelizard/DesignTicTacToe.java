package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;

/*
 * 348. Design Tic-Tac-Toe
 * Link: https://leetcode.com/problems/design-tic-tac-toe/
 */
public class DesignTicTacToe {
    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(1): constant on moving
     * @space: O(n): for hash maps
     */
    class TicTacToe {

        /** Initialize your data structure here. */
        int[][] board;
        Map<Integer, Map<Integer, Integer>> rowMap;
        Map<Integer, Map<Integer, Integer>> colMap;
        Map<Integer, Integer> leftDiagonalMap;
        Map<Integer, Integer> rightDiagonalMap;
        int n;

        public TicTacToe(int n) {
            // empty board for the battle field!
            board = new int[n][n];
            rowMap = new HashMap<>();
            colMap = new HashMap<>();
            leftDiagonalMap = new HashMap<>();
            rightDiagonalMap = new HashMap<>();
            this.n = n;
        }

        /**
         * Player {player} makes a move at ({row}, {col}).
         * 
         * @param row    The row of the board.
         * @param col    The column of the board.
         * @param player The player, can be either 1 or 2.
         * @return The current winning condition, can be either: 0: No one wins. 1:
         *         Player 1 wins. 2: Player 2 wins.
         */
        public int move(int row, int col, int player) {
            if (!rowMap.containsKey(row)) {
                // init a hashmap for a row map instance
                Map<Integer, Integer> map = new HashMap<>();
                rowMap.put(row, map);
            }
            // count with player number
            rowMap.get(row).put(player, rowMap.get(row).getOrDefault(player, 0) + 1);
            if (ifWinner(rowMap.get(row), player)) {
                return player;
            }
            // if not winning
            if (!colMap.containsKey(col)) {
                Map<Integer, Integer> map = new HashMap<>();
                colMap.put(col, map);
            }
            // check column
            colMap.get(col).put(player, colMap.get(col).getOrDefault(player, 0) + 1);
            if (ifWinner(colMap.get(col), player)) {
                return player;
            }
            // if not winning
            // check left diagonal map
            if (row == col) {
                leftDiagonalMap.put(player, leftDiagonalMap.getOrDefault(player, 0) + 1);
            }
            if (ifWinner(leftDiagonalMap, player)) {
                return player;
            }
            // if still not winning
            // check right diagonal map
            if (row + col == n - 1) {
                rightDiagonalMap.put(player, rightDiagonalMap.getOrDefault(player, 0) + 1);
            }
            if (ifWinner(rightDiagonalMap, player)) {
                return player;
            }
            return 0;
        }

        private boolean ifWinner(Map<Integer, Integer> map, int player) {
            // if there are more than 1 user using this row
            if (map.size() > 1) {
                return false;
            }
            // if the position of this row/col/diagonal has all been taken
            // indicates winning
            int count = map.getOrDefault(player, 0);
            return count == n;
        }
    }
}
