package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;

public class MaxCutRodsIntoSegments {
    //https://www.naukri.com/code360/problems/cut-into-segments_1214651?topList=love-babbar-dsa-sheet-problems&leftPanelTab=0&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
    int cutSegments(int n , int x, int y, int z){
        int ans = solve(n,x,y,z);
        return ans;
    }

    private int solve(int n, int x, int y, int z) {
        //base case
        if(n == 0){
            //rod length is 0 , then we cannot break it further
           return 0;
        }
        if(n<0){
            return Integer.MIN_VALUE;
        }

        int a = solve(n-x,x,y,z) + 1;
        int b = solve(n-y,x,y,z) + 1;
        int c = solve(n-z,x,y,z) + 1;

        return Math.max(a,Math.max(b,c));
    }

    //top down : rec + memo
    int cutSegments_top_down(int n , int x, int y, int z){
        int[] memo = new int[n+1]; // dp of i , max segs in rod of length i, so to find max segs in rod of length n , we need array of size n
        Arrays.fill(memo,-1);
        return solveMem(n,x,y,z,memo);
    }

    private int solveMem(int n, int x, int y, int z,int[] memo) {
        //base case
        if(n == 0){
            //rod length is 0 , then we cannot break it further
            return 0;
        }
        if(n<0){
            return Integer.MIN_VALUE;
        }

        if(memo[n]!=-1){
            return memo[n];
        }

        int a = solveMem(n-x,x,y,z,memo) + 1;
        int b = solveMem(n-y,x,y,z,memo) + 1;
        int c = solveMem(n-z,x,y,z,memo) + 1;

        memo[n] = Math.max(a,Math.max(b,c));
        return memo[n];
    }

    int cutSegments_bottom_up(int n , int x, int y, int z){
        int[] memo = new int[n+1]; // memo of i , max segs in rod of length i, so to find max segs in rod of length n , we need array of size n
        Arrays.fill(memo,Integer.MIN_VALUE);
        memo[0] = 0;

        for(int i=1;i<=n;i++){
            if(i-x>=0){
                memo[i] = Math.max(memo[i],memo[i-x]+1);
            }

            if(i-y>=0){
                memo[i] = Math.max(memo[i],memo[i-y]+1);
            }

            if(i-z>=0){
                memo[i] = Math.max(memo[i],memo[i-z]+1);
            }
        }

        if(memo[n]<0){
            return 0;
        }else{
            return memo[n];
        }
    }
}
