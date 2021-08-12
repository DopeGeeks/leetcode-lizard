package daohuei.leetcodelizard;

/*
 * 383. Ransom Note
 * Link: https://leetcode.com/problems/ransom-note/description/
 */
public class RansomNote {
    /**
     * @author: daohuei
     * @description: hash method
     * @time: O(n): go through every characters
     * @space: O(n): for hash set
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null)
            return true;
        if (magazine == null || magazine.length() < ransomNote.length())
            return false;

        // for hashmap actually
        // but in integer array way
        int[] count = new int[256];
        // count with magazine characters
        for (char c : magazine.toCharArray()) {
            count[c]++;
        }

        // reduce count with ransom note char
        for (char c : ransomNote.toCharArray()) {
            count[c]--;
            // if over reduce
            if (count[c] < 0) {
                // failed
                return false;
            }
        }

        return true;
    }
}