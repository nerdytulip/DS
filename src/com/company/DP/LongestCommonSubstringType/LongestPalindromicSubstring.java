package com.company.DP.LongestCommonSubstringType;

public class LongestPalindromicSubstring {
    /**
     *     https://leetcode.com/problems/longest-palindromic-substring/description/?envType=study-plan-v2&envId=dynamic-programming
     * */

    private boolean isPalRec(String s, int i, int j, Boolean[][] memo){
        if(i>=j){
            return true;
        }

        if(memo[i][j]!=null){
            return memo[i][j];
        }

        boolean result;
        if(s.charAt(i) == s.charAt(j)){
             result = isPalRec(s,i+1,j-1,memo);
        }else{
            result = false;
        }

        return memo[i][j] = result;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        Boolean[][] memo = new Boolean[n][n];

        int maxLen = Integer.MIN_VALUE;
        int start = 0;

        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(isPalRec(s,i,j,memo) == true){
                    if(j-i+1 > maxLen){
                        maxLen = j-i+1;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start,start+maxLen);
    }
}
