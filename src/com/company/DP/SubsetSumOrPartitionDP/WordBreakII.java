package com.company.DP.SubsetSumOrPartitionDP;

import java.util.*;

public class WordBreakII {
    /**
     * https://leetcode.com/problems/word-break-ii/?envType=company&envId=amazon&favoriteSlug=amazon-thirty-days
     * https://www.youtube.com/watch?v=TmkRMtbXVPw
     * */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, List<String>> memo = new HashMap<>();
        return backtrack(s, wordSet, memo);
    }

    private List<String> backtrack(String s, Set<String> wordSet, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        List<String> result = new ArrayList<>();

        if (s.isEmpty()) {
            result.add(""); // base case
            return result;
        }

        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordSet.contains(prefix)) {
                List<String> suffixWays = backtrack(s.substring(i), wordSet, memo);
                for (String suffix : suffixWays) {
                    if (suffix.isEmpty())
                        result.add(prefix);
                    else
                        result.add(prefix + " " + suffix);
                }
            }
        }

        memo.put(s, result);
        return result;
    }
}
