package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 215. Kth Largest Element in an Array
 * Link: https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestElementInArray {
    /**
     * @author: daohuei
     * @description: brutal force
     * @time: O(nlogn): if doing merge sort
     * @space: O(n): for merge sort
     */
    public int brutalForce(int[] nums, int k) {
        // sort and get the number directly
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * @author: daohuei
     * @description: quick sort method
     * @time: O(nlogn): for the quick sort -> nlogn is the worst case
     * @space: O(logn): for quick sort recursion stack
     */
    public int quickSortMethod(int[] nums, int k) {
        return findKthLargestHelper(nums, 0, nums.length - 1, k);
    }

    private int findKthLargestHelper(int[] nums, int start, int end, int k) {
        // using quick sort
        int i = start;
        // getting the pivot with end
        int pivot = nums[end];
        // from start to the end
        for (int j = start; j < end; j++) {
            if (nums[j] >= pivot) {
                // get all the large one to the left
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        // exchange pivot and the last point i
        // (put the pivot back)
        int temp = nums[i];
        nums[i] = pivot;
        nums[end] = temp;
        // count -> the large number count
        int count = i - start + 1;
        if (count == k) {
            // if the larger number count is k, then the pivot is the k largest number
            return nums[i];
        } else if (count < k) {
            // if still less than k, means there are some larger part at the right side
            // search the right side!
            return findKthLargestHelper(nums, i + 1, end, k - count);
        } else {
            // if larger than k, means there are some redundant number which should be
            // smaller one at the left side
            // quicksort left
            return findKthLargestHelper(nums, start, i - 1, k);
        }
    }

    /**
     * @author: daohuei
     * @description: priority queue method
     * @time: O(nlogn): for priority queue sorting
     * @space: O(n): for queue
     */
    public int priorityQueueMethod(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<Integer>();
        for (int i = 0; i < nums.length; i++) {
            // only store the largest k number in the queue
            // will sort when offer into it
            q.offer(nums[i]);
            // if larger than k, poll the smallest out
            if (q.size() > k) {
                q.poll();
            }
        }
        // when finished, poll the smallest out -> kth largest
        return q.poll();
    }
}