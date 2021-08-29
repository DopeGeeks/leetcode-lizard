package daohuei.leetcodelizard;

import java.util.Iterator;

/*
 * 284. Peeking Iterator
 * Link: https://leetcode.com/problems/peeking-iterator/description/
 */
public class PeekingIterator {

    /**
     * @author: daohuei
     * @description: cache method
     * @time: O(1): since we cache, no need to look for the peek value
     * @space: O(n): for iterator
     */
    class PeekingIteratorSolution implements Iterator<Integer> {
        private int cache;
        private Iterator<Integer> itt;
        private boolean notEnd;

        public PeekingIteratorSolution(Iterator<Integer> iterator) {
            // initialize any member here.
            itt = iterator;
            // store the next node as cahce
            cache = itt.next();
            // set the flag of has next
            notEnd = iterator.hasNext();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            // just return cahce: our peek value
            return cache;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            // take out the current cache
            int curr = cache;
            // update the next flag
            notEnd = itt.hasNext();
            // if has next
            if (itt.hasNext()) {
                // update the cache
                cache = itt.next();
            }
            // return current cache
            return curr;
        }

        @Override
        public boolean hasNext() {
            // just use the has next flag: notEnd
            return notEnd;
        }
    }
}