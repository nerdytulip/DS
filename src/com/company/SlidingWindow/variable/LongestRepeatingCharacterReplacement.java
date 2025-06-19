package com.company.SlidingWindow.variable;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    /**
     * https://leetcode.com/problems/longest-repeating-character-replacement/description/
     * */
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> countMap = new HashMap<>();
        int i = 0, j = 0;
        int maxCount = 0, maxLen = 0;

        while (j < s.length()) {
            char c = s.charAt(j);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            maxCount = Math.max(maxCount, countMap.get(c));

            int windowSize = j - i + 1;

            // If more than k chars need replacement, shrink window
            if (windowSize - maxCount > k) {
                char leftChar = s.charAt(i);
                countMap.put(leftChar, countMap.get(leftChar) - 1);
                i++;
            }

            maxLen = Math.max(maxLen, j - i + 1);
            j++;
        }

        return maxLen;
    }
}
