package daohuei.leetcodelizard;

/*
 * 69. Sqrt(x)
 * Link: https://leetcode.com/problems/sqrtx/
 */
public class MySqrt {

    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(log(x)) since using binary search
     * @space: O(1) only use 2 pointer
     */
    public int binaryMethod(int x) {
        // left and right
        int L = 1, R = x;
        // use for storing answers
        int ans = 0;
        while (L <= R) {
            // to prevent overflow from (R+L)/2 when x = Integer.MAXVALUE
            int mid = L + (R - L) / 2;
            // also for prevent overflow
            int div = x / mid;
            // if x/mid = mid, then it should be the answer
            if (div == mid) {
                return mid;
            } else if (mid < div) {
                // if x/mid > mid, we still store the value since it may be the largest integer
                // for the square root
                ans = mid;
                // move the L to mid + 1
                L = mid + 1;
            } else {
                // move the R to mid -1
                R = mid - 1;
            }
        }
        return ans;
    }
}
