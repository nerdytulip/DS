package com.company.DP;

import java.util.Arrays;

public class BuyAndSellV {
    private int solveSO(int[] prices,int fee){
        int[] curr = new int[2];
        Arrays.fill(curr,0);

        int[] next = new int[2];
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
                    int sellValue = prices[index] + next[1] - fee;
                    int ignoreValue = 0 + next[0];
                    profit = Math.max(sellValue,ignoreValue);
                }
                curr[buy] = profit;
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[1];
    }

    public int maxProfit(int[] prices, int fee) {
        return  solveSO(prices,fee);
    }
}
