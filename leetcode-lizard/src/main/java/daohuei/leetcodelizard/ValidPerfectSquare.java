package daohuei.leetcodelizard;

/*
 * 367. Valid Perfect Square
 * Link: https://leetcode.com/problems/valid-perfect-square/
 */
public class ValidPerfectSquare {
    /**
     * @author: daohuei
     * @description: binary search method
     * @time: O(logn): binary search
     * @space: O(1): not using any
     */
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;
        while (left <= right) {
            // to avoid overflow incase (left+right)>2147483647
            int mid = left + (right - left) / 2;
            // remain is for make sure no remainders
            int remain = num % mid;
            // check if mid * mid == num
            if (mid == num / mid && remain == 0)
                return true;
            else if (mid > num / mid)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }
}