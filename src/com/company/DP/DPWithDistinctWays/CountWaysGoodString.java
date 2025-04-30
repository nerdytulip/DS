package com.company.DP.DPWithDistinctWays;

public class CountWaysGoodString {
    private int solveRec(int length, int low, int high, int zero, int one) {
        if(length>high){
            return 0;
        }

        // If current length is within the good range, count it as 1 way
        int count = 0;
        if(length>=low){
            count = 1;
        }

        // Two choices: add 'zero' length 0's or 'one' length 1's
        int addZero = solveRec(length+zero,low,high,zero,one);
        int addOne = solveRec(length+one,low,high,zero,one);

        return (count + addZero + addOne) % 1000000007;
    }

    public int countGoodStringsRec(int low, int high, int zero, int one) {
        return solveRec(0,low,high,zero,one);
    }

    private int solveMem(int length, int low, int high, int zero, int one, Integer[] memo) {
        if(length>high){
            return 0;
        }

        if(memo[length]!=null){
            return memo[length];
        }

        // If current length is within the good range, count it as 1 way
        int count = 0;
        if(length>=low){
            count = 1;
        }

        // Two choices: add 'zero' length 0's or 'one' length 1's
        int addZero = solveMem(length+zero,low,high,zero,one,memo);
        int addOne = solveMem(length+one,low,high,zero,one,memo);

        return memo[length] = (count + addZero + addOne) % 1000000007;
    }

    public int countGoodStringsMem(int low, int high, int zero, int one) {
        Integer[] memo = new Integer[high+1];
        return solveMem(0,low,high,zero,one,memo);
    }

    private int solveTab(int low, int high, int zero, int one){
        int MOD = 1_000_000_007;
        int[] dp = new int[high + 1];

        // Base case: if we are already at length between [low, high], it's a valid string
        for (int len = low; len <= high; len++) {
            dp[len] = 1;
        }

        for (int i = high; i >= 0; i--) {
            if (i + zero <= high) {
                dp[i] = (dp[i] + dp[i + zero]) % MOD;
            }
            if (i + one <= high) {
                dp[i] = (dp[i] + dp[i + one]) % MOD;
            }
        }

        return dp[0];
    }
    public int countGoodStrings(int low, int high, int zero, int one) {
        return solveTab(low,high,zero,one);
    }
}
