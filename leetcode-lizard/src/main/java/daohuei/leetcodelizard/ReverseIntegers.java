package daohuei.leetcodelizard;

public class ReverseIntegers {

    public static int reverse(int x) {
        int result = 0;
        int prev = 0;
        while (x != 0) {
            prev = result;
            result = result * 10 + x % 10;
            x /= 10;
            if (result / 10 != prev) {
                return 0;
            }
        }
        return result;
    }

}
