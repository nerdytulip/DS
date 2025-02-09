package com.company.Greedy;

import java.util.Arrays;

public class MinimumCostOfBuyingWithDiscount {

    //https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/description/
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int minCost = 0;
        //buy last two so that we have higher min and skip third because we get it from and repeat
        int n = cost.length;

        for(int i=n-1;i>=0;i-=3){
            minCost+=cost[i];
            if(i-1>=0){
                minCost+=cost[i-1];
            }
            //skip third
        }

        return minCost;
    }
}
