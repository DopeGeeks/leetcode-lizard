package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * 127. Word Ladder
 * Link: https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {
    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n): bfs traverse through all nodes, worst case will be n
     * @space: O(n): for queue and hashsets
     */
    public int bfs(String beginWord, String endWord, List<String> wordList) {
        // if the end word not in the word list
        if (!wordList.contains(endWord)) {
            // there will be no possible
            return 0;
        }
        // do the BFS
        int len = 1;
        // start with the first word
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        boolean isFound = false;

        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            // go through the queue
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                // get the neighbor of temp
                ArrayList<String> neighbors = getNeighbors(temp, dict);
                // go through the neighbor
                for (String neighbor : neighbors) {
                    // if haven't visit the same string as the neighbor
                    if (!visited.contains(neighbor)) {
                        // add it to the visited neighbor set
                        subVisited.add(neighbor);
                        // if found the endWord
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                            break;
                        }
                        queue.offer(neighbor);
                    }
                }

            }
            // since we found at least one path
            if (subVisited.size() > 0) {
                // result path length count 1
                len++;
            }
            // put all neighbor into the visited
            // which is also the next round to be visited
            visited.addAll(subVisited);
            // if found, directly return len
            if (isFound) {
                return len;
            }
        }
        return 0;
    }

    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();

        // from a to z
        for (char ch = 'a'; ch <= 'z'; ch++) {
            // go through the node string
            for (int i = 0; i < chs.length; i++) {
                // if it is the same character, ignore
                if (chs[i] == ch)
                    continue;
                // change the old one to the new one
                char old_ch = chs[i];
                chs[i] = ch;
                // if the it is in the wordlist
                if (dict.contains(String.valueOf(chs))) {
                    // it is one of the neighbor
                    res.add(String.valueOf(chs));
                }
                // put the old character back
                chs[i] = old_ch;
            }
        }
        return res;
    }

    /**
     * @author: daohuei
     * @description: search from start and end
     * @time: O(n): it traverse until the end is found
     * @space: O(n): for every set, they all have worst case n
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // if the end word not in the word list
        if (!wordList.contains(endWord))
            return 0;
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
        int len = 1;
        HashSet<String> visited = new HashSet<String>();
        HashSet<String> dict = new HashSet<String>(wordList);
        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // beginSet should always be smaller one
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            // go through first set
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        // get its all possible neighbors => target
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        // if the target includes in the other set
                        if (endSet.contains(target)) {
                            // need to count 1 more since we return directly
                            return len + 1;
                        }
                        // if the target is never found before and it is in word list
                        if (!visited.contains(target) && dict.contains(target)) {
                            // add to temp and visited
                            temp.add(target);
                            visited.add(target);
                        }
                        // put the old char back
                        chs[i] = old;
                    }
                }
            }
            // go with next level set
            beginSet = temp;
            // len count 1
            len++;
        }
        return 0;
    }
}