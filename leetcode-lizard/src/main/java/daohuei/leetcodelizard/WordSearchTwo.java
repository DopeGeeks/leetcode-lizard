package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 212. Word Search II
 * Link: https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchTwo {
    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): all nodes
     * @space: O(h): recursion stack with prefix tree height
     */
    public List<String> dfs(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        int rows = board.length;
        // empty case
        if (rows == 0) {
            return res;
        }
        int cols = board[0].length;

        Trie trie = new Trie();
        // put all words into the prefix tree
        for (String word : words) {
            trie.insert(word);
        }

        // go through every part in the board with the prefix tree
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                existRecursive(board, i, j, trie.root, res);
            }
        }
        return res;

    }

    private void existRecursive(char[][] board, int row, int col, TrieNode node, List<String> res) {
        // out of bound
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        // the current char
        char cur = board[row][col];
        // if '$' -> visited before or its node not exist -> there is no such word
        if (cur == '$' || node.children[cur - 'a'] == null) {
            return;
        }
        // get the node
        node = node.children[cur - 'a'];
        // if it is the end of the word -> if node.word exist
        if (node.word != null) {
            // an answer found
            res.add(node.word);
            // clear it so we won't find it twice
            node.word = null;
        }

        char temp = board[row][col];
        // set the current the char to visited
        board[row][col] = '$';

        // search up/down/left/right
        existRecursive(board, row - 1, col, node, res);
        existRecursive(board, row + 1, col, node, res);
        existRecursive(board, row, col - 1, node, res);
        existRecursive(board, row, col + 1, node, res);
        // when finish the search, put it back
        board[row][col] = temp;
    }

    // prefix tree node: same as #208
    class TrieNode {
        public TrieNode[] children;
        // diff part: a var for storing current word
        public String word;

        public TrieNode() {
            children = new TrieNode[26];
            word = null;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // prefix tree
    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] array = word.toCharArray();
            TrieNode cur = root;
            for (int i = 0; i < array.length; i++) {
                // if children char not exist, just init a new one for it
                if (cur.children[array[i] - 'a'] == null) {
                    cur.children[array[i] - 'a'] = new TrieNode();
                }
                cur = cur.children[array[i] - 'a'];
            }
            // store the word in the last node
            cur.word = word;
        }
    };
}