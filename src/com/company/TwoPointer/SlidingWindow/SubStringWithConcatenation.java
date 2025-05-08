package com.company.TwoPointer.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubStringWithConcatenation {
    /***
     * https://leetcode.com/problems/substring-with-concatenation-of-all-words/?envType=study-plan-v2&envId=top-interview-150
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        // Edge case: empty string or words
        if (words.length == 0 || s.length() == 0) return result;

        int wordLen = words[0].length();             // Each word is of same length
        int wordCount = words.length;                // Total number of words
        int windowSize = wordLen * wordCount;        // Total window size to match all words

        // If the string is smaller than total window, early return
        if (s.length() < windowSize) return result;

        // Step 1: Build frequency map for the words
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Step 2: Try every offset in the word length
        // This ensures we check all possible word-aligned starting positions
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i;
            Map<String, Integer> seen = new HashMap<>();

            // Move the right pointer one word at a time
            while (right + wordLen <= s.length()) {
                // Extract the current word
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // Case 1: word not in original list ⇒ reset window
                if (!wordFreq.containsKey(word)) {
                    seen.clear();
                    left = right;
                } else {
                    // Add word to seen map
                    seen.put(word, seen.getOrDefault(word, 0) + 1);

                    // If we see the word too many times, shrink window from left
                    while (seen.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                    }

                    // If current window matches the full concatenation
                    if (right - left == windowSize) {
                        result.add(left); // Valid starting index
                    }
                }
            }
        }

        return result;
    }

}
