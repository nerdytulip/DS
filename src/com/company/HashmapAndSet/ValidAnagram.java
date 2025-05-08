package com.company.HashmapAndSet;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }

        Map<Character,Integer> map = new HashMap<>();

        // add chars to map
        // populate stage
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        // empty stage
        for(char c : t.toCharArray()){
            if(!map.containsKey(c)){
                return false;
            }
            map.put(c,map.get(c)-1);
            if(map.get(c) == 0){
                map.remove(c);
            }
        }

        return map.isEmpty();
    }

    //using alphabet array
    public boolean isAnagram_Using_Array(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] freq = new int[26]; // only 26 lowercase English letters

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) return false;
        }

        return true;
    }
}
