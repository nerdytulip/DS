package com.company.DP;

import java.util.Arrays;

public class UniqueBSTs {
    private int solveRec(int n) {
        if(n <= 1){
            return 1;
        }

        int ans = 0;

        //think i as root nodes
        for(int i = 1; i<= n; i++){
            ans+=numTrees(i-1) * numTrees(n -i);
        }

        return ans;
    }

    public int numTreesRec(int n) {
        return solveRec(n);
    }


    //top-down
    private int solveMem(int n,int[] dp) {
        if(n <= 1){
            return 1;
        }

        if(dp[n]!=-1){
            return dp[n];
        }

        int ans = 0;

        //think i as root nodes
        for(int i = 1; i<= n; i++){
            ans+=solveMem(i-1,dp) * solveMem(n -i,dp);
        }

        return dp[n] = ans;
    }

    public int numTreesMem(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return solveMem(n,dp);
    }

    private int solveTab(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,0);

        dp[0] = 1;
        dp[1] = 1;

        //i -> number of nodes
        for(int i=2;i<=n;i++){
            //j -> root node
            for(int j=1;j<=i;j++){
                dp[i] += dp[j-1]*dp[i-j];
            }
        }

        return dp[n];
    }

    public int numTrees(int n) {
        return solveTab(n);
    }

}
