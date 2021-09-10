package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * Link: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {
    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(1): with the hashmap, every operation only need O(1)
     * @space: O(n): all values insert to 2 maps
     */
    class RandomizedCollection {
        // value:index
        Map<Integer, Integer> forwardMap;
        // index:value
        Map<Integer, Integer> reverseMap;
        // auto-incremented index
        int index;
        Random rand;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            forwardMap = new HashMap();
            reverseMap = new HashMap();
            index = 0;
            rand = new Random();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not
         * already contain the specified element.
         */
        public boolean insert(int val) {
            boolean contains;
            // check if value exist
            if (reverseMap.containsValue(val)) {
                contains = false;
            } else {
                contains = true;
            }
            // this will overwrite the preivous key with a new index if the key already
            // exists
            forwardMap.put(val, index);
            reverseMap.put(index, val);
            index++;
            return contains;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained
         * the specified element.
         */
        public boolean remove(int val) {
            boolean contains;
            // check if value exist
            if (reverseMap.containsValue(val)) {
                contains = true;
                // exist in forward map as well
                if (forwardMap.containsKey(val)) {
                    // get the index, remove from both map
                    int i = forwardMap.get(val);
                    forwardMap.remove(val);
                    reverseMap.remove(i);
                } else {
                    // remove reverse map only
                    reverseMap.values().remove(val);
                }
            } else {
                contains = false;
            }
            return contains;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            // get the random number from index
            int randNum = rand.nextInt(index);
            // if reverseMap not contains key, randomize until find it
            while (!reverseMap.containsKey(randNum)) {
                randNum = rand.nextInt(index);
            }
            return reverseMap.get(randNum);
        }
    }
}
