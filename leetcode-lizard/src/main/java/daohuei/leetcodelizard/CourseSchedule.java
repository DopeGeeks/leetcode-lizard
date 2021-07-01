package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 207. Course Schedule
 * Link: https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {
    /**
     * @author: daohuei
     * @description: hash map + queue
     * @time: O(n^2): worst case: n^2
     * @space: O(n^2): for course list and the hash map
     */
    public boolean hashMapAndQueue(int numCourses, int[][] prerequisites) {
        // number of prerequisite
        HashMap<Integer, Integer> outNum = new HashMap<>();
        // the course list that need key to be prerequisite
        HashMap<Integer, ArrayList<Integer>> inNodes = new HashMap<>();
        // for every node
        HashSet<Integer> set = new HashSet<>();
        // all combination
        int rows = prerequisites.length;
        for (int i = 0; i < rows; i++) {
            // further course
            int key = prerequisites[i][0];
            // pre course
            int value = prerequisites[i][1];

            // add all courses in the set
            set.add(key);
            set.add(value);
            // init with 0
            if (!outNum.containsKey(key)) {
                outNum.put(key, 0);
            }
            if (!outNum.containsKey(value)) {
                outNum.put(value, 0);
            }
            // count 1 for pre course count
            int num = outNum.get(key);
            outNum.put(key, num + 1);

            // put empty array(further course list) for pre course
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
        while (!queue.isEmpty()) {

            // get the available course
            int v = queue.poll();
            // get its further course list
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
                return false;
            }
        }
        return true;
    }

    /**
     * @author: daohuei
     * @description: dfs
     * @time: O(n): go through each node
     * @space: O(n^2): for course list and the hash map
     */
    public boolean dfsMethod(int numCourses, int[][] prerequisites) {
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

        HashSet<Integer> visitedFinish = new HashSet<>();
        for (int k : set) {
            if (!dfs(k, outNodes, new HashSet<Integer>(), visitedFinish)) {
                return false;
            }
            // add to global visited set
            visitedFinish.add(k);
        }
        // all checks are passed, done!
        return true;
    }

    private boolean dfs(int start, HashMap<Integer, ArrayList<Integer>> outNodes, HashSet<Integer> visited,
            HashSet<Integer> visitedFinish) {
        // 1. visit before
        // 2. start has no pre course
        if (visitedFinish.contains(start) || !outNodes.containsKey(start)) {
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
            if (!dfs(k, outNodes, visited, visitedFinish)) {
                return false;
            }
        }
        // if precourses are all fine, then return true and remove it from visited set
        visited.remove(start);
        return true;
    }
}