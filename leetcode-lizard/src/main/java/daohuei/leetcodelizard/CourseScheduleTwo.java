package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  210. Course Schedule II
 * Link: https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleTwo {
    /**
     * @author: daohuei
     * @description: bfs
     * @time: O(n^2): worst case: n^2
     * @space: O(n^2): for course list and the hash map
     */
    public int[] bfs(int numCourses, int[][] prerequisites) {
        // precourse count
        HashMap<Integer, Integer> outNum = new HashMap<>();
        // further course list
        HashMap<Integer, ArrayList<Integer>> inNodes = new HashMap<>();
        // all courses
        HashSet<Integer> set = new HashSet<>();
        int rows = prerequisites.length;
        for (int i = 0; i < rows; i++) {
            // further course
            int key = prerequisites[i][0];
            // pre course
            int value = prerequisites[i][1];
            // add all courses in the set
            set.add(key);
            set.add(value);
            // if no exist, init with 0
            if (!outNum.containsKey(key)) {
                outNum.put(key, 0);
            }
            if (!outNum.containsKey(value)) {
                outNum.put(value, 0);
            }
            // count 1 for pre course count
            int num = outNum.get(key);
            outNum.put(key, num + 1);

            // if no exist put empty array(further course list) for pre course
            if (!inNodes.containsKey(value)) {
                inNodes.put(value, new ArrayList<Integer>());
            }
            // update the further course list
            ArrayList<Integer> list = inNodes.get(value);
            list.add(key);
        }

        // put the course that no need to have precourse into the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int k : set) {
            if (outNum.get(k) == 0) {
                queue.offer(k);
            }
        }
        // the array of order answer
        int[] res = new int[numCourses];
        // for answer index
        int count = 0;
        while (!queue.isEmpty()) {

            // get the available course
            int v = queue.poll();

            // since available, put into answer
            res[count++] = v;

            ArrayList<Integer> list = inNodes.getOrDefault(v, new ArrayList<Integer>());

            for (int k : list) {
                // get the remains pre course
                int num = outNum.get(k);
                // if 1, then means it is going to have no precourse
                if (num == 1) {
                    queue.offer(k);
                }
                // pre count -1
                outNum.put(k, num - 1);
            }
        }

        // if all course got no pre course, then done
        for (int k : set) {
            if (outNum.get(k) != 0) {
                // if there is course has precourse remain
                // no answer, return empty array
                return new int[0];
            }
        }
        // put all answer into the set
        HashSet<Integer> resSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            resSet.add(res[i]);
        }
        // if there are some course has no precourse and no further course
        // find and add them to the end
        for (int i = 0; i < numCourses; i++) {
            if (!resSet.contains(i)) {
                res[count++] = i;
            }
        }
        return res;
    }

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): go through each node
     * @space: O(n^2): for course list and the hash map
     */
    public int[] dfsMethod(int numCourses, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> outNodes = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        int rows = prerequisites.length;
        for (int i = 0; i < rows; i++) {
            int key = prerequisites[i][0];
            int value = prerequisites[i][1];
            set.add(key);
            if (!outNodes.containsKey(key)) {
                outNodes.put(key, new ArrayList<Integer>());
            }
            // pre courses list for further course
            ArrayList<Integer> list = outNodes.get(key);
            list.add(value);
        }

        int[] res = new int[numCourses];
        // for prevent duplicate course
        HashSet<Integer> resSet = new HashSet<>();
        HashSet<Integer> visitedFinish = new HashSet<>();
        // go through every course
        for (int k : set) {
            if (!dfs(k, outNodes, new HashSet<Integer>(), visitedFinish, res, resSet)) {
                return new int[0];
            }
            // add to global visited set
            visitedFinish.add(k);
        }
        // if there are some course has no precourse and no further course
        // find and add them to the end
        for (int i = 0; i < numCourses; i++) {
            if (!resSet.contains(i)) {
                res[count++] = i;
            }
        }
        return res;
    }

    // global count for res order index
    int count = 0;

    private boolean dfs(int start, HashMap<Integer, ArrayList<Integer>> outNodes, HashSet<Integer> visited,
            HashSet<Integer> visitedFinish, int[] res, HashSet<Integer> resSet) {
        // visit before
        if (visitedFinish.contains(start)) {
            return true;
        }
        // start has no pre course(reach the leaf)
        if (!outNodes.containsKey(start)) {
            // if this is not duplicated
            if (!resSet.contains(start)) {
                // add to the result set
                resSet.add(start);
                // put into the order
                res[count++] = start;
            }
            return true;
        }
        // there is a cycle
        if (visited.contains(start)) {
            return false;
        }
        // put start into the visit route
        visited.add(start);
        // get the pre course list
        ArrayList<Integer> list = outNodes.get(start);
        // keep checking its precourses
        for (int k : list) {
            if (!dfs(k, outNodes, visited, visitedFinish, res, resSet)) {
                return false;
            }
        }
        // if start is not duplicated
        if (!resSet.contains(start)) {
            // add to the result set and put it into the result order
            resSet.add(start);
            res[count++] = start;
        }
        // remove from current visited set
        visited.remove(start);
        return true;
    }
}