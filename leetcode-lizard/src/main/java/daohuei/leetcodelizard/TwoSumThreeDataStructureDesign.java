package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * 170. Two Sum III - Data Structure Design
 * Link: https://leetcode.com/problems/two-sum-iii-data-structure-design/description/
 */
public class TwoSumThreeDataStructureDesign {
    /**
     * @author: daohuei
     * @description: hashmap method with memorization
     * @time: O(n): worst case: search all entry in the hashmap, best case: constant
     *        for memo!
     * @space: O(n): for hashmap
     */
    class TwoSum {
        // hashmap for storing the count of numbers
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> memo = new HashSet<>();

        /** Initialize your data structure here. */
        public TwoSum() {
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            // add count of number
            map.putIfAbsent(number, 0);
            map.put(number, map.get(number) + 1);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            // if memo contains value => which means has been searched before and found is
            // true
            if (memo.contains(value))
                return true;
            // go through every map entry
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                // get the number and count
                int num = entry.getKey(), count = entry.getValue();
                // calculate the remain
                int remain = value - num;
                // if remain number is in hashmap
                if (map.containsKey(remain)) {
                    // if current number is remain, and we only got 1 such num
                    if (remain == num && count < 2)
                        // means it is not possible
                        continue;
                    // if found, add the found record into the memo
                    memo.add(value);
                    return true;
                }
            }
            return false;
        }
    }
}
