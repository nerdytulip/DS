package com.company.SlidingWindow.variable;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    /**
     * https://leetcode.com/problems/minimum-window-substring/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        Map<Character, Integer> countMap_T = new HashMap<>();
        for (char c : t.toCharArray()) {
            countMap_T.put(c, countMap_T.getOrDefault(c, 0) + 1);
        }

        int start = 0;
        int i = 0, j = 0;
        int count = countMap_T.size();

        while (j < s.length()) {
            char ch = s.charAt(j);
            if (countMap_T.containsKey(ch)) {
                // subtracting to determine how much is needed
                countMap_T.put(ch, countMap_T.get(ch) - 1);
                if (countMap_T.get(ch) == 0) {
                    count--;
                }
            }

            //try to contract from left
            while (count == 0) {
                if (j - i + 1 < minLen) {
                    minLen = j - i + 1;
                    start = i;
                }

                char leftChar = s.charAt(i);
                if (countMap_T.containsKey(leftChar)) {
                    countMap_T.put(leftChar, countMap_T.get(leftChar) + 1);
                    if (countMap_T.get(leftChar) == 1) {
                        count++;
                    }
                }
                i++;
            }
            j++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
       String s = "ADOBECODEBANC", t = "ABC";
       MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
       minimumWindowSubstring.minWindow(s,t);
    }
}
