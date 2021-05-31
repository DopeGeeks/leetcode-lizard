package daohuei.leetcodelizard;

import java.util.HashSet;

/*
 * 136. Single Number
 * Link: https://leetcode.com/problems/single-number/
 */
public class SingleNumber {

    /**
     * @author: daohuei
     * @description: hashset for storing unique, get the diff of unique and
     *               non-unique sum, the diff will be the single number
     * @time: O(n): n = array length
     * @space: O(n): the hashset
     */
    public int hashSet(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // non-unique sum
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // put every element into the set(unique)
            set.add(nums[i]);
            sum += nums[i];
        }
        // unique sum from iter through the set
        int sumUnique = 0;
        for (int n : set) {
            sumUnique += n;
        }
        // multiply 2
        sumUnique = sumUnique * 2;
        // the diff between double unique sum and sum is the answer
        return sumUnique - sum;
    }

    /**
     * @author: daohuei
     * @description: exclusive or
     * @time: O(n): n = array length
     * @space: O(1): not using any
     */
    public int exclusiveOr(int[] nums) {
        int ans = 0;
        /**
         * a ⊕ b ⊕ a ⊕ b ⊕ c ⊕ c ⊕ d = ( a ⊕ a ) ⊕ ( b ⊕ b ) ⊕ ( c ⊕ c ) ⊕ d = 0 ⊕ 0 ⊕ 0
         * ⊕ d = d
         */
        for (int i = 0; i < nums.length; i++) {
            // exclusive or with each other
            ans ^= nums[i];
        }
        return ans;
    }
}
