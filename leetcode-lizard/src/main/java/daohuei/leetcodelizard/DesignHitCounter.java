package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

/*
* Design a hit counter which counts the number of hits received in the past 5 minutes.

* Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

* It is possible that several hits arrive roughly at the same time.

* Example:

* HitCounter counter = new HitCounter();

* // hit at timestamp 1.
* counter.hit(1);

* // hit at timestamp 2.
* counter.hit(2);

* // hit at timestamp 3.
* counter.hit(3);

* // get hits at timestamp 4, should return 3.
* counter.getHits(4);

* // hit at timestamp 300.
* counter.hit(300);

* // get hits at timestamp 300, should return 4.
* counter.getHits(300);

* // get hits at timestamp 301, should return 3.
* counter.getHits(301); 
 */
/*
 * 362. Design Hit Counter
 * https://leetcode.com/problems/design-hit-counter/description/
 */
public class DesignHitCounter {

    /**
     * @author: daohuei
     * @description: integer array method
     * @time: O(1): actually need to go thru whole integer array when get the hits
     * @space: O(1): 2 arrays for 300 secs
     */
    class HitCounterWithIntArray {

        /** Initialize your data structure here. */
        // 2 arrays contains hits and timestamp
        int[] times;
        int[] hits;

        public HitCounterWithIntArray() {
            times = new int[300];
            hits = new int[300];
        }

        /**
         * Record a hit.
         * 
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            // when hit at ts, we get the index by mod 300 (5 min we record at most)
            int idx = timestamp % 300;
            // if not equal the current ts: means another 300 passed
            if (times[idx] != timestamp) {
                // update the ts
                times[idx] = timestamp;
                // reset the hits
                hits[idx] = 1;
            }
            // in 300 sec, same index has hit before
            else {
                // accumulate the hits
                hits[idx]++;
            }
        }

        /**
         * Return the number of hits in the past 5 minutes.
         * 
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            int totalCount = 0;
            // go thru the hits
            for (int i = 0; i < 300; i++) {
                // if in the range of 300 sec
                if (timestamp - times[i] < 300) {
                    // accumulate the hits
                    totalCount += hits[i];
                }
            }
            return totalCount;
        }
    }

    /**
     * @author: daohuei
     * @description: queue method
     * @time: O(1): cleaning queue may take times
     * @space: O(1): 1 queue for all hits
     */
    class HitCounterWithQueue {

        /** Initialize your data structure here. */
        Queue<Integer> queue;

        public HitCounterWithQueue() {
            // an int queue
            queue = new LinkedList<Integer>();
        }

        /**
         * Record a hit.
         * 
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        // put the timestamp into the queue
        public void hit(int timestamp) {
            queue.offer(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         * 
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        // could be slow when not cleaning it frequently
        public int getHits(int timestamp) {
            // get rid of the queue that is older than 5 min
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
                queue.poll();
            }
            // return the size will be the count
            return queue.size();
        }
    }
}
