package com.company.DP.DPWithStateOptimization;

import java.util.Arrays;

public class BuyAndSellIV {

    /**
     * Approach 1 : Based on optimized solution from part III
     */
    private int solveSO(int k, int[] prices) {
        int[][] curr = new int[2][k + 1];
        for (int[] row : curr) {
            Arrays.fill(row, 0);
        }


        int[][] next = new int[2][k + 1];
        for (int[] row : next) {
            Arrays.fill(row, 0);
        }

        for (int index = prices.length - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int limit = 1; limit <= k; limit++) {
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

        return next[1][k];
    }

    public int maxProfitSO(int k, int[] prices) {

        // buy = 0 , not allowed to buy
        // buy = 1, allowed to buy

        return solveSO(k, prices);
    }

    /**
     * Approach 2 : transaction number/operation number based
     * <p>
     * operationNo == 2 * k , because each transaction can have two operations
     */
    private int solveOperationRec(int index, int operationNo, int k, int[] prices) {
        if (index == prices.length) {
            return 0;
        }

        if (operationNo == 2 * k) {
            return 0;
        }

        int profit = 0;
        if (operationNo % 2 == 0) {
            //buy
            int buyValue = -prices[index] + solveOperationRec(index + 1, operationNo + 1, k, prices);
            int ignoreValue = 0 + solveOperationRec(index + 1, operationNo, k, prices);
            profit = Math.max(buyValue, ignoreValue);
        } else {
            //sell
            int sellValue = prices[index] + solveOperationRec(index + 1, operationNo + 1, k, prices);
            int ignoreValue = 0 + solveOperationRec(index + 1, operationNo, k, prices);
            profit = Math.max(sellValue, ignoreValue);
        }

        return profit;
    }

    public int maxProfitRec(int k, int[] prices) {

        return solveOperationRec(0, 0, k, prices);
    }

    private int solveOperationMem(int index, int operationNo, int k, int[] prices, int[][] dp) {
        if (index == prices.length) {
            return 0;
        }

        if (operationNo == 2 * k) {
            return 0;
        }

        if (dp[index][operationNo] != -1) {
            return dp[index][operationNo];
        }

        int profit = 0;
        if (operationNo % 2 == 0) {
            //buy
            int buyValue = -prices[index] + solveOperationMem(index + 1, operationNo + 1, k, prices, dp);
            int ignoreValue = 0 + solveOperationMem(index + 1, operationNo, k, prices, dp);
            profit = Math.max(buyValue, ignoreValue);
        } else {
            //sell
            int sellValue = prices[index] + solveOperationMem(index + 1, operationNo + 1, k, prices, dp);
            int ignoreValue = 0 + solveOperationMem(index + 1, operationNo, k, prices, dp);
            profit = Math.max(sellValue, ignoreValue);
        }

        return dp[index][operationNo] = profit;
    }

    public int maxProfitMem(int k, int[] prices) {

        int[][] dp = new int[prices.length][2 * k];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveOperationMem(0, 0, k, prices, dp);
    }

    private int solveOperationTab( int k, int[] prices) {
        int[][] dp = new int[prices.length+1][2*k+1];
        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }

        for(int index = prices.length-1;index>=0;index--){
            for(int operationNo=0;operationNo<2*k;operationNo++){
                int profit = 0;
                if (operationNo % 2 == 0) {
                    //buy
                    int buyValue = -prices[index] + dp[index + 1][operationNo+1];
                    int ignoreValue = 0 + dp[index + 1][operationNo];
                    profit = Math.max(buyValue, ignoreValue);
                } else {
                    //sell
                    int sellValue = prices[index] + dp[index + 1][operationNo + 1];
                    int ignoreValue = 0 + dp[index + 1][operationNo];
                    profit = Math.max(sellValue, ignoreValue);
                }

                dp[index][operationNo] = profit;
            }
        }

        return dp[0][0];
    }

    public int maxProfitTab(int k, int[] prices) {

        return solveOperationTab(k, prices);
    }

    private int solveOperationSO( int k, int[] prices) {
        int[] curr = new int[2*k+1];
        Arrays.fill(curr, 0);

        int[] next = new int[2*k+1];
        Arrays.fill(next, 0);

        for(int index = prices.length-1;index>=0;index--){
            for(int operationNo=0;operationNo<2*k;operationNo++){
                int profit = 0;
                if (operationNo % 2 == 0) {
                    //buy
                    int buyValue = -prices[index] + next[operationNo+1];
                    int ignoreValue = 0 + next[operationNo];
                    profit = Math.max(buyValue, ignoreValue);
                } else {
                    //sell
                    int sellValue = prices[index] + next[operationNo + 1];
                    int ignoreValue = 0 + next[operationNo];
                    profit = Math.max(sellValue, ignoreValue);
                }

                curr[operationNo] = profit;
            }
            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[0];
    }

    public int maxProfit(int k, int[] prices) {

        return solveOperationSO(k, prices);
    }

}

