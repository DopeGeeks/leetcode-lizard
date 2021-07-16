package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * 380. Insert Delete GetRandom O(1)
 * Link: https://leetcode.com/problems/insert-delete-getrandom-o1/
 */

public class InsertDeleteGetRandom {
    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(1): each function only contant
     * @space: O(n): for hashmap and linked list
     */
    class RandomizedSet {
        // list for storing numbers
        List<Integer> list = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        // a random object for randomize numbers
        Random rnd = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {

        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain
         * the specified element.
         */
        public boolean insert(int val) {
            // if the value not exist before
            if (!map.containsKey(val)) {
                // add val to the list and map
                list.add(val);
                // put with index number in the list
                map.put(val, list.size() - 1);
                return true;
            }
            return false;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified
         * element.
         */
        public boolean remove(int val) {
            // if not exist
            if (!map.containsKey(val))
                return false;
            // get the index of the value
            int index = map.get(val);
            // swap it to the last
            swap(index, list.size() - 1);
            // update index's new index
            map.put(list.get(index), index);
            // remove the value in map
            map.remove(val);
            // remove the last number
            list.remove(list.size() - 1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            // get random number with from 0 to size-1 as index
            int index = rnd.nextInt(list.size());
            return list.get(index);
        }

        // swap the element in index a and b
        private void swap(int a, int b) {
            int temp = list.get(a);
            list.set(a, list.get(b));
            list.set(b, temp);
        }
    }

}
