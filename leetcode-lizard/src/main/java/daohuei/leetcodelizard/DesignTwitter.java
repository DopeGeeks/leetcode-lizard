package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
/*
 * 355. Design Twitter
 * Link: https://leetcode.com/problems/design-twitter/
 */

public class DesignTwitter {

    /**
     * @author: daohuei
     * @description: it is all about oop design
     * @time: O(n): consider follow function, the worst case n - 1 for follow all
     *        users
     * @space: O(nk): for n users and their average k posts
     */
    class Twitter {

        // the global var to store time
        private int timeStamp = 0;
        // map user from id: easy to find if user exist
        private HashMap<Integer, User> userMap;

        /** Initialize your data structure here. */
        public Twitter() {
            userMap = new HashMap<>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            // if the user not exist
            if (!userMap.containsKey(userId)) {
                // put new user obj into the map
                userMap.put(userId, new User(userId));
            }
            // post with new tweet
            userMap.get(userId).post(tweetId);
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
         * the news feed must be posted by users who the user followed or by the user
         * herself. Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> rst = new ArrayList<>();
            // if user not exist, return empty list
            if (!userMap.containsKey(userId)) {
                return rst;
            }

            // get the followed users
            Set<Integer> users = userMap.get(userId).getFollowed();
            // use priority queue to sort the most recent tweet
            PriorityQueue<Tweet> minHeap = new PriorityQueue<>(10, new Comparator<Tweet>() {
                @Override
                public int compare(Tweet a, Tweet b) {
                    return b.time - a.time;
                }
            });
            // get the head tweet from every followed users
            for (Integer u : users) {
                Tweet t = userMap.get(u).getTweet_head();
                // very imporant! If we add null to the head we are screwed.
                if (t != null) {
                    minHeap.add(t);
                }
            }

            int count = 0;
            // while the minHeap is not empty yet(means still have tweet left and more than
            // 10 from all followed users)
            // and the feed count is not 10 yet, keep polling
            while (!minHeap.isEmpty() && count < 10) {
                Tweet t = minHeap.poll();
                rst.add(t.id);
                count++;
                // remeber to extract next tweet to the heap
                if (t.next != null) {
                    minHeap.add(t.next);
                }
            }
            // return the result as the tweet id list
            return rst;
        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a
         * no-op.
         */
        public void follow(int followerId, int followeeId) {
            // if the follower not exist
            if (!userMap.containsKey(followerId)) {
                // create new user
                User user = new User(followerId);
                userMap.put(followerId, user);
            }
            // if the followee not exist
            if (!userMap.containsKey(followeeId)) {
                // create new user
                User user = new User(followeeId);
                userMap.put(followeeId, user);
            }
            // follow it
            userMap.get(followerId).follow(followeeId);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a
         * no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            // if not exist or they are the same user, ignore it
            if (!userMap.containsKey(followerId) || followerId == followeeId) {
                return;
            }
            // unfollow it
            userMap.get(followerId).unfollow(followeeId);
        }

        /**
         * OO design so User can follow, unfollow and post itself. The property of the
         * class are better to be private.
         */
        class User {
            private int id;
            // Store the Tweet that the user post
            // Think about why we use the pointer but not the arrays or set ?
            // (Similar to Using LinkedList)
            private Tweet tweet_head;
            private Set<Integer> followed;

            public Tweet getTweet_head() {
                return tweet_head;
            }

            public Set<Integer> getFollowed() {
                return followed;
            }

            User(int id) {
                this.id = id;
                followed = new HashSet<>();
                // The user should first follow itself
                // So that when we do getNewsFeed, we get get the result directly.
                follow(id);
                this.tweet_head = null;
            }

            public void follow(int id) {
                followed.add(id);
            }

            public void unfollow(int id) {
                followed.remove(id);
            }

            /**
             * everytime user post a new tweet, add it to the head of Tweet list.
             * 
             * @param id - the Tweet id
             */
            public void post(int id) {
                Tweet t = new Tweet(id);
                t.next = tweet_head;
                tweet_head = t;
            }
        }

        /**
         * Tweet link to next Tweet so that we can save a lot of time when we execute
         * getNewsFeed(userId)
         */
        private class Tweet {
            int id;
            int time;
            Tweet next;
            // String content; We don't need this property here...

            Tweet(int id) {
                this.id = id;
                time = timeStamp++;
                next = null;
            }
        }
    }

}
