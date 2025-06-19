package com.company.Greedy;

import java.util.ArrayList;
import java.util.List;

public class LongestUnequalAdjacentGroupsSubsequence {
    /**
     * https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/description/
     * */
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;

        List<String> result = new ArrayList<>();

        for(int i=0;i<n;i++){
            if(i == 0 || groups[i]!=groups[i-1]){
                result.add(words[i]);
            }
        }

        return result;
    }
}
