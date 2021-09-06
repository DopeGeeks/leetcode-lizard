package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * 336. Palindrome Pairs
 * Link: https://leetcode.com/problems/palindrome-pairs/description/
 */

public class PalindromePairs {
    class ReverseEndHashMapSolution {
        HashMap<String, Integer> map = new HashMap<>();

        /**
         * @author: daohuei
         * @description: reverse end with hash map method
         * @time: O(nk): n is the word length, k is the max length of a word
         * @space: O(n): for hashmap of every word
         */
        public List<List<Integer>> palindromePairsReverseEndHashMap(String[] words) {
            List<List<Integer>> rst = new ArrayList<>();
            // empty case
            if (words == null || words.length == 0) {
                return rst;
            }

            // build up index map for each word
            int n = words.length;
            for (int i = 0; i < n; i++) {
                map.put(words[i], i);
            }

            // go through every word: O(n)
            for (String word : words) {
                // go through every substring: O(m)
                for (int i = 0; i < word.length(); i++) {
                    // get the substring of word start from i+1: we want this to be palindrome
                    String middle = word.substring(i + 1);
                    // reverse the head part: we want this to have appeared in the word list as
                    // another word
                    String reverseEnd = new StringBuffer(word.substring(0, i + 1)).reverse().toString();
                    // Cut middle and append the reverse to end
                    findPair(rst, word, middle, reverseEnd, true);

                    // do it in another direction
                    // Cut front and append the reverse to front
                    middle = word.substring(0, i + 1);
                    reverseEnd = new StringBuffer(word.substring(i + 1)).reverse().toString();
                    findPair(rst, word, middle, reverseEnd, false);
                }
            }

            return rst;
        }

        // find the pair and add to the results
        private void findPair(List<List<Integer>> rst, String word, String middle, String reverseEnd, boolean front) {
            // if middle is not palindrome
            if (!isPalindrome(middle)) {
                return;
            }
            // if reverse end exist, and it is not the current word
            if (map.containsKey(reverseEnd) && map.get(reverseEnd) != map.get(word)) {
                // the pair will be word and reverseEnd, add to the results
                int index1 = map.get(word);
                int index2 = map.get(reverseEnd);
                List<Integer> list = Arrays.asList(index1, index2);
                // if another direction, reverse the answer
                if (!front) {
                    Collections.reverse(list);
                }
                rst.add(list);
                // if reverse end is empty, then reverse it can be another answer
                if (reverseEnd.equals("")) {
                    list = Arrays.asList(index2, index1);
                    if (!front) {
                        Collections.reverse(list);
                    }
                    rst.add(list);
                }
            }
        }

        // check if it is palindrome
        private boolean isPalindrome(String word) {
            if (word.length() == 1) {
                return true;
            }
            int start = 0;
            int end = word.length() - 1;
            while (start < end) {
                if (word.charAt(start) != word.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }

            return true;
        }
    }

    class TrieNodeSolution {
        /**
         * @author: daohuei
         * @description: trie node method
         * @time: O(nk): n is the word length, k is the max length of a word
         * @space: O(nk): for prefix tree, contains every characters, worst case nk
         */
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();

            TrieNode root = new TrieNode();

            // add every word into the prefix tree
            for (int i = 0; i < words.length; i++) {
                addWord(root, words[i], i);
            }

            // search every word
            for (int i = 0; i < words.length; i++) {
                search(words, i, root, res);
            }

            return res;
        }

        // using prefix tree to store words
        private class TrieNode {
            // or children, point to the next possible characters
            TrieNode[] next;
            // for index in the original word list
            int index;
            List<Integer> list;

            TrieNode() {
                // 26 trie node for each character
                next = new TrieNode[26];
                index = -1;
                list = new ArrayList<>();
            }
        }

        private void addWord(TrieNode root, String word, int index) {
            // iter backword
            for (int i = word.length() - 1; i >= 0; i--) {
                // number representation as j
                int j = word.charAt(i) - 'a';

                // if no node with j character, init new one
                if (root.next[j] == null) {
                    root.next[j] = new TrieNode();
                }

                // if the word contains palindrome, add index to the palindrome list
                if (isPalindrome(word, 0, i)) {
                    root.list.add(index);
                }
                // move to next
                root = root.next[j];
            }
            // add current word index into the first of word node
            root.list.add(index);
            // assign the word index to the first of word node
            root.index = index;
        }

        private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
            // go through every char in word[i]
            for (int j = 0; j < words[i].length(); j++) {
                // if node index is >= 0: means the word exist in the list
                // and it is not the current word
                // and the word substring start from j is palindrome
                // since the prefix tree is build backward, so at this point, it is already
                // proved is palindrome between word[i].charAt(0-j) and root.index word
                if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                    // add the pair to the result
                    res.add(Arrays.asList(i, root.index));
                }
                // traverse through the prefix tree
                root = root.next[words[i].charAt(j) - 'a'];
                // if reaching the end, return
                if (root == null)
                    return;
            }
            // go through the palindrome list in the node
            for (int j : root.list) {
                // skip current word
                if (i == j)
                    continue;
                // the list would be all answer of the other half of the pair
                res.add(Arrays.asList(i, j));
            }
        }

        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--))
                    return false;
            }

            return true;
        }
    }
}
