package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;

public class MinCutRodsIntoSegments {
    //leetcode
    //https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
    public int minCost(int n, int[] cuts) {
        int[] memo = new int[n+1]; // memo of i , max segs in rod of length i, so to find max segs in rod of length n , we need array of size n
        Arrays.fill(memo,Integer.MIN_VALUE);
        memo[0] = 0;

        for(int i=1;i<=n;i++){
            for(int cut:cuts){
                if(i-cut>=0){
                    memo[i] = Math.min(memo[i],memo[i-cut]+1);
                }
            }
        }

        if(memo[n]<0){
            return 0;
        }else{
            return memo[n];
        }
    }
}
