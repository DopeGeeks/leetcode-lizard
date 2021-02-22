package daohuei.leetcodelizard;

import java.util.*;

/**
 * Question: Valid Parentheses
 * Description: Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * Example 1: 
 * Input: s = "()"
 * Output: true
 * 
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * 
 * Example 4:
 * Input: s = "([)]"
 * Output: false
 * 
 * Example 5:
 * Input: s = "{[]}"
 * Output: true
 * 
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'
 */

public class ValidParentheses{
    // initialize a linked list store all valid openingBrackets
    public static class Stack{
        private LinkedList<Character> inputList = new LinkedList<Character> ();
        // put at top
        public void push(char c){
            inputList.addFirst(c);
        }
        // get the first element
        public char peek(){
            return inputList.getFirst();
        }
        // remove the top element
        public char pop(){
            return inputList.removeFirst();
        }
        // true if empty, else false
        public boolean isEmpty(){
            return inputList.isEmpty();
        }
    }
    /* Stack with linked list
     * 
     * Author: @RaymondKao
     * 
     * @Time_Complexity: O(n).
     * 
     * @Reason: Scan the input string with a for loop, so the time complexity equals to O(n).
     * 
     * @Space_Complexity: O(n).
     * 
     * @Reason: Implement Stack using linked list, the stack will at most stores all chars into the linked list.
     */
    public static boolean validParenthesesStack(String inputString){
        Stack s = new Stack();
        for(int i = 0; i < inputString.length(); i++){
            // RESTRAINT: cannot exceed 104
            if (i > 104){
                return false;
            }
            
            char c = inputString.charAt(i);
            // push valid openingBracket
            if(c == '(' || c == '{' || c == '['){
                s.push(c);
                continue;
            }
            
            // invalid openingBracket
            if (s.isEmpty()){
                return false;
            }
            
            char openingBracket;
            openingBracket = s.pop();
            // map to valid closingBracket
            switch(openingBracket){
                case ')':
                    if (openingBracket == '{' || openingBracket == '[')
                        return false;
                    break;
                case '}':
                    if (openingBracket == '(' || openingBracket == '[')
                        return false;
                    break; 
                case ']':
                    if (openingBracket == '(' || openingBracket == '{')
                        return false;
                    break;
            }
        }
        return (s.isEmpty());
    }
}