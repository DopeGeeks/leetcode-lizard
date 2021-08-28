package daohuei.leetcodelizard;

/*
 * 275. H-Index II
 * Link: https://leetcode.com/problems/h-index-ii/description/
 */
public class HIndexTwo {
    /**
     * @author: daohuei
     * @description: binary search method
     * @time: O(logn): binary search
     * @space: O(1): not using any
     */
    public int hIndex(int[] citations) {
        int len = citations.length;
        // start and end pointer
        int lo = 0, hi = len - 1;
        while (lo <= hi) {
            // get the mid point
            int med = (hi + lo) / 2;
            // if the citation number in the mid point is same as the real mid number
            // luckily directly found the answer
            if (citations[med] == len - med) {
                return len - med;
            } else if (citations[med] < len - med) {
                // if smaller, the answer should be right side
                lo = med + 1;
            } else {
                // (citations[med] > len-med), med qualified as a hIndex,
                // but we have to continue to search for a higher one.
                hi = med - 1;
            }
        }
        // this ans will be the h-index
        return len - lo;
    }
}