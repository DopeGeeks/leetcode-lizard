package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*
 * 139. Word Break 
 * Link: https://leetcode.com/problems/word-break/
 */
public class WordBreak {
    /**
     * @author: daohuei
     * @description: hashmap
     * @time: O(n^2): find every combination
     * @space: O(n^2): hashmap store boolean for every combination
     */
    public boolean hashMap(String s, List<String> wordDict) {
        // go with hashmap method
        return wordBreakHelper(s, wordDict, "", new HashMap<String, Boolean>());
    }

    private boolean wordBreakHelper(String s, List<String> wordDict, String temp, HashMap<String, Boolean> hashMap) {
        // if temp string exceed the target string
        if (temp.length() > s.length()) {
            return false;
        }

        // if the temp have been formed before
        if (hashMap.containsKey(temp)) {
            return hashMap.get(temp);
        }
        // check if temp match the string so far
        for (int i = 0; i < temp.length(); i++) {
            if (s.charAt(i) != temp.charAt(i)) {
                return false;
            }
        }
        // if they are all matched
        if (s.length() == temp.length()) {
            return true;
        }

        // n * n
        for (int i = 0; i < wordDict.size(); i++) {
            // check next character
            if (wordBreakHelper(s, wordDict, temp + wordDict.get(i), hashMap)) {
                // put the result into the hashmap
                hashMap.put(temp, true);
                return true;
            }
        }
        // put the result into the hashMap
        hashMap.put(temp, false);
        return false;
    }

    /**
     * @author: daohuei
     * @description: dp
     * @time: O(n^2): find every combination
     * @space: O(n): for dp array and the hashset
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // store the hash set for evey string in dict
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < wordDict.size(); i++) {
            set.add(wordDict.get(i));
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // go through all combination
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // previously works and the current interval is in the set
                dp[i] = dp[j] && set.contains(s.substring(j, i));
                // if the true found, then break
                if (dp[i]) {
                    break;
                }
            }
        }
        // the final bool is the answer
        return dp[s.length()];
    }
}