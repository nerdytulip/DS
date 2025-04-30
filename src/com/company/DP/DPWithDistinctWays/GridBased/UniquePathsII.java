package com.company.DP.DPWithDistinctWays.GridBased;

import java.util.Arrays;

public class UniquePathsII {
    private int solveRec(int i, int j, int[][] obstacleGrid, int[][] memo) {
        if(i >= obstacleGrid.length || j >= obstacleGrid[0].length){
            return 0;
        }

        if(obstacleGrid[i][j] == 1){
            return 0;
        }

        if(i == obstacleGrid.length-1 && j == obstacleGrid[0].length-1){
            return 1;
        }

        if(memo[i][j]!=-1){
            return memo[i][j];
        }

        // Otherwise, explore right and down
        int rightPaths = solveRec(i, j + 1, obstacleGrid, memo);
        int downPaths  = solveRec(i + 1, j, obstacleGrid, memo);
        // Cache & return the total
        memo[i][j] = rightPaths + downPaths;

        return memo[i][j];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // Use Integer so that null = “not computed yet”
        int[][] memo = new int[m][n];
        for(int[] row:memo){
            Arrays.fill(row,-1);
        }

        return solveRec(0, 0, obstacleGrid, memo);
    }


}
