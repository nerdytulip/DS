package com.company.DP;

import java.util.Arrays;

public class MinCostClimbingStairs {

    //recursion
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int ans = Math.min(solve(cost,n-1),solve(cost,n-2));
        return ans;
    }

    //recursive solution
    private int solve(int[] cost, int n) {
        if(n==0){
            return cost[0];
        }
        if(n==1){
            return cost[1];
        }
        int ans = cost[n] + Math.min(solve(cost,n-1),solve(cost,n-2));
        return ans;
    }

    public int minCostClimbingStairs_top_down(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);
        int ans = Math.min(solve(cost,n-1,memo),solve(cost,n-2,memo));
        return ans;
    }

    private int solve(int[] cost, int n, int[] memo) {
        if(n==0){
            return cost[0];
        }
        if(n==1){
            return cost[1];
        }
        if(memo[n]!=-1){
            return memo[n];
        }
        memo[n] = cost[n] + Math.min(solve(cost,n-1,memo),solve(cost,n-2,memo));
        return memo[n];
    }

    //bottom up

    public int minCostClimbingStairs_bottom_up(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n+1];
        memo[0] = cost[0];
        memo[1] = cost[1];

        for(int i=2;i<n;i++){
            memo[i] = cost[i]+ Math.min(memo[i-1],memo[i-2]);
        }

        return Math.min(memo[n-1],memo[n-2]);
    }

    public static void main(String[] args){
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        int[] costs = {10,15,20};
        minCostClimbingStairs.minCostClimbingStairs_top_down(costs);
    }

}
