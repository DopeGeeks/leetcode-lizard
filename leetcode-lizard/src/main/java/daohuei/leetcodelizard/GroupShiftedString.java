package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 249. Group Shifted Strings
 * Link: https://leetcode.com/problems/group-shifted-strings/description/
 */
/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:
[
    ["abc","bcd","xyz"],
    ["az","ba"],
    ["acef"],
    ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
Hide Company Tags Google Uber
Hide Tags Hash Table String
Hide Similar Problems (M) Group Anagrams
*/

public class GroupShiftedString {
    /**
     * @author: daohuei
     * @description: hashmap method
     * @time: O(n): go through words list
     * @space: O(n): for storing origin map in the hashmap
     */
    public List<List<String>> groupStrings(String[] strings) {
        // generate hashmap for all strings
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            // get the shifted origin string(start from 'a')
            String origin = convert(s);
            // use it as key of map to put the same origin together
            map.putIfAbsent(origin, new ArrayList<String>());
            map.get(origin).add(s);
        }
        // return all answers in the map
        return new ArrayList<>(map.values());
    }

    // shift s to the string start from 'a'
    private String convert(String s) {
        StringBuffer sb = new StringBuffer();
        int offset = s.charAt(0) - 'a';
        // for every char in the string
        for (char c : s.toCharArray()) {
            // shift the character to the sequence started from 'a'
            // the first newChar must be 'a'
            char newChar = (char) (c - offset);
            // if less than 'a', we search for next round of char
            if (newChar < 'a')
                newChar += 26;
            sb.append(newChar);
        }
        return sb.toString();
    }
}
