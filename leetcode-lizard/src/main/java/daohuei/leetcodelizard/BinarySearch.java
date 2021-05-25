package daohuei.leetcodelizard;
/*  
 * Author: @ballm06m06
 * Qusetion: Binary Search
 * Description: Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. 
 *              If target exists, then return its index, otherwise return -1.
 *            
 *  Time complexity : O(logN) 
 *  Space complexity : O(1) since it's a constant space solution.
 */

public class BinarySearch {
    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    // Using recursion
    // 100%, 27%
    public static int binarySearch(int[] nums, int l, int r, int target) {

        if (r >= l) {
            int mid = (l + r) / 2;

            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                return binarySearch(nums, l, mid - 1, target);
            else
                return binarySearch(nums, mid + 1, r, target);
        }
        return -1;
    }

    // Using iterative
    // 100%, 81.28%
    public int binarySearch1(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (r >= l) {
            int mid = (l + r) / 2;

            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

}
