package daohuei.leetcodelizard;

import java.util.Arrays;

/*
 * 324. Wiggle Sort II
 * Link: https://leetcode.com/problems/wiggle-sort-ii/
 */
public class WiggleSortTwo {
    /**
     * @author: daohuei
     * @description: sort method
     * @time: O(nlogn): for sorting
     * @space: O(n): for temp array
     */
    public void sortMethod(int[] nums) {
        // sort first
        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        // s -> mid point, t -> end point
        int s = (nums.length + 1) / 2, t = nums.length;
        for (int i = 0; i < nums.length; i++) {
            // if even, assign smaller point
            // if odd, assign larger point
            temp[i] = (i % 2) == 0 ? nums[--s] : nums[--t];
        }
        // reassign to nums
        for (int i = 0; i < nums.length; i++)
            nums[i] = temp[i];
    }

    /**
     * @author: daohuei
     * @description: sort method
     * @time: O(n): but worst case can be n^2
     * @space: O(n): for temp array
     */
    public void wiggleSort(int[] nums) {
        // found the median
        int median = findMedian(nums, 0, nums.length - 1, (nums.length + 1) / 2);
        int s = 0, t = nums.length - 1, mid_index = (nums.length + 1) / 2;

        // use temp to get the reverse sorted array
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < median)
                temp[s++] = nums[i];
            else if (nums[i] > median)
                temp[t--] = nums[i];
        }

        // there may have multiple medians
        // put them back the reset of places
        while (s < mid_index)
            temp[s++] = median;
        while (t >= mid_index)
            temp[t--] = median;

        // same as above
        t = nums.length;
        for (int i = 0; i < nums.length; i++)
            nums[i] = (i & 1) == 0 ? temp[--s] : temp[--t];
    }

    // the function for getting median
    private int findMedian(int[] nums, int L, int R, int k) {
        // if L exceed R
        // return R only
        if (L >= R)
            return nums[R];
        // i is the last pivot location
        int i = partition(nums, L, R);
        // if numbers count in the left is k
        // means we found the median
        int cnt = i - L + 1;
        if (cnt == k)
            return nums[i];
        // if larger, find left
        // if smaller, find right
        return cnt > k ? findMedian(nums, L, i - 1, k) : findMedian(nums, i + 1, R, k - cnt);
    }

    // doing quicksort
    private int partition(int[] nums, int L, int R) {
        // val is pivot
        int val = nums[L];
        int i = L, j = R + 1;
        while (true) {
            while (++i < R && nums[i] < val)
                ;
            while (--j > L && nums[j] > val)
                ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, L, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}