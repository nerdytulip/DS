package com.company.DP.DPWithDistinctWays;

import java.util.Arrays;

public class UniquePaths {

    /**
     * https://leetcode.com/problems/unique-paths/description/?envType=study-plan-v2&envId=leetcode-75
     * */
    // Helper method that computes paths from cell (i, j) to destination (m-1, n-1)
    private int solveRec(int i, int j, int m, int n) {
        // If we've reached the bottom-right corner, there's one valid path.
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        int paths = 0;

        // Move down if within bounds.
        if (i < m - 1) {
            paths += solveRec(i + 1, j, m, n);
        }

        // Move right if within bounds.
        if (j < n - 1) {
            paths += solveRec(i, j + 1, m, n);
        }

        return paths;
    }

    // Main method that initializes the recursion from (0, 0)
    public int uniquePathsRec(int m, int n) {
        return solveRec(0, 0, m, n);
    }

    private int solveMem(int i, int j, int m, int n, int[][] memo) {
        // If we've reached the bottom-right corner, there's one valid path.
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if(memo[i][j]!=-1){
            return memo[i][j];
        }

        int paths = 0;

        // Move down if within bounds.
        if (i < m - 1) {
            paths += solveMem(i + 1, j, m, n,memo);
        }

        // Move right if within bounds.
        if (j < n - 1) {
            paths += solveMem(i, j + 1, m, n,memo);
        }

        return memo[i][j] = paths;
    }

    // Main method that initializes the recursion from (0, 0)
    public int uniquePathsMem(int m, int n) {
        int[][] memo = new int[m][n];
        for(int[] rows : memo){
            Arrays.fill(rows,-1);
        }
        return solveMem(0, 0, m, n,memo);
    }

    //BU
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];

        for(int i=0;i<m;i++){
            memo[i][n-1] = 1;
        }

        for(int j=0;j<n;j++){
            memo[m-1][j] = 1;
        }

        for(int i = m-2;i>=0;i--){
            for(int j = n-2;j>=0;j--){
                memo[i][j] = memo[i + 1][j] + memo[i][j + 1];
            }
        }

        return memo[0][0];
    }
    
}
