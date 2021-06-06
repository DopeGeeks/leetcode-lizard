package daohuei.leetcodelizard;

/*
 * 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {
    /**
     * @author: daohuei
     * @description: 3 pointers for nums1, nums2, insert points
     * @time: O(max(m,n)): only traverse m to n time if m<n, worst case can be n
     *        times
     * @space: O(1): not using any
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // all pointers start from the end
        // for traverse nums1 original value
        int i = m - 1;
        // for traverse num2 values
        int j = n - 1;
        // for insert into nums1
        int k = m + n - 1;
        // if finish nums2, means just finished
        while (j >= 0) {
            // if finish nums1 original value first
            if (i < 0) {
                // put all the rest of nums2 into nums1 into k
                while (j >= 0) {
                    nums1[k--] = nums2[j--];
                }
                return;
            }
            // add the larger one and then move backward
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}