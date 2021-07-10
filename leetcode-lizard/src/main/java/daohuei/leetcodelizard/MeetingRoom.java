package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 252. Meeting Rooms
 * Link: https://leetcode.com/problems/meeting-rooms/description/
 */
public class MeetingRoom {

    /**
     * @author: daohuei
     * @description: sort and iteration
     * @time: O(nlogn) for the sorting
     * @space: O(1) not using any
     */
    public boolean canAttendMeetings(int[][] intervals) {
        int n = intervals.length;
        // empty case
        if (n == 0) {
            return false;
        }
        // sort according to the start time
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        // loop over the intervals
        // compare with the previous interval and check if there is any overlap
        for (int i = 1; i < n; i++) {
            int start = intervals[i][0];
            int preEnd = intervals[i - 1][1];
            if (start < preEnd) {
                return false;
            }
        }
        return true;
    }
}
