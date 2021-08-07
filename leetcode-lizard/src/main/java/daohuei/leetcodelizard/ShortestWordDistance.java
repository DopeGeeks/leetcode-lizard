package daohuei.leetcodelizard;

/*
 * 243. Shortest Word Distance
 * Link: https://leetcode.com/problems/shortest-word-distance/description/
 */
/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/
public class ShortestWordDistance {
    /**
     * @author: daohuei
     * @description: single loop method
     * @time: O(n): for the length of the word list
     * @space: O(1): not using any
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        Integer p1 = null, p2 = null;
        int min = Integer.MAX_VALUE;
        // go through the word list
        for (int i = 0; i < words.length; i++) {
            // find out both index
            if (words[i].equals(word1))
                p1 = i;
            if (words[i].equals(word2))
                p2 = i;
            // get the absolute difference of 2 indexes
            // and we want the smallest one
            if (p1 != null && p2 != null)
                min = Math.min(min, Math.abs(p1 - p2));
        }
        return min;
    }

}
