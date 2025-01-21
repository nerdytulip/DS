package com.company.DP;

import java.util.Arrays;

public class BuyAndSellIII {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

    // part 3

    private int solveRec_III(int index,int buy,int[] prices,int limit){
        if(index == prices.length){
            return 0;
        }

        if(limit == 0){
            return 0;
        }

        int profit = 0;

        if(buy == 1){
            //buy
            int buyValue = -prices[index] + solveRec_III(index+1,0,prices,limit);
            int ignoreValue = 0 + solveRec_III(index+1,1,prices,limit);
            profit = Math.max(buyValue,ignoreValue);
        }else{
            //buy == 0
            int sellValue = prices[index] + solveRec_III(index+1,1,prices,limit-1);
            int ignoreValue = 0 + solveRec_III(index+1,0,prices,limit);
            profit = Math.max(sellValue,ignoreValue);
        }
        return profit;
    }
    public int maxProfitRec_III(int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy

        return  solveRec_III(0,1,prices,2);
    }

    private int solveMem_III(int index,int buy,int[] prices,int limit,int[][][] dp){
        if(index == prices.length){
            return 0;
        }

        if(limit == 0){
            return 0;
        }

        if(dp[index][buy][limit]!=-1){
            return dp[index][buy][limit];
        }

        int profit = 0;

        if(buy == 1){
            //buy
            int buyValue = -prices[index] + solveMem_III(index+1,0,prices,limit,dp);
            int ignoreValue = 0 + solveMem_III(index+1,1,prices,limit,dp);
            profit = Math.max(buyValue,ignoreValue);
        }else{
            //buy == 0
            int sellValue = prices[index] + solveMem_III(index+1,1,prices,limit-1,dp);
            int ignoreValue = 0 + solveMem_III(index+1,0,prices,limit,dp);
            profit = Math.max(sellValue,ignoreValue);
        }
        return dp[index][buy][limit] = profit;
    }
    public int maxProfitMem_III(int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy
        int[][][] dp = new int[prices.length][2][3];

        // Loop through all dimensions
//        for (int i = 0; i < prices.length; i++) {
//            for (int j = 0; j < 2; j++) {
//                Arrays.fill(dp[i][j], -1); // Fill the innermost 1D array
//            }
//        }

        Arrays.stream(dp)
                .forEach(arr2D -> Arrays.stream(arr2D)
                        .forEach(arr1D -> Arrays.fill(arr1D, -1)));

        return  solveMem_III(0,1,prices,2,dp);
    }

    private int solveTab_III(int[] prices){
        int[][][] dp = new int[prices.length+1][2][3];
        Arrays.stream(dp)
                .forEach(arr2D -> Arrays.stream(arr2D)
                        .forEach(arr1D -> Arrays.fill(arr1D, 0)));

        for(int index = prices.length - 1;index>=0;index--){
            for(int buy = 0;buy<=1;buy++){
                //because when limit is 0 , ans is 0 , already in DP array
                for(int limit = 1;limit<=2;limit++){
                    int profit = 0;
                    if(buy == 1){
                        //buy
                        int buyValue = -prices[index] + dp[index+1][0][limit];
                        int ignoreValue = 0 + dp[index+1][1][limit];
                        profit = Math.max(buyValue,ignoreValue);
                    }else{
                        //buy == 0
                        int sellValue = prices[index] + dp[index+1][1][limit-1];
                        int ignoreValue = 0 + dp[index+1][0][limit];
                        profit = Math.max(sellValue,ignoreValue);
                    }
                    dp[index][buy][limit] = profit;
                }
            }
        }

        return dp[0][1][2];
    }
    public int maxProfitTab_III(int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy

        return  solveTab_III(prices);
    }

    private int solveSO_III(int[] prices) {
        int[][] curr = new int[2][3];
        for (int[] row : curr) {
            Arrays.fill(row, 0);
        }


        int[][] next = new int[2][3];
        for (int[] row : next) {
            Arrays.fill(row, 0);
        }

        for (int index = prices.length - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int limit = 1; limit <= 2; limit++) {
                    int profit = 0;
                    if (buy == 1) {
                        //buy
                        int buyValue = -prices[index] + next[0][limit];
                        int ignoreValue = 0 + next[1][limit];
                        profit = Math.max(buyValue, ignoreValue);
                    } else {
                        //buy == 0
                        int sellValue = prices[index] + next[1][limit - 1];
                        int ignoreValue = 0 + next[0][limit];
                        profit = Math.max(sellValue, ignoreValue);
                    }
                    curr[buy][limit] = profit;
                }
            }

            int[][] temp = next;
            next = curr;
            curr = temp;
        }

        return next[1][2];
    }

    public int maxProfit(int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy

        return solveSO_III(prices);
    }
}
