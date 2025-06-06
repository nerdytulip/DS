package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;

public class CoinChangeI_MinimumCoins {

    /**
     * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
     * */

    int solveRec(int[] coins,int target){
        //base case
        if(target == 0){
            return 0;
        }
        if(target<0){
            return Integer.MAX_VALUE;
        }

        int mini = Integer.MAX_VALUE;
        for(int coin:coins){
            int ans = solveRec(coins,target-coin);
            if(ans!=Integer.MAX_VALUE){
                mini = Math.min(mini,1+ans);
            }
        }

        return mini;
    }

    public int minimumAddedCoins(int[] coins, int target) {
        int ans = solveRec(coins,target);
        if(ans == Integer.MAX_VALUE){
            return -1;
        }else{
            return ans;
        }
    }

    private int solveMem(int[] coins, int target, int[] memo) {

        //base case
        if (target == 0) {
            return 0;
        }
        if (target < 0) {
            return Integer.MAX_VALUE;
        }

        if(memo[target] == -1){
            return memo[target];
        }

        int mini = Integer.MAX_VALUE;
        for (int coin:coins) {
            int ans = solveMem(coins, target - coin, memo);;
            if (ans != Integer.MAX_VALUE) {
                mini = Math.min(mini, 1 + ans);
            }
        }
        memo[target] = mini;

        return mini;
    }

    public int minimumAddedCoins_top_down(int[] coins, int target) {
        int[] memo = new int[target+1];
        int ans = solveMem(coins,target,memo);
        Arrays.fill(memo,-1);
        if(ans == Integer.MAX_VALUE){
            return -1;
        }else{
            return ans;
        }
    }

    public int minimumAddedCoins_bottom_up(int[] coins, int target) {
        int[] memo = new int[target+1];
        Arrays.fill(memo,Integer.MAX_VALUE);
        memo[0] = 0;

        for(int i=1;i<=target;i++){
            //i am trying to solve for every amount figure from 1 to x
            for(int coin:coins){
                //the first condition checks if i-coin is a valid index
                //second condition is to check if memo[i-coin] is iNT_MAX ,
                // if it is and we add 1 to it - it will cause integer overflow
                if(i-coin>=0 && memo[i-coin]!=Integer.MAX_VALUE) {
                    memo[i] = Math.min(memo[i], 1 + memo[i - coin]);
                }
            }
        }
        if(memo[target] == Integer.MAX_VALUE){
            return -1;
        }else{
            return memo[target];
        }
    }

    public static void main(String[] args){
        CoinChangeI_MinimumCoins coinChangeIMinimumCoins = new CoinChangeI_MinimumCoins();
        int[] coins = {1,4,10};
        System.out.println(coinChangeIMinimumCoins.minimumAddedCoins_bottom_up(coins,19));
    }
}
