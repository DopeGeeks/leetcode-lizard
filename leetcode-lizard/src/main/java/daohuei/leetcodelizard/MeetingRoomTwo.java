package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 253. Meeting Rooms
 * Link: https://leetcode.com/problems/meeting-rooms-ii/
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] 
 * find the minimum number of conference rooms required.
 */
public class MeetingRoomTwo {
    /**
     * @author: daohuei
     * @description: sort and priority queue
     * @time: O(nlogn) for the sorting and priority queue sorting
     * @space: O(n) for priority queue
     */
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        // empty case
        if (n == 0) {
            return 0;
        }
        // sort according to the start time
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;
        for (int[] itv : intervals) {
            // if the heap is empty(no room is using)
            if (heap.isEmpty()) {
                count++;
                // add the end time
                heap.offer(itv[1]);
            } else {
                // if the start time is larger than the using interval
                if (itv[0] >= heap.peek()) {
                    // remove the using interval
                    heap.poll();
                } else {
                    // when conflict, need a new room
                    count++;
                }
                // put the interval into the heap indicates there is a room using right now
                heap.offer(itv[1]);
            }
        }

        return count;
    }
}
