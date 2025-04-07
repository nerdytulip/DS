package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;
import java.util.List;

public class MaximumValueOfKCoinsFromPiles {

    private int solveRec(List<List<Integer>> piles,int i, int k){
        if(i == piles.size() || k == 0){
            return 0;
        }

        //option 1 : exclude this pile
        int maxValue = solveRec(piles,i+1,k);

        //option 2 : include this pile
        int currSum = 0;
        for(int j = 0;j<Math.min(k,piles.get(i).size());j++){
            currSum+=piles.get(i).get(j);
            maxValue = Math.max(maxValue,currSum + solveRec(piles,i+1,k- (j+1)));
        }

        return maxValue;
    }

    public int maxValueOfCoinsRec(List<List<Integer>> piles, int k) {
        return solveRec(piles,0,k);
    }

    private int solveMem(List<List<Integer>> piles,int i, int k, int[][] dp){
        if(i == piles.size() || k == 0){
            return 0;
        }

        if(dp[i][k]!=-1){
            return dp[i][k];
        }

        //option 1 : exclude this pile
        int maxValue = solveMem(piles,i+1,k,dp);

        //option 2 : include this pile
        int currSum = 0;
        for(int j = 0;j<Math.min(k,piles.get(i).size());j++){
            currSum+=piles.get(i).get(j);
            maxValue = Math.max(maxValue,currSum + solveMem(piles,i+1,k- (j+1),dp));
        }

        return dp[i][k] = maxValue;
    }

    public int maxValueOfCoinsMem(List<List<Integer>> piles, int k) {
        int[][] dp = new int[piles.size()][k+1];
        for(int[] rows : dp){
            Arrays.fill(rows,-1);
        }
        return solveMem(piles,0,k,dp);
    }

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[][] dp = new int[piles.size() + 1][k + 1];
        for (int[] rows : dp) {
            Arrays.fill(rows, 0);
        }

        // so the case for when row == piles.size() and column == k is already 0

        for (int i = piles.size() - 1; i >= 0; i--) {
            for (int coin = 0; coin <= k; coin++) {
                //option 1 :exclude
                int maxValue = dp[i + 1][coin];

                //option 2:include
                int currSum = 0;
                for (int j = 0; j < Math.min(k, piles.get(i).size()); j++) {
                    {
                        currSum += piles.get(i).get(j);
                        if ((coin - (j + 1)) >= 0) {
                            maxValue = Math.max(maxValue, currSum + dp[i + 1][coin - (j + 1)]);
                        }
                    }

                }

                dp[i][coin] = maxValue;
            }
        }
        return dp[0][k];
    }
}
