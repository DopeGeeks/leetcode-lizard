package daohuei.leetcodelizard;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 346. Moving Average from Data Stream
 * Link: https://leetcode.com/problems/moving-average-from-data-stream/description/
 */
/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
*/
public class MovingAverageFromDataStream {

    /**
     * @author: daohuei
     * @description: too easy to explain
     * @time: O(1): I don't want to explain this
     * @space: O(size): for queue
     */

    class MovingAverage {

        /** Initialize your data structure here. */
        Queue<Integer> queue;
        int size;
        double sum;

        public MovingAverage(int size) {
            sum = 0;
            // the size for the sliding window
            this.size = size;
            queue = new LinkedList<>();
        }

        public double next(int val) {
            // add value to the queue
            queue.add(val);
            // calculate the sum
            sum += val;
            // if exceed the window
            if (queue.size() > size) {
                // remove the oldest one
                // and remove from sum
                sum -= queue.remove();
            }
            // calculate the average
            return sum / queue.size();
        }
    }

}
