package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
 * 352. Data Stream as Disjoint Intervals
 * Link: https://leetcode.com/problems/data-stream-as-disjoint-intervals/description/
 */
public class DataStreamAsDisjointIntervals {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(logn): for binary search
     * @space: O(n): for array list
     */
    class SummaryRangesBinarySearch {
        List<int[]> lst;

        /** Initialize your data structure here. */
        public SummaryRangesBinarySearch() {
            lst = new ArrayList<int[]>();
        }

        // add the number into the ranges
        public void addNum(int val) {
            // search index with start and end
            int start = 0;
            int end = lst.size() - 1;

            // if empty, directly add the self interval
            if (lst.isEmpty()) {
                lst.add(new int[] { val, val });
                return;
            }

            // if earlier than the start of first interval
            if (val < lst.get(start)[0]) {
                // and it is continuous number with start of first interval
                if (val == lst.get(start)[0] - 1)
                    // update the start of the first interval
                    lst.get(start)[0] = val;
                else
                    // if not, still add the self interval
                    lst.add(0, new int[] { val, val });
                return;
            }

            // same as above but with the end of the last interval
            if (val > lst.get(end)[1]) {
                if (val == lst.get(end)[1] + 1)
                    lst.get(end)[1] = val;
                else
                    lst.add(new int[] { val, val });
                return;
            }

            // do binary search
            while (start < end - 1) {
                // get the middle index
                int mid = (start + end) / 2;

                // if the value is in the middle range, just ignore
                if (val >= lst.get(mid)[0] && val <= lst.get(mid)[1]) {
                    return;
                }

                // if larger than end of the middle range
                if (val > lst.get(mid)[1]) {
                    // move to right => increase start index
                    start = mid;
                    continue;
                }

                // if smaller than start of the middle range
                if (val < lst.get(mid)[0]) {
                    // move to left => decrease end index
                    end = mid;
                    continue;
                }
            }

            // for checking if it is the continuous number
            // if it connect the start and end range => merge the start and end range by
            // update start's end then remove the end range
            if (lst.get(start)[1] + 1 == val && lst.get(end)[0] - 1 == val) {
                lst.get(start)[1] = lst.get(end)[1];
                lst.remove(end);
            }
            // or if it is continuous number of start's end
            else if (lst.get(start)[1] + 1 == val) {
                lst.get(start)[1] = val;
            }
            // or end's start
            else if (lst.get(end)[0] - 1 == val) {
                lst.get(end)[0] = val;
            }
            // or in the start range
            else if (lst.get(start)[0] <= val && lst.get(start)[1] >= val) {
                return;
            }
            // or in the end range
            else if (lst.get(end)[0] <= val && lst.get(end)[1] >= val) {
                return;
            }
            // or no overlap => just add self interval
            else {
                lst.add(end, new int[] { val, val });
            }
        }

        // return the interval list as integer array
        public int[][] getIntervals() {
            int[][] intervals = new int[lst.size()][2];
            for (int i = 0; i < lst.size(); i++) {
                intervals[i] = lst.get(i);
            }
            return intervals;
        }
    }

    /**
     * @author: daohuei
     * @description: tree map
     * @time: O(logn): for bst
     * @space: O(n): for tree map
     */
    class SummaryRangesTreeMap {
        TreeMap<Integer, int[]> tree;

        /** Initialize your data structure here. */
        public SummaryRangesTreeMap() {
            tree = new TreeMap<Integer, int[]>();
        }

        // add the number into the ranges
        public void addNum(int val) {
            // if add before, return
            if (tree.containsKey(val))
                return;

            // get the key of such value
            Integer low = tree.lowerKey(val);
            Integer high = tree.higherKey(val);

            // if they both exist and is continuous number
            if (low != null && high != null && tree.get(low)[1] + 1 == val && high == val + 1) {
                // merge them
                tree.get(low)[1] = tree.get(high)[1];
                tree.remove(high);
            }
            // if low exist and is continuous
            else if (low != null && tree.get(low)[1] + 1 >= val) {
                // update low end with the higher one
                tree.get(low)[1] = Math.max(tree.get(low)[1], val);
            }
            // if continuous with high
            else if (high != null && high == val + 1) {
                // merge with high with new start and give it a new key
                tree.put(val, new int[] { val, tree.get(high)[1] });
                // remove old high range
                tree.remove(high);
            } else {
                // insert self range with start as key
                tree.put(val, new int[] { val, val });
            }
        }

        // return the interval list as integer array
        public int[][] getIntervals() {
            int[][] intervals = new int[tree.size()][2];
            List<int[]> lst = new ArrayList<>(tree.values());
            for (int i = 0; i < lst.size(); i++) {
                intervals[i] = lst.get(i);
            }
            return intervals;
        }
    }

}
