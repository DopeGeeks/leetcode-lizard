package daohuei.leetcodelizard.utils;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int[] values) {
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        this.val = dummyHead.next.val;
        this.next = dummyHead.next.next;
    }

    @Override
    public String toString() {
        ListNode ptr = this;
        String repr = Integer.toString(this.val);
        while (ptr.next != null) {
            ptr = ptr.next;
            repr = repr + " -> " + Integer.toString(ptr.val);
        }
        return repr;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == this) || (o == null && this.val == 0 && this.next == null)) {
            return true;
        }
        if (!(o instanceof ListNode)) {
            return false;
        }
        ListNode inputListNode = (ListNode) o;
        ListNode thisListNode = this;
        while (inputListNode != null || thisListNode != null) {
            if (inputListNode == null || thisListNode == null || inputListNode.val != thisListNode.val) {
                return false;
            }
            inputListNode = inputListNode.next;
            thisListNode = thisListNode.next;
        }
        return true;
    }
}
