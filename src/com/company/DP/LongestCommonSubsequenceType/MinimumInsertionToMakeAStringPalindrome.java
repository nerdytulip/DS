package com.company.DP.LongestCommonSubsequenceType;

public class MinimumInsertionToMakeAStringPalindrome {

    /**
     * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/?envType=study-plan-v2&envId=dynamic-programming
     *
     * TC - O(n^2)
     * SC - O(n)
     *
     * */
    public int minInsertions(String s) {
        LongestPalindromicSubsequence palindromicSubsequence = new LongestPalindromicSubsequence();
        int longestPalindromicSubSequenceLength = palindromicSubsequence.longestPalindromeSubseq(s);

        return s.length() - longestPalindromicSubSequenceLength;
    }
}
