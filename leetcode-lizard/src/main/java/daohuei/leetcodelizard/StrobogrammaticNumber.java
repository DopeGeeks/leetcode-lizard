package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 246. Strobogrammatic Number
 * Link: https://leetcode.com/problems/strobogrammatic-number/description/
 */
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.
Tags: Hash Table Math
Similar Problems: (M) Strobogrammatic Number II, (H) Strobogrammatic Number III
*/
public class StrobogrammaticNumber {
    /**
     * @author: daohuei
     * @description: hashMap method
     * @time: O(n): for 2 pointer loops
     * @space: O(1): constant in map
     */

    public boolean hashMapMethod(String num) {
        if (num == null || num.length() == 0)
            return true;
        HashMap<Character, Character> map = buildMap();
        // two pointer for checking the num
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            char charL = num.charAt(left++), charR = num.charAt(right--);
            // if any of them are not strobogrammatic
            // of left is not equal the reverse of right
            if (!map.containsKey(charR) || (charL != map.get(charR))) {
                return false;
            }
        }
        return true;
    }

    // build the map for strobogrammatic transfer
    private HashMap<Character, Character> buildMap() {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        return map;
    }
}
