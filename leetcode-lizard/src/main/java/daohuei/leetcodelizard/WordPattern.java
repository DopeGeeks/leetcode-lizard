package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 * 290. Word Pattern
 * Link: https://leetcode.com/problems/word-pattern/description/
 */
public class WordPattern {
    /**
     * @author: daohuei
     * @description: hash map
     * @time: O(n): loop once
     * @space: O(n): for hashmap
     */
    public boolean hashMap(String pattern, String s) {
        String[] array1 = s.split(" ");
        // check length is same as pattern
        if (array1.length != pattern.length()) {
            return false;
        }
        // get pattern array
        String[] array2 = pattern.split("");
        // need to check with 2 directions
        return wordPatternHelper(array1, array2) && wordPatternHelper(array2, array1);
    }

    private boolean wordPatternHelper(String[] array1, String[] array2) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            // map pattern with string
            String key = array1[i];
            // if key exist
            if (map.containsKey(key)) {
                // compare if they are same
                if (!map.get(key).equals(array2[i])) {
                    return false;
                }
            } else {
                // store the key val
                map.put(key, array2[i]);
            }
        }
        return true;
    }

    /**
     * @author: daohuei
     * @description: hash map with object equal
     * @time: O(n): loop once
     * @space: O(n): for hashmap
     */
    public boolean hashMapObjectEqual(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (int i = 0; i < words.length; ++i)
            // using object equal can compare i while putting
            if (!Objects.equals(index.put(pattern.charAt(i), i), index.put(words[i], i)))
                return false;
        return true;
    }
}