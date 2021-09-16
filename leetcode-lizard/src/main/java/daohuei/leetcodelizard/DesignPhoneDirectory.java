package daohuei.leetcodelizard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Design a Phone Directory which supports the following operations:
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 * Example:
 * Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 * It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 * Assume it returns 1.
 * directory.get();
 * The number 2 is available, so return true.
 * directory.check(2);
 * It returns 2, the only number that is left.
 * directory.get();
 * The number 2 is no longer available, so return false.
 * directory.check(2);
 * Release number 2 back to the pool.
 * directory.release(2);
 * Number 2 is available again, return true.
 * directory.check(2);
 * @param maxNumbers
 */

/**
 * @author: daohuei
 * @description: queue method
 * @time: O(n): go thru every maxnumbers
 * @space: O(n): for hashset and the queue
 */
public class DesignPhoneDirectory {

    // used phone book
    HashSet<Integer> used = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    int maxNumbers;

    public DesignPhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        // offer every number into the queue
        for (int i = 0; i < maxNumbers; i++) {
            queue.offer(i);
        }
    }

    public int get() {
        // poll the number out
        Integer res = queue.poll();
        if (res == null) {
            return -1;
        }
        // if not null, add to the used phone book
        used.add(res);
        return res;
    }

    public boolean check(int number) {
        // if larger than the max numbers or negative: means false
        if (number >= maxNumbers || number < 0) {
            return false;
        }
        // check if phone book contains the number
        return !used.contains(number);
    }

    // if the number is removed, release the number
    public void release(int number) {
        if (used.remove(number)) {
            // put it back to the queue
            queue.offer(number);
        }
    }
}
