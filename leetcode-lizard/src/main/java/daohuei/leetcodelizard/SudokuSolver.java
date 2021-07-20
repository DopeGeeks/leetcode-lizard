package daohuei.leetcodelizard;

/*
 * 37. Sudoku Solver
 * Link: https://leetcode.com/problems/sudoku-solver/description/
 */
public class SudokuSolver {
    /**
     * @author: daohuei
     * @description: backtracking method
     * @time: O(n^2): but actually go through every possibilities before finding the
     *        solution
     * @space: O(1): not using any
     */
    public void solveSudoku(char[][] board) {
        solver(board);
    }

    private boolean solver(char[][] board) {
        // go through every positions
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // if empty cell
                if (board[i][j] == '.') {
                    // init the number with string 1
                    char count = '1';
                    // from 1 to 9
                    while (count <= '9') {
                        // if valid for it
                        if (isValid(i, j, board, count)) {
                            // assign to it
                            board[i][j] = count;
                            // if the board is totally solved
                            if (solver(board)) {
                                return true;
                            } else {
                                // if not solved, check next number
                                board[i][j] = '.';
                            }
                        }
                        // next number
                        count++;
                    }
                    // all number checked and still not solved
                    // means current place is not solvable yet
                    // return false
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char[][] board, char c) {
        // check the row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
        }

        // check the col
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }

        int start_row = row / 3 * 3;
        int start_col = col / 3 * 3;
        // check the current 3*3 block
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[start_row + i][start_col + j] == c) {
                    return false;
                }
            }

        }
        return true;
    }
}