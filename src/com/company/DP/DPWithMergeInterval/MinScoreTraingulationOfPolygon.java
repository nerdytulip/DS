package com.company.DP.DPWithMergeInterval;

import java.util.Arrays;

public class MinScoreTraingulationOfPolygon {

    //https://leetcode.com/problems/minimum-score-triangulation-of-polygon/description/

    int solveRec(int[] values,int i,int j){
        if(i+1 == j){
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for(int k = i+1;k<j;k++){
            ans = Math.min(ans,values[i]*values[j]*values[k] + solveRec(values,i,k) + solveRec(values,k,j));
        }

        return ans;
    }
    public int minScoreTriangulationRec(int[] values) {
        return solveRec(values,0,values.length -1);
    }

    int solveMem(int[] values,int i,int j, int[][] memo){
        if(i+1 == j){
            return 0;
        }

        if(memo[i][j]!=-1){
            return memo[i][j];
        }

        int ans = Integer.MAX_VALUE;
        for(int k = i+1;k<j;k++){
            ans = Math.min(ans,values[i]*values[j]*values[k] + solveMem(values,i,k,memo) + solveMem(values,k,j,memo));
        }

        return memo[i][j] = ans;
    }
    public int minScoreTriangulationMem(int[] values) {
        int[][] memo = new int[values.length][values.length];
        for(int[] row:memo){
            Arrays.fill(row,-1);
        }
        return solveMem(values,0,values.length -1,memo);
    }

    int solveTab(int[] values){
        int[][] memo = new int[values.length][values.length];
        for(int[] row:memo){
            Arrays.fill(row,0);
        }

        for(int i = values.length-1;i>=0;i--){
            for(int j = i+2;j<values.length;j++){
                int ans = Integer.MAX_VALUE;
                for(int k = i+1;k<j;k++){
                    ans = Math.min(ans,values[i]*values[j]*values[k] + memo[i][k] + memo[k][j]);
                }
                memo[i][j] = ans;
            }
        }

        return memo[0][values.length-1];
    }

    public int minScoreTriangulation(int[] values) {
        return solveTab(values);
    }
}
