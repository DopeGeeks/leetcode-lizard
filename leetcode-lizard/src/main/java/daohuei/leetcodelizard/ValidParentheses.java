package daohuei.leetcodelizard;

import java.util.*;
import daohuei.leetcodelizard.utils.*;

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
    static class Stack{
        private LinkedList<Character> inputList = new LinkedList<Character> ();
        public void push(char c){
            inputList.addFirst(c);
        }
        public char peek(){
            return inputList.getFirst();
        }
        public char pop(){
            return inputList.removeFirst();
        }
        public boolean isEmpty(){
            return inputList.isEmpty();
        }
    }
    /*
     * Author: @RaymondKao
     * 
     * @Time_Complexity: O().
     * 
     * @Reason: .
     * 
     * @Space_Complexity: O().
     * 
     * @Reason: .
     */
    public static boolean ValidParenthesesStack(String inputString){
        Stack s = new Stack();
        for(int i = 0; i < inputString.length(); i++){
            char c = inputString.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                s.push(c);
                continue;
            }
            
            if (s.isEmpty())
                return false;
            
            char openingBracket;
            openingBracket = s.pop();
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
                    if (openingBracket == '(' || openingBracket == '[')
                    return false;
                    break;
            }
        }
        return (s.isEmpty());
    }
}