package com.company.DP.LongestCommonSubsequenceType;

import java.util.Arrays;

public class MinimumASCIIDeleteSum {
    /**
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/?envType=study-plan-v2&envId=dynamic-programming
     * */

    private int solveRec(String s1, String s2, int i, int j) {
        if(i>=s1.length() && j>=s2.length()){
            return 0;
        }

        if(i >= s1.length()){
            return s2.charAt(j) + solveRec(s1,s2,i,j+1);
        }

        if(j>=s2.length()){
            return s1.charAt(i) + solveRec(s1,s2,i+1,j);
        }

        // when match, no delete
        if(s1.charAt(i) == s2.charAt(j)){
            return solveRec(s1,s2,i+1,j+1);
        }else{
            // two options : delete ith character of s1, and delete jth character of s2
            int delete_s1_i = s1.charAt(i) + solveRec(s1,s2,i+1,j);
            int delete_s2_j = s2.charAt(j) + solveRec(s1,s2,i,j+1);
            return Math.min(delete_s1_i,delete_s2_j);
        }
    }

    public int minimumDeleteSumRec(String s1, String s2) {
        return solveRec(s1,s2,0,0);
    }

    private int solveMem(String s1, String s2, int i, int j,int[][] memo) {
        if(i>=s1.length() && j>=s2.length()){
            return 0;
        }

        if(i >= s1.length()){
            return s2.charAt(j) + solveMem(s1,s2,i,j+1,memo);
        }

        if(j>=s2.length()){
            return s1.charAt(i) + solveMem(s1,s2,i+1,j,memo);
        }

        if(memo[i][j]!=-1){
            return memo[i][j];
        }

        // when match, no delete
        if(s1.charAt(i) == s2.charAt(j)){
            return memo[i][j] = solveMem(s1,s2,i+1,j+1,memo);
        }else{
            // two options : delete ith character of s1, and delete jth character of s2
            int delete_s1_i = s1.charAt(i) + solveMem(s1,s2,i+1,j,memo);
            int delete_s2_j = s2.charAt(j) + solveMem(s1,s2,i,j+1,memo);
            return memo[i][j] = Math.min(delete_s1_i,delete_s2_j);
        }
    }

    public int minimumDeleteSumMem(String s1, String s2) {
        int[][] memo = new int[s1.length()][s2.length()];
        for(int[] row : memo){
            Arrays.fill(row,-1);
        }
        return solveMem(s1,s2,0,0,memo);
    }

    private int solveTab(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] memo = new int[n+1][m+1];

        // Fill last row
        for (int j = m-1; j >= 0; j--) {
            memo[n][j] = s2.charAt(j) + memo[n][j+1];
        }

        // Fill last column
        for (int i = n-1; i >= 0; i--) {
            memo[i][m] = s1.charAt(i) + memo[i+1][m];
        }

        for(int i = n-1;i>=0;i--){
            for(int j = m-1;j>=0;j--){
                // when match, no delete
                if(s1.charAt(i) == s2.charAt(j)){
                    memo[i][j] = memo[i+1][j+1];
                }else{
                    // two options : delete ith character of s1, and delete jth character of s2
                    int delete_s1_i = s1.charAt(i) + memo[i+1][j];
                    int delete_s2_j = s2.charAt(j) + memo[i][j+1];
                    memo[i][j] = Math.min(delete_s1_i,delete_s2_j);
                }
            }
        }

        return memo[0][0];
    }

    public int minimumDeleteSumMemo(String s1, String s2) {
        return solveTab(s1,s2);
    }

    private int solveSO(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[] curr = new int[m+1];
        int[] next = new int[m+1];

        // Fill the last row (i = n)
        for (int j = m-1; j >= 0; j--) {
            next[j] = s2.charAt(j) + next[j+1];
        }

        for(int i = n-1;i>=0;i--){
            curr[m] = s1.charAt(i) + next[m]; // Fill last column for this row

            for(int j = m-1;j>=0;j--){
                // when match, no delete
                if(s1.charAt(i) == s2.charAt(j)){
                    curr[j] = next[j+1];
                }else{
                    // two options : delete ith character of s1, and delete jth character of s2
                    int delete_s1_i = s1.charAt(i) + next[j];
                    int delete_s2_j = s2.charAt(j) + curr[j+1];
                    curr[j] = Math.min(delete_s1_i,delete_s2_j);
                }
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[0];
    }

    public int minimumDeleteSum(String s1, String s2) {
        return solveSO(s1,s2);
    }
}
