package com.company.DP;

import java.util.Arrays;

public class CountDerangements {

    //https://www.geeksforgeeks.org/count-derangements-permutation-such-that-no-element-appears-in-its-original-position/
    public int countDerangementsRec(int n){
        if(n==1){
            return 0;
        }
        if(n==2){
            return 1;
        }

        int ans = (n-1) * (countDerangementsRec(n-2)+countDerangementsRec(n-1));
        return ans;
    }

    // top - down
    private int solveMem(int n, int[] memo){
        if(n==1){
            return 0;
        }
        if(n==2){
            return 1;
        }

        if(memo[n]!=-1){
            return memo[n];
        }

        memo[n] = (n-1) * (solveMem(n-2,memo)+solveMem(n-1,memo));
        return memo[n];
    }

    //top -down
    public int countDerangementMem(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);
        return solveMem(n,memo);
    }

    //bottom -up
    public int countDerangementTabulation(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo,0);

        memo[1] = 0;
        memo[2] =1;

        for(int i=3;i<=n;i++){
            //n-1
            int first = memo[i-1];
            int second = memo[i-2];
            int result = (i-1) * (first+second);
            memo[i] = result;
        }

        return memo[n];
    }

    public int countDerangementSpaceOptimized(int n){

        int prev2 = 0; //n-2
        int prev1 =1;  //n-1

        for(int i=3;i<=n;i++){
            //n-1
            int first = prev1;
            int second = prev2;
            int result = (i-1) * (first+second);
            int ans = result;
            prev2 =prev1;
            prev1 = ans;
        }

        return prev1;
    }

}