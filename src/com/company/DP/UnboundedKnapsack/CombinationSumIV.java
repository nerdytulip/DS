package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;

public class CombinationSumIV {

    /**
     * https://leetcode.com/problems/combination-sum-iv/description/
     * */
    int solveRec(int[] num,int target){
        if(target<0){
            return 0;
        }

        if(target==0){
            return 1;
        }

        int ans = 0;
        for(int i=0;i<num.length;i++){
            ans+=solveRec(num,target-num[i]);
        }

        return ans;
    }

    //top-down
    int solveMem(int[] num,int target,int[] memo){
        if(target<0){
            return 0;
        }

        if(target==0){
            return 1;
        }

        if(memo[target]!=-1){
            return memo[target];
        }

        int ans = 0;
        for(int i=0;i<num.length;i++){
            ans+=solveMem(num,target-num[i],memo);
        }

        memo[target] = ans;
        return memo[target];
    }

    public int combinationSum4_Mem(int[] nums, int target) {
        int[] memo = new int[target+1];
        Arrays.fill(memo,-1);
        return solveMem(nums,target,memo);
    }

    //top-down
    public int combinationSum4_tabulation(int[] nums, int target) {
        int[] memo = new int[target+1];
        Arrays.fill(memo,0);
        memo[0] = 1;
        for(int i=1;i<=target;i++){
            for(int n:nums){
                if(i-n>=0) {
                    memo[i] += memo[i - n];
                }
            }
        }

        return memo[target];
    }
}
