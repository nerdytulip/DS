package com.company.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        final int THRESHOLD = 3;
        int N = searchWord.length();

        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            result.add(new ArrayList<>());
        }

        for (String str : products) {
            // for each possible prefix length
            for (int i = 0; i < N; i++) {
                // stop if str is too short or mismatches the prefix
                if (i >= str.length() || str.charAt(i) != searchWord.charAt(i)) {
                    break;
                }
                // only add if we haven’t reached 3 suggestions yet
                if (result.get(i).size() < THRESHOLD) {
                    result.get(i).add(str);
                }
                // (no break here—keep going so we can fill later prefixes)
            }
        }

        return result;
    }

}
