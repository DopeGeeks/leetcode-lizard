package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 323. Number of Connected Components in an Undirected Graph
 * Link: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
 */
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
(each edge is a pair of nodes), write a function to find 
the number of connected components in an undirected graph.
Example 1:
Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
     0          3
     |          |
     1 --- 2    4 
Output: 2
Example 2:
Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
     0           4
     |           |
     1 --- 2 --- 3
Output:  1
Note:
You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and 
thus will not appear together in edges.
*/
public class NumberOfConnectedComponentsInAnUndirectedGraph {

    int[] parent;
    int count;

    // union function
    private void union(int a, int b) {
        // get the parents from nodes a and b
        int parentA = parent[a];
        int parentB = parent[b];
        // if they are not the same
        if (parentA != parentB) {
            // make b's parent to be a's grandparent
            parent[parentA] = parentB;
            // reduce the count of total nodes, since there is a node being connected
            // after union all the edges, the count will represent the connected components
            // count
            count--;
        }
    }

    // find function, recursive worst case: O(n) for both space and time
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // return count function
    private int query() {
        return count;
    }

    /**
     * @author: daohuei
     * @description: union find method
     * @time: O(nk): k denotes # of edges, n => means finding the ancestor at worst
     *        need n
     * @space: O(n): for parent array
     */
    public int countComponentsUnionFind(int n, int[][] edges) {
        // empty case
        if (n == 0) {
            return 0;
        } else if (edges == null || edges.length == 0) {
            return n;
        }

        count = n;
        parent = new int[n];
        // init the parent by every node itself
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // iter through every edges
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            // if they are not having same ancestor(root parent)
            // they should be since they are connected together
            if (find(x) != find(y)) {
                // union them
                union(x, y);
            }
        }
        // query the count, which represent connected components count
        return query();
    }

    /**
     * @author: daohuei
     * @description: dfs method
     * @time: O(n): it only go through every node once
     * @space: O(k): k denotes to the max number of the components(could be n in the
     *         worst case)
     */
    public int countComponentsDFS(int n, int[][] edges) {
        if (n == 0) {
            return 0;
        } else if (edges == null || edges.length == 0) {
            return n;
        }

        int result = 0;
        // build a graph with hash map data structure
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);
        boolean[] visited = new boolean[n];

        // dfs(graph, visited, i), and count result
        // go through every nodes
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // use dfs to search all its neighbors
                dfs(graph, visited, i);
                // for every finished dfs, means a connected component
                // count it into the result
                result++;
            }
        }

        return result;
    }

    // build graph in form of adjacent list
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // init new list for all nodes
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<Integer>());
            }
        }
        // go through every edge
        for (int[] edge : edges) {
            // put connected node into the hashmap
            // it is a neighbor hashmap
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    // dfs: mark visited nodes, and keep dfs into children nodes
    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int i) {
        // if visited then break
        if (visited[i]) {
            return;
        }
        // mark it visited
        visited[i] = true;
        // search all neighbors
        for (int j : graph.get(i)) {
            dfs(graph, visited, j);
        }
    }
}
