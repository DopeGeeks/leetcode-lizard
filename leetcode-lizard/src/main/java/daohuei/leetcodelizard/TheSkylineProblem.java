package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/*
 * 218. The Skyline Problem
 * Link: https://leetcode.com/problems/the-skyline-problem/
 */
public class TheSkylineProblem {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(nlogn): go through every building's skylines
     * @space: O(logn): recursion stack
     */
    public List<List<Integer>> binarySearchMethod(int[][] buildings) {
        if (buildings.length == 0) {
            return new ArrayList<>();
        }
        return merge(buildings, 0, buildings.length - 1);
    }

    private List<List<Integer>> merge(int[][] buildings, int start, int end) {

        List<List<Integer>> res = new ArrayList<>();
        // base case: only one building => [x, h], [y, 0]
        if (start == end) {
            List<Integer> temp = new ArrayList<>();
            // x, h
            temp.add(buildings[start][0]);
            temp.add(buildings[start][2]);
            res.add(temp);

            // y, 0
            temp = new ArrayList<>();
            temp.add(buildings[start][1]);
            temp.add(00);
            res.add(temp);
            return res;
        }
        // split the buildings: binary search
        int mid = (start + end) >>> 1;
        List<List<Integer>> Skyline1 = merge(buildings, start, mid);
        List<List<Integer>> Skyline2 = merge(buildings, mid + 1, end);

        // merge 2 results
        int h1 = 0;
        int h2 = 0;
        int i = 0;
        int j = 0;
        while (i < Skyline1.size() || j < Skyline2.size()) {
            // get x of both
            long x1 = i < Skyline1.size() ? Skyline1.get(i).get(0) : Long.MAX_VALUE;
            long x2 = j < Skyline2.size() ? Skyline2.get(j).get(0) : Long.MAX_VALUE;
            long x = 0;
            // update h of the left one
            if (x1 < x2) {
                // update h1
                h1 = Skyline1.get(i).get(1);
                x = x1;
                i++;
            } else if (x1 > x2) {
                h2 = Skyline2.get(j).get(1);
                x = x2;
                j++;
            } else {
                h1 = Skyline1.get(i).get(1);
                h2 = Skyline2.get(j).get(1);
                x = x1;
                i++;
                j++;
            }
            // update height
            int height = Math.max(h1, h2);
            // if res is empty or no duplicate on height
            if (res.isEmpty() || height != res.get(res.size() - 1).get(1)) {
                // add x,h to the answer
                List<Integer> temp = new ArrayList<>();
                temp.add((int) x);
                temp.add(height);
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * @author: daohuei
     * @description: tree map
     * @time: O(nlogn): insert and sort -> logn, remove -> logn, check largest num
     *        -> logn, finally-> n*logn
     * @space: O(n): worst case: n for treemap
     */
    public List<List<Integer>> treeMapMethod(int[][] buildings) {
        List<List<Integer>> points = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        int n = buildings.length;
        // get the left upper and right upper point
        // but left upper's y we need negative
        for (int[] b : buildings) {
            // left upper
            List<Integer> p1 = new ArrayList<>();
            p1.add(b[0]);
            p1.add(-b[2]);
            points.add(p1);

            // right upper
            List<Integer> p2 = new ArrayList<>();
            p2.add(b[1]);
            p2.add(b[2]);
            points.add(p2);
        }
        // sort it with comparator according to x then y
        Collections.sort(points, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> p1, List<Integer> p2) {
                int x1 = p1.get(0);
                int y1 = p1.get(1);
                int x2 = p2.get(0);
                int y2 = p2.get(1);
                if (x1 != x2) {
                    // compare x
                    return x1 - x2;
                } else {
                    // compare height, we want higher one at front if left upper
                    // we want lower one at front if right upper
                    return y1 - y2;
                }
            }

        });
        // for storing height of the left point
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(new Comparator<Integer>() {
            // sort the height of the left point
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });

        treeMap.put(0, 1);
        int preMax = 0;

        // go through the point
        for (List<Integer> p : points) {
            int x = p.get(0);
            int y = p.get(1);
            // if y less than 0 -> left point
            if (y < 0) {
                // check if there is same height
                Integer v = treeMap.get(-y);
                if (v == null) {
                    // if no same height before
                    treeMap.put(-y, 1);
                } else {
                    // count one more
                    treeMap.put(-y, v + 1);
                }
            } else {
                // if right point
                Integer v = treeMap.get(y);
                if (v == 1) {
                    // if only 1 point, remove it
                    treeMap.remove(y);
                } else {
                    // reduce count
                    treeMap.put(y, v - 1);
                }
            }

            // get the largest height
            int curMax = treeMap.firstKey();
            // if the max has changed
            if (curMax != preMax) {
                List<Integer> temp = new ArrayList<>();
                // add x and max height into the ans
                temp.add(x);
                temp.add(curMax);
                results.add(temp);
                // update preMax
                preMax = curMax;
            }
        }
        return results;
    }
}