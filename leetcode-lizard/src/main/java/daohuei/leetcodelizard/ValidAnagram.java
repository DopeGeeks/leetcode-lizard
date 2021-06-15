package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 242. Valid Anagram
 * Link: https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {
    /**
     * @author: daohuei
     * @description: hashmap
     * @time: O(n): iterations through s and t
     * @space: O(n): for hash map
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            // count every words in hashmap
            int count = map.getOrDefault(sArray[i], 0);
            map.put(sArray[i], count + 1);
        }

        char[] tArray = t.toCharArray();
        for (int i = 0; i < tArray.length; i++) {
            // remove count of every words by 1 from hashmap
            int count = map.getOrDefault(tArray[i], 0);
            // if already zero
            // means never appears before
            if (count == 0) {
                // not anagram, sorry bro
                return false;
            }
            map.put(tArray[i], count - 1);
        }
        return true;
    }
}