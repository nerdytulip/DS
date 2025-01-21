package com.company.DP;

import java.util.Arrays;

public class EditDistance {

    private int solveRec(String word1,String word2, int i, int j){

        //base case
        // if word1 is less than word2
        if(i == word1.length()){
            return word2.length() - j;
        }

        //if word1 is greater than word2
        if(j == word2.length()){
            return word1.length() - i;
        }

        int ans = 0;
        if(word1.charAt(i) == word2.charAt(j)){
            return solveRec(word1,word2,i+1,j+1);
        }
        else{
            //insert
            int insertAns =  1 + solveRec(word1,word2,i,j+1);

            //delete
            int deleteAns = 1 + solveRec(word1,word2,i+1,j);

            //replace
            int replaceAns = 1 + solveRec(word1,word2,i+1,j+1);

            ans = Math.min(insertAns, Math.min(deleteAns,replaceAns));
        }

        return ans;
    }

    public int minDistanceRec(String word1, String word2) {
        return solveRec(word1,word2,0,0);
    }

    private int solveMem(String word1, String word2, int i, int j, int[][] dp){

        //base case
        // if word1 is less than word2
        if(i == word1.length()){
            return word2.length() - j;
        }

        //if word1 is greater than word2
        if(j == word2.length()){
            return word1.length() - i;
        }

        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        int ans = 0;
        if(word1.charAt(i) == word2.charAt(j)){
            return solveMem(word1,word2,i+1,j+1,dp);
        }
        else{
            //insert
            int insertAns =  1 + solveMem(word1,word2,i,j+1,dp);

            //delete
            int deleteAns = 1 + solveMem(word1,word2,i+1,j,dp);

            //replace
            int replaceAns = 1 + solveMem(word1,word2,i+1,j+1,dp);

            ans = Math.min(insertAns, Math.min(deleteAns,replaceAns));
        }

        return dp[i][j] = ans;
    }

    public int minDistanceMem(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return solveMem(word1,word2,0,0,dp);
    }

    private int solveTab(String word1, String word2){
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int[] row : dp){
            Arrays.fill(row,0);
        }

        for(int j=0;j<word2.length();j++){
            dp[word1.length()][j] = word2.length() - j;
        }

        for(int i=0;i<word1.length();i++){
            dp[i][word2.length()] = word1.length() - i;
        }

        for(int i=word1.length()-1;i>=0;i--){
            for(int j = word2.length()-1;j>=0;j--){
                int ans = 0;
                if(word1.charAt(i) == word2.charAt(j)){
                    ans = dp[i+1][j+1];
                }
                else{
                    //insert
                    int insertAns =  1 + dp[i][j+1];

                    //delete
                    int deleteAns = 1 + dp[i+1][j];

                    //replace
                    int replaceAns = 1 + dp[i+1][j+1];

                    ans = Math.min(insertAns, Math.min(deleteAns,replaceAns));
                }

                dp[i][j] = ans;
            }
        }

        return dp[0][0];
    }

    public int minDistanceTab(String word1, String word2) {
        return solveTab(word1,word2);
    }

    private int solveSO(String word1, String word2){
        int[] curr = new int[word2.length()+1];
        Arrays.fill(curr,0);

        int[] next = new int[word2.length()+1];
        Arrays.fill(next,0);

        for(int j=0;j<word2.length();j++){
            //dp[word1.length()][j] - [word1.length()] is index of n+1 row , having nth index, hence next
            next[j] = word2.length() - j;
        }

        for(int i=word1.length()-1;i>=0;i--){
            for(int j = word2.length()-1;j>=0;j--){
                curr[word2.length()] = word1.length() - i;
                int ans = 0;
                if(word1.charAt(i) == word2.charAt(j)){
                    ans = next[j+1];
                }
                else{
                    //insert
                    int insertAns =  1 + curr[j+1];

                    //delete
                    int deleteAns = 1 + next[j];

                    //replace
                    int replaceAns = 1 + next[j+1];

                    ans = Math.min(insertAns, Math.min(deleteAns,replaceAns));
                }

                curr[j] = ans;
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[0];
    }

    public int minDistance(String word1, String word2) {
        if(word1.isEmpty()){
            return word2.length();
        }

        if(word2.isEmpty()){
            return word1.length();
        }
        return solveTab(word1,word2);
    }
}
