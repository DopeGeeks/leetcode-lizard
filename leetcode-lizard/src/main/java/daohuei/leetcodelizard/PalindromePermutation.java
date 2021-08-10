package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 266. Palindrome Permutation
 * Link: https://leetcode.com/problems/palindrome-permutation/description/
 */
public class PalindromePermutation {
    /**
     * @author: daohuei
     * @description: hashmap method
     * @time: O(n): for go through the string
     * @space: O(n): for hashmap, worst case n
     */
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            String str = s.charAt(i) + "";
            // count with hash map
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }

        // go through every entry
        int countOdd = 0;
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            // if not the multiple of 2
            if (entry.getValue() % 2 == 1) {
                // add odd count
                countOdd++;
            }
            // if larger than 1, return false
            if (countOdd > 1) {
                return false;
            }
        }
        return true;
    }
}
