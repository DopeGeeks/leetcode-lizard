package daohuei.leetcodelizard;

/*
 * 1041. Robot Bounded In Circle
 * Link: https://leetcode.com/problems/robot-bounded-in-circle
 */
public class RobotBoundedInCircle {
    /**
     * @author: daohuei
     * @description: simply iterations
     * @time: O(n): n = instructions.length
     * @space: O(1): not really using any
     */
    public boolean isRobotBounded(String instructions) {
        if (instructions.length() == 0)
            return false;
        // coordinates for the robot
        int x = 0;
        int y = 0;
        // 0: right, 1: up, 2: left, 3: down
        int direction = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char instruction = instructions.charAt(i);
            if (instruction == 'G') {
                if (direction == 0)
                    x++;
                if (direction == 1)
                    y++;
                if (direction == 2)
                    x--;
                if (direction == 3)
                    y--;
            } else if (instruction == 'L') {
                direction = (direction + 1) % 4;
            } else {
                // for preventing the negative number
                direction = (direction + 4 - 1) % 4;
            }
        }
        // if the direction of the final state is not the initial state
        // with further instructions, it will finally get back to the original point
        return (x == 0 && y == 0) ? true : (direction != 0);
    }
}