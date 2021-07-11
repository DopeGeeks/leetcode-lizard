package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.HashSet;

/*
 * 287. Find the Duplicate Number
 * Link: https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindDuplicateNumber {
    /**
     * @author: daohuei
     * @description: sort
     * @time: O(nlogn): for sorting
     * @space: O(1): not using any
     */
    public int sortMethod(int[] nums) {
        // just sort and check with the next one
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * @author: daohuei
     * @description: hash set
     * @time: O(n): just iter through nums
     * @space: O(n): n for worst case in hash map
     */
    public int hashSetMethod(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // check if appear before
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }

    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(nlogn): just iter through nums with binary search
     * @space: O(1): not using any
     */
    public int binarySearchMethod(int[] nums) {
        int n = nums.length - 1;
        int low = 1;
        int high = n;
        // from 1 to n
        while (low < high) {
            // get the mid for low and high
            int mid = (low + high) >>> 1;
            int count = 0;
            // go through every num
            for (int i = 0; i < nums.length; i++) {
                // if less equal than mid, count it
                if (nums[i] <= mid) {
                    count++;
                }
            }
            // if count is larger than mid, there must be some duplicate has higher value
            if (count > mid) {
                high = mid;
            } else {
                // if less equal than, than search left
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * @author: daohuei
     * @description: bit operations
     * @time: O(32n)=O(n): just iter through nums with 32 bits
     * @space: O(1): not using any
     */
    public int bitOperations(int[] nums) {
        int res = 0;
        int n = nums.length;
        // calculate every bit
        for (int i = 0; i < 32; i++) {
            int a = 0;
            int b = 0;
            // get the mask shift to the i position
            int mask = (1 << i);
            for (int j = 0; j < n; j++) {
                // count the 1 of the number in this position
                if ((nums[j] & mask) > 0) {
                    a++;
                }
                // count the 1 of this position
                if ((j & mask) > 0) {
                    b++;
                }
            }
            // a = actual 1 count of numbers
            // b = prospected 1 count of numbers
            // compare a and b
            // if a > b: there is an additional bit in this position
            if (a > b) {
                // get this bit in the result
                res = res | mask;
            }
        }
        return res;
    }

    /**
     * @author: daohuei
     * @description: slow fast pointers
     * @time: O(n): just iter through nums with fast slow pointers
     * @space: O(1): not using any
     */
    public int slowFastPointers(int[] nums) {
        // considering the duplicated number is the cycle point
        // use the same way to find duplicated number
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            // move 1 step
            slow = nums[slow];
            // move 2 steps
            fast = nums[nums[fast]];
        }
        // when slow meet fast
        slow = 0;
        while (slow != fast) {
            // they will meet at cycle point when both moving bu 1 step
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}