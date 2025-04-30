package com.company.DP.DPWithMergeInterval;

import java.util.Arrays;

public class GuessNumberHigherOrLower {

    /**
     * https://leetcode.com/problems/guess-number-higher-or-lower-ii/description/
     * */

    private int solveRec(int start,int end){
        if(start>=end){
            return 0;
        }

        int maxi = Integer.MAX_VALUE;
        for(int i = start;i<=end;i++){
            maxi = Math.min(maxi,i+ Math.max(solveRec(start,i-1),solveRec(i+1,end)));
        }

        return maxi;
    }
    public int getMoneyAmountRec(int n) {
        return solveRec(1,n);
    }

    private int solveMem(int start,int end,int[][] dp){
        if(start>=end){
            return 0;
        }
        if(dp[start][end]!=-1){
            return dp[start][end];
        }

        int maxi = Integer.MAX_VALUE;
        for(int i = start;i<=end;i++){
            maxi = Math.min(maxi,i+ Math.max(solveMem(start,i-1,dp),solveMem(i+1,end,dp)));
        }

        return dp[start][end] = maxi;
    }
    public int getMoneyAmountMem(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int[] rows:dp){
            Arrays.fill(rows,-1);
        }
        return solveMem(1,n,dp);
    }

    private int solveTab(int n){
        int[][] dp = new int[n+2][n+2];
        for(int[] rows:dp){
            Arrays.fill(rows,0);
        }

        for(int start = n;start>=1;start--){
            for(int end = start; end<=n;end++){
                if(start == end){
                    continue;
                }else{
                    int maxi = Integer.MAX_VALUE;

                    for(int i = start;i<=end;i++){
                        maxi = Math.min(maxi,i+ Math.max(dp[start][i-1],dp[i+1][end]));
                    }
                    dp[start][end] = maxi;
                }
            }
        }

        return dp[1][n];
    }

    public int getMoneyAmount(int n) {

        return solveTab(n);
    }
}
