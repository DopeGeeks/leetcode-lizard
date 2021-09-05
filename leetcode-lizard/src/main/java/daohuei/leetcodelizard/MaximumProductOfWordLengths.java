package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 318. Maximum Product of Word Lengths
 * Link: https://leetcode.com/problems/maximum-product-of-word-lengths/description/
 */
public class MaximumProductOfWordLengths {
    /**
     * @author: daohuei
     * @description: bit operations method
     * @time: O(n^2): go through all words combination
     * @space: O(n): for storing characters bits union
     */
    public int maxProduct(String[] words) {
        int max = 0;
        // sort the array according its word length
        // quick sort: nlogn
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });

        int[] marks = new int[words.length];
        // go through every words and length
        for (int i = 0; i < words.length; ++i) {
            char[] array = words[i].toCharArray();
            for (int j = 0; j < array.length; ++j) {
                // for every word
                // assign their relevant mark
                marks[i] |= 1 << (array[j] - 'a');
            }

        }
        // go through every mark
        for (int i = 0; i < marks.length; i++) {
            // if the word length is not be able to exceed the max anymore, break the loop
            if (words[i].length() * words[i].length() <= max)
                break;
            // go through every mark combination
            for (int j = i + 1; j < marks.length; ++j) {
                // if their bits intersection results in 0: means no duplicate number between 2
                // words
                if ((marks[j] & marks[i]) == 0) {
                    // calculate the max length
                    max = Math.max(max, words[i].length() * words[j].length());
                    // break this subloop, since there is no longer combination anymore
                    break;
                }
            }
        }

        return max;
    }
}