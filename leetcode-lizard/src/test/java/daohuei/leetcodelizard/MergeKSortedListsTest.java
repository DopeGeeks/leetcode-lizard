package daohuei.leetcodelizard;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import daohuei.leetcodelizard.utils.ListNode;

//  * Example 2:
//  * Input: lists = []
//  * Output: []
//  * 
//  * Example 3:
//  * Input: lists = [[]]
//  * Output: []
public class MergeKSortedListsTest {
    @Test
    public void testOnMergeKSortedLists() {
        ListNode ans;
        ListNode[] lists1, lists2;
        lists1 = new ListNode[] { new ListNode(new int[] { 1, 4, 5 }), new ListNode(new int[] { 1, 3, 4 }),
                new ListNode(new int[] { 2, 6 }) };
        lists2 = new ListNode[] { new ListNode(new int[] { 1, 4, 5 }), new ListNode(new int[] { 1, 3, 4 }),
                new ListNode(new int[] { 2, 6 }) };
        ans = new ListNode(new int[] { 1, 1, 2, 3, 4, 4, 5, 6 });
        assertTrue(ans.equals(MergeKSortedLists.mergeKListsPriorityQueue(lists1)));
        assertTrue(ans.equals(MergeKSortedLists.mergeKListsBinary(lists2)));
        lists1 = new ListNode[] {};
        lists2 = new ListNode[] {};
        ans = new ListNode();
        assertTrue(ans.equals(MergeKSortedLists.mergeKListsPriorityQueue(lists1)));
        assertTrue(ans.equals(MergeKSortedLists.mergeKListsBinary(lists2)));
        lists1 = new ListNode[] { new ListNode() };
        lists2 = new ListNode[] { new ListNode() };
        ans = new ListNode();
        assertTrue(ans.equals(MergeKSortedLists.mergeKListsPriorityQueue(lists1)));
        assertTrue(ans.equals(MergeKSortedLists.mergeKListsBinary(lists2)));
    }
}
