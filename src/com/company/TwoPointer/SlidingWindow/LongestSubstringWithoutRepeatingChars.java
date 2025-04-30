package com.company.TwoPointer.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingChars {
    // Variable sliding window
    // In this question , k is also not given
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/?envType=company&envId=amazon&favoriteSlug=amazon-three-months&difficulty=EASY%2CMEDIUM
    public int lengthOfLongestSubstringWithoutRepeatingCharacters(String s) {
        int longest_str_length = Integer.MIN_VALUE;
        Map<Character,Integer> count_map = new HashMap<>();
        int i=0,j=0;

        while (j<s.length()){
            if(count_map.containsKey(s.charAt(j))){
                count_map.put(s.charAt(j),count_map.get(s.charAt(j))+1);
            }else{
                count_map.put(s.charAt(j),1);
            }
            //k here is window size
            if(count_map.size() == j-i+1){
                longest_str_length = Math.max(longest_str_length,j-i+1);
            }else if(count_map.size()< j-i+1){
                //repeating char
                while(count_map.size()<j-i+1){
                    count_map.put(s.charAt(i),count_map.get(s.charAt(i))-1);
                    if(count_map.get(s.charAt(i)) == 0){
                        count_map.remove(s.charAt(i));
                    }
                    i++;
                }
            }
            j++;
        }

        return longest_str_length;
    }

    public int lengthOfLongestSubstringWithoutRepeatingCharactersUsingHashSet(String s) {
        Set<Character> charSet = new HashSet<>();

        int maxLength = 0;
        int left = 0;

        for(int right = 0;right<s.length();right++){

            while(charSet.contains(s.charAt(right))){
                charSet.remove(s.charAt(left));
                left++;
            }

            charSet.add(s.charAt(right));
            maxLength = Math.max(maxLength,right - left +1);
        }

        return maxLength;
    }
}
