package com.company.SlidingWindow.fixed;

import java.util.HashMap;
import java.util.Map;

public class PermutationsInAString {
    /**
     * https://www.youtube.com/watch?v=iTwwvsyUsi4
     * https://leetcode.com/problems/permutation-in-string/description/
     * */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> freqMap_S1 = new HashMap<>();

        for (char c : s1.toCharArray()) {
            freqMap_S1.put(c, freqMap_S1.getOrDefault(c, 0) + 1);
        }

        int i = 0, j = 0;
        int k = s1.length();
        Map<Character, Integer> freqMap_S2 = new HashMap<>();

        while (j < s2.length()) {
            char key = s2.charAt(j);
            freqMap_S2.put(key, freqMap_S2.getOrDefault(key, 0) + 1);

            // If window size is less than k, just expand
            if (j - i + 1 < k) {
                j++;
            }
            // When window size is exactly k
            else if (j - i + 1 == k) {
                if (freqMap_S1.equals(freqMap_S2)){
                    return true;
                }

                // Slide the window forward: remove i-th character
                char charAtI = s2.charAt(i);
                freqMap_S2.put(charAtI, freqMap_S2.get(charAtI) - 1);
                if (freqMap_S2.get(charAtI) == 0) {
                    freqMap_S2.remove(charAtI);
                }
                i++;
                j++;
            }
        }

        return false;
    }
}
