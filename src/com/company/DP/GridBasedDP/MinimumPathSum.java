package com.company.DP.GridBasedDP;

import java.util.Arrays;

public class MinimumPathSum {
    private int solveRec(int[][] grid, int i, int j) {
        if(i == grid.length || j == grid[0].length){
            return Integer.MAX_VALUE;
        }
        if(i == grid.length-1 && j== grid[0].length-1){
            return grid[i][j];
        }

        return (grid[i][j] + Math.min(solveRec(grid,i+1,j),solveRec(grid,i,j+1)));
    }

    public int minPathSumRec(int[][] grid) {
        return solveRec(grid,0,0);
    }

    private int solveMem(int[][] grid, int i, int j, int[][] memo) {
        if(i >= grid.length || j >= grid[0].length){
            return Integer.MAX_VALUE;
        }
        if(i == grid.length-1 && j== grid[0].length-1){
            return grid[i][j];
        }

        if(memo[i][j] != -1){
            return memo[i][j];
        }

        int right = solveMem(grid, i, j + 1, memo);
        int down = solveMem(grid, i + 1, j, memo);

        memo[i][j] = grid[i][j] + Math.min(down, right);

        return memo[i][j];
    }

    public int minPathSumMem(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        for (int[] rows : memo) {
            Arrays.fill(rows, -1);
        }
        return solveMem(grid,0,0,memo);
    }

    private int solveTab(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int INF = Integer.MAX_VALUE;
        int[][] dp = new int[m][n];

        // iterate from bottom-right → top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // destination cell
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = grid[i][j];
                } else {
                    int downCost  = (i + 1 < m) ? dp[i + 1][j] : INF;
                    int rightCost = (j + 1 < n) ? dp[i][j + 1] : INF;
                    dp[i][j] = grid[i][j] + Math.min(downCost, rightCost);
                }
            }
        }
        return dp[0][0];
    }

    public int minPathSumTab(int[][] grid) {
        return solveTab(grid);
    }

    private int solveSO(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int INF = Integer.MAX_VALUE;
        int[] curr = new int[n];
        int[] next = new int[n];

        // iterate from bottom-right → top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // destination cell
                if (i == m - 1 && j == n - 1) {
                    curr[j] = grid[i][j];
                } else {
                    int downCost  = (i + 1 < m) ? next[j] : INF;
                    int rightCost = (j + 1 < n) ? curr[j + 1] : INF;
                    curr[j] = grid[i][j] + Math.min(downCost, rightCost);
                }
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }
        return next[0];
    }

    public int minPathSumSO(int[][] grid) {
        return solveSO(grid);
    }

    //using no extra space
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int INF = Integer.MAX_VALUE;

        // bottom-right → top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // skip the destination cell
                if (i == m - 1 && j == n - 1) continue;

                int down  = (i + 1 < m) ? grid[i + 1][j] : INF;
                int right = (j + 1 < n) ? grid[i][j + 1] : INF;

                grid[i][j] += Math.min(down, right);
            }
        }

        // after the loops, grid[0][0] holds the answer
        return grid[0][0];
    }

}
