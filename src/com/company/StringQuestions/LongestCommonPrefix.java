package com.company.StringQuestions;

import java.util.Arrays;

public class LongestCommonPrefix {
    /**
     * https://leetcode.com/problems/longest-common-prefix/?envType=study-plan-v2&envId=top-interview-150
     * */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();

        Arrays.sort(strs);

        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length-1].toCharArray();

        for(int i=0;i<first.length;i++){
            if(first[i]!=last[i]){
                break;
            }
            result.append(first[i]);
        }

        return result.toString();
    }
}
