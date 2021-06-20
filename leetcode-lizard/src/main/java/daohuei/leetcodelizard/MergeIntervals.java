package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/*
 * 56. Merge Intervals
 * Link: https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {
    /**
     * @author: daohuei
     * @description: sort it first and then walk through
     * @time: O(n logn) for sorting
     * @space: O(n): for sorting and merge result
     */

    public int[][] sortAndMerge(int[][] intervals) {
        // sort the intervals according to the start value of the interval
        Arrays.sort(intervals, new IntervalComparator());
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if empty
            // or last end if smaller then the next start: means next one is out of last
            // interval
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                // we add a new merged interval
                merged.add(interval);
            }
            // else if still in the last interval
            // then update the last interval end
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    private class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
        }
    }
}