package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/*
 * 269. Alien Dictionary
 * Link: https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {
	/**
	 * @author: daohuei
	 * @description: iteration
	 * @time: O(n): n = word length
	 * @space: O(k): k is all unique characters amount, for hash map
	 */
    public String alienOrder(String[] words) {
        String result = "";
        if (words == null || words.length == 0) {
            return result;
        }

        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        // Degree char = 0
        // init degree map with all chars with 0 degree
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c, 0);
            }
        }

        // go through every word
        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            int min = Math.min(curr.length(), next.length());
            for (int j = 0; j < min; j++) {
                char c1 = curr.charAt(j);
                char c2 = next.charAt(j);
                // if the first diff character found between 2 words
                if (c1 != c2) {
                    // get the characters set that must behind c1
                    Set<Character> set = map.getOrDefault(c1, new HashSet<Character>());
                    // if c2 is not in the behind set
                    if (!set.contains(c2)) {
                        // add c2 to the behind set, since we just found it
                        set.add(c2);
                        // update the set
                        map.put(c1, set);
                        // add 1 degree of behind set (how many char is in front of c2)
                        degree.put(c2, degree.get(c2) + 1); // update c2, c1 < c2
                    }
                    break;
                }
            }
        }

        LinkedList<Character> q = new LinkedList<>();
        for (char c : degree.keySet()) {
            // add the chars that is front clear to the queue
            if (degree.get(c) == 0) {
                q.add(c);
            }
        }

        while (!q.isEmpty()) {
            // add the char in the queue to the result string
            char c = q.poll();
            result += c;
            // if the char has behind character
            if (map.containsKey(c)) {
                for (char next : map.get(c)) {
                    // decrease the count of their front char count
                    degree.put(next, degree.get(next) - 1);
                    // if there is no one in front of them anymore
                    if (degree.get(next) == 0) {
                        // put them into the queue
                        q.offer(next);
                    }
                }
            }
        }
        // if the result length is not match the size of the degree map
        // means there is a conflict, should return ""
        return result.length() == degree.size() ? result : "";
    }
}
