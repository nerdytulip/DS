package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;

public class MinimumSquares {

    // https://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
    int solveRec(int n){
        if(n == 0){
            return 0;
        }

        //max can be we just use n 1s
        int ans = n;

        for(int i=1;i*i<=n;i++){
            ans = Math.min(ans, 1+ solveRec(n - (i*i)));
        }

        return ans;
    }

    int minSquares(int n){
        return solveRec(5);
    }

    int solveMem(int n, int[] memo){
        if(n == 0){
            return 0;
        }

        if(memo[n]!=-1){
            return memo[n];
        }

        //max can be we just use n 1s
        int ans = n;
        for(int i=1;i*i<=n;i++){
            ans = Math.min(ans, 1+ solveMem(n - (i*i),memo));
        }

        memo[n] = ans;
        return memo[n];
    }

    int minSquaresMem(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);
        return solveMem(5,memo);
    }

    int minSquaresTabulation(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo,Integer.MAX_VALUE);
        memo[0] = 0;

        for(int i=1;i<=n;i++){
            for(int j = 1; i*j<=n;j++){
                int temp = j*j;
                if(i-temp>=0) {
                    memo[i] = Math.min(memo[i], 1+ memo[i - temp]);
                }
            }
        }

        return memo[n];
    }
}
