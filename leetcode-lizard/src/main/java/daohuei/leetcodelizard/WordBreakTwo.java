package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*
 * 140. Word Break II
 * Link: https://leetcode.com/problems/word-break-ii/
 */
public class WordBreakTwo {
    /**
     * @author: daohuei
     * @description: recursion
     * @time: O(n!): find every combination
     * @space: O(n!): for hashmap with all possible substring
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        // store every string in dict in the hashset
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < wordDict.size(); i++) {
            set.add(wordDict.get(i));
        }
        return wordBreakHelper(s, set, new HashMap<String, List<String>>());
    }

    private List<String> wordBreakHelper(String s, HashSet<String> set, HashMap<String, List<String>> map) {
        // empty case
        if (s.length() == 0) {
            return new ArrayList<>();
        }
        // if the string already exist
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        for (int j = 0; j < s.length(); j++) {
            // check if the current substring is valid string in given dict(hashset)
            if (set.contains(s.substring(j, s.length()))) {
                // if j is the 0 index
                if (j == 0) {
                    // directly add whole string as answer
                    res.add(s.substring(j, s.length()));
                } else {
                    // get the answer from all possible substrings
                    List<String> temp = wordBreakHelper(s.substring(0, j), set, map);
                    // combine the temp answer as the res answer
                    for (int k = 0; k < temp.size(); k++) {
                        String t = temp.get(k);
                        res.add(t + " " + s.substring(j, s.length()));
                    }
                }

            }
        }
        // store in the hash map with the key of substring/or whole string
        map.put(s, res);
        return res;
    }
}