package com.company.HashmapAndSet;

import java.util.*;

public class GroupAnagrams {
    /**
     * https://leetcode.com/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public List<List<String>> groupAnagrams_Using_Custom_Keys(String[] strs) {
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }

        Map<String,List<String>> frequencyStringsMap = new HashMap<>();

        for(String str : strs){
            String frequencyString = getFrequencyString(str);

            if(frequencyStringsMap.containsKey(frequencyString)){
                frequencyStringsMap.get(frequencyString).add(str);
            } else{
                List<String> strList = new ArrayList<>();
                strList.add(str);
                frequencyStringsMap.put(frequencyString,strList);
            }
        }

        return new ArrayList<>(frequencyStringsMap.values());
    }

    private String getFrequencyString(String str) {
        int[] freq = new int[26];

        for(char c : str.toCharArray()){
            freq[c - 'a']++;
        }

        StringBuilder frequencyString = new StringBuilder("");
        char c = 'a';

        for(int i : freq){
            frequencyString.append(c);
            frequencyString.append(i);
        }

        return frequencyString.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }

        Map<String, List<String>> result = new HashMap<>();

        for(String s: strs){
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);

            if(!result.containsKey(key)){
                result.put(key, new ArrayList());
            }
            result.get(key).add(s);
        }
        return new ArrayList(result.values());
    }
}
