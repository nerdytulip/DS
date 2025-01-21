package com.company.DP;

import java.util.Arrays;

import static java.util.Collections.reverse;

public class LongestPalindromicSubsequence {

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

    public int longestPalindromeSubseq(String s) {
        StringBuilder revS = new StringBuilder();
        for(int i = s.length()-1;i>=0;i--){
            revS.append(s.charAt(i));
        }

        return solveSO(s,revS.toString());
    }
}
