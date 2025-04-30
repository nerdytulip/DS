package com.company.TwoPointer.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKUniqueChars {
    //Variable sliding window
    //https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
    public int longestKSubstr(String s, int k){
        int longest_substring_k_unique_character =0;
        int i=0,j=0;
        Map<Character,Integer> count_map = new HashMap<>();


        while(j<s.length()){
            //calcs
            if(count_map.containsKey(s.charAt(j))){
                count_map.put(s.charAt(j),count_map.get(s.charAt(j))+1);
            }else{
                count_map.put(s.charAt(j),1);
            }

            if(count_map.size()<k){
                //j will be incremented
            }else if(count_map.size() == k){
                longest_substring_k_unique_character = Math.max(longest_substring_k_unique_character,j-i+1);
            }else if(count_map.size()>k){
                while (count_map.size()>k){
                    count_map.put(s.charAt(i),count_map.get(s.charAt(i))-1);
                    if(count_map.get(s.charAt(i)) == 0){
                        count_map.remove(s.charAt(i));
                    }
                    i++;
                }
            }

            j++;
        }

        return longest_substring_k_unique_character;
    }
}
