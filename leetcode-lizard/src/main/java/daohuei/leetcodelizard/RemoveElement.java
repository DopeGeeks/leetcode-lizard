package daohuei.leetcodelizard;

/*
 * 27. Remove Element
 * Link: https://leetcode.com/problems/remove-element/
 */
public class RemoveElement {

    /**
     * @author: daohuei
     * @description: fast slow pointer method
     * @time: O(n): n = nums.length
     * @space: O(1): only 2 pointer
     */
    public int twoPointer(int[] nums, int val) {
        int fast = 0;
        int slow = 0;
        while (fast < nums.length) {
            // if fast is not the value
            if (nums[fast] != val) {
                // if not the value to be removed, just put to slow index
                // so that it only puts value we need to the front
                // slow move forward
                nums[slow++] = nums[fast];
            }
            // keep moving
            fast++;
        }
        return slow;
    }

    /**
     * @author: daohuei
     * @description: start end pointer method
     * @time: O(n): n = nums.length
     * @space: O(1): only 2 pointer
     */
    public int startEndPointers(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                // if start element should be removed
                // replace with end element
                nums[i] = nums[n - 1];
                // end index - 1
                n--;
            } else {
                // else do nothing but start index + 1
                i++;
            }
        }
        return n;
    }
}
