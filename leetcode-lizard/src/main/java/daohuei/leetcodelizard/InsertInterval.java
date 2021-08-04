package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.List;

/*
 * 57. Insert Interval
 * Link: https://leetcode.com/problems/insert-interval/description/
 */
public class InsertInterval {
    /**
     * @author: daohuei
     * @description: insert and merge
     * @time: O(n): linear loop only
     * @space: O(n): for temp list storing intervals
     */

    int START = 0, END = 1;

    public int[][] insertAndMergeMethod(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0)
            return new int[][] { newInterval };
        // insert according to start only, and return as list
        List<int[]> list = addInterval(intervals, newInterval);
        // merge intervals
        merge(list);
        // list to array
        return toArray(list);
    }

    private void merge(List<int[]> list) {

        int[] pre = list.get(0), curr = null;
        for (int i = 1; i < list.size(); i++) {
            curr = list.get(i);
            // check if overlapping
            if (pre[END] >= curr[START]) {
                // update pre end with larger end
                pre[END] = pre[END] > curr[END] ? pre[END] : curr[END];
                // remove current number
                list.remove(i);
                // redo thos round
                i--;
            } else
                pre = curr; // move forward
        }
    }

    private List<int[]> addInterval(int[][] intervals, int[] newInterval) {
        List<int[]> list = new LinkedList<>();
        // front is the spot to insert
        Integer front = null;
        for (int i = 0; i < intervals.length; i++) {
            // add interval according to start time
            // found the spot to insert
            if (intervals[i][START] <= newInterval[START])
                front = i + 1;
            // transfer int array to list
            list.add(intervals[i]);
        }
        // if front is null, means new interval has smallest start time
        if (front == null)
            list.add(0, newInterval);
        // else just add at the position front
        else
            list.add(front, newInterval);
        return list;
    }

    private int[][] toArray(List<int[]> list) {
        // transfer list to int[][]
        int[][] rst = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            rst[i][START] = list.get(i)[START];
            rst[i][END] = list.get(i)[END];
        }
        return rst;
    }

    /**
     * @author: daohuei
     * @description: insert on the fly
     * @time: O(n): linear loop only
     * @space: O(n): for temp list storing intervals
     */
    /**
     * - handle edge cases: - new interval is non-overlapping - 1) head - 2) tail -
     * 3) in middle - new interval is overlapping: - 1) end index in existing
     * interval; reuse the existing interval end to close new range - 2) end index
     * in the gap of 2 intervals, use new interval.end to close the new range
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0)
            return new int[][] { newInterval };

        int start = newInterval[0], end = newInterval[1];
        List<int[]> list = new LinkedList<>();
        // head case: if the end is less than first start
        if (end < intervals[0][0])
            list.add(0, new int[] { start, end }); // non-overlapping: head

        // go through old intervals
        for (int i = 0; i < n; i++) {
            int lo = intervals[i][0], hi = intervals[i][1];
            // if no overlap in the middle
            if (lo > end || hi < start) {
                // free interval to insert
                list.add(intervals[i]);
                // if interval is larger and i is not the end
                // insert new interval
                if (hi < start && isNotEnd(intervals, end, i))
                    list.add(new int[] { start, end });
                continue;
            }
            // if overlap with start, update new start
            if (lo <= start && start <= hi)
                start = lo;
            // if overlap with end, update new end
            if (lo <= end && end <= hi)
                end = hi;
            // if end is same as local end, and i is not the end
            // insert new interval
            if (end == hi || isNotEnd(intervals, end, i))
                list.add(new int[] { start, end });
        }

        // tail case: if the end is larger than the last end
        if (end > intervals[n - 1][1])
            list.add(new int[] { start, end });
        // convert list the array
        return convert(list);
    }

    private boolean isNotEnd(int[][] intervals, int end, int i) {
        // if i is not the end and end is less than next start
        return i + 1 < intervals.length && end < intervals[i + 1][0];
    }

    private int[][] convert(List<int[]> list) {
        int[][] rst = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            rst[i][0] = list.get(i)[0];
            rst[i][1] = list.get(i)[1];
        }
        return rst;
    }
}