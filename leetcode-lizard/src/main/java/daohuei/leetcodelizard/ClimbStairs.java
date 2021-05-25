package daohuei.leetcodelizard;

/*
 * 70. Climbing Stairs
 * Link: https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbStairs {

    /**
     * @author: daohuei
     * @description: brutal force: this is actually same as Fibonacci Sequence
     * @time: O(2^n): using binary tree
     * @space: O(n): binary tree
     */
    public int brutalForce(int n) {
        return climbStairsNBrutalForce(n);
    }

    private int climbStairsNBrutalForce(int n) {
        // base case
        if (n == 1) {
            // if 1 then return 1
            return 1;
        }
        if (n == 2) {
            // if 2 then return 2
            return 2;
        }
        // should be mixture of previous 2 step
        return climbStairsNBrutalForce(n - 1) + climbStairsNBrutalForce(n - 2);
    }

    /**
     * @author: daohuei
     * @description: memorization: using integer array(or u can use hashmap,
     *               whatever)
     * @time: O(2^n): using binary tree
     * @space: O(n): for integer array and binary tree
     */
    public int memorization(int n) {
        int memo[] = new int[n + 1];
        return climbStairsNMemorization(n, memo);
    }

    private int climbStairsNMemorization(int n, int[] memo) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int n1 = 0;
        // integer array has default element 0
        if (memo[n - 1] == 0) {
            // if no answer in previous stair, check previous stair
            n1 = climbStairsNMemorization(n - 1, memo);
            memo[n - 1] = n1;
        } else {
            // if already have it, take it out
            n1 = memo[n - 1];
        }
        int n2 = 0;
        if (memo[n - 2] == 0) {
            n2 = climbStairsNMemorization(n - 2, memo);
            memo[n - 2] = n2;

        } else {
            n2 = memo[n - 2];
        }
        return n1 + n2;
    }

    /**
     * @author: daohuei
     * @description: dynamically update 
     * @time: O(n): only use single for loop to dynamically update 
     * @space: O(n): for integer array and binary tree
     */
    public int dynamicallyProgramming(int n) {
        int n1 = 1;
        int n2 = 2;
        if (n == 1) {
            return n1;
        }
        if (n == 2) {
            return n2;
        }
        // let n1 and n2 move forward one step
        for (int i = 3; i <= n; i++) {
            int temp = n2;
            n2 = n1 + n2;
            n1 = temp;
        }
        return n2;
    }
}
