package com.company.DP.DPWithDistinctWays;

import java.util.Arrays;

public class DiceRollWithTargetSum {
    private int solveRec(int dice, int faces, int target) {
        if (target < 0) {
            return 0;
        }
        if (dice == 0 && target != 0) {
            return 0;
        }
        if (dice != 0 && target == 0) {
            return 0;
        }
        if (dice == 0 && target == 0) {
            return 1;
        }

        int ans = 0;
        for (int i = 1; i <= faces; i++) {
            ans = ans + solveRec(dice - 1, faces, target - i);
        }

        return ans;
    }

    public int numRollsToTargetRec(int n, int k, int target) {
        return solveRec(n,k,target);
    }

    private int solveMem(int dice, int faces, int target, int[][] memo) {
        if (target < 0) {
            return 0;
        }
        if (dice == 0 && target != 0) {
            return 0;
        }
        if (dice != 0 && target == 0) {
            return 0;
        }
        if (dice == 0 && target == 0) {
            return 1;
        }

        if(memo[dice][target]!=-1){
            return memo[dice][target];
        }

        int ans = 0;
        for (int i = 1; i <= faces; i++) {
            ans = ans + solveRec(dice - 1, faces, target - i);
        }

        return memo[dice][target] = ans;
    }

    public int numRollsToTargetMem(int n, int k, int target) {
        int[][] memo = new int[n+1][target+1];
        for(int[] row : memo){
            Arrays.fill(row,-1);
        }
        return solveMem(n,k,target,memo);
    }

    private int solveTab(int dices, int faces, int target) {
        int[][] memo = new int[dices+1][target+1];
        for(int[] row : memo){
            Arrays.fill(row,0);
        }
        //analysing base case
        memo[0][0]=1;

        for(int dice = 1;dice<=dices;dice++){
            for(int t = 1;t<=target;t++){
                int ans = 0;
                for (int i = 1; i <= faces; i++) {
                    if(t - i >= 0) {
                        ans = ans + memo[dice - 1][t - i];
                    }
                }
                memo[dice][t] = ans;
            }
        }

        return memo[dices][target];
    }

    public int numRollsToTargetTab(int n, int k, int target) {

        return solveTab(n,k,target);
    }

    private int solveSO(int dices, int faces, int target) {
        int[] prev = new int[target+1];
        Arrays.fill(prev,0);
        int[] current = new int[target+1];
        Arrays.fill(current,0);

        //analysing base case
        prev[0]=1;

        for(int dice = 1;dice<=dices;dice++){
            for(int t = 1;t<=target;t++){
                int ans = 0;
                for (int i = 1; i <= faces; i++) {
                    if(t - i >= 0) {
                        ans = ans + prev[t - i];
                    }
                }
                current[t] = ans;
            }

            int[] temp = prev;
            prev = current;
            current = temp;
        }

        return prev[target];
    }

    public int numRollsToTarget(int n, int k, int target) {

        return solveSO(n,k,target);
    }
}
