package com.company.HashmapAndSet;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    /**
     * https://leetcode.com/problems/ransom-note/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character,Integer> countMap = new HashMap<>();
        for(char c : magazine.toCharArray()){
            countMap.put(c,countMap.getOrDefault(c,0)+1);
        }

        for(char c : ransomNote.toCharArray()){
            if(!countMap.containsKey(c)){
                return false;
            }

            countMap.put(c,countMap.get(c)-1);

            if(countMap.get(c) == 0){
                countMap.remove(c);
            }
        }

        return true;
    }

    // using char array for alphabets
    public boolean canConstruct_usingArray(String ransomNote, String magazine) {
        int[] charCount = new int[26];

        for(char c : magazine.toCharArray()){
            charCount[c-'a']++;
        }

        for(char c : ransomNote.toCharArray()){
            if(charCount[c-'a'] == 0){
                return false;
            }
           charCount[c-'a']--;
        }

        return true;
    }
}
