package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 133. Clone Graph
 * https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * @author: daohuei
     * @description: bfs: process every node first
     * @time: O(n): traverse every nodes
     * @space: O(n): hashmap contains all the nodes
     */
    public Node bfs(Node node) {
        // empty case
        if (node == null) {
            return node;
        }
        // the queue is for storing original node to be processed
        Queue<Node> queue = new LinkedList<>();
        // map is to map the value and node itself of the new copied node
        Map<Integer, Node> map = new HashMap<>();

        // process first node:
        // put original node into the queue
        queue.offer(node);
        // new a new node
        Node n = new Node();
        // copy the value
        n.val = node.val;
        // new an empty neighbor list
        n.neighbors = new ArrayList<Node>();
        // put node into the hashmap with the key of value
        map.put(n.val, n);

        while (!queue.isEmpty()) {
            // get an original node from the queue
            Node cur = queue.poll();
            for (Node temp : cur.neighbors) {
                // traverse the node's neighbors
                // if map do not have that neighbor
                if (!map.containsKey(temp.val)) {
                    // copy it and put it into the map
                    n = new Node();
                    n.val = temp.val;
                    n.neighbors = new ArrayList<Node>();
                    map.put(n.val, n);
                    // and put original one into the queue
                    queue.offer(temp);
                }
                // then put the copied one into the copied neighbors
                map.get(cur.val).neighbors.add(map.get(temp.val));
            }
        }

        return map.get(node.val);
    }

    /**
     * @author: daohuei
     * @description: dfs: process every neighbors first
     * @time: O(n): traverse every nodes
     * @space: O(n): hashmap contains all the nodes
     */
    public Node dfsMethod(Node node) {
        // empty case
        if (node == null) {
            return node;
        }
        // new a hashmap for storing copied node
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Integer, Node> map) {
        // if the map already contains the node.val
        // means this node has already been copied
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }
        // copy the node with an empty neighbor list
        Node n = new Node();
        n.val = node.val;
        n.neighbors = new ArrayList<Node>();
        // put it into the map
        map.put(node.val, n);
        // add its neighbors
        for (Node temp : node.neighbors) {
            // if the node not already in the map (have not been copied yet)
            // just keep do the copy with recursion method
            n.neighbors.add(dfs(temp, map));
        }
        // and return that node
        return n;
    }

}