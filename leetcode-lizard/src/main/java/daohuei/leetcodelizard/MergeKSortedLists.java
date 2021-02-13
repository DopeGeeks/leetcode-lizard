package daohuei.leetcodelizard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import daohuei.leetcodelizard.utils.ListNode;

/*
 * 23. Merge k Sorted Lists
 * 
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * 
 * Merge all the linked-lists into one sorted linked-list and return it. 
 * Constraints:
 *      k == lists.length
 *      0 <= k <= 10^4
 *      0 <= lists[i].length <= 500
 *      -10^4 <= lists[i][j] <= 10^4
 *      lists[i] is sorted in ascending order.
 *      The sum of lists[i].length won't exceed 10^4.
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *      1->4->5,
 *      1->3->4,
 *      2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * 
 * Example 2:
 * Input: lists = []
 * Output: []
 * 
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 */
public class MergeKSortedLists {
    /**
     * Priority Queue Method
     * 
     * @author: daohuei
     * @description: This method use the priority queue to sort the head of nodes in
     *               `lists`. Each time we pop out the node from the queue, we will
     *               always get node with the minimal value
     * @time: O(N log k): N = number of all nodes in all lists, k = lists.length.
     *        Since each insert of priority queue is O(log k) and we examine through
     *        all nodes. So the answer is O(N log k)
     * @space: O(k): the queue's length, which contains all head of each node in
     *         `lists`
     */
    public static ListNode mergeKListsPriorityQueue(ListNode[] lists) {
        // Init the comparator for priority queue
        Comparator<ListNode> diffOnListNodeValue = new Comparator<ListNode>() {
            // the sorting of priority queue is using binary search, so it will cost
            // O(log k), which k = lists.length
            @Override
            public int compare(ListNode list1, ListNode list2) {
                // calculate the difference between current node value of each list
                // for the priority weight
                return list1.val - list2.val;
            }
        };

        // Init the priority queue with the rule just defined
        Queue<ListNode> listNodeQueue = new PriorityQueue<ListNode>(diffOnListNodeValue);
        // put every notnull listNode into the queue
        for (ListNode inputListNode : lists) {
            if (inputListNode != null) {
                listNodeQueue.add(inputListNode);
            }
        }

        // dummyHead is just for returning answer
        ListNode dummyHead = new ListNode();
        // now we use current node for storing answer
        ListNode currentNode = dummyHead;
        while (!listNodeQueue.isEmpty()) {
            // pop out the highest priority node
            // which means containing the minimal value
            currentNode.next = listNodeQueue.poll();
            // move to next
            currentNode = currentNode.next;
            // check the next node of the popped-out listNode
            ListNode nextNode = currentNode.next;
            if (nextNode != null) {
                // if still have node need to be examine, put it back to the queue
                listNodeQueue.add(nextNode);
            }
        }
        return dummyHead.next;
    }

    /**
     * Binary Merge Method
     * 
     * @author: daohuei
     * @description: the method use the merge 2 array and then binary select the
     *               list to be merged.
     * @time: O(N log k): N = number of all nodes in all lists, k = lists.length.
     *        The answer will be O(binary search) * O(merge 2 sorted array), the
     *        binary search = O(log k). Merge 2 sorted array = O(l1.length +
     *        l2.length). Each round of Sum(Merge 2 sorted array) = O(N). Therefore,
     *        the answer is O(N log k)
     * @space: O(N): the space of merge 2 sorted array function
     */
    public static ListNode mergeKListsBinary(ListNode[] lists) {
        // If the lists is empty, then we got null
        if (lists.length == 0) {
            return null;
        }
        // Start with interval: 1
        // which means we merge with neighbors(next node)
        int interval = 1;
        // if the interval is not larger then total length
        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i = i + interval * 2) {
                // the loop here is tricky:
                // we loop per merge
                // list at i will merge with list at i+interval
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
                // for the next iter, we will jump to next interval
            }
            // for the next round, we merge with list that its index is 2 times of interval
            interval *= 2;
        }
        return lists[0];
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans = h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            h.next = l2;
        }
        if (l2 == null) {
            h.next = l1;
        }
        return ans.next;
    }
}
