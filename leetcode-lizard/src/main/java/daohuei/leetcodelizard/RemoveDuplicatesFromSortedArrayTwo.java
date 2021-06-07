package daohuei.leetcodelizard;

/*
 * 80. Remove Duplicates from Sorted Array II
 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArrayTwo {
    /**
     * @author: daohuei
     * @description: slow fast pointers
     * @time: O(n): only walkthrough once with fast pointer
     * @space: O(1): not using any
     */
    public int removeDuplicates(int[] nums) {
        int slow = 1;
        int fast = 2;
        int max = 2;
        for (; fast < nums.length; fast++) {
            // when not the same, put into the position next to slow
            // since that one fast has already met
            // slow - max + 1 means the tolerance range of max duplicates
            // so we skip if equal and exceed the range
            if (nums[fast] != nums[slow - max + 1]) {
                slow++;
                nums[slow] = nums[fast];
            }

        }
        // slow will be the end index of the non-duplicated elements
        return slow + 1;
    }
}