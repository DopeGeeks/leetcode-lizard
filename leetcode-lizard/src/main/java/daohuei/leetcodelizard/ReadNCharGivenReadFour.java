package daohuei.leetcodelizard;

/*
 * 157. Read N Characters Given Read4
 * Link: https://leetcode.com/problems/read-n-characters-given-read4/description/
 */
public class ReadNCharGivenReadFour {

    /**
     * @author: daohuei
     * @description: iteration method
     * @time: O(log n): actually log_4 (n)
     * @space: O(1): only need constant 4 for tmp
     */

    public int read(char[] buf, int n) {
        if (buf == null || n <= 0)
            return 0;

        boolean eof = false; // end of file flag
        int total = 0; // total bytes have read
        char[] tmp = new char[4]; // temp buffer

        while (!eof && total < n) {
            int count = read4(tmp);

            // check if it's the end of the file
            eof = count < 4;

            // get the current buffer actual count
            count = Math.min(count, n - total);

            // copy from temp buffer to buf
            for (int i = 0; i < count; i++)
                buf[total++] = tmp[i];
        }
        // return total count
        return total;
    }

    // The API: int read4(char *buf) reads 4 characters at a time from a file.
    public int read4(char[] buf) {
        return 0;
    }
}
