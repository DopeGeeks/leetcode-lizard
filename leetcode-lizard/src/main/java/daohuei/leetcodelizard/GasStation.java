package daohuei.leetcodelizard;

/*
 * 134. Gas Station 
 * Link: https://leetcode.com/problems/gas-station/
 */
public class GasStation {
    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(n^2): check every start with every points
     * @space: O(1): not using any
     */
    public int brutalForce(int[] gas, int[] cost) {
        int n = gas.length;
        // every iter is a new start
        for (int i = 0; i < n; i++) {
            // j is the current point
            int j = i;
            // init the remain gas with the start point gas
            int remain = gas[i];
            // if the remain is sufficient to the next point
            while (remain - cost[j] >= 0) {
                // update the remain by removing the cost and refilling the gas
                remain = remain - cost[j] + gas[(j + 1) % n];
                // move forward (% means if equal n, then we get 0)
                j = (j + 1) % n;
                // if we make a circuit
                if (j == i) {
                    return i;
                }
            }
        }
        // failed!
        return -1;

    }

    /**
     * @author: daohuei
     * @description: optimized brutal force
     * @time: O(n^2): check every start with every points(worst case)
     * @space: O(1): not using any
     */
    public int optimizedBrutalForce(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            // same as above
            while (remain - cost[j] >= 0) {
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                if (j == i) {
                    return i;
                }
            }
            // this means j already pass the last index of gas
            // means we have tried all the refilled but still not work
            // therefore, there is no need to try more
            if (j < i) {
                return -1;
            }
            // we can start with the last end as the next start
            i = j;

        }
        return -1;
    }
}