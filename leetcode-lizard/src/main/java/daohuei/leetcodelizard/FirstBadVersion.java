package daohuei.leetcodelizard;

/*
 * 278. First Bad Version
 * Link: https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(n): latest version n
     * @space: O(1): not using any
     */
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        while (low < high) {
            // logically shift -> equal to divide 2
            int mid = (low + high) >>> 1;
            if (isBadVersion(mid)) {
                // we still need to keep mid,
                // so should not be mid-1, or else we will miss it
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public boolean isBadVersion(int ver) {
        return true;
    }
}