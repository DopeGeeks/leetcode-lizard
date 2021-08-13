package daohuei.leetcodelizard;

/*
 * 158. Read N Characters Given Read4 II - Call multiple times
 * Link: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/description/
 */
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) 
that reads n characters from the file.
Note:
The read function may be called multiple times.
Example 1: 
Given buf = "abc"
read("abc", 1) // returns "a"
read("abc", 2); // returns "bc"
read("abc", 1); // returns ""
Example 2: 
Given buf = "abc"
read("abc", 4) // returns "abc"
read("abc", 1); // returns ""
 */
public class ReadNCharactersGivenRead4TwoCallMultipleTimes {

    /**
     * @author: daohuei
     * @description: cache method that allow continuous read from last read
     * @time: O(n): actually log_4 (n)
     * @space: O(1): only need constant 4 for cache
     */

    char[] cache = new char[4];
    int cacheIndex = 0;
    int count = 0;
    boolean isEnd = false;

    int read4(char[] buf) {
        return 0;
    }

    public int read(char[] buf, int n) {
        // if read4 is ended, return 0;
        if (buf == null || n <= 0 || isEnd)
            return 0;

        // index for the buffer
        int i = 0;
        // for adding cache into the buffer first
        while (cacheIndex > 0 && cacheIndex < count && i < n) {
            buf[i++] = cache[cacheIndex++];
        }
        // read while using while loop until n is exhausted
        while (i < n) {
            // read file into the cache
            cache = new char[4];
            count = read4(cache);
            // get the current range that indicates numbers amount which we need to put into
            // buffer from cahce
            int range = i + 3 < n ? count : Math.min(n - i, count);
            // copy data in cache to buffer from i to i+range
            System.arraycopy(cache, 0, buf, i, range);
            // i: buffer index move forward
            i += range;
            // update newest cacheIndex (for further use)
            if (range < 4)
                cacheIndex = range;
            // check if data to read is reaching the end and set the flag if so
            if (count < 4 && range == count)
                isEnd = true;
            // if nothing to read for future, just break the loop
            if (count < 4)
                break;
        }
        return i;
    }
}
