package daohuei.leetcodelizard;

import java.util.HashSet;
import java.util.PriorityQueue;

/*
 * 264. Ugly Number II
 * Link: https://leetcode.com/problems/ugly-number-ii/description/
 */
public class UglyNumberTwo {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): go through number to n
     * @space: O(n): for dp array
     */
    public int dp(int n) {
        // base/empty case
        if (n <= 1)
            return n;
        // dp array for
        int[] dp = new int[n];
        dp[0] = 1;
        // index indicates the position of number that will multiply its corresponding
        // factor (2,3,5) next
        int index2 = 0, index3 = 0, index5 = 0;
        // the factor is the current product of corresponding factor(2,3,5)
        int factor2 = 2, factor3 = 3, factor5 = 5;
        // iterate from 1 to n
        for (int i = 1; i < n; i++) {
            // get the min of 3 factors
            int min = Math.min(Math.min(factor2, factor3), factor5);
            // the smallest one should be assigned first
            dp[i] = min;
            // update the next factor (only update the smallest one)
            if (factor2 == min)
                factor2 = dp[++index2] * 2;
            if (factor3 == min)
                factor3 = dp[++index3] * 3;
            if (factor5 == min)
                factor5 = dp[++index5] * 5;
        }
        // return the last dp element -> indicates nth ugly number
        return dp[n - 1];
    }

    /**
     * @author: daohuei
     * @description: dp + priority queue
     * @time: O(n): go through number to n
     * @space: O(n): for dp array
     */
    public int nthUglyNumber(int n) {
        if (n <= 1)
            return n;

        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        PriorityQueue<Long> candidate = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        set.add(dp[1]);
        for (int i = 2; i <= n; i++) {
            // same as previous method, but using priority queue for getting min value
            // instead
            populate(candidate, set, dp, dp[i - 1], 2);
            populate(candidate, set, dp, dp[i - 1], 3);
            populate(candidate, set, dp, dp[i - 1], 5);
            dp[i] = candidate.poll();
        }

        return (int) dp[n];
    }

    private void populate(PriorityQueue<Long> candidate, HashSet<Long> set, long[] dp, long last, int factor) {
        long nextCandidate = last * factor;
        if (!set.contains(nextCandidate)) {
            candidate.add(nextCandidate);
            set.add(nextCandidate);
        }
    }
}