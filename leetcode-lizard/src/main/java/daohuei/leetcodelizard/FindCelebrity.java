package daohuei.leetcodelizard;

/*
 * 277. Find the Celebrity
 * Link: https://leetcode.com/problems/find-the-celebrity/
 * Suppose you are at a party with n people (labeled from 0 to n - 1) 
 * and among them, there may exist one celebrity. 
 * The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. 
 * The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. 
 * You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 */
public class FindCelebrity {

    /**
     * @author: daohuei
     * @description: iteration
     * @time: O(n) just simply iter through people twice
     * @space: O(1) not using any
     */
    public int findCelebrity(int n) {
        int suspectedCelebrity = 0;
        for (int i = 1; i < n; i++) {
            // if someone is being know, he is the suspected celebrity
            if (knows(suspectedCelebrity, i)) {
                suspectedCelebrity = i;
            }
        }
        // go through people before sus cel
        for (int i = 0; i < suspectedCelebrity; i++) {
            // if suspected celebrity knows some other body or
            // someone does not know the sus cel
            if (knows(suspectedCelebrity, i) || !knows(i, suspectedCelebrity)) {
                // no one is celebrity
                return -1;
            }
        }
        // go through every people after sus cel
        // since for the people after sus cel, we already known that sus cel does not
        // know people after him/her at the first loop
        for (int i = suspectedCelebrity + 1; i < n; i++) {
            // if someone does not know the sus cel
            if (!knows(i, suspectedCelebrity)) {
                // no one is celebrity
                return -1;
            }
        }
        return suspectedCelebrity;
    }

    /*
     * The knows API is defined in the parent class Relation. boolean knows(int a,
     * int b); bool knows(a, b) which tells you whether A knows B
     */
    private boolean knows(int a, int b) {
        return true;
    };
}
