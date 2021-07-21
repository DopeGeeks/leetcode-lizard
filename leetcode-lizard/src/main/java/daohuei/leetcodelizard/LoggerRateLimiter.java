package daohuei.leetcodelizard;

import java.util.HashMap;
import java.util.Map;

/*
 * 359. Logger Rate Limiter
 * Link: https://leetcode.com/problems/logger-rate-limiter/description/
 */
/**
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 * It is possible that several messages arrive roughly at the same time.
 */
/**
 * Logger logger = new Logger();
 * 
 * logging string "foo" at timestamp 1 logger.shouldPrintMessage(1, "foo");
 * returns true;
 * 
 * logging string "bar" at timestamp 2 logger.shouldPrintMessage(2,"bar");
 * returns true;
 * 
 * logging string "foo" at timestamp 3 logger.shouldPrintMessage(3,"foo");
 * returns false;
 * 
 * logging string "bar" at timestamp 8 logger.shouldPrintMessage(8,"bar");
 * returns false;
 * 
 * logging string "foo" at timestamp 10 logger.shouldPrintMessage(10,"foo");
 * returns false;
 * 
 * logging string "foo" at timestamp 11 logger.shouldPrintMessage(11,"foo");
 * returns true;
 */
public class LoggerRateLimiter {

    /**
     * @author: daohuei
     * @description: hash map method
     * @time: O(1): no need to explain
     * @space: O(n): hashmap store all unique messages
     */
    public class Logger {
        // hash map for storing appeared msg and its last print timestamp
        Map<String, Integer> map = new HashMap<>();

        // rate limiter in seconds
        int limiter = 10;

        /** Initialize your data structure here. */
        public Logger() {

        }

        /**
         * Returns true if the message should be printed in the given timestamp,
         * otherwise returns false. If this method returns false, the message will not
         * be printed. The timestamp is in seconds granularity.
         */
        public boolean shouldPrintMessage(int timestamp, String message) {
            // if message not exist before
            if (!map.containsKey(message)) {
                // store its timestamp
                map.put(message, timestamp);
                // print it
                return true;
            } else {
                // if over 10 seconds (limiter)
                if (timestamp - map.get(message) >= limiter) {
                    // update timestamp
                    map.put(message, timestamp);
                    // print it
                    return true;
                }
            }
            // skip
            return false;
        }
    }
}
