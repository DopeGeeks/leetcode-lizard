package daohuei.leetcodelizard;

/*
 * 338. Counting Bits
 * Link: https://leetcode.com/problems/counting-bits/description/
 */
public class CountingBits {
    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n): just go through the n
     * @space: O(1): not using any
     */
    public int[] countBits(int n) {
        // empty case
        if (n == 0) {
            return new int[] { 0 };
        }
        int[] res = new int[n + 1];
        // initialization
        res[1] = 1;
        // start from 2
        for (int i = 2, k = 1; i <= n; i++) {
            if (i < 2 * k) {
                // assume k is a multiple of two
                // then for k < i < 2k, f(i) = 1 + f(i - k)
                // since i - k means i removing the largest digit
                // after the removal, we have the result already in the dp array
                // then just pick it up and add it with the count for hte largest digit, which
                // is 1
                res[i] = 1 + res[i - k];
            } else {
                // if it is the 2's power
                // only got single 1 bit
                k *= 2;
                res[i] = 1;
            }
        }
        return res;
    }
}