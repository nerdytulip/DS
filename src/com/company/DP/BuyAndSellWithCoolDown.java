package com.company.DP;

import java.util.Arrays;

public class BuyAndSellWithCoolDown {
    private int solveRec(int index, int buy, int[] prices) {
        // Base case: No more days left
        if (index >= prices.length) {
            return 0;
        }

        int profit = 0;

        if (buy == 1) { // Buy allowed
            // Option 1: Buy the stock and move to sell state
            int buyValue = -prices[index] + solveRec(index + 1, 0, prices);
            // Option 2: Skip buying
            int ignoreValue = 0 + solveRec(index + 1, 1, prices);
            profit = Math.max(buyValue, ignoreValue);
        } else { // Sell
            // Option 1: Sell the stock and move to cooldown (index + 2)
            int sellValue = prices[index] + solveRec(index + 2, 1, prices);
            // Option 2: Skip selling
            int ignoreValue = 0 + solveRec(index + 1, 0, prices);
            profit = Math.max(sellValue, ignoreValue);
        }

        return profit;
    }

    public int maxProfitRec(int[] prices) {
        return solveRec(0, 1, prices); // Start at day 0 and allowed to buy
    }

    private int solveMem(int index, int buy, int[] prices, int[][] dp) {
        // Base case: No more days left
        if (index >= prices.length) {
            return 0;
        }

        if(dp[index][buy]!=-1){
            return dp[index][buy];
        }

        int profit = 0;

        if (buy == 1) { // Buy allowed
            // Option 1: Buy the stock and move to sell state
            int buyValue = -prices[index] + solveRec(index + 1, 0, prices);
            // Option 2: Skip buying
            int ignoreValue = 0 + solveMem(index + 1, 1, prices,dp);
            profit = Math.max(buyValue, ignoreValue);
        } else { // Sell
            // Option 1: Sell the stock and move to cooldown (index + 2)
            int sellValue = prices[index] + solveRec(index + 2, 1, prices);
            // Option 2: Skip selling
            int ignoreValue = 0 + solveMem(index + 1, 0, prices,dp);
            profit = Math.max(sellValue, ignoreValue);
        }

        return dp[index][buy] = profit;
    }

    public int maxProfitMem(int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy
        int[][] dp = new int[prices.length][2];
        for(int[] rows: dp){
            Arrays.fill(rows,-1);
        }
        return  solveMem(0,1,prices,dp);
    }

    private int solveTab(int[] prices){
        int[][] dp = new int[prices.length+2][2];
        for(int[] rows: dp){
            Arrays.fill(rows,0);
        }

        for(int index = prices.length - 1;index>=0;index--){
            for(int buy = 0;buy<=1;buy++){
                int profit = 0;
                if(buy == 1){
                    //buy
                    int buyValue = -prices[index] + dp[index+1][0];
                    int ignoreValue = 0 + dp[index+1][1];
                    profit = Math.max(buyValue,ignoreValue);
                }else{
                    //buy == 0
                    int sellValue = prices[index] + dp[index+2][1];
                    int ignoreValue = 0 + dp[index+1][0];
                    profit = Math.max(sellValue,ignoreValue);
                }
                dp[index][buy] = profit;
            }
        }

        return dp[0][1];
    }

    public int maxProfitTab(int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy

        return  solveTab(prices);
    }


    private int solveSO(int[] prices){
        int[] curr = new int[2]; // index
        Arrays.fill(curr,0);

        int[] next = new int[2]; // index+1
        Arrays.fill(next,0);

        int[] cooldown = new int[2]; // index+2
        Arrays.fill(next,0);

        for(int index = prices.length - 1;index>=0;index--){
            for(int buy = 0;buy<=1;buy++){
                int profit = 0;
                if(buy == 1){
                    //buy
                    int buyValue = -prices[index] + next[0];
                    int ignoreValue = 0 + next[1];
                    profit = Math.max(buyValue,ignoreValue);
                }else{
                    //buy == 0
                    int sellValue = prices[index] + cooldown[1];
                    int ignoreValue = 0 + next[0];
                    profit = Math.max(sellValue,ignoreValue);
                }
                curr[buy] = profit;
            }

            int[] temp = cooldown;
            cooldown = next;
            next = curr;
            curr = temp;
        }

        return next[1];
    }

    public int maxProfit(int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy

        return  solveSO(prices);
    }
}
