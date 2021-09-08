package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 354. Russian Doll Envelopes
 * Link: https://leetcode.com/problems/russian-doll-envelopes/description/
 */
public class RussianDollEnvelopes {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n^2): nested looping envelopes
     * @space: O(n): for dp array
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        // sort according the width then height
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });

        int n = envelopes.length;
        int[] dp = new int[n];
        int max = 0;
        // go through every envelope
        for (int i = 0; i < n; i++) {
            // every dp start with 1 envelop
            dp[i] = 1;
            // go through every envelope potentially smaller than current one
            for (int j = 0; j < i; j++) {
                // if smaller then compare with dp and choose the max one
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    // dp[j] + 1 indicate j envelop's max + 1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // get the max of dp[i]
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}