package com.company.DP.LongestCommonSubsequence;

import java.util.Arrays;

public class LongestCommonSubsequence {

    private int solveRec(int i, int j, String text1,String text2){
        if(i == text1.length() || j == text2.length()){
            return 0;
        }

        int ans = 0;
        if(text1.charAt(i) == text2.charAt(j)){
            ans = 1+ solveRec(i+1,j+1,text1,text2);
        } else{
            ans = Math.max(solveRec(i+1,j,text1,text2), solveRec(i,j+1,text1,text2));
        }

        return ans;
    }

    public int longestCommonSubsequenceRec(String text1, String text2) {
        return solveRec(0,0,text1,text2);
    }

    private int solveMem(int i, int j, String text1,String text2, int[][] dp){
        if(i == text1.length() || j == text2.length()){
            return 0;
        }

        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        int ans = 0;
        if(text1.charAt(i) == text2.charAt(j)){
            ans = 1+ solveMem(i+1,j+1,text1,text2,dp);
        } else{
            ans = Math.max(solveMem(i+1,j,text1,text2,dp), solveMem(i,j+1,text1,text2,dp));
        }

        return dp[i][j] = ans;
    }

    public int longestCommonSubsequenceMem(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return solveMem(0,0,text1,text2,dp);
    }

    private int solveTab(String text1, String text2){
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int[] row:dp){
            Arrays.fill(row,0);
        }

        for(int i = text1.length() -1 ;i>=0;i--){
            for(int j = text2.length() -1;j>=0;j--){
                int ans = 0;
                if(text1.charAt(i) == text2.charAt(j)){
                    ans = 1+ dp[i+1][j+1];
                } else{
                    ans = Math.max(dp[i+1][j], dp[i][j+1]);
                }

                dp[i][j] = ans;
            }
        }

        return dp[0][0];
    }
    public int longestCommonSubsequenceTab(String text1, String text2) {
        return solveTab(text1,text2);
    }

    private int solveSO(String text1, String text2){
        int[] curr = new int[text2.length()+1];
        Arrays.fill(curr,0);

        int[] next = new int[text2.length()+1];
        Arrays.fill(next,0);

        for(int i = text1.length() -1 ;i>=0;i--){
            for(int j = text2.length() -1;j>=0;j--){
                int ans = 0;
                if(text1.charAt(i) == text2.charAt(j)){
                    ans = 1+ next[j+1];
                } else{
                    ans = Math.max(next[j], curr[j+1]);
                }

                curr[j] = ans;
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[0];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return solveSO(text1,text2);
    }


}
