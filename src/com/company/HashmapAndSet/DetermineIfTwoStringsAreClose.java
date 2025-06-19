package com.company.HashmapAndSet;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DetermineIfTwoStringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        HashMap<Character,Integer> countMapWord1 = new HashMap<>();
        HashMap<Character,Integer> countMapWord2 = new HashMap<>();

        if(word1.length()!=word2.length()){
            return false;
        }

        for(char c:word1.toCharArray()){
            countMapWord1.put(c,countMapWord1.getOrDefault(c,0)+1);
        }

        for(char c:word2.toCharArray()){
            countMapWord2.put(c,countMapWord2.getOrDefault(c,0)+1);
        }

        if(!countMapWord1.keySet().equals(countMapWord2.keySet())){
            return false;
        }

        List<Integer> frequencyListWord1 = countMapWord1.values().stream().sorted().collect(Collectors.toList());
        List<Integer> frequencyListWord2 = countMapWord2.values().stream().sorted().collect(Collectors.toList());

        return frequencyListWord1.equals(frequencyListWord2);
    }
}
