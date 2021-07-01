package daohuei.leetcodelizard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/*
 * 179. Largest Number
 * Link: https://leetcode.com/problems/largest-number/
 */
public class LargestNumber {
    /**
     * @author: daohuei
     * @description: hash map
     * @time: O(n^2): insert will at most n(n-1)/2
     * @space: O(n): for hashmap and node list
     */
    public String hashMap(int[] nums) {
        HashMap<Integer, MyNode> map = new HashMap<>();
        // put header from 0 to 9
        for (int i = 9; i >= 0; i--) {
            map.put(i, new MyNode(-1));
        }
        // go through nums
        for (int i = 0; i < nums.length; i++) {
            // key is the left most digit
            int key = getHighestPosition(nums[i]);

            // insert the current num to the node list
            MyNode head = map.get(key);
            MyNode MyNode = new MyNode(nums[i]);
            insert(head, MyNode);
        }
        // form the string
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            MyNode head = map.get(i).next;
            while (head != null) {
                sb.append(head.val);
                head = head.next;
            }
        }
        String res = sb.toString();
        // if the first char is 0, then it must be 0
        return res.charAt(0) == '0' ? "0" : res;

    }

    class MyNode {
        int val;
        MyNode next;

        MyNode(int val) {
            this.val = val;
        }
    }

    // for getting the left most digit
    private int getHighestPosition(int num) {
        while (num / 10 > 0) {
            num /= 10;
        }
        return num;
    }

    // for insert the node to the node list
    private void insert(MyNode head, MyNode node) {
        // go through the list
        while (head != null && head.next != null) {
            int cur = head.next.val;
            int insert = node.val;
            // if insert got larger combination than cur
            if (compare(cur, insert) == -1) {
                // insert before cur
                node.next = head.next;
                head.next = node;
                return;
            }
            // else keep looking
            head = head.next;
        }
        // if not found any insert point, just insert in the end
        head.next = node;
    }

    private int compare(int n1, int n2) {
        // check if they are same digit length
        int len1 = (n1 + "").length();
        int len2 = (n2 + "").length();
        // if same
        if (len1 == len2) {
            // simply check who's larger
            if (n1 > n2) {
                return 1;
            } else if (n1 < n2) {
                return -1;
            } else {
                return 0;
            }
        }

        // if not same, see how is larger when combine with each other
        int combination1 = (int) (n1 * Math.pow(10, len2)) + n2;
        int combination2 = (int) (n2 * Math.pow(10, len1)) + n1;

        if (combination1 > combination2) {
            return 1;
        } else if (combination1 < combination2) {
            return -1;
        } else {
            return 0;
        }

    }

    /**
     * @author: daohuei
     * @description: array sort
     * @time: O(nlogn): for sorting
     * @space: O(n): for array
     */
    public String arraySort(int[] nums) {
        // need integer class for comparator
        Integer[] n = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = nums[i];
        }
        // quick sort with nlogn
        // compare is same as above
        Arrays.sort(n, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                int len1 = (n1 + "").length();
                int len2 = (n2 + "").length();
                if (len1 == len2) {
                    if (n1 > n2) {
                        return -1;
                    } else if (n1 < n2) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
                int combination1 = (int) (n1 * Math.pow(10, len2)) + n2;
                int combination2 = (int) (n2 * Math.pow(10, len1)) + n1;

                if (combination1 > combination2) {
                    return -1;
                } else if (combination1 < combination2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // same as above, simply build the string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(n[i]);
        }
        String res = sb.toString();
        return res.charAt(0) == '0' ? "0" : res;

    }

    /**
     * @author: daohuei
     * @description: string compare
     * @time: O(nlogn): for sorting
     * @space: O(n): for array
     */
    public String stringCompare(int[] nums) {
        Integer[] n = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = nums[i];
        }
        Arrays.sort(n, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                String s1 = n1 + "" + n2;
                String s2 = n2 + "" + n1;

                // compareTo:
                // if their character order is same -> 0
                // if the order is further -> <0
                // if the order is former -> >0 。
                return s2.compareTo(s1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(n[i]);
        }
        String res = sb.toString();
        return res.charAt(0) == '0' ? "0" : res;
    }
}