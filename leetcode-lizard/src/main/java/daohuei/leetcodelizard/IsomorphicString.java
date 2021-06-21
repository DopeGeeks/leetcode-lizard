package daohuei.leetcodelizard;

import java.util.HashMap;

/*
 * 205. Isomorphic Strings
 * Link: https://leetcode.com/problems/isomorphic-strings/description/
 */
public class IsomorphicString {
    /**
     * @author: daohuei
     * @description: hashmap
     * @time: O(n): traverse string, but 2 pass
     * @space: O(n): for storing unique mapping
     */
    public boolean hashMap(String s, String t) {
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
    }

    private boolean isIsomorphicHelper(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            // if not first seen
            if (map.containsKey(c1)) {
                // check if the mapping matched
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                // put the map of first seen
                map.put(c1, c2);
            }
        }
        return true;
    }

    /**
     * @author: daohuei
     * @description: two hashmap
     * @time: O(n): traverse string once
     * @space: O(1): but actually using 2 * all amount of alphabets
     */
    public boolean twoHashMap(String s, String t) {
        int n = s.length();
        // init 2 map with size of all amount of alphabets
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            // if mapping value the same, they matched
            if (mapS[c1] != mapT[c2]) {
                return false;
            } else {
                // use index for mapping value
                if (mapS[c1] == 0) {
                    mapS[c1] = i + 1;
                    mapT[c2] = i + 1;
                }
            }
        }
        return true;
    }
}