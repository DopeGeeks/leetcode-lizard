package daohuei.leetcodelizard;

/*
 * 307. Range Sum Query – Mutable
 * Link: https://leetcode.com/problems/range-sum-query-2d-mutable/
 */
public class RangeSumQueryMutable {
    class TreeNode {
        int start;
        int end;
        int sum;
        TreeNode leftChild;
        TreeNode rightChild;

        // a node will have array's start index and end index and its accumulated sum of
        // all its children (including itself)
        public TreeNode(int left, int right, int sum) {
            this.start = left;
            this.end = right;
            this.sum = sum;
        }

        public TreeNode(int left, int right) {
            this.start = left;
            this.end = right;
            this.sum = 0;
        }
    }

    /**
     * @author: daohuei
     * @description: accumulated difference with binary search tree
     * @time: O(logn): update: log(n) , sumArray: log(n)
     * @space: O(n): for binary search tree
     */
    public class BSTNumArray {
        TreeNode root = null;

        public BSTNumArray(int[] nums) {
            if (nums == null || nums.length == 0)
                return;
            // build the tree
            this.root = buildTree(nums, 0, nums.length - 1);
        }

        // for update value
        void update(int i, int val) {
            updateHelper(root, i, val);
        }

        void updateHelper(TreeNode root, int i, int val) {
            if (root == null)
                return;
            // binary search to the target
            int mid = root.start + (root.end - root.start) / 2;
            if (i <= mid) {
                updateHelper(root.leftChild, i, val);
            } else {
                updateHelper(root.rightChild, i, val);
            }
            // if there is only one node: the leaf is found
            if (root.start == root.end && root.start == i) {
                root.sum = val;
                return;
            }
            // else update the root value
            root.sum = root.leftChild.sum + root.rightChild.sum;
        }

        public int sumRange(int i, int j) {
            return sumRangeHelper(root, i, j);
        }

        public int sumRangeHelper(TreeNode root, int i, int j) {
            if (root == null || j < root.start || i > root.end || i > j)
                return 0;
            // if the range is exactly the current root's start and end
            // then root's accumulated sum is the answer
            if (i <= root.start && j >= root.end) {
                return root.sum;
            }
            // binary search
            int mid = root.start + (root.end - root.start) / 2;
            int result = sumRangeHelper(root.leftChild, i, Math.min(mid, j))
                    + sumRangeHelper(root.rightChild, Math.max(mid + 1, i), j);

            return result;
        }

        public TreeNode buildTree(int[] nums, int i, int j) {
            if (nums == null || nums.length == 0 || i > j)
                return null;

            // if only one node
            if (i == j) {
                return new TreeNode(i, j, nums[i]);
            }

            TreeNode current = new TreeNode(i, j);
            // build up the tree with binary search
            int mid = i + (j - i) / 2;

            current.leftChild = buildTree(nums, i, mid);
            current.rightChild = buildTree(nums, mid + 1, j);
            // calculate the accumulated sum
            current.sum = current.leftChild.sum + current.rightChild.sum;

            return current;
        }
    }

    /**
     * @author: daohuei
     * @description: using Fenwick tree/binary index tree for sum array
     * @time: O(logn): update: log(n) , sumArray: log(n)
     * @space: O(n): for binary index tree
     */
    public class FenwickTreeNumArray {

        int[] btree;
        int[] arr;

        public FenwickTreeNumArray(int[] nums) {
            // the binary index tree
            btree = new int[nums.length + 1];
            arr = nums;

            for (int i = 0; i < nums.length; i++) {
                // insert number into the binary index tree
                add(i + 1, nums[i]);
            }
        }

        void add(int i, int val) {
            // j&-j, the right most 1
            // 1 -> 1 1 -> 1
            // 2 -> 2 10 -> 10
            // 3 -> 1 11 -> 01
            // 4 -> 4 100 -> 100
            // 5 -> 1 101 -> 001
            // 6 -> 2 110 -> 010
            // 7 -> 1 111 -> 001
            // 8 -> 8 1000 -> 1000
            // 9 -> 1 1001 -> 0001
            // 10 -> 2 1010 -> 0010
            // 11 -> 1 1011 -> 0001
            // 12 -> 4 1100 -> 0100
            // this will go through every index that has accumulated sum need this number
            for (int j = i; j < btree.length; j = j + (j & (-j))) {
                btree[j] += val;
            }
        }

        void update(int i, int val) {
            add(i + 1, val - arr[i]);
            arr[i] = val;
        }

        public int sumRange(int i, int j) {
            return sumHelper(j + 1) - sumHelper(i);
        }

        public int sumHelper(int i) {
            int sum = 0;
            // if we sum up, we just go through the binary accumulated sum
            for (int j = i; j >= 1; j = j - (j & (-j))) {
                sum += btree[j];
            }
            return sum;
        }
    }

}
