package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 295. Find Median from Data Stream
 * Link: https://leetcode.com/problems/find-median-from-data-stream/
 */

public class FindMedianFromDataStream {
    /**
     * @author: daohuei
     * @description: binary search
     * @time: O(n): since list.add still n
     * @space: O(1): not using any
     */
    class binarySearchMedianFinder {
        List<Integer> list = new ArrayList<>();

        /** initialize your data structure here. */
        public binarySearchMedianFinder() {

        }

        public void addNum(int num) {
            int insert = -1;
            int low = 0;
            int high = list.size() - 1;
            // using binary search
            while (low <= high) {
                int mid = (low + high) >>> 1;
                // if num is less equal than the mid num
                if (num <= list.get(mid)) {
                    // check if it larger than previous num in the list
                    int pre = mid > 0 ? list.get(mid - 1) : Integer.MIN_VALUE;
                    if (num >= pre) {
                        // if so, this is the insert point
                        insert = mid;
                        break;
                    } else {
                        // if not, search with lower part
                        high = mid - 1;
                    }
                } else {
                    // search with higher part
                    low = mid + 1;
                }
            }
            // if insert point not found
            if (insert == -1) {
                // means the insert point should be the last point
                insert = list.size();
            }
            // insert it: need n though
            list.add(insert, num);
        }

        public double findMedian() {
            int n = list.size();
            // if even
            if ((n & 1) == 1) {
                return list.get(n / 2);
            } else {
                // if odd
                return ((double) list.get(n / 2) + list.get(n / 2 - 1)) / 2;
            }
        }
    }

}

/**
 * @author: daohuei
 * @description: binary search tree
 * @time: O(n): worst case can be n, but usually logn
 * @space: O(1): not using any
 */
class BinarySearchTreeMedianFinder {
    BST bst;

    /** initialize your data structure here. */
    public BinarySearchTreeMedianFinder() {
        bst = new BST();
    }

    public void addNum(int num) {
        bst.add(num);
    }

    public double findMedian() {
        int num = bst.size();
        if (num % 2 == 0) {
            return ((double) bst.find(num / 2) + bst.find(num / 2 - 1)) / 2;
        } else {
            return bst.find(num / 2);
        }
    }
}

// build a binary search tree
class BST {
    class Node {
        int val;
        int size;
        Node left, right;

        Node(int v) {
            val = v;
            size = 1;
        };
    };

    private Node root;

    BST() {

    }

    public void add(int val) {
        // add new node
        Node newNode = new Node(val);
        // init current node with root
        Node current = root;
        // init parent node with null
        Node parent = null;
        // if no root
        if (current == null) {
            // let new node be the root
            root = newNode;
            return;
        }
        while (true) {
            parent = current;
            // if less than current, must be in the left tree
            if (val < current.val) {
                current = current.left;
                parent.size++;
                // if left tree not exist
                if (current == null) {
                    // make new node be the root of the left tree
                    parent.left = newNode;
                    return;
                }
                // else: right tree
            } else {
                current = current.right;
                parent.size++;
                // if right tree not exist
                if (current == null) {
                    // make new node be the root of the right tree
                    parent.right = newNode;
                    return;
                }
            }
        }

    }

    public int find(int k) {
        Node t = root;
        while (true) {
            // get the left tree size
            int leftSize = t.left != null ? t.left.size : 0;
            // if it is k, than the root is the kth node, found it
            if (leftSize == k)
                return t.val;
            // if larger, which means in left tree
            if (leftSize > k) {
                t = t.left;
            } else {
                // smaller: in right tree
                // we can drop left tree and current root, move to the right
                k = k - leftSize - 1;
                t = t.right;
            }
        }
    }

    public int size() {
        return root.size;
    }
};

/**
 * @author: daohuei
 * @description: priority queue
 * @time: O(n): worst case can be n, but usually logn
 * @space: O(1): not using any
 */
class PriorityQueueMedianFinder {
    private Queue<Long> left = new PriorityQueue<Long>();
    private Queue<Long> right = new PriorityQueue<Long>();

    public void addNum(int num) {
        // left one add the current number
        left.add((long) num);
        // right one add the smallest number with negative
        right.add(-left.poll());
        // if right size is larger than left, add the smallest of right to the left
        // for making sure they are same size or left is 1 larger than right
        if (left.size() < right.size())
            left.add(-right.poll());
    }

    public double findMedian() {
        // if left larger, left's top is the median
        // else means they must be same, so calculate their top average
        return left.size() > right.size() ? left.peek() : (left.peek() - right.peek()) / 2.0;
    }
}