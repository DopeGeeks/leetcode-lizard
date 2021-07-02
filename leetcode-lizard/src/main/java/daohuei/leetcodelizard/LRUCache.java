package daohuei.leetcodelizard;

import java.util.HashMap;
/*
 * 146. LRU Cache
 * Link: https://leetcode.com/problems/lru-cache/
 */

public class LRUCache {
    /**
     * @author: daohuei
     * @description: double linked list
     * @time: O(1): use contant time every step
     * @space: O(capacity): for double linked list and hashmap
     */
    private int capacity = 0;
    // for implement O(1) push and get we need hashmap
    private HashMap<Integer, MyNode> map = new HashMap<>();
    // use for determine last LRU element
    private DoubleLinkedList list = new DoubleLinkedList();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        // if exist
        if (map.containsKey(key)) {
            // get it
            MyNode myNode = map.get(key);
            // put it the the tail
            list.moveToTail(myNode);
            // return its number
            return (int) myNode.value;
        } else {
            // no key
            return -1;
        }
    }

    public void put(int key, int value) {
        // if key exist
        if (map.containsKey(key)) {
            // get the node
            MyNode myNode = map.get(key);
            // update it
            myNode.value = value;
            // move to last used tail
            list.moveToTail(myNode);
        } else {
            // if full
            if (map.size() == capacity) {
                // get the head
                MyNode head = list.getHead();
                // remove head in the hash map
                map.remove((int) head.key);
                // remove head in the LRU list
                list.removeMyNode(head);
                // add the node and put it into the hashmap
                MyNode myNode = new MyNode(key, value);
                list.add(myNode);
                map.put(key, myNode);
            } else {
                // add the node and put it into the hashmap
                MyNode myNode = new MyNode(key, value);
                list.add(myNode);
                map.put(key, myNode);
            }
        }
    }
}

// for double linked list, we need prev and next
// for hash map, we need key value
class MyNode {
    Object key;
    Object value;
    MyNode prev = null;
    MyNode next = null;

    MyNode(Object k, Object v) {
        key = k;
        value = v;
    }
}

class DoubleLinkedList {
    // init with same head and tail with null node
    private MyNode dummyHead = new MyNode(null, null);
    private MyNode tail = dummyHead;

    // add new node
    public void add(MyNode myNode) {
        tail.next = myNode;
        myNode.prev = tail;
        tail = myNode;
    }

    // get the head
    public MyNode getHead() {
        return dummyHead.next;
    }

    // remove node
    public void removeMyNode(MyNode myNode) {
        // remove myNode
        myNode.prev.next = myNode.next;

        // if myNode is not the tail
        if (myNode.next != null) {
            // attch next and prev -> remove myNode with backward
            myNode.next.prev = myNode.prev;
        } else {
            // if myNode is the tail
            // then prev of myNode will become new taill afterward
            tail = myNode.prev;
        }
        // clear myNode's prev and next
        myNode.prev = null;
        myNode.next = null;
    }

    // move to tail
    public void moveToTail(MyNode myNode) {
        // just remove the node and add to the tail
        removeMyNode(myNode);
        add(myNode);
    }
}
