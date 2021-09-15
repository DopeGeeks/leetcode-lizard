package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 30. Substring with Concatenation of All Words
 * Link: https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 */
public class SubstringWithConcatenationOfAllWords {
    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(n^2): nested loop for the input string
     * @space: O(k): hashmap for storing target word count
     */
    public List<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        // if the string length is less than all words combination
        if (words.length == 0 || s.length() < words.length * words[0].length())
            return list;

        // create a map for the wordcount
        HashMap<String, Integer> mapWords = new HashMap<String, Integer>();
        for (String s1 : words) {
            mapWords.put(s1, mapWords.getOrDefault(s1, 0) + 1);
        }

        // for wordcount of the input string
        HashMap<String, Integer> mapS1 = new HashMap<String, Integer>();
        // num => total substring length
        // wordlength => single word length
        int index = 0, wordLength = words[0].length(), num = (words.length * wordLength);
        // while current index plus total substring length is not out of bound
        while (index + num <= s.length()) {
            // the begin string
            int begin = index;
            String s2 = s.substring(begin, begin + wordLength);
            // if it is in the target words map
            while (mapWords.containsKey(s2)) {
                // put it into the another map and count it
                mapS1.put(s2, mapS1.getOrDefault(s2, 0) + 1);
                // if another map has more count than target word map
                // means there is redundant target word in the input string
                if (mapWords.get(s2) < mapS1.get(s2))
                    break;

                // move to next word in the string
                begin = begin + wordLength;
                // if still in the range
                if (begin + wordLength <= index + num)
                    // update the substring
                    s2 = s.substring(begin, begin + wordLength);
                else
                    // out of range we break the loop
                    break;
            }
            // if 2 map finally got same count: means an any order substring is found
            if (mapWords.equals(mapS1))
                // add to the result
                list.add(index);
            // move forward
            ++index;
            // clear the string map
            mapS1.clear();
        }
        return list;
    }
}