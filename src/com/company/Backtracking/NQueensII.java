package com.company.Backtracking;

import java.util.Arrays;

public class NQueensII {
    /**
     * https://leetcode.com/problems/n-queens-ii/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=nalYyLZgvCY
     *
     * 🧩 1. N-Queens (LeetCode #51)
     * Goal: Place N queens on an N x N board so that no two queens attack each other.
     *
     * Output: All valid board configurations.
     *
     * Each solution is a list of strings representing queen placements ('Q' and '.').
     *
     * ✅ You return the actual boards.
     *
     * 🧮 2. N-Queens II (LeetCode #52)
     * Goal: Same — place N queens safely.
     *
     * Output: Only the total number of distinct solutions (no need to return the actual boards).
     *
     * ✅ You return just the count.
     * */
    private int count = 0;

    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        backtrack(0, board);
        return count;
    }

    private void backtrack(int row, char[][] board) {
        if (row == board.length) {
            count++;
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';
                backtrack(row + 1, board);
                board[row][col] = '.'; // backtrack
            }
        }
    }

    private boolean isSafe(int row, int col, char[][] board) {
        int n = board.length;

        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q') return false;

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 'Q') return false;

        return true;
    }
}
