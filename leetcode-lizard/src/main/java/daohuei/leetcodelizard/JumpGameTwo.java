package daohuei.leetcodelizard;

/*
 * 45. Jump Game II
 * Link: https://leetcode.com/problems/jump-game-ii/
 */
public class JumpGameTwo {
    /**
     * @author: daohuei
     * @description: bfs-like method
     * @time: O(n): single loop
     * @space: O(1): constant
     */
    public int jump(int[] nums) {
        // jumps -> level
        // curFarthest -> next level size
        // curEnd -> current level size
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // mark the next level's max place
            curFarthest = Math.max(curFarthest, i + nums[i]);
            // if we finished examining current level
            if (i == curEnd) {
                // move to next level
                jumps++;
                // and update current level size
                curEnd = curFarthest;
            }
        }
        // the level is the answer
        return jumps;
    }
}