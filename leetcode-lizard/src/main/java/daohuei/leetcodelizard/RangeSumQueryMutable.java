package daohuei.leetcodelizard;

/*
 * 307. Range Sum Query - Mutable
 * Link: https://leetcode.com/problems/range-sum-query-mutable/description/
 */

public class RangeSumQueryMutable {
    class NumArray {
        /**
         * @author: daohuei
         * @description: segment tree
         * @time: O(n/logn): n for build the segment tree by DFS, logn for each query
         *        with binary search
         * @space: O(n): for segment tree
         */
        SegTreeNode segTree;

        public NumArray(int[] nums) {
            // empty case
            if (nums == null || nums.length == 0)
                return;
            segTree = build(nums, 0, nums.length - 1);
        }

        public void update(int index, int val) {
            updateNode(segTree, index, val);
        }

        public int sumRange(int left, int right) {
            return rangeQuery(segTree, left, right);
        }

        class SegTreeNode {
            int start, end, sum;
            SegTreeNode left, right;

            public SegTreeNode(int start, int end, int sum, SegTreeNode left, SegTreeNode right) {
                // start and end for the segment
                this.start = start;
                this.end = end;
                // the sum of the segment
                this.sum = sum;
                // left node and right node
                this.left = left;
                this.right = right;
            }
        }

        // build the seg tree
        private SegTreeNode build(int[] nums, int start, int end) {
            // if only 1 number, no left and right nodes
            if (start == end)
                return new SegTreeNode(start, end, nums[start], null, null);
            // get the mid index
            int mid = start + (end - start) / 2;
            // build with dfs
            SegTreeNode left = build(nums, start, mid);
            SegTreeNode right = build(nums, mid + 1, end);
            // sum the left node and the right node
            return new SegTreeNode(start, end, left.sum + right.sum, left, right);
        }

        //
        private void updateNode(SegTreeNode node, int i, int val) {
            // if the node reach the leaf, update the sum with new value
            if (node.start == i && node.end == i) {
                node.sum = val;
                return;
            }
            // if less or equal than left's end, try left
            if (i <= node.left.end)
                updateNode(node.left, i, val);
            // else just try right
            else
                updateNode(node.right, i, val);
            // update the sum
            node.sum = node.left.sum + node.right.sum;
        }

        private int rangeQuery(SegTreeNode node, int i, int j) {
            // if the node is exactly at that index, the sum is the value
            if (node.start == i && node.end == j)
                return node.sum;
            // try left or right based on conditions
            if (j <= node.left.end)
                return rangeQuery(node.left, i, j);
            else if (i >= node.right.start)
                return rangeQuery(node.right, i, j);
            // or else get the mid point
            int mid = node.start + (node.end - node.start) / 2;
            // query left and right with the slpit mid point
            return rangeQuery(node.left, i, mid) + rangeQuery(node.right, mid + 1, j);
        }
    }

}
