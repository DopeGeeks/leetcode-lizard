package daohuei.leetcodelizard;

/*
 * 55. Jump Game
 * Link: https://leetcode.com/problems/jump-game/
 */
public class JumpGame {

    /**
     * @author: daohuei
     * @description: single loop
     * @time: O(n) 1 loop only through nums
     * @space: O(1) not using any
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // max means so far the most we can reach with current index
            if (i > max) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }
}