package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 245. Shortest Word Distance III
 * Link: https://leetcode.com/problems/shortest-word-distance-iii/description/
 */
/*
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 
Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/
public class ShortestWordDistanceThree {

    /**
     * @author: daohuei
     * @description: hashmap method
     * @time: O(n): go through words list
     * @space: O(n): for storing word location in the hashmap
     */
    class WordDistance {
        // hashmap for storing word's location
        Map<String, List<Integer>> map = new HashMap<>();

        public WordDistance(String[] words) {
            // go through the word list
            for (int i = 0; i < words.length; i++) {
                // put all words location into the map
                map.putIfAbsent(words[i], new ArrayList<Integer>());
                map.get(words[i]).add(i);
            }
        }

        // function to find the shortest distance between word1 and word2
        public int shortest(String word1, String word2) {
            // get the location list of 2 words
            List<Integer> list1 = map.get(word1), list2 = map.get(word2);
            int min = Integer.MAX_VALUE;
            int i = 0, j = 0;
            while (i < list1.size() && j < list2.size()) {
                // get the location
                int p1 = list1.get(i), p2 = list2.get(j);
                // calculate the min distance
                min = Math.min(min, Math.abs(p1 - p2));
                // move the smaller one for moving 2 pointer closer
                if (p1 < p2)
                    i++;
                else
                    j++;
            }
            return min;
        }
    }
}
