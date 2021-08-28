package daohuei.leetcodelizard;

/*
 * 274. H-Index
 * Link: https://leetcode.com/problems/h-index/description/
 */
public class HIndex {
    /**
     * @author: daohuei
     * @description: bucket method
     * @time: O(n): just go through the string list
     * @space: O(n): for the bucket
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        // go through the citations
        for (int c : citations) {
            // if the citation number is more than the paper amount
            if (c >= n) {
                // count into the last bucket
                buckets[n]++;
            } else {
                // else just count into the bucket map with citation number
                buckets[c]++;
            }
        }
        // count the total citation from backward
        int count = 0;
        for (int i = n; i >= 0; i--) {
            // accumulate the count from backward
            count += buckets[i];
            // if the accumulated count is larger or equal than the citation number i
            if (count >= i) {
                // this is the h-index
                return i;
            }
        }
        return 0;
    }
}