package com.company.DP;

import java.util.Arrays;

public class WildcardMatching {

    private boolean solveRec(String s, String p , int i, int j){
        if(i<0 && j<0){
            return true;
        }

        //string there but pattern finished
        if(i>=0 && j<0){
            return false;
        }

        if(i<0 && j>=0){
            for(int k=0;k<=j;k++){
                if(p.charAt(k) !='*'){
                    return false;
                }
            }
            return true;
        }

        //match
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            return solveRec(s,p,i-1,j-1);
        }else if(p.charAt(j) == '*'){
            return (solveRec(s,p,i-1,j) || solveRec(s,p,i,j-1));
        }else{
            return false;
        }
    }

    public boolean isMatchRec(String s, String p) {
        return solveRec(s,p,s.length()-1,p.length()-1);
    }

    private boolean solveMem(String s, String p , int i, int j, int[][] dp){
        if(i<0 && j<0){
            return true;
        }

        //string there but pattern finished
        if(i>=0 && j<0){
            return false;
        }

        if(i<0 && j>=0){
            for(int k=0;k<=j;k++){
                if(p.charAt(k) !='*'){
                    return false;
                }
            }
            return true;
        }

        if(dp[i][j] !=-1){
            return dp[i][j] == 1;
        }

        //match
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            boolean ans = solveMem(s,p,i-1,j-1,dp);
            dp[i][j] = ans ? 1:0;
            return ans;
        }else if(p.charAt(j) == '*'){
            boolean ans = (solveMem(s,p,i-1,j,dp) || solveMem(s,p,i,j-1,dp));
            dp[i][j] = ans ? 1:0;
            return ans;
        }else{
            return false;
        }
    }

    public boolean isMatchMem(String s, String p) {
        int[][] dp = new int[s.length()][p.length()];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }

        return solveMem(s,p,s.length()-1,p.length()-1,dp);
    }

    private boolean solveTDEasyToConvertToBU(String s, String p, int i, int j , int[][] dp){
        if(i==0 && j==0){
            return true;
        }

        //string there but pattern finished
        if(i>0 && j==0){
            return false;
        }

        if(i==0 && j>0){
            for(int k=1;k<=j;k++){
                if(p.charAt(k-1) !='*'){
                    return false;
                }
            }
            return true;
        }

        if(dp[i][j] !=-1){
            return dp[i][j] == 1;
        }

        //match
        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
            boolean ans = solveMem(s,p,i-1,j-1,dp);
            dp[i][j] = ans ? 1:0;
            return ans;
        }else if(p.charAt(j-1) == '*'){
            boolean ans = (solveMem(s,p,i-1,j,dp) || solveMem(s,p,i,j-1,dp));
            dp[i][j] = ans ? 1:0;
            return ans;
        }else{
            return false;
        }

    }
    public boolean isMatchSimplified(String s, String p) {
        // +1 to handle less than case
        int[][] dp = new int[s.length()+1][p.length()+1];
        for(int[] row:dp){
            Arrays.fill(row,0);
        }

        return solveTDEasyToConvertToBU(s,p,s.length(),p.length(),dp);
    }

    private boolean solveTab(String s, String p) {
        // +1 to handle less than case
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }

        dp[0][0] = 1;

        for (int j = 1; j <= p.length(); j++) {
            boolean flag = true;
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[0][j] = flag ? 1 : 0;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                // match
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] == 1 || dp[i][j - 1] == 1 ? 1 : 0;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return dp[s.length()][p.length()] == 1;
    }

    public boolean isMatchTab(String s, String p) {
        return solveTab(s, p);
    }

    private boolean solveSO(String s, String p) {
        // +1 to handle less than case
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }

        int[] curr = new int[p.length() + 1];
        Arrays.fill(curr, 0);

        int[] prev = new int[p.length() + 1];
        Arrays.fill(prev, 0);

        prev[0] = 1;

        for (int j = 1; j <= p.length(); j++) {
            boolean flag = true;
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            prev[j] = flag ? 1 : 0;
        }

        for (int i = 1; i <= s.length(); i++) {
            curr[0] = 0; // Empty pattern can't match non-empty string
            for (int j = 1; j <= p.length(); j++) {
                // match
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    curr[j] = prev[j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    curr[j] = prev[j] == 1 || curr[j - 1] == 1 ? 1 : 0;
                } else {
                    curr[j] = 0;
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[p.length()] == 1;
    }

    public boolean isMatch(String s, String p) {
        return solveSO(s, p);
    }
}
