package daohuei.leetcodelizard;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
 * 353. Design Snake Game
 * Link: https://leetcode.com/problems/design-snake-game/description/
 */
/*
1. Snake start with position [0, 0], snake length is 1
    Use a doublely LinkedList to represent snake with head and tail. At first, SnakeTail = SnakeHead
2. Snake encounter a food, snake length + 1, go to next food
    insert a node into snake body when encounter a food, and board maxtrix need to be update, and food index point to next food
3. Snake collide with the border, dead
    border check in each move
4. Snake collide with itself, dead
    use a board matrix to store the position where snake exist
5. 
Test Case:
["SnakeGame","move","move","move","move","move","move","move","move","move","move","move","move","move","move","move"]
[[3,3,[[2,0],[0,0],[0,2],[0,1],[2,2],[0,1]]],["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["L"],["D"],["R"],["U"]]
*/
public class DesignSnakeGame {
    /**
     * @author: daohuei
     * @description: linked list node method
     * @time: O(1): just do a bunch of edge case checking
     * @space: O(w*h): for memorize the snake location
     */
    class SnakeGameListNode {
        int width;
        int height;
        int[][] board; // check whether the snake crash into itself, 1 means the snake is in that loc
        int[][] food;
        int foodIndex;
        ListNode snakeHead;
        ListNode snakeTail;

        /**
         * Initialize your data structure here.
         * 
         * @param width  - screen width
         * @param height - screen height
         * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
         *               first food is positioned at [1,1], the second is at [1,0].
         */
        public SnakeGameListNode(int width, int height, int[][] food) {
            // game screen's height and width
            this.width = width;
            this.height = height;
            // init the board for it: for checking whether the snake crashed to itself when
            // accessing this cell, 1 is the point snake at
            board = new int[height][width];
            // start point for the snake
            board[0][0] = 1;
            // the food location array
            this.food = food;
            foodIndex = 0;
            // init the snake head and tail
            snakeHead = new ListNode(0, 0);
            snakeTail = snakeHead;
        }

        /**
         * Moves the snake.
         * 
         * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         * @return The game's score after the move. Return -1 if game over. Game over
         *         when snake crosses the screen boundary or bites its body.
         */
        public int move(String direction) {
            ListNode newNode = null;
            // move up
            if (direction.equals("U")) {
                // if head reaching the boundary
                if (snakeHead.row == 0)
                    // game over
                    return -1;
                // make a new snake part
                newNode = new ListNode(snakeHead.row - 1, snakeHead.col);
            } else if (direction.equals("D")) {
                // if head reaching the boundary
                if (snakeHead.row == height - 1)
                    // game over
                    return -1;
                // make a new snake part
                newNode = new ListNode(snakeHead.row + 1, snakeHead.col);
            } else if (direction.equals("L")) {
                // if head reaching the boundary
                if (snakeHead.col == 0)
                    // game over
                    return -1;
                // make a new snake part
                newNode = new ListNode(snakeHead.row, snakeHead.col - 1);
            } else if (direction.equals("R")) {
                // if head reaching the boundary
                if (snakeHead.col == width - 1)
                    // game over
                    return -1;
                // make a new snake part
                newNode = new ListNode(snakeHead.row, snakeHead.col + 1);
            }
            // update SnakeHead
            newNode.next = snakeHead;
            snakeHead.prev = newNode;
            snakeHead = newNode;
            // check encounter food or not
            // if not getting all food yet and in the right location
            if (foodIndex < food.length && newNode.row == food[foodIndex][0] && newNode.col == food[foodIndex][1]) {
                // eat the food
                foodIndex++;
            } else {
                // if not get the food, need to remove tail
                // remove from board
                board[snakeTail.row][snakeTail.col] = 0;
                // update snake tail
                ListNode last = snakeTail.prev;
                last.next = null;
                snakeTail = last;
            }
            // check whether collide into snake itself or not
            if (board[snakeHead.row][snakeHead.col] == 1)
                return -1;
            // mark the head on the board
            board[snakeHead.row][snakeHead.col] = 1;
            // food index indicate current score
            return foodIndex;
        }

        // the snake is consisted of list nodes
        class ListNode {
            // location
            int row;
            int col;
            // next part and previous part
            ListNode next;
            ListNode prev;

            public ListNode(int row, int col) {
                this.row = row;
                this.col = col;
                next = null;
                prev = null;
            }
        }
    }

    /**
     * @author: daohuei
     * @description: deque method - you can peek, poll, and offer node to first and
     *               last
     * @time: O(1): just do a bunch of edge case checking
     * @space: O(w*h): for memorize the snake location in the hashset and body queue
     */
    class SnakeGame {

        // 2D position info is encoded to 1D and stored as two copies
        Set<Integer> set; // this copy is good for fast loop-up for eating body case
        Deque<Integer> body; // this copy is good for updating tail
        int score;
        int[][] food;
        int foodIndex;
        int width;
        int height;

        /**
         * Initialize your data structure here.
         * 
         * @param width  - screen width
         * @param height - screen height
         * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
         *               first food is positioned at [1,1], the second is at [1,0].
         */
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            set = new HashSet<>();
            set.add(0); // initially at [0][0]
            body = new LinkedList<>();
            body.offerLast(0);
        }

        /**
         * Moves the snake.
         * 
         * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         * @return The game's score after the move. Return -1 if game over. Game over
         *         when snake crosses the screen boundary or bites its body.
         */
        public int move(String direction) {
            // case 0: game already over: do nothing
            if (score == -1) {
                return -1;
            }

            // compute new head
            // index = row * width + col
            // get the head location
            int rowHead = body.peekFirst() / width;
            int colHead = body.peekFirst() % width;
            // move the head
            switch (direction) {
                case "U":
                    rowHead--;
                    break;
                case "D":
                    rowHead++;
                    break;
                case "L":
                    colHead--;
                    break;
                default:
                    colHead++;
            }
            // calculate new head location
            int head = rowHead * width + colHead;

            // case 1: out of boundary or eating body
            // remove the last location
            set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily
            // if out of boundary or collide into it own body part
            if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
                // game over
                return score = -1;
            }

            // add head for case2 and case3
            set.add(head);
            body.offerFirst(head);

            // case2: eating food, keep tail, add head
            if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
                set.add(body.peekLast()); // old tail does not change, so add it back to set
                // food index move forward and increase the score
                foodIndex++;
                return ++score;
            }

            // case3: normal move, remove tail, add head
            body.pollLast();
            return score;

        }
    }
}
