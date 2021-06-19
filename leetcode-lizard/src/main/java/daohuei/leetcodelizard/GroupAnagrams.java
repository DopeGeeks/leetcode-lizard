package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * 49. Group Anagrams
 * Link: https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagrams {
    /**
     * @author: daohuei
     * @description: hash map
     * @time: O(nKlog(K)): n = length of string array, k = length of chars array
     * @space: O(NK): for hash map
     */
    public List<List<String>> hashMap(String[] strs) {
        HashMap<String, List<String>> hash = new HashMap<>();
        // go through the strings
        for (int i = 0; i < strs.length; i++) {
            char[] s_arr = strs[i].toCharArray();
            // sort the string chars first
            Arrays.sort(s_arr);
            // convert to the string
            String key = String.valueOf(s_arr);
            // use hash map for memorization
            if (hash.containsKey(key)) {
                // if exist, put it into the array of such key
                hash.get(key).add(strs[i]);
            } else {
                // if not existed before
                // put a new array with a key into hash map
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }
        }
        // return array with values(an array of arrays)
        return new ArrayList<List<String>>(hash.values());
    }

    /**
     * @author: daohuei
     * @description: hash map with no sorting
     * @time: O(nk): n = length of string array, k = length of chars array
     * @space: O(NK): for hashmap
     */
    public List<List<String>> hashMapWithNoSort(String[] strs) {
        // init hashmap with key string and value string list
        HashMap<String, List<String>> hash = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            // num for memorizing all characters count
            int[] num = new int[26];
            // memorize char for current string
            for (int j = 0; j < strs[i].length(); j++) {
                num[strs[i].charAt(j) - 'a']++;
            }
            // format string to be like 0#2#2#
            String key = "";
            // and make it as hash key
            for (int j = 0; j < num.length; j++) {
                key = key + num[j] + '#';
            }
            // same as previous method
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }
}