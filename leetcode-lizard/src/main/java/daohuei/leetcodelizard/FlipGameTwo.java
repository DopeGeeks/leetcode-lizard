package daohuei.leetcodelizard;

/*
 * 294. Flip Game II
 * Link: https://leetcode.com/problems/flip-game-ii/description/
 */
/*
You are playing the following Flip Game with your friend: 
Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and 
therefore the other person will be the winner.
Write a function to determine if the starting player can guarantee a win.
For example, given s = "++++", return true. 
The starting player can guarantee a win by flipping the middle "++" to become "+--+".
Follow up:
Derive your algorithm's runtime complexity.
Tags: Backtracking
Similar Problems: (E) Nim Game, (E) Flip Game
*/
public class FlipGameTwo {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(nk): k is all possible move, n is length of string
     * @space: O(k): for recursion stack
     */

    public boolean canWin(String s) {
        // base case or empty case -> not possible
        if (s == null || s.length() < 2) {
            return false;
        }
        // give a new boolean sign array for string
        boolean[] sign = new boolean[s.length()];
        // + -> true, - -> false
        for (int i = 0; i < sign.length; i++)
            sign[i] = s.charAt(i) == '+';
        return dfs(sign);
    }

    public boolean dfs(boolean[] sign) {
        // go through the sign
        for (int i = 0; i < sign.length - 1; i++) {
            // if consecutive +
            if (sign[i] && sign[i + 1]) {
                // set them to -
                setSign(sign, i, false);
                // When opponent is possible to lose, return true for curr player
                // recur with the next player, is next player not able to win
                if (!dfs(sign)) {
                    // set it back => backtracking
                    setSign(sign, i, true);
                    // current player win
                    return true;
                }
                // set it back => backtracking
                setSign(sign, i, true);
            }
        }
        // no other else to move
        return false;
    }

    private void setSign(boolean[] sign, int i, boolean value) {
        sign[i] = value;
        sign[i + 1] = value;
    }
}
