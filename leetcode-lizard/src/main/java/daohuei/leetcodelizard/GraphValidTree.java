package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 261. Graph Valid Tree
 * Link: https://leetcode.com/problems/graph-valid-tree/description/
 */
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.
For example:
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class GraphValidTree {
    /**
     * @author: daohuei
     * @description: union and find method
     * @time: O(n): go through every edges
     * @space: O(n): for storing all nodes
     */

    int[] father;
    int count;

    public boolean unionFindMethod(int n, int[][] edges) {
        // init union find data structure
        father = new int[n];
        count = n;
        // init father structure: all node point to themselves
        for (int i = 0; i < n; i++)
            father[i] = i;
        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0], y = edges[i][1];
            if (find(x) == find(y))
                return false; // ideally, this edges[i] should be the very first time x and y node connect;
                              // however, if they have been grouped together under same ancestor before, there
                              // exist a feedback loop (cycle) between them.
            union(x, y);
        }
        // no other isolated sub-graph
        return count == 1;
    }

    private void union(int x, int y) {
        // find the root of x and y
        int rootX = find(x), rootY = find(y);
        // if they are not the same
        if (rootX != rootY) {
            // link x to the y
            father[rootX] = rootY;
            count--;
        }
    }

    // find the root of x
    private int find(int x) {
        if (father[x] == x)
            return x;
        return father[x] = find(father[x]);
    }

    /**
     * @author: daohuei
     * @description: dfs method
     * @time: O(n): go through every node and their children
     * @space: O(n): for storing visited set, graph, and recursion stack
     */
    public boolean dfsMethod(int n, int[][] edges) {
        if (n == 0)
            return false;
        // using hashmap to build the graph first
        Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();

        // dfs(graph, visited, i, -1) and validate cycle
        if (!dfs(graph, visited, 0, -1))
            return false;

        // validate if all edge connected: # of visited node should match graph size
        return visited.size() == graph.size();
    }

    // build graph in form of adjacent list
    private Map<Integer, Set<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.putIfAbsent(i, new HashSet<Integer>());
        // undirected, add edge to both nodes
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    // dfs: mark visited nodes, and keep dfs into children nodes
    private boolean dfs(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int curr, int pre) {
        // if contains current => means a cycle
        if (visited.contains(curr))
            return false;
        // visited
        visited.add(curr);
        // go through every child in curr
        for (int child : graph.get(curr)) {
            // if child is previous visited, means parent => ignore
            if (child == pre)
                continue;
            // keep checking children
            if (!dfs(graph, visited, child, curr))
                return false;
        }
        // if all pass then return true
        return true;
    }

    /**
     * @author: daohuei
     * @description: bfs method
     * @time: O(n): go through every nodes and their children
     * @space: O(n): for storing visited set, graph and queue
     */
    public boolean validTree(int n, int[][] edges) {
        // same as dfs method
        Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();

        // queue for bfs
        Queue<Integer> queue = new LinkedList<>();
        // init with node 0
        queue.offer(0);
        visited.add(0);

        while (!queue.isEmpty()) {
            // get the node
            int node = queue.poll();
            // for every child
            for (int child : graph.get(node)) {
                // if visit before, cycle
                // if not visit, add child to visit list
                if (!visited.add(child))
                    return false;
                // put it into the queue
                queue.offer(child);
                // remove previous pointer to avoid infinite loop
                graph.get(child).remove(node);
            }
        }

        // validate if all edge connected: # of visited node should match graph size
        return visited.size() == graph.size();
    }

}
