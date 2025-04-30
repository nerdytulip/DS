package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;

public class CoinChangeII {

    /**
     * https://leetcode.com/problems/coin-change-ii/description/?envType=study-plan-v2&envId=dynamic-programming
     * */

    int solveRec(int[] coins,int target, int start){
        //base case
        if(target == 0){
            return 1;
        }
        if(target<0){
            return 0;
        }

        int totalWays = 0;
        for(int i = start;i<coins.length;i++){
            int ans = solveRec(coins,target-coins[i],i);
            totalWays += ans;
        }

        return totalWays;
    }

    public int changeRec(int amount, int[] coins){
        return solveRec(coins,amount,0);
    }

    private int solveMem(int[] coins, int target, int start, int[][] memo) {

        //base case
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }

        if(memo[start][target] != -1){
            return memo[start][target];
        }

        int totalWays = 0;
        for(int i = start;i<coins.length;i++){
            int ans = solveMem(coins,target-coins[i],i,memo);
            totalWays += ans;
        }

        return memo[start][target] = totalWays;
    }

    public int changeMem(int amount, int[] coins) {
        int[][] memo = new int[coins.length][amount+1];
        for(int[] rows : memo){
            Arrays.fill(rows,-1);
        }

        int ans = solveMem(coins,amount,0,memo);
        return ans;
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j] = number of ways to make amount j using first i coins.
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: there's exactly 1 way to form amount 0 (choose nothing)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Build the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    // Ways without the current coin + ways including the current coin.
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    // If the current coin is too large, just use the result from above.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][amount];
    }
}
