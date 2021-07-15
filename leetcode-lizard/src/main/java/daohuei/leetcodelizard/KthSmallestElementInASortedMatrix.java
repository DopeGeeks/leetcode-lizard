package daohuei.leetcodelizard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 378. Kth Smallest Element in a Sorted Matrix
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestElementInASortedMatrix {

    /**
     * @author: daohuei
     * @description: priority queue method
     * @time: O(nlogk): for sorting in the priority queue
     * @space: O(k): for priority queue, it only store at most k numbers
     */
    public int priorityQueueMethod(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || k <= 0) {
            return 0;
        }
        int n = matrix.length;
        Queue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });

        // Initialize the queue with head of each row
        for (int i = 0; i < n; i++) {
            queue.offer(new Node(i, 0, matrix[i][0]));
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // if k is reached, that is the answer
            if (k == 1) {
                return node.val;
            }
            // if not at the end column
            if (node.y + 1 < n) {
                // add next node into the queue with right
                // once it is offered, it will be sorted
                queue.offer(new Node(node.x, node.y + 1, matrix[node.x][node.y + 1]));
            }
            k--;
        }

        return 0;
    }

    class Node {
        // row
        int x;
        // col
        int y;
        int val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    /**
     * @author: daohuei
     * @description: binary search method
     * @time: O(nlogn): for binary search
     * @space: O(1): for priority queue
     */
    public int kthSmallest(int[][] matrix, int k) {
        // empty case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0)
            return -1;

        int n = matrix.length;
        int min = matrix[0][0];
        int max = matrix[n - 1][n - 1];
        // binary search with min and max
        while (min < max) {
            // calculate the average, make it as the mid point for binary search
            int target = min + (max - min) / 2;
            // get the count that smaller than target
            int count = countSmallerItem(matrix, target);
            // if need larger target
            if (count < k) {
                // move min to the right of the mid point
                min = target + 1;
            } else {
                // move max to the mid point
                max = target;
            }
        }
        return min;
    }

    // O(n)
    private int countSmallerItem(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        // row start from head
        // col start from tail
        int i = 0, j = n - 1; // n*n
        while (i < n && j >= 0) {
            // too large
            if (matrix[i][j] > target) {
                // move to previous col
                j--;
            } else {
                // if found the smaller number
                // since smaller column has smaller number
                // so we conclude all the number before current column are smaller
                // we count them together
                count += j + 1;
                // move to next row
                i++;
            }
        }
        return count;
    }
}