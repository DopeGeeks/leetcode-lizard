package daohuei.leetcodelizard;

public class ImplementTriePrefixTree {
    /*
     * 208. Implement Trie (Prefix Tree) Link:
     * https://leetcode.com/problems/implement-trie-prefix-tree/
     */
    class Trie {
        /**
         * @author: daohuei
         */
        // self define subclass of trienode
        class TrieNode {
            // the children of such node
            TrieNode[] children;
            // flag for whether such node is the end of a word
            boolean flag;

            // init function
            public TrieNode() {
                // children is a trienode array for 26 characters
                children = new TrieNode[26];
                // init
                flag = false;
                for (int i = 0; i < 26; i++) {
                    children[i] = null;
                }
            }
        }

        // must has a root
        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            // init root
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            // convert word to char array
            char[] array = word.toCharArray();
            // start with the root
            TrieNode cur = root;
            for (int i = 0; i < array.length; i++) {
                // check if the character exist in the current node
                if (cur.children[array[i] - 'a'] == null) {
                    // if not exist , give it a new node
                    cur.children[array[i] - 'a'] = new TrieNode();
                }
                // move to that node
                cur = cur.children[array[i] - 'a'];
            }
            // when reaching the end, set the node (the last char of that word) true
            cur.flag = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] array = word.toCharArray();
            TrieNode cur = root;
            // search
            for (int i = 0; i < array.length; i++) {
                // if no exist
                if (cur.children[array[i] - 'a'] == null) {
                    return false;
                }
                cur = cur.children[array[i] - 'a'];
            }
            // when all searched, check if the last node is the end of the word
            return cur.flag;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            char[] array = prefix.toCharArray();
            TrieNode cur = root;
            // if there is a path exist, then it must be true
            // since we can find the end eventually
            for (int i = 0; i < array.length; i++) {
                if (cur.children[array[i] - 'a'] == null) {
                    return false;
                }
                cur = cur.children[array[i] - 'a'];
            }
            return true;
        }
    }
}
