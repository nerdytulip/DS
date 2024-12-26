package com.company.DP;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    private boolean solveRec(int i, int[] nums, int target) {
        if(i >= nums.length){
            return false;
        }
        if(target <0){
            return false;
        }
        if(target == 0 ){
            return true;
        }

        boolean incl = solveRec(i+1,nums,target - nums[i]);
        boolean excl = solveRec(i+1,nums,target-0);

        return incl || excl;
    }

    public boolean canPartitionRec(int[] nums) {
        int total = 0;
        for(int num:nums){
            total+=num;
        }

        if(total%2 != 0){
            return false;
        }else{
            return solveRec(0,nums,total/2);
        }
    }

    private boolean solveMem(int i, int[] nums, int target,int[][] memo) {
        if(i >= nums.length){
            return false;
        }
        if(target <0){
            return false;
        }
        if(target == 0 ){
            return true;
        }

        if(memo[i][target]!=-1){
           return memo[i][target] == 1;
        }

        boolean incl = solveMem(i+1,nums,target - nums[i],memo);
        boolean excl = solveMem(i+1,nums,target-0,memo);

        memo[i][target] = incl || excl? 1 : 0;

        return memo[i][target] == 1;
    }

    public boolean canPartitionMem(int[] nums) {
        int total = 0;
        for(int num:nums){
            total+=num;
        }

        if(total%2 != 0){
            return false;
        }else{
            int target = total / 2;
            int[][] memo = new int[nums.length+1][target+1];
            for(int[] row : memo){
                Arrays.fill(row,-1);
            }
            return solveMem(0,nums, target,memo);
        }
    }

    private boolean solveTab(int[] nums, int targetSum) {

        int[][] memo = new int[nums.length+1][(targetSum)+1];
        for(int[] row : memo){
            Arrays.fill(row,0);
        }

        for(int i=0;i<=nums.length;i++){
            memo[i][0] = 1;
        }

        for(int index = nums.length-1;index >=0;index--){
            for(int target =0;target<=targetSum;target++){

                boolean incl = false;
                if(target - nums[index]>=0) {
                    incl = memo[index + 1][target - nums[index]] == 1;
                }
                boolean excl = memo[index+1][target-0] == 1;

                memo[index][target] = incl || excl? 1 : 0;
            }
        }

        return memo[0][targetSum] == 1;
    }

    public boolean canPartitionTab(int[] nums) {
        int total = 0;
        for(int num:nums){
            total+=num;
        }

        if(total%2 != 0){
            return false;
        }else{
            int target = total / 2;
            return solveTab(nums, target);
        }
    }

    private boolean solveSO(int[] nums, int targetSum) {

        int[] next = new int[targetSum+1];
        Arrays.fill(next,0);

        int[] current = new int[targetSum+1];
        Arrays.fill(current,0);

        next[0] = 1;
        current[0] = 1;

        for(int index = nums.length-1;index >=0;index--){
            Arrays.fill(current,0);

            for(int target =0;target<=targetSum;target++){

                boolean incl = false;
                if(target - nums[index]>=0) {
                    incl = next[target - nums[index]] == 1;
                }
                boolean excl = next[target-0] == 1;

                current[target] = incl || excl? 1 : 0;
            }

            int[] temp = next;
            next = current;
            current = temp;
        }

        return next[targetSum] == 1;
    }

    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int num:nums){
            total+=num;
        }

        if(total%2 != 0){
            return false;
        }else{
            int target = total / 2;
            return solveSO(nums, target);
        }
    }

}
