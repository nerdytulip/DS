package com.company.DP.SubsetSumOrPartitionDP;

import java.util.HashSet;
import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);

        int maxLen = 0;
        for(String word:wordDict){
            maxLen = Math.max(maxLen,word.length());
        }

        int n = s.length();

        // dp[i] states if the substring s[0...i] can be segmented
        boolean[] dp = new boolean[n+1];

        //Base case: empty string valid
        dp[0] = true;

        for(int i=1;i<=n;i++){

            // check prefixes of length up to maxLen
            for(int j=i-1;j>=Math.max(0,i-maxLen);j--){
                if(dp[j] && wordSet.contains(s.substring(j,i))){
                    dp[i] = true;
                    break; //No need to check further
                }
            }
        }

        return dp[n];
    }
}
