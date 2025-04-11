package com.company.DP.ZeroOneKnapsack;

public class OnesAndZeroes {

    /**
     * https://leetcode.com/problems/ones-and-zeroes/description/?envType=study-plan-v2&envId=dynamic-programming
     * */
    private int solveRec(int i, String[] strs, int m, int n) {
        if(i == strs.length){
            return 0;
        }

        int countZeroes = 0;
        int countOnes = 0;
        for(char c : strs[i].toCharArray()){
            if(c=='0')
                countZeroes++;
            else if(c == '1')
                countOnes++;
        }

        //exclude
        int exclude = solveRec(i+1,strs,m,n);

        //include
        int include = 0;
        if(countZeroes<=m && countOnes<=n){
            include = 1 + solveRec(i+1,strs,m-countZeroes,n-countOnes);
        }

        int maxSubset = Math.max(exclude,include);

        return maxSubset;
    }

    public int findMaxFormRec(String[] strs, int m, int n) {
        return solveRec(0,strs,m,n);
    }

    private int solveMem(int i, String[] strs, int m, int n,int[][][] memo) {
        if(i == strs.length){
            return 0;
        }

        if(memo[i][m][n]!=-1){
            return memo[i][m][n];
        }

        int countZeroes = 0;
        int countOnes = 0;
        for(char c : strs[i].toCharArray()){
            if(c=='0')
                countZeroes++;
            else if(c == '1')
                countOnes++;
        }

        //exclude
        int exclude = solveMem(i+1,strs,m,n,memo);

        //include
        int include = 0;
        if(countZeroes<=m && countOnes<=n){
            include = 1 + solveMem(i+1,strs,m-countZeroes,n-countOnes,memo);
        }

        int maxSubset = Math.max(exclude,include);

        return memo[i][m][n] = maxSubset;
    }

    public int findMaxFormMem(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m+1][n+1];

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }

        return solveMem(0,strs,m,n,memo);
    }

    private int solveTab(String[] strs, int m, int n) {

        int[][] dp = new int[m+1][n+1];

        for(String s : strs){
            int countZeroes = 0;
            int countOnes = 0;
            for(char c : s.toCharArray()){
                if(c=='0')
                    countZeroes++;
                else if(c == '1')
                    countOnes++;
            }

            for(int i=m;i>=countZeroes;i--){
                for(int j=n;j>=countOnes;j--){
                    int exclude = dp[i][j];
                    int include = 1 + dp[i-countZeroes][j-countOnes];
                    dp[i][j] = Math.max(exclude,include);
                }
            }
        }

        return dp[m][n];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        return solveTab(strs,m,n);
    }

}
