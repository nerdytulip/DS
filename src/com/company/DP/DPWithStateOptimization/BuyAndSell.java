package com.company.DP.DPWithStateOptimization;

public class BuyAndSell {

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    // part 1

    public int maxProfitEasy(int[] prices) {
        int mini = prices[0];
        int profit = 0;
        for(int i=1;i<prices.length;i++){
            int diff = prices[i] - mini;
            profit = Math.max(mini,diff);
            mini = Math.min(mini,prices[i]);
        }
        return profit;
    }

}
