package daohuei.leetcodelizard;
/*  
 * Author: @ballm06m06
 * Qusetion: Remove Duplicates from Sorted Array
 * Description: Given a sorted array nums, remove the duplicates in-place such that 
 *              each element appears only once and returns the new length.
 *
 *                  i
 * Example: nums = [0,0,1,1,1,2,2,3,3,4]
 *                      j
 *                          i
 *          nums = [0,1,2,3,4,2,2,3,3,4]
 *                                    j                
 *
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(1)
 */

public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {

        // i is a slow pointer, only looking for distinct elements
        int i = 0;

        // j is a fast pointer, will go through whole array
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                /*
                 * Interesting facts: nums[++i] or nums[i++] will actually increment i's value,
                 * while nums[i+1] won't. So remember to use i++ when you're using nums[i+1]
                 */
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }
}
