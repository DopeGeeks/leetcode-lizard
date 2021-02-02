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
}
