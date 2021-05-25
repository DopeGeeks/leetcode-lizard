package daohuei.leetcodelizard;
/*
 * Author: @ballm06m06
 * Qusetion: Palindrome Number
 * Description: An integer is a palindrome when it reads the same backward as forward. 
 *              For example, 121 is palindrome while 123 is not.            
*/

public class PalindromeNumber {
    // My Method - Poor performance
    public boolean isPalindrome(int x) {
        // Convert x into char array
        char[] array = String.valueOf(x).toCharArray();
        int check = 0;

        // check if left element's value equals to the right element's value
        for (int i = 0; i < array.length / 2; i++) {
            if (array[i] == array[(array.length - 1) - i]) {
                check++;
            }
        }
        // if the check number equals to half of array length, it means it's palindrome
        if (check == array.length / 2)
            return true;
        else
            return false;
    }

    // Other online genius 1
    public boolean isPalindrome2(int x) {
        // Convert int to String
        String s = String.valueOf(x);
        // Check if s equals to reverse s, StringBuilder got built-in reverse function
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    // Other online genius 1
    public boolean isPalindrome3(int x) {

        int reverse = 0;
        int num = x;

        // Reverse the number without convert it into String will improve performance.
        while (num > 0) {
            int a = num % 10;
            reverse = reverse * 10 + a;
            num = num / 10;
        }

        if (reverse == x) {
            return true;
        }

        return false;
    }
}
