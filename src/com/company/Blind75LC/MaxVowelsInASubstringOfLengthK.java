package com.company.Blind75LC;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MaxVowelsInASubstringOfLengthK {
    public int maxVowels(String s, int k) {
        int i=0,j=0;
        int noOfVowels = 0;
        int maxNoOfVowels = Integer.MIN_VALUE;
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        while(j<s.length()){
            if(vowels.contains(s.charAt(j))) noOfVowels++;
            if(j-i+1 <k){
                j++;
            } else if(j-i+1 == k){
                maxNoOfVowels = Math.max(maxNoOfVowels,noOfVowels);
                if(vowels.contains(s.charAt(i))) noOfVowels--;
                i++;
                j++;
            }
        }

        return maxNoOfVowels;
    }
}
